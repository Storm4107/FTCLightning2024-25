package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.vision.ColorHuskylens;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SuperstructureSubsystem;

@Config
@Autonomous(name = "2025 - blueSpecimenAuto", group = "Autonomous")
public class blueSpecimenAuto extends LinearOpMode {
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


        // init

        m_Superstructure.laterator.retract();
        m_Superstructure.laterator.level();
        m_Superstructure.pincher.close();
        m_Superstructure.pincher.scoreSpecimen();

        // scores preload spec

        m_Drive.AutoDriveRC(33.5, 0,4);
        m_Superstructure.setAutoPosition(-300,3);
        sleep(300);
        m_Superstructure.pincher.open();
        m_Superstructure.setAutoPosition(0,3);

        // It will now go to push the 3 samples on the ground and give them to the human player

        // It drops the first one to the human player

        m_Drive.AutoDriveRC(-4,0,2);
        m_Drive.AutoDriveRC(0,30, 5);
        m_Drive.AutoDriveRC(19,0,3);
        m_Drive.AutoDriveRC(0,-10.5,3);
        m_Drive.AutoDriveRC(-37,0,5);

        // It now does the second one

        m_Drive.AutoDriveRC(37,0,5);
        m_Drive.AutoDriveRC(0,12,4);
        m_Drive.AutoDriveRC(-37,0,5);

        // It now does the third/ last one

        m_Drive.AutoDriveRC(37,0,5);
        m_Drive.AutoDriveRC(0,12,4);
        m_Drive.AutoDriveRC(-37,0,5);

        // It will now pick up and score the next specimen

        m_Drive.AutoDriveRC(6,0,3);
        sleep(2000);
        m_Superstructure.pincher.wallPickup();
        m_Superstructure.pincher.wideOpen();
        m_Drive.AutoDriveRC(-12,0,2);
        sleep(1000);
        m_Superstructure.pincher.close();
        sleep(300);
        m_Superstructure.pincher.scoreSpecimen();
        m_Drive.AutoDriveRC(0,-60,6);
        m_Drive.AutoDriveRC(27.5,0,4);
        m_Superstructure.setAutoPosition(-300,3);
        sleep(300);
        m_Superstructure.pincher.wideOpen();
        m_Superstructure.setAutoPosition(0,3);

        // It will now get and the second specimen

        m_Drive.AutoDriveRC(0,60,6);
        m_Superstructure.scoreSpecimen();
        m_Drive.AutoDriveRC(-6,0,2);
        sleep(1000);
        m_Superstructure.pincher.close();
        sleep(300);
        m_Superstructure.pincher.scoreSpecimen();
        m_Drive.AutoDriveRC(0,-60,6);
        m_Drive.AutoDriveRC(27.5,0,4);
        m_Superstructure.setAutoPosition(-300,3);
        sleep(300);
        m_Superstructure.pincher.wideOpen();
        m_Superstructure.setAutoPosition(0,3);

        // It will now get the third Specimen

        m_Drive.AutoDriveRC(0,60,6);
        m_Superstructure.scoreSpecimen();
        m_Drive.AutoDriveRC(-6,0,2);
        sleep(1000);
        m_Superstructure.pincher.close();
        sleep(300);
        m_Superstructure.pincher.scoreSpecimen();
        m_Drive.AutoDriveRC(0,-60,6);
        m_Drive.AutoDriveRC(27.5,0,4);
        m_Superstructure.setAutoPosition(-300,3);
        sleep(300);
        m_Superstructure.pincher.wideOpen();
        m_Superstructure.setAutoPosition(0,3);

        // It will now get the last/fourth one

        m_Drive.AutoDriveRC(0,60,6);
        m_Superstructure.scoreSpecimen();
        m_Drive.AutoDriveRC(-6,0,2);
        sleep(1000);
        m_Superstructure.pincher.close();
        sleep(300);
        m_Superstructure.pincher.scoreSpecimen();
        m_Drive.AutoDriveRC(0,-60,6);
        m_Drive.AutoDriveRC(27.5,0,4);
        m_Superstructure.setAutoPosition(-300,3);
        sleep(300);
        m_Superstructure.pincher.wideOpen();
        m_Superstructure.setAutoPosition(0,3);










    }
}
