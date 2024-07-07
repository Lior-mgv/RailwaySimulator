package Simulation;

import java.time.Duration;

public class TimeTools {
    private static final long millisInHour = 60*60*1000;

    public static Duration toVirtualTime(Duration duration) {
        return toVirtualTime(duration.toMillis());
    }

    public static Duration toVirtualTime(long millis){
        return Duration.ofMillis((long) (millis/SimulationSettings.timeCoefficient));
    }

    public static Duration ofHours(double hours) {
        return Duration.ofMillis((long) (hours*millisInHour));
    }

    public static double toHours(Duration duration) {
        return (double)duration.toMillis() / millisInHour;
    }
}
