package org.firstinspires.ftc.teamcode.opModes.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.BeaconActivator;

@Autonomous(name="legend27: Beacon Testing Red", group="legend27")

public class AutoBeaconTestRed extends LinearOpMode{

    private BeaconActivator beaconActivator;

    public void initialize() {
        beaconActivator = new BeaconActivator(hardwareMap.colorSensor.get("sensor"), hardwareMap.deviceInterfaceModule.get("dim"), hardwareMap.led.get("led"), hardwareMap.dcMotor.get("button_pusher"));

        beaconActivator.stop();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        waitForStart();
        telemetry.addData("Done", false);
        telemetry.addData("isRed", beaconActivator.isRed());
        beaconActivator.doBeacon(true);
        telemetry.addData("Done", true);
    }
}
