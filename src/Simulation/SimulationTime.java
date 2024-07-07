package Simulation;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Represents a train ride simulation time
 * Train activities are simulated using a virtual time, which has a linear dependency from the real time (see SimulationSettings.timeCoefficient)
 */
public class SimulationTime {
    private LocalTime started;
    private Duration passed;

    public SimulationTime() {
        started = LocalTime.now();
        passed = Duration.ZERO;
    }
    public SimulationTime(SimulationTime other) {
        started = other.started;
        passed = other.passed;
    }
    public void updateFromSystemClock() {
        Duration realTimePassed = Duration.between(started, LocalTime.now());
        passed = TimeTools.toVirtualTime(realTimePassed);
    }
    public SimulationTime projectTo(Duration duration) {
        var newSimTime = new SimulationTime(this);
        newSimTime.passed = newSimTime.passed.plus(duration);
        return newSimTime;
    }
    public LocalTime toRealTime() {
        return started.plusNanos((long) (passed.toNanos()*SimulationSettings.timeCoefficient));
    }
    public void increment(Duration duration) {
        passed = passed.plus(duration);
    }
}
