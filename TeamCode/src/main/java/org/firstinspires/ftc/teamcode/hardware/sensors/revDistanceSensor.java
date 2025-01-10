package org.firstinspires.ftc.teamcode.hardware.sensors;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;

import org.firstinspires.ftc.teamcode.subsystems.SuperstructureSubsystem;

public class revDistanceSensor {

    private DistanceSensor sensorDistance;
    private Telemetry telemetry;
    private String Distance1;

    private SuperstructureSubsystem m_Superstructure;


    /**
     * Sets up the constructor for a Distance sensor.
     *
     * @param Map        Hardware map object
     * @param telemetry  Telemetry object
     * @param Distance1 The name of the sensor in the robot config
     */
    public revDistanceSensor(HardwareMap Map, Telemetry telemetry, String Distance1) {
        this.telemetry = telemetry;

        sensorDistance = Map.get(DistanceSensor.class, "sensor_distance");
    }

    public revDistanceSensor(HardwareMap hardwareMap, Telemetry telemetry) {
    }

    /**
     * Sensor periodic- will output state with telemetry. (Optional)
     */
    public void runDigitalSensor() {

        if (getCM() >= 3) {
            m_Superstructure.zeroPreset();
        }

        telemetry.addData("deviceName", sensorDistance.getDeviceName());
        telemetry.addData("range", String.format("%.01f cm", sensorDistance.getDistance(DistanceUnit.CM)));
        telemetry.addData("range", String.format("%.01f in", sensorDistance.getDistance(DistanceUnit.INCH)));
    }

    /**
     * returns the CM distance value of the sensor.
     */
    public double getCM() {
        return sensorDistance.getDistance(DistanceUnit.CM);
    }

    /**
     * returns the Inches distance value of the sensor.
     */
    public double getInches() {
        return sensorDistance.getDistance(DistanceUnit.INCH);
    }
}
