package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.ServoActuator;

public class LateratorSubsystem {
    private ServoActuator leftPivot;
    private ServoActuator rightPivot;
    private ServoActuator leftLaterator;
    private ServoActuator rightLaterator;

    private CRServo intakeServo;
    private Servo leftPivotServo;
    private Servo rightPivotServo;
    private Servo leftLateratorServo;
    private Servo rightLateratorServo;

    public LateratorSubsystem(HardwareMap Map) {
        //Servo hardwaremap setup
        intakeServo = Map.get(CRServo.class, "intakeServo");
        leftPivotServo = Map.get(Servo.class, "leftPivotServo");
        rightPivotServo = Map.get(Servo.class, "rightPivotServo");
        leftLateratorServo = Map.get(Servo.class, "leftLateratorServo");
        rightLateratorServo = Map.get(Servo.class, "rightLateratorServo");

        //Hardware compilation
        leftPivot = new ServoActuator(leftPivotServo);
        rightPivot = new ServoActuator(rightPivotServo);
        leftLaterator = new ServoActuator(leftLateratorServo);
        rightLaterator = new ServoActuator(rightLateratorServo);
    }

    //set the angle of the pivot
    public void setPivotAngle(double angle) {
        leftPivot.setServos(angle);
        rightPivot.setServos(1-angle);
    }

    //set the angle of the wrist
    public void setLaterator(double value) {
        leftLaterator.setServos(value);
        rightLaterator.setServos(1 -value);
    }

    //set pincher to open
    public void intake() {
        intakeServo.setPower(1);
    }

    public void intakeWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime < currentTime) && (currentTime <= endTime)) {
            intake();
        }
    }
    public void outake() {
        intakeServo.setPower(-1);}

    public void outakeWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime < currentTime) && (currentTime <= endTime)) {
            outake();
        }
    }
    public void stopIntake() {
        intakeServo.setPower(0);
    }

    public void stopIntakeWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime < currentTime) && (currentTime <= endTime)) {
            stopIntake();
        }
    }
    //Presets
    public void extend() {
        setLaterator(0.35);
    }

    public void extendWithScheduler(
            double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime < currentTime) && (currentTime <= endTime)) {
            extend();
        }
    }

    public void level() {
        setPivotAngle(.73);
    }

    public void levelWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime < currentTime) && (currentTime <= endTime)) {
            level();
        }
    }

    public void shortExtend() {
        setLaterator(.55);
    }

    public void shortExtendWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime < currentTime) && (currentTime <= endTime)) {
            shortExtend();
        }
    }

    public void groundPickUp() {
        setPivotAngle(.898);
    }

    public void groundPickupWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime < currentTime) && (currentTime <= endTime)) {
            groundPickUp();
        }
    }

    public void discard() {
        setPivotAngle(.3);
    }

    public void retract() {
        setLaterator(.615);
    }

    public void retractWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime < currentTime) && (currentTime <= endTime)) {
            retract();
        }
    }

    public void dump() {
        //setLaterator(0);
        setPivotAngle(0.15);
        //stopIntake();
    }

}
