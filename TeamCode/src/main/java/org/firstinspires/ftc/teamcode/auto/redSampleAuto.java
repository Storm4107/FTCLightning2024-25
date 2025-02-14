package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.vision.ColorHuskylens;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SuperstructureSubsystem;

@Config
@Autonomous(name = "2025 - redSampleAuto", group = "Autonomous")
public class redSampleAuto extends LinearOpMode {
    //Instantiate mechanisms
    private SuperstructureSubsystem m_Superstructure;
    private MecanumDriveSubsystem m_Drive;

    public ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        //Run when initializing
        m_Superstructure = new SuperstructureSubsystem(hardwareMap, telemetry);
        m_Drive = new MecanumDriveSubsystem(hardwareMap, telemetry);

        while (!isStopRequested() && !opModeIsActive()) {
            telemetry.update();
            runtime.reset();
            telemetry.addData("Auto", "Selected");

            // TODO: 1/11/2025
            m_Superstructure.pincher.close();
            m_Superstructure.pincher.setWrisAngle(.7);
            m_Superstructure.zeroPresetAuto();
        }
        waitForStart();
        m_Drive.resetDriveEncoders();


        if (isStopRequested()) return;

        while (opModeIsActive()) {

            //sample 1

            //m_Superstructure.pincher.scoreSpecimenWithScheduler(0, 30, runtime);
            m_Drive.AutoDriveRC(-16,4.5, 0,1, runtime);
            //m_Drive.AutoDriveRC(-13.5, 0, .5, 1.25, runtime);
            m_Drive.SetHeading(45, 1.01, 2.5, runtime);
            m_Superstructure.highPresetWithScheduler(1.2, 2.25, runtime);
            m_Superstructure.pincher.scoreSampleWithScheduler(1.75, 2.25, runtime);
            m_Superstructure.pincher.wideOpenWithScheduler(2.15,2.25, runtime);
            m_Superstructure.pincher.wristUpWithScheduler(3, 3.25, runtime);
            m_Superstructure.pincher.openWithScheduler(3.1,3.51, runtime);
            m_Superstructure.elevatorDownWithScheduler(3, 4, runtime);
            m_Drive.SetHeading(71,3.5, 4.5, runtime);
            m_Drive.AutoDriveRC(11, 0, 4.51, 5.25, runtime);
            m_Superstructure.laterator.extendWithScheduler(4, 4.25, runtime);
            m_Superstructure.laterator.intakeWithScheduler(4.02,4.26,  runtime);
            m_Superstructure.laterator.groundPickupWithScheduler(4.5, 6, runtime);
            m_Drive.AutoDriveRC(-12, -2, 6.01, 7, runtime);
            m_Superstructure.laterator.levelWithScheduler(6.01, 6.75, runtime);
            m_Superstructure.laterator.retractWithScheduler(6.02,6.76, runtime);
            m_Superstructure.laterator.stopIntakeWithScheduler(7.5,7.9, runtime);
            m_Drive.SetHeading(45,7,7.85, runtime);
            m_Superstructure.HandoffPresetWithScheduler(6.25, 7.5, runtime);
            m_Superstructure.pincher.closeWithScheduler(7, 7.25, runtime);
            m_Superstructure.highPresetWithScheduler(7.5, 8.5, runtime);
            m_Superstructure.pincher.scoreSampleWithScheduler(7.75, 8, runtime);
            m_Superstructure.pincher.wideOpenWithScheduler(8.25, 8.5, runtime);
            m_Superstructure.pincher.wristUpWithScheduler(9, 9.25, runtime);
            m_Superstructure.pincher.openWithScheduler(9.1,9.2, runtime);
            m_Superstructure.elevatorDownWithScheduler(9,10, runtime);
            m_Drive.SetHeading(89, 9.5, 10.5, runtime);
            m_Superstructure.laterator.extendWithScheduler(10, 10.25, runtime);
            m_Superstructure.laterator.intakeWithScheduler(9.75, 10.24, runtime);
            m_Superstructure.laterator.groundPickupWithScheduler(10.5, 10.75, runtime);
            m_Drive.AutoDriveRC(6,0,10.51,11.25, runtime);
            m_Superstructure.laterator.levelWithScheduler(12.25, 12.5, runtime);
            m_Superstructure.laterator.retractWithScheduler(12.26, 12.51, runtime);
            m_Superstructure.laterator.stopIntakeWithScheduler(14, 14.2, runtime);
            m_Drive.AutoDriveRC(-6, 0, 12.5, 13.25, runtime);
            m_Drive.SetHeading(40, 13.26, 14.26, runtime);
            m_Superstructure.HandoffPresetWithScheduler(13.75, 14.5, runtime);
            m_Superstructure.pincher.closeWithScheduler(14.51, 14.75, runtime);
            m_Superstructure.highPresetWithScheduler(15, 16.25, runtime);
            m_Superstructure.pincher.scoreSampleWithScheduler(15.5, 16, runtime);
            m_Superstructure.pincher.wideOpenWithScheduler(16.25, 16.5, runtime);
            m_Superstructure.pincher.wristUpWithScheduler(17.25, 17.5, runtime);
            m_Superstructure.pincher.openWithScheduler(17.26, 17.51, runtime);
            m_Superstructure.elevatorDownWithScheduler(17.25, 18.25, runtime);






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

    }
        //init





    }
}
