package org.firstinspires.ftc.teamcode.teleop;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

@TeleOp(name ="Lightning Teleop")
public class LightningTeleop extends LinearOpMode {

    //Gamepad Bindings
    private GamepadEx Driver;
    private GamepadEx Operator;


    //subsystems
    private MecanumDriveSubsystem  mec_Drive;



    @Override
    public void runOpMode() {
        //Run when initializing
        mec_Drive = new MecanumDriveSubsystem(hardwareMap, telemetry);

        Driver = new GamepadEx(gamepad1);

        telemetry.update();
        waitForStart();
        //Run immediately when starting


        while (opModeIsActive()) {
            //IMU Reset Button
            if (Driver.getButton(GamepadKeys.Button.Y)) {
                mec_Drive.resetHeading();
            }

            //Drivetrain method
            mec_Drive.Drive(Driver.getLeftX(), Driver.getLeftY(), Driver.getRightX(), Driver.getButton(GamepadKeys.Button.RIGHT_BUMPER));

            telemetry.update();

        }
    }
}
