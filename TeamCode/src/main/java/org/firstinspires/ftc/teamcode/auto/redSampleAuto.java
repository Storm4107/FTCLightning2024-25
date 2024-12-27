package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.vision.ColorHuskylens;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SuperstructureSubsystem;

@Config
@Autonomous(name = "2025 - redSampleAuto", group = "Autonomous")
public class redSampleAuto extends LinearOpMode {
    //Instantiate mechanisms
    private SuperstructureSubsystem m_Superstructure;
    private MecanumDriveSubsystem m_Drive;

    @Override
    public void runOpMode() {

        //Run when initializing
        m_Superstructure = new SuperstructureSubsystem(hardwareMap, telemetry);
        m_Drive = new MecanumDriveSubsystem(hardwareMap, telemetry);

        while (!isStopRequested() && !opModeIsActive()) {
            telemetry.update();
            telemetry.addData("Auto", "Selected");
        }
        waitForStart();


        if (isStopRequested()) return;

        /*Note that all commands are in reference to the human perspective.
        This being said, the back of the robot is notated by a negative Ex: m_Drive.AutoDriveRC( -14, 0, 0); == drive back 14in
        The robot understands left to be the default strafe direction Ex: m_Drive.AutoDriveRC( 0, 10, 0); == strafe left 10in
        To strafe right the robot uses the opposite of left so right == -left  Ex: m_Drive.AutoDriveRC( 0, -10, 0); == strafe right 10in
        m_Drive has a built in safety feature in terms of a time out which allows the robot to do the task until ends or the timer runs out, whichever comes first Ex: m_Drive.AutoDriveRC( 0, 0, 5)     m_Drive.SetHeading( 0, 5) == these both give the robot 5 seconds to complete the task given
        The robot turning clockwise reference to its front is notated by a negative number Ex: m_Drive.SetHeading( -90, 0); == 90 degrees counterclockwise

        For the sake of time during a stressful point in a comp try to seperate the code using spaces
        Ex:
        // specimen 1


        // sample 1



        // sample 2
        */


        // specimen 1


        //init
        m_Drive.autoInvert();
        m_Drive.zeroPowerBrake();
        m_Superstructure.pincher.close();
        m_Superstructure.laterator.level();
        m_Superstructure.laterator.retract();
        sleep(1000);
        //m_Superstructure.pincher.autoSamFront();
        //sleep(1000);
        m_Superstructure.setAutoPosition(-1670, 1);
        sleep(500);
        m_Superstructure.pincher.autoSamFront();
        sleep(1000);
        m_Drive.AutoDriveRC(-10,0,1);
        m_Superstructure.pincher.open();
        sleep(1000);
        m_Superstructure.pincher.autoSamBack();
        sleep(1000);
        m_Superstructure.setAutoPosition(-500, 1);
        m_Drive.AutoDriveRC(0, 20, 1);
        sleep(500);
        m_Drive.AutoDriveRC(-5, 0, 1);
        sleep(500);
        m_Drive.AutoDriveRC(0, 18, 1);
        sleep(500);
        //m_Superstructure.setAutoPosition(-500, 1);
        m_Superstructure.laterator.setLaterator(.6);
        m_Superstructure.laterator.groundPickUp();
        m_Superstructure.laterator.intake();
        sleep(2000);
        m_Superstructure.laterator.level();
        sleep(500);
        m_Superstructure.laterator.retract();
        //m_Superstructure.laterator.stopIntake();
        sleep(1000);
        m_Drive.AutoDriveRC(0, -38, 2);
        sleep(2000);
        m_Superstructure.pincher.autoSamBack();
        m_Superstructure.setAutoPosition(-0, 1);
        sleep(500);
        m_Superstructure.pincher.close();
        sleep(500);
        m_Superstructure.setAutoPosition(-1600, 1);
        m_Superstructure.pincher.autoSamFront();
        sleep(1000);
        m_Drive.AutoDriveRC(-3.5, 0, 1);
        sleep(1000);
        m_Superstructure.pincher.open();
        sleep(1000);
        m_Superstructure.pincher.autoSamBack();
        sleep(1000);
        m_Drive.AutoDriveRC(5, 0, 1);
        m_Superstructure.setAutoPosition(0, 1);
        sleep(1000000);

        //sleep(100000);
        //m_Drive.AutoDriveRC(0, -26, 1);
       // m_Drive.AutoDriveRC(-5, 0, 3);

        /*
        //score specimen
        //reset

        // sample 1

        // moves the robot forward slightly to get ready to turn
        m_Drive.AutoDriveRC(6,0,5);
        // Turn to get ready to pick up the next sample
        m_Drive.SetHeading(-90,3);
        // strafe 5 inches to the left
        m_Drive.AutoDriveRC(0,5,3 );
        // moves forward 25 inches
        m_Drive.AutoDriveRC(25, 0, 5);
        // strafes right 11 inches
        m_Drive.AutoDriveRC( 0, -11, 3);
        //extend
        //pick up
        //retract
        //handoff

         */




    }
}
