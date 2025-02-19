package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.ServoActuator;

public class PincherSubsystem {
    private ServoActuator pincher;
    private ServoActuator leftWrist;
    private ServoActuator rightWrist;

    private Servo pincherServo;
    private Servo leftWristServo;
    private Servo rightWristServo;

    public PincherSubsystem(HardwareMap Map) {
        //Servo hardwaremap setup
        pincherServo = Map.get(Servo.class, "pincherServo");

        leftWristServo = Map.get(Servo.class, "leftWristServo");
        rightWristServo = Map.get(Servo.class, "rightWristServo");

        //Hardware compilation
        pincher = new ServoActuator(pincherServo);
        leftWrist = new
                ServoActuator(leftWristServo);
        rightWrist = new
                ServoActuator(rightWristServo);

    }

    public void setWrisAngle(double value) {
        leftWrist.setServos(value);
        rightWrist.setServos(1-value);
    }

    public void wristUp() {
        setWrisAngle(.865);
    }

    public void wristUpWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime < currentTime) && (currentTime <= endTime)) {
            wristUp();
        }
    }
    public void wristDown() {
        leftWristServo.setPosition(-.4);
        rightWristServo.setPosition(.4);
    }

    //set pincher to open
    public void open() {
        pincher.setServos(.325);
    }
    public void openWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime <  currentTime) && (currentTime<= endTime)) {
            open();
        }
    }

    //set pincher to wide open (for specimens off the wall)
    public void wideOpen() {
        pincher.setServos(0.15);
    }

    public void wideOpenWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
    double currentTime = runtime.seconds();
    if ((initialTime <  currentTime) && (currentTime<= endTime)) {
        wideOpen();
        }
    }
    //set pincher to closed
    public void close() {
        pincher.setServos(.445);}
    public void closeWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime <  currentTime) && (currentTime<= endTime)) {
            close();
        }
    }

    //Presets
    public void untuck() {
        close();
    }

    public void wallPickup() {
       setWrisAngle(.066);
    }

    public void wallPickupPresetWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime < currentTime) && (currentTime <= endTime)) {
            wallPickup();
        }
    }

    public void scoreSample() {
        setWrisAngle(.28);
    }

    public void scoreSampleWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime < currentTime) && (currentTime <= endTime)) {
            scoreSample();
        }
    }
    public void scoreSpecimen() {
       setWrisAngle(.53);
    }

    public void scoreSpecimenWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime < currentTime) && (currentTime <= endTime)) {
            scoreSpecimen();
        }
    }

    public void lowTaperFade() {setWrisAngle(.65); }

    public void lowTaperFadeWithScheduler(double initialTime, double endTime, ElapsedTime runtime) {
        double currentTime = runtime.seconds();
        if ((initialTime < currentTime) && (currentTime <= endTime)) {
            lowTaperFade();
        }
    }
}
