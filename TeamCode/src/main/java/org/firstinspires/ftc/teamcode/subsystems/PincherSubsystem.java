package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

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
        leftWrist = new ServoActuator(leftWristServo);
        rightWrist = new ServoActuator(rightWristServo);
    }

    //set the angle of the pivot
    public void setPivotAngle(double angle) {
        pivot.setServos(angle);
    }

    //set the angle of the wrist
    public void setWristAngle(double value) {
        leftWrist.setServos(value);
        rightWrist.setServos(1 -value);
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
        open();
        setWristAngle(-1);
        setPivotAngle(0.3);
    }
    public void tuck() {
        setWristAngle(1);
        setPivotAngle(0.3);
    }

    public void wallPickup() {
       // open();
        setWristAngle(0);
        setPivotAngle(1);
    }

    public void retract() {
        setWristAngle(-1);
        setPivotAngle(0);
    }

    public void scoreSample() {
        setWristAngle(0.75);
       // setPivotAngle(1);
    }

    public void scoreSpecimen() {
        setWristAngle(-0.25);
      //  setPivotAngle(1);
    }


}
