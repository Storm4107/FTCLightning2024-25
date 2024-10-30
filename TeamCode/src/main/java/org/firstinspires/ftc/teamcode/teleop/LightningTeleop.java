package org.firstinspires.ftc.teamcode.teleop;

import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SuperstructureSubsystem;

@TeleOp(name ="Lightning Teleop")
public class LightningTeleop extends LinearOpMode {

    //Gamepad Bindings
    private GamepadEx Driver;
    private GamepadEx Operator;
    private GamepadButton imuReset;

    //subsystems
    private MecanumDriveSubsystem  mec_Drive;
    private SuperstructureSubsystem mec_Superstructure;



    @Override
    public void runOpMode() {
        //Run when initializing
        mec_Superstructure = new SuperstructureSubsystem(hardwareMap, telemetry);
        mec_Drive = new MecanumDriveSubsystem(hardwareMap, telemetry);

        Driver = new GamepadEx(gamepad1);
        Operator = new GamepadEx(gamepad2);

        telemetry.update();
        waitForStart();
        //Run immediately when starting


        while (opModeIsActive()) {
            //Periodic Opmode
            mec_Superstructure.periodic();


            //IMU Reset Button
            if (Driver.getButton(GamepadKeys.Button.Y)) {
                mec_Drive.resetHeading();
            }

            //Drivetrain method
            mec_Drive.Drive(Driver.getLeftX(), Driver.getLeftY(), Driver.getRightX(), Driver.getButton(GamepadKeys.Button.RIGHT_BUMPER));
            if (Operator.getButton(GamepadKeys.Button.BACK)) {
                mec_Superstructure.zeroPreset();
            }

            if (Operator.getButton(GamepadKeys.Button.A)) {
                mec_Superstructure.pickupPreset();
            }

            if (Operator.getButton(GamepadKeys.Button.X)) {
                mec_Superstructure.mediumPreset();
            }

            if (Operator.getButton(GamepadKeys.Button.Y)) {
                mec_Superstructure.highPreset();
            }

            telemetry.update();

        }
    }
}
