package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.hardware.DoubleMotorArm;
import org.firstinspires.ftc.teamcode.hardware.DoubleMotorLinearActuator;
import org.firstinspires.ftc.teamcode.hardware.DoubleServoPincher;
import org.firstinspires.ftc.teamcode.hardware.LinearActuator;
import org.firstinspires.ftc.teamcode.hardware.ServoActuator;
import org.firstinspires.ftc.teamcode.hardware.SingleMotorArm;

public class SuperstructureSubsystem {

    public DoubleMotorLinearActuator Elevator;

    public PincherSubsystem pincher;
    public LateratorSubsystem laterator;

    private Motor elevatorMotor1;
    private Motor elevatorMotor2;

    private Telemetry telemetry;


    ElapsedTime runtime;

    //Creates new superstructure (arm, elevator, wrist)
    public SuperstructureSubsystem(HardwareMap Map, Telemetry telemetry){

        //Create motor objects
        this.telemetry = telemetry;

        elevatorMotor1 = new Motor(Map, "elevatorMotor1");
        elevatorMotor2 = new Motor(Map, "elevatorMotor2");

        pincher = new PincherSubsystem(Map);

        laterator = new LateratorSubsystem(Map);

        //Link motors to superstructure parts
        Elevator = new DoubleMotorLinearActuator(
                elevatorMotor1,
                elevatorMotor2,
                Constants.SuperstructureConstants.elevatorCPI,
                true,
                false,
                Constants.SuperstructureConstants.elevatorPID);
    }

    public void enableDebug() {

        Elevator.setDebug();
    }
    // calls the power brake for the elevator motors
    public void elePowerBrake(){
        elevatorMotor1.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        elevatorMotor2.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }


    //Sample preset - Brings all mechanisms to 0
    public void zeroPreset() {

        Elevator.setInches(-400);
        laterator.retract();
        pincher.wristUp();
        pincher.open();
    }

    public void zeroPresetAuto() {
        laterator.retract();
        pincher.close();
        laterator.level();
    }

    public void elevatorDownWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime < currentTime) && (currentTime <= endTime)) {
            Elevator.setInches(-400);
            periodic();
        }
    }

    public void groundPickupPreset() {
        laterator.extend();
    }
    public void wallPickup() {
        Elevator.setInches(0);
        pincher.wallPickup();
    }

    public void wallPickupWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime < currentTime) && (currentTime <= endTime)) {
            wallPickup();
            periodic();
        }
    }
    public void tuckLaterator() {

        Elevator.setInches(6);
        laterator.retract();
        pincher.open();
        pincher.untuck();
    }

    public void prepSpecWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime < currentTime) && (currentTime <= endTime)) {
            Elevator.setInches(-400);
            periodic();
        }
    }
    public void HandoffPreset() {

        Elevator.setInches(-200);
        laterator.retract();
    }

    public void HandoffPresetWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime < currentTime) && (currentTime <= endTime)) {
            HandoffPreset();
            periodic();
        }
    }

    public void resetElevatorWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime < currentTime) && (currentTime <= endTime)) {
            Elevator.setInches(0);
            periodic();
        }
    }
            //specimen preset - Brings all mechanisms to high rung / low basket
    public void lowPreset() {

        Elevator.setInches(-950);
        //pincher.scoreSpecimen();
    }

    // resets the elevator
    public void resetElevator() {
        elevatorMotor2.stopAndResetEncoder();
        elevatorMotor1.stopAndResetEncoder();
    }

    public void lowPresetWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime < currentTime) && (currentTime <= endTime)) {
            lowPreset();
            periodic();
        }
    }
    public void scoreSpecimen() {

        Elevator.setInches(-400);

    }
    //Sample preset - Brings all mechanisms to high bucket
    public void highPreset() {

        Elevator.setInches(-1680);
        laterator.retract();
        pincher.scoreSample();
    }

    public void highPresetWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime < currentTime) && (currentTime <= endTime)) {
            Elevator.setInches(-1680);
            periodic();
        }
    }
    /**
     * Sets the Elevator/laterator into a manual input mode where the input can be toggled by button
     * @param input1 raw input to the elevator - should be a joystick
     */
    public void ManualInput(double input1) {

        Elevator.setOutput(input1 * 1);
        telemetry.addData("Elevator tick", Elevator.motor1.getCurrentPosition());
    }

    public void periodic() {

        Elevator.Periodic();
        telemetry.addData("Elevator Inches", Elevator.getInches());
    }

    public void setAutoPosition(double ElevatorInches, double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        Elevator.setInches(ElevatorInches);

        if((initialTime < currentTime) && (currentTime<= endTime)) {


            //Periodic
            //actually drives the Superstructure.
            Elevator.Periodic();
            telemetry.addData("SUPERSTRUCTURE STATUS", "RUNNING");
            telemetry.addData("Elevator ticks:", Elevator.getInches());
            telemetry.update();
        }

    }
}
