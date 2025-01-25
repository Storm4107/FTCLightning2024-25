package org.firstinspires.ftc.teamcode.teleop;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

@TeleOp(name = "FF Tuning")
public class FFTuning extends LinearOpMode {
    private GamepadEx Driver;

    private MecanumDriveSubsystem m_Drive;



    @Override
    public void runOpMode() {
        //Run when initializing
        m_Drive = new MecanumDriveSubsystem(hardwareMap, telemetry);

        Driver = new GamepadEx(gamepad1);

        telemetry.update();
        waitForStart();
        //Run immediately when starting


        while (opModeIsActive()) {
                //Periodic Opmode
                telemetry.addData(
                        "Periodic currently running",
                        "Operator can hold left bumper for manual arm control");


                //IMU Reset button
                if (Driver.getButton(GamepadKeys.Button.Y)) {
                   m_Drive.resetHeading();
                }

                //Drivetrain method
                m_Drive.DriveRobotRelative(Driver.getLeftX() * 0.01, Driver.getLeftY() * 0.01, Driver.getRightX() * 0.01, Driver.getButton(GamepadKeys.Button.RIGHT_BUMPER));

            telemetry.addData(
                    "Turning FF Constant",
                    Driver.getRightX() * 0.01);





                telemetry.update();
            }
        }
    }

