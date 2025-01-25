package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

@Config
public class Constants {
    public static class DriveConstants {


        //test comment

        public static final boolean SquareInputs = false;
        public static final double DampenMult = 0.3;
        public static final double DriveSpeedMult = 1;

        //Initial IMU angle offset for field centric in degrees
        public static final double IMUOffset = -90;
    }

    public static class SuperstructureConstants {

        //Amount of elevator stages in cascade rig. Needed for gear ratio.
        public static final int elevatorStages = 3;

        //Counts per inch of the main elevator stage 2229
        public static final double initialCPI = 1;

        //Initial counts / elevator stages returns the counts per inch for a cascade elevator.
        public static final double elevatorCPI = initialCPI / elevatorStages;
        public static final PIDCoefficients elevatorPID = new PIDCoefficients(0.002, 0.1, 0.00005);
    }

    public static class AutoConstants {
        public static final double COUNTS_PER_INCH = 279.797101; //Found empirically with drive tuning
        public static final PIDCoefficients TranslationPID = new PIDCoefficients(0.000115, 0.00000  , 0.0001);
        public static final PIDCoefficients StrafePID = new PIDCoefficients(0.0005, 0.00000, 0.005);
        public static final PIDCoefficients HeadingPID = new PIDCoefficients(0.02, 0, 0.003);
        public static final double AutoGain = 1; // All movements in auto are multiplied by this number. Can be used to reduce overall speed.
        public static double PIDTolerance = 200; //Tolerance in TICKS

        public static double rightFF = -0.108;
        public static double leftFF = 0.12;
    }
}
