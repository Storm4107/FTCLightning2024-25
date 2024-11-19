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
    private CRServo leftWristServo;
    private CRServo rightWristServo;

    public PincherSubsystem(HardwareMap Map) {
        //Servo hardwaremap setup
        pincherServo = Map.get(Servo.class, "pincherServo");
        pivotServo = Map.get(Servo.class, "pivotServo");
        leftWristServo = Map.get(CRServo.class, "leftWristServo");
        rightWristServo = Map.get(CRServo.class, "rightWristServo");

        //Hardware compilation
        pincher = new ServoActuator(pincherServo);
        pivot = new ServoActuator(pivotServo);

    }

    public void wristUp() {
        leftWristServo.setPower(1);
        rightWristServo.setPower(-1);
    }

    public void wristDown() {
        leftWristServo.setPower(-1);
        rightWristServo.setPower(1);
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
        open();
        setPivotAngle(0.3);
    }
    public void tuck() {
        setPivotAngle(0.3);
    }

    public void wallPickup() {
       // open();
        setPivotAngle(1);
    }

    public void retract() {
        setPivotAngle(0);
    }

    public void scoreSample() {
       // setPivotAngle(1);
    }

    public void scoreSpecimen() {
      //  setPivotAngle(1);
    }


}
