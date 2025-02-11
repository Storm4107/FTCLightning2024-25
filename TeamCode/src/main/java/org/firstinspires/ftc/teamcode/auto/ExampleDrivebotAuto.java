package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SuperstructureSubsystem;



@Autonomous(name = "2025 - ExampleDrivebotAuto", group = "Autonomous")
public class ExampleDrivebotAuto extends LinearOpMode {
    //Instantiate mechanisms

    public SuperstructureSubsystem m_Superstructure;
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
            m_Superstructure.pincher.setWrisAngle(.8);
            m_Superstructure.zeroPresetAuto();
        }
        waitForStart();
        m_Drive.resetDriveEncoders();


        if (isStopRequested()) return;

        while (opModeIsActive()) {

            m_Drive.AutoDriveRC(10,0, 0, 2, runtime);
            m_Drive.AutoDriveRC(10, 10, 2.1, 4, runtime);
            m_Drive.AutoDriveRC(0, 10, 4.1, 6, runtime);
            m_Drive.AutoDriveRC(0, 0, 6.1, 8, runtime);


        }

    }
}
