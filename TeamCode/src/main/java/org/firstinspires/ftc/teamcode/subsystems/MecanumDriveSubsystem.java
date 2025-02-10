package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveKinematics;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveOdometry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.checkerframework.checker.units.qual.Current;
import org.ejml.equation.IntegerSequence;
import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.GoBildaPinpointDriver;

public class MecanumDriveSubsystem {
    public MecanumDrive Drive;

    public Motor leftFront;
    public Motor rightFront;
    public Motor leftBack;
    public Motor rightBack;
    private Telemetry telemetry;

    private double IMUOffset;

    public RevIMU imu;

    public GoBildaPinpointDriver odo; // Declare OpMode member for the Odometry Computer

    //Creates new Mecanum Drivetrain
    public MecanumDriveSubsystem(HardwareMap Map, Telemetry telemetry) {


        this.telemetry = telemetry;

        leftFront = new Motor(Map, "leftFront");
        rightFront = new Motor(Map, "rightFront");
        leftBack = new Motor(Map, "leftBack");
        rightBack = new Motor(Map, "rightBack");

        Drive = new MecanumDrive(leftFront, rightFront, leftBack, rightBack);


        imu = new RevIMU(Map, "imu");
        imu.init();

        odo = Map.get(GoBildaPinpointDriver.class,"odo");
        odo.setOffsets(-10, -150);
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.REVERSED, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odo.resetPosAndIMU();
    }

    public void Drive(double x, double y, double t, boolean Dampen) {
        double m;
        if (Dampen){
            m = Constants.DriveConstants.DampenMult;
        } else {
            m = Constants.DriveConstants.DriveSpeedMult;
        }
        Drive.driveFieldCentric(-y*m, x*m, -t*m,
                getHeading() + Constants.DriveConstants.IMUOffset, Constants.DriveConstants.SquareInputs);
        drivePeriodic();
    }

    public void DriveWithHeading(double x, double y, double heading, boolean Dampen) {
        double m;
        double adjustedSetpoint;
        double adjustedHeading;



        //Create PID constants
        PIDCoefficients HC = Constants.AutoConstants.HeadingPID;

        PIDController HeadingController = new PIDController(HC.p, HC.i, HC.d);


        if (Dampen){
            m = Constants.DriveConstants.DampenMult;
        } else {
            m = Constants.DriveConstants.DriveSpeedMult;
        }
        Drive.driveFieldCentric(-y*m, x*m, HeadingController.calculate((getHeading()), calculateContinousSetpoint(getHeading(), heading)) * 1.1,
                getHeading() + Constants.DriveConstants.IMUOffset, Constants.DriveConstants.SquareInputs);
        drivePeriodic();
    }

    public void DriveRobotRelative(double x, double y, double t, boolean Dampen) {

        //Create PID constants
        PIDCoefficients HC = Constants.AutoConstants.HeadingPID;

        PIDController HeadingController = new PIDController(HC.p, HC.i, HC.d);
        double m;
        if (Dampen){
            m = Constants.DriveConstants.DampenMult;
        } else {
            m = Constants.DriveConstants.DriveSpeedMult;
        }
        Drive.driveRobotCentric(y*m, -x*m, t*m, Constants.DriveConstants.SquareInputs);
        drivePeriodic();
    }

    public double getHeading() {
        return imu.getHeading() - IMUOffset;
    }

    public double calculateContinousSetpoint(double CurrentAngle, double TargetAngle) {
        TargetAngle= Math.IEEEremainder(TargetAngle, 360);
        double remainder = CurrentAngle % (360);
        double adjustedAngleSetpoint = TargetAngle + (CurrentAngle - remainder);

        if (adjustedAngleSetpoint - CurrentAngle > 180) {
            adjustedAngleSetpoint -= 360;
        } else if (adjustedAngleSetpoint - CurrentAngle < -180) {
            adjustedAngleSetpoint += 360;
        }
        return adjustedAngleSetpoint;
    }


    public void resetHeading() {
        IMUOffset = imu.getAbsoluteHeading();
    }

    public int getForwardTicks(){
        //assumes Forward deadwheel is plugged into LeftFront
        return leftFront.getCurrentPosition();
    }

    public int getStrafeTicks(){
        //assumes Forward deadwheel is plugged into RightBack
        return -rightBack.getCurrentPosition();
    }

    public void resetDriveEncoders() {
        leftFront.stopAndResetEncoder();
        rightBack.stopAndResetEncoder();
    }

    public double xInches() {
       return odo.getPosX() / 25.4;
    }
    public double yInches() {
        return odo.getPosX() / 25.4 ;
    }

    public void zeroPowerBrake(){
        leftFront.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    public void drivePeriodic() {
        telemetry.addData("Heading", getHeading());
        //Called once per scheduler run
        //PUT PERIODIC HERE
        odo.update();

        telemetry.addData("x", xInches());
        telemetry.addData("y", yInches());
        telemetry.addData("T", odo.getPosition().getHeading());
    }

    //Drivebot scheduler: a custom movement utility.
    //This is an autonomous tool, but it can also be used for automatic movement in teleop.
    //TODO: PID for Strafe/translation/heading needs to be tuned.

    //finds the amount of ticks to move for a given distance in inches
    public static int driveDistance(double distance) {
        double drive = (Constants.AutoConstants.COUNTS_PER_INCH);
        int outputTicks = (int) Math.floor(drive * distance);
        return outputTicks;
    }

    //TODO: Figure out FF directions
    public static double calculateFFDirection(double input) {
        if (input < 0) {
            return Constants.AutoConstants.leftFF;
        } else {
            if (input > 0) {
                return Constants.AutoConstants.rightFF;
            } else {
                return 0;
            }
        }
    }

    /**
     * Autonomously drive robot centric.
     * @param x forward/backward in inches (forward is positive)
     * @param y Right/left in inches (Left is positive)
     * @param initialTime  starting time for the command
     * @param endTime  finishing time for the command
     * @param runtime  passes the elapsedTime to the class
     */
    public void AutoDriveRC(double x, double y, double initialTime, double endTime, ElapsedTime runtime) {
        int ForwardTarget;
        int StrafeTarget;
        double gain = Constants.AutoConstants.AutoGain;
        double initialHeading = getHeading();

        double currentTime = runtime.seconds();

        rightBack.setInverted(true);
        leftFront.setInverted(true);
        rightFront.setInverted(false);
        leftBack.setInverted(false);

        //Create PID constants
        PIDCoefficients TC = Constants.AutoConstants.TranslationPID;
        PIDCoefficients SC = Constants.AutoConstants.StrafePID;
        PIDCoefficients HC = Constants.AutoConstants.HeadingPID;

        PIDController TranslationController = new PIDController(TC.p, TC.i, TC.d);
        PIDController StrafeController = new PIDController(SC.p, SC.i, SC.d);
        PIDController HeadingController = new PIDController(HC.p, HC.i, HC.d);

        TranslationController.setTolerance(Constants.AutoConstants.PIDTolerance);
        StrafeController.setTolerance(Constants.AutoConstants.PIDTolerance);

        //set target positions
        ForwardTarget = driveDistance(x);
        StrafeTarget  = driveDistance(y);

        StrafeController.setSetPoint(StrafeTarget);
        TranslationController.setSetPoint(ForwardTarget);

        if((initialTime < currentTime) && (currentTime<= endTime)) {
            //Drivebot Periodic
            //actually drives the robot.
            DriveRobotRelative((StrafeController.calculate(getStrafeTicks(), StrafeTarget)  * gain), (TranslationController.calculate(getForwardTicks(), ForwardTarget) * gain), HeadingController.calculate(getHeading(), calculateContinousSetpoint(getHeading(), initialHeading)) + calculateFFDirection(HeadingController.calculate(getHeading(), calculateContinousSetpoint(getHeading(), initialHeading))), false);
            telemetry.addData("AUTO DRIVE STATUS", "RUNNING");
            telemetry.addData("X Travelled;", xInches());
            telemetry.addData("Y Travelled;", yInches());
            telemetry.addData("Heading;", getHeading());
            telemetry.update();
        }
        if ((endTime < currentTime) && (currentTime<= endTime + 0.1)) {
            //Stop all motion
            DriveRobotRelative(0, 0, 0, false);
            rightBack.setInverted(false);
            leftFront.setInverted(false);
            rightFront.setInverted(false);
            leftBack.setInverted(false);
        }
    }

    /**
     * Autonomously Drive to a specific heading.
     * @param HeadingTarget Heading setpoint in degrees. Absolute in relation to robot start position
     * @param initialTime  starting time for the command
     * @param endTime  finishing time for the command
     * @param runtime  passes the elapsedTime to the class
     */
    public void SetHeading(double HeadingTarget, double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();

        rightBack.setInverted(true);
        leftFront.setInverted(false);
        rightFront.setInverted(false);
        leftBack.setInverted(true);

        //Create PID constants
        PIDCoefficients HC = Constants.AutoConstants.HeadingPID;

        PIDController HeadingController = new PIDController(HC.p, HC.i, HC.d);
        HeadingController.setTolerance(0.1);

        HeadingController.setSetPoint(calculateContinousSetpoint(getHeading(), HeadingTarget));

        if((initialTime < currentTime) && (currentTime<= endTime)) {
            //Drivebot Periodic
            //actually drives the robot.
            DriveRobotRelative(0, HeadingController.calculate(getHeading(), calculateContinousSetpoint(getHeading(), HeadingTarget)) + calculateFFDirection(HeadingController.calculate(getHeading(), calculateContinousSetpoint(getHeading(), HeadingTarget))), 0, false);
            telemetry.addData("AUTO DRIVE STATUS", "HEADING");
            telemetry.addData("Heading;", getHeading());
            telemetry.update();
        }
        if ((endTime < currentTime) && (currentTime<= endTime + 0.1)) {
            //Stop all motion
            DriveRobotRelative(0, 0, 0, false);
            resetDriveEncoders();

            rightBack.setInverted(false);
            leftFront.setInverted(false);
            rightFront.setInverted(false);
            leftBack.setInverted(false);
        }
    }
}
