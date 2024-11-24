package org.firstinspires.ftc.teamcode.teleop;

import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.SuperstructureSubsystem;

@TeleOp(name = "2025 Teleop")
public class Teleop2025 extends LinearOpMode {

    //Gamepad bindings
    private GamepadEx Driver;
    private GamepadEx Operator;

    private GamepadButton imuReset;

    //subsystems
    private MecanumDriveSubsystem m_Drive;
    private SuperstructureSubsystem m_Superstructure;

    @Override
    public void runOpMode() {
        //Run when initializing
        m_Drive = new MecanumDriveSubsystem(hardwareMap, telemetry);
        m_Superstructure = new SuperstructureSubsystem(hardwareMap, telemetry);

        Driver = new GamepadEx(gamepad1);
        Operator = new GamepadEx(gamepad2);

        telemetry.update();
        waitForStart();
        //Run immediately when starting

        while (opModeIsActive()) {
                //Periodic Opmode
                m_Superstructure.periodic();

                telemetry.addData(
                        "Periodic currently running",
                        "Operator can hold left bumper for manual control");

                //TODO: Put button bindings below here
            ////////////////////////////////////////////////////////////////////////////////
                //IMU Reset button
                if (Driver.getButton(GamepadKeys.Button.Y)) {
                   m_Drive.resetHeading();
                }

                //Drivetrain method
                m_Drive.Drive(Driver.getLeftX(), Driver.getLeftY(), Driver.getRightX(), Driver.getButton(GamepadKeys.Button.RIGHT_BUMPER));

                // sets the power brake on the drivetrain
                m_Drive.zeroPowerBrake();

                // sets the power brake for the elevator
                m_Superstructure.elePowerBrake();


                if (Operator.getButton(GamepadKeys.Button.DPAD_UP)) {
                    m_Superstructure.pincher.wristUp();
                }

                if (Operator.getButton(GamepadKeys.Button.DPAD_DOWN)) {
                    m_Superstructure.pincher.wristDown();
                }


                //Superstructure preset - Zero everything
                if (Operator.getButton(GamepadKeys.Button.START)) {
                    m_Superstructure.zeroPreset();
                }

                //extends the laterator only
                if (Operator.getButton(GamepadKeys.Button.X)) {
                    m_Superstructure.groundPickupPreset();
                }

                //pitches the intake
                if (Operator.getButton(GamepadKeys.Button.LEFT_BUMPER)) {
                    m_Superstructure.laterator.discard();
                } else if (Operator.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.03) {
                    m_Superstructure.laterator.groundPickUp();
                } else {
                    m_Superstructure.laterator.level();
                }



                //spins the impellers
                if (Operator.getButton(GamepadKeys.Button.RIGHT_BUMPER)) {
                    m_Superstructure.laterator.outake();
                } else if (Operator.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.03) {
                    m_Superstructure.laterator.intake();
                } else {
                    m_Superstructure.laterator.stopIntake();
                }

                //drops elevator into the intake for pass through
                if (Operator.getButton(GamepadKeys.Button.A)) {
                   m_Superstructure.HandoffPreset();
                }

                //prepares to score in the high basket
                if (Operator.getButton(GamepadKeys.Button.Y)) {
                    m_Superstructure.highPreset();
                }

                //prepares to score specimen
                if (Operator.getButton(GamepadKeys.Button.B)) {
                    m_Superstructure.lowPreset();
                }

                //opens the pincher
                if (Operator.getButton(GamepadKeys.Button.LEFT_STICK_BUTTON)) {
                    m_Superstructure.pincher.open();
                }

                //closes the pincher
                if (Operator.getButton(GamepadKeys.Button.RIGHT_STICK_BUTTON)) {
                    m_Superstructure.pincher.close();
            }
                //Superstructure manual input toggle - Triggered by holding holding left bumper
                if (Operator.getButton(GamepadKeys.Button.BACK)) {
                    m_Superstructure.ManualInput(Operator.getLeftY());
                    telemetry.addData(
                            "MANUAL INPUT", "ENABLED");
                }

                if (Operator.getButton(GamepadKeys.Button.DPAD_LEFT)) {
                    m_Superstructure.pincher.setPivotAngle(0.42);
                }

                if (Operator.getButton(GamepadKeys.Button.DPAD_RIGHT)) {
                    m_Superstructure.pincher.setPivotAngle(0.07);
                }

                telemetry.update();
            }
        }
    }

