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
            m_Superstructure.pincher.setWrisAngle(.8);
            m_Superstructure.zeroPresetAuto();
        }
        waitForStart();
        m_Drive.resetDriveEncoders();


        if (isStopRequested()) return;

        while (opModeIsActive()) {

            //sample 1

            //m_Superstructure.pincher.scoreSpecimenWithScheduler(0, 30, runtime);
            m_Drive.AutoDriveRC(0,5, 0,.45, runtime);
            m_Drive.AutoDriveRC(-13.5, 0, .5, 1.25, runtime);
            m_Drive.SetHeading(45, 1.27, 3, runtime);
            m_Superstructure.lowPresetWithScheduler(1.35,1.75, runtime);
            m_Superstructure.highPresetWithScheduler(1.751, 2.74, runtime);
            m_Superstructure.pincher.wideOpenWithScheduler(2.45,2.75, runtime);
            m_Superstructure.pincher.wristUpWithScheduler(2.76, 3.24, runtime);
            m_Superstructure.pincher.openWithScheduler(3.25,3.501, runtime);
            m_Superstructure.elevatorDownWithScheduler(3.25, 4.5, runtime);
            m_Drive.AutoDriveRC(8, 0, 4, 4.5, runtime);
            m_Drive.SetHeading(79.3,4.51, 6.26, runtime);
            m_Superstructure.laterator.extendWithScheduler(4.76, 5.25, runtime);
            m_Superstructure.laterator.intakeWithScheduler(4.75, 5.26,  runtime);
            m_Superstructure.laterator.groundPickupWithScheduler(5, 5.5, runtime);
            m_Drive.AutoDriveRC(5, 0, 5.25, 6, runtime);
            m_Superstructure.laterator.levelWithScheduler(6.25, 6.75, runtime);
            m_Superstructure.laterator.retractWithScheduler(6.26,6.76, runtime);
            m_Superstructure.laterator.stopIntakeWithScheduler(7.5,7.9, runtime);
            m_Drive.AutoDriveRC(-5,0,6.35,6.8, runtime);
            m_Drive.SetHeading(45,6.81,8.251, runtime);
            m_Drive.AutoDriveRC(-5, 0,7.76, 8.5, runtime);
            m_Superstructure.HandoffPresetWithScheduler(7.77, 8.25, runtime);
            m_Superstructure.pincher.closeWithScheduler(8.26, 8.5, runtime);
            m_Superstructure.elevatorDownWithScheduler(8.51, 9.15, runtime);
            m_Superstructure.highPresetWithScheduler(9.16, 10.5, runtime);
            m_Superstructure.pincher.wideOpenWithScheduler(10.25, 10.5, runtime);
            m_Superstructure.pincher.wristUpWithScheduler(11, 11.5, runtime);
            m_Superstructure.pincher.openWithScheduler(11.25,11.5, runtime);
            m_Superstructure.elevatorDownWithScheduler(11.26,12.75, runtime);
            m_Drive.SetHeading(94, 12, 14.1, runtime);
            m_Superstructure.laterator.extendWithScheduler(13, 13.5, runtime);
            m_Superstructure.laterator.intakeWithScheduler(13.25, 13.51, runtime);
            m_Superstructure.laterator.groundPickupWithScheduler(13.75, 14, runtime);
            m_Drive.AutoDriveRC(8,0,14.25,15, runtime);
            m_Superstructure.laterator.levelWithScheduler(15.25, 15.5, runtime);
            m_Superstructure.laterator.retractWithScheduler(15.26, 15.6, runtime);
            m_Superstructure.laterator.stopIntakeWithScheduler(15.75, 16, runtime);
            m_Drive.AutoDriveRC(-8, 0, 15.26, 16, runtime);
            m_Drive.AutoDriveRC(0, -4, 16.1, 16.75, runtime);
            m_Drive.SetHeading(45, 16.76, 18.5, runtime);
            m_Superstructure.HandoffPresetWithScheduler(17, 17.75, runtime);
            m_Superstructure.pincher.closeWithScheduler(17.76, 18, runtime);
            m_Superstructure.lowPresetWithScheduler(18.1, 18.8, runtime);
            m_Superstructure.highPresetWithScheduler(18.81, 20, runtime);
            m_Superstructure.pincher.wideOpenWithScheduler(20.1, 20.5, runtime);
            m_Superstructure.pincher.wristUpWithScheduler(21.25, 21.75, runtime);
            m_Superstructure.pincher.openWithScheduler(21.5, 22, runtime);
            m_Superstructure.resetElevatorWithScheduler(22.5, 25, runtime);







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
