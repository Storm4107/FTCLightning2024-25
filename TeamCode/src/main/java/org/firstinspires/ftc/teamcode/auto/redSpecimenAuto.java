package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SuperstructureSubsystem;
import org.firstinspires.ftc.teamcode.hardware.sensors.revDistanceSensor;

@Config
@Autonomous(name = "2025 - redSpecimenAuto", group = "Autonomous")
public class redSpecimenAuto extends LinearOpMode {
    //Instantiate mechanisms
    private SuperstructureSubsystem m_Superstructure;
    private MecanumDriveSubsystem m_Drive;

    private revDistanceSensor m_Distance;

    public ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        //Run when initializing
        m_Superstructure = new SuperstructureSubsystem(hardwareMap, telemetry);
        m_Drive = new MecanumDriveSubsystem(hardwareMap, telemetry);
        m_Distance = new revDistanceSensor(hardwareMap, telemetry);

        while (!isStopRequested() && !opModeIsActive()) {
            telemetry.update();
            runtime.reset();
            telemetry.addData("Auto", "Selected");

          // TODO: 1/11/2025
            m_Superstructure.pincher.scoreSpecimen();
            m_Superstructure.zeroPresetAuto();

        }
        waitForStart();
        m_Drive.resetDriveEncoders();


        if (isStopRequested()) return;

        while (opModeIsActive()) {

           m_Drive.AutoDriveRC(30, 5,0, 2, runtime);
           m_Drive.SetHeading(0, 2.1, 2.5, runtime);
           m_Drive.AutoDriveRC(11, 0, 2.6, 3.25, runtime);
           m_Superstructure.prepSpecWithScheduler(.5, 1.5, runtime);
           m_Superstructure.lowPresetWithScheduler(3.5, 5, runtime);
           m_Superstructure.pincher.wideOpenWithScheduler(4, 4.25, runtime);
           m_Drive.AutoDriveRC(-15, -45, 4.5, 7, runtime);
           m_Superstructure.wallPickupWithScheduler(6, 10, runtime);
           m_Drive.SetHeading(0, 7.1, 8, runtime);
           m_Drive.AutoDriveRC(17.5, 0,8.1, 9.25, runtime);
           m_Drive.AutoDriveRC(0, -20, 9.3, 10.4, runtime);
           m_Drive.SetHeading(0, 10.45, 10.75, runtime);
           m_Drive.AutoDriveRC(-50, 0,10.8, 13.25, runtime);
           m_Drive.SetHeading(0, 13.3, 13.85, runtime);
           m_Drive.AutoDriveRC(-12, 0, 13.9, 14.5, runtime);
           m_Superstructure.pincher.closeWithScheduler(14.55, 14.65, runtime);
           m_Superstructure.prepSpecWithScheduler(15, 16, runtime);
           m_Superstructure.pincher.scoreSpecimenWithScheduler(15, 16, runtime);
           m_Drive.AutoDriveRC(15, 50,15.5, 18.5, runtime );
           m_Drive.SetHeading(0, 18.55, 19, runtime);
           m_Drive.AutoDriveRC(10, 0, 19.05, 19.75, runtime);
           m_Superstructure.lowPresetWithScheduler(19.8, 25, runtime);
           m_Superstructure.pincher.openWithScheduler(20.65, 20.75, runtime);
           m_Drive.AutoDriveRC(-15, 0, 21, 22.5, runtime);
           m_Superstructure.pincher.scoreSpecimenWithScheduler(23, 25, runtime);
           m_Superstructure.resetElevatorWithScheduler(25.1, 28, runtime);
           m_Drive.SetHeading(-90, 22.6, 24.5, runtime);
           m_Drive.AutoDriveRC(60, -35, 24.6, 27, runtime);
           m_Drive.SetHeading(0, 27.1, 30, runtime);













        }

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











    }
}
