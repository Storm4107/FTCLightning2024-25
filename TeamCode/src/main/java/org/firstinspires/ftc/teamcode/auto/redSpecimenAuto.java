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
            m_Superstructure.pincher.setWrisAngle(.7);
            m_Superstructure.zeroPresetAuto();

        }
        waitForStart();
        m_Drive.resetDriveEncoders();


        if (isStopRequested()) return;

        while (opModeIsActive()) {

            m_Superstructure.pincher.scoreSpecimenWithScheduler(0,1, runtime);
            m_Drive.AutoDriveRC(35,0, 0, 2.5, runtime);
            m_Drive.SetHeading(0, 2.51, 3, runtime);
            m_Drive.AutoDriveRC(8, 0, 3, 4, runtime);
            m_Superstructure.lowPresetWithScheduler(4.1, 6, runtime);
            m_Superstructure.pincher.wideOpenWithScheduler(6.1, 7, runtime);
            m_Drive.AutoDriveRC(-10, 0, 6.5, 8.5, runtime);
            m_Superstructure.resetElevatorWithScheduler(7.5, 30, runtime);













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
