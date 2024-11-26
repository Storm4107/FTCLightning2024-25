package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.Servo;

public class ServoActuator {
    public Servo Servo;

    public ServoActuator(Servo Servo){
        this.Servo = Servo;

    }

    public void setServos(double Value) {
       Servo.setPosition(Value);
    }

    public double getServoPosition(){
        return Servo.getPosition();
    }
}
