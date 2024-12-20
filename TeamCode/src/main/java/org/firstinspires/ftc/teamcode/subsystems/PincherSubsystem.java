package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import org.firstinspires.ftc.teamcode.hardware.ServoActuator;

public class PincherSubsystem {
    private ServoActuator pincher;
    private ServoActuator pivot;
    private ServoActuator leftWrist;
    private ServoActuator rightWrist;

    private Servo pincherServo;
    private Servo pivotServo;
    private Servo leftWristServo;
    private Servo rightWristServo;

    public PincherSubsystem(HardwareMap Map) {
        //Servo hardwaremap setup
        pincherServo = Map.get(Servo.class, "pincherServo");
        pivotServo = Map.get(Servo.class, "pivotServo");
        leftWristServo = Map.get(Servo.class, "leftWristServo");
        rightWristServo = Map.get(Servo.class, "rightWristServo");

        //Hardware compilation
        pincher = new ServoActuator(pincherServo);
        pivot = new ServoActuator(pivotServo);

    }

    public void wristUp() {
        leftWristServo.setPosition(.95);
        rightWristServo.setPosition(-.95);
    }

    public void noWrist() {
        leftWristServo.setPosition(-.7);
        rightWristServo.setPosition(.7);
    }

    public void wristDown() {
        leftWristServo.setPosition(-.4);
        rightWristServo.setPosition(.4);
    }

    //set the angle of the pivot
    public void setPivotAngle(double angle) {
        pivot.setServos(angle);
    }

    //set pincher to open
    public void open() {
        pincher.setServos(0.2);
    }

    //set pincher to closed
    public void close() {
        pincher.setServos(0);
    }

    //Presets
    public void untuck() {
        close();
        setPivotAngle(0.3);
    }
    public void tuck() {
        setPivotAngle(0.3);
        close();
    }

    public void wallPickup() {
       // open();
        setPivotAngle(1);
    }

    public void retract() {
        setPivotAngle(0);
    }

    public void scoreSample() {
       leftWristServo.setPosition(-.7);
       rightWristServo.setPosition(.7);
       setPivotAngle(.07);
    }

    public void scoreSpecimen() {
        setPivotAngle(1);
        leftWristServo.setPosition(-.2);
        rightWristServo.setPosition(.2);
    }


}
