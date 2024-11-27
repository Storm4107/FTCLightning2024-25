package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.hardware.ServoActuator;

public class LateratorSubsystem {
    private ServoActuator leftPivot;
    private ServoActuator rightPivot;
    private ServoActuator leftLaterator;
    private ServoActuator rightLaterator;

    private CRServo intakeServo;
    private Servo leftPivotServo;
    private Servo rightPivotServo;
    private Servo leftLateratorServo;
    private Servo rightLateratorServo;

    public LateratorSubsystem(HardwareMap Map) {
        //Servo hardwaremap setup
        intakeServo = Map.get(CRServo.class, "intakeServo");
        leftPivotServo = Map.get(Servo.class, "leftPivotServo");
        rightPivotServo = Map.get(Servo.class, "rightPivotServo");
        leftLateratorServo = Map.get(Servo.class, "leftLateratorServo");
        rightLateratorServo = Map.get(Servo.class, "rightLateratorServo");

        //Hardware compilation
        leftPivot = new ServoActuator(leftPivotServo);
        rightPivot = new ServoActuator(rightPivotServo);
        leftLaterator = new ServoActuator(leftLateratorServo);
        rightLaterator = new ServoActuator(rightLateratorServo);
    }

    //set the angle of the pivot
    public void setPivotAngle(double angle) {
        leftPivot.setServos(angle);
        rightPivot.setServos(1-angle);
    }

    //set the angle of the wrist
    public void setLaterator(double value) {
        leftLaterator.setServos(value);
        rightLaterator.setServos(1 -value);
    }

    //set pincher to open
    public void intake() {
        intakeServo.setPower(-1);
    }
    public void outake() {
        intakeServo.setPower(1);
    }
    public void stopIntake() {
        intakeServo.setPower(0);
    }

    //Presets
    public void extend() {
        setLaterator(0.4);
        setPivotAngle(.4);
    }


    public void level() {
        setPivotAngle(.31);
    }

    public void groundPickUp() {
        setPivotAngle(.47);
    }


    public void discard() {
        setPivotAngle(-1);
    }

    public void retract() {
        setLaterator(.73);
    }

    public void dump() {
        //setLaterator(0);
        setPivotAngle(0.15);
        //stopIntake();
    }

}
