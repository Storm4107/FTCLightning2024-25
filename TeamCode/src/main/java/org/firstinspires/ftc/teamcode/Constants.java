package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

@Config
public class Constants {
    public static class DriveConstants {

        public static final boolean SquareInputs = false;
        public static final double DampenMult = 0.3;
        public static final double DriveSpeedMult = 1;

        //Initial IMU angle offset for field centric in degrees
        public static final double IMUOffset = -90;
    }

    public static class SuperstructureConstants {

        //Amount of elevator stages in cascade rig. Needed for gear ratio.
        public static final int elevatorStages = 3;

        //Coiunts per inch of the main elevator stage 2229
        public static final double initialCPI = 1;

        //Initial counts / elevator stages returns the counts per inch for a cascade elevator.
        public static final double elevatorCPI = initialCPI / elevatorStages;
        public static final PIDCoefficients elevatorPID = new PIDCoefficients(0.0005, 0.0000, 0);
    }

    public static class AutoConstants {
        public static final double COUNTS_PER_INCH = 338.569; //Found empirically with drive tuning
        public static final PIDCoefficients TranslationPID = new PIDCoefficients(0.00035, 0, 0.000012);
        public static final PIDCoefficients StrafePID = new PIDCoefficients(0.0003, 0, 0.0);
        public static final PIDCoefficients HeadingPID = new PIDCoefficients(0.030, 0, 0.0);
        public static final double AutoGain = 0.75; // All movements in auto are multiplied by this number. Can be used to reduce overall speed.
        public static double PIDTolerance = 20; //Tolerance in TICKS
    }
}
