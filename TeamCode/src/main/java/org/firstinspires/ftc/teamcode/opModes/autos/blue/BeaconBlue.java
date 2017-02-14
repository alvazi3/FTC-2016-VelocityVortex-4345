package org.firstinspires.ftc.teamcode.opModes.autos.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.BeaconActivator;
import org.firstinspires.ftc.teamcode.subsystems.Catapult;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

@Autonomous(name="legend27: Beacon Blue", group="legend27")
public class BeaconBlue extends LinearOpMode {

    private boolean teamRed = false;

    private ElapsedTime timer;

    private Catapult catapult;
    private Drivetrain drivetrain;
    private Intake intake;
    private BeaconActivator beaconActivator;

    @Override
    public void runOpMode() throws InterruptedException {
        timer = new ElapsedTime();

        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("left_drive"), hardwareMap.dcMotor.get("right_drive"));
        intake = new Intake(hardwareMap.dcMotor.get("intake"));
        catapult = new Catapult(hardwareMap.dcMotor.get("catapult"));
        beaconActivator = new BeaconActivator(hardwareMap.colorSensor.get("sensor"), hardwareMap.deviceInterfaceModule.get("dim"), hardwareMap.led.get("led"), hardwareMap.dcMotor.get("button_pusher"));

        waitForStart();

        drivetrain.tankDrive(1, 1);
        telemetry.addData("Status", "Driving forward0");
        timer.reset();
        while (timer.seconds() < 1 && opModeIsActive());

        if(teamRed) {
            drivetrain.tankDrive(-0.125, 0.125);
        }
        else {
            drivetrain.tankDrive(0.125, -0.125);
        }
        telemetry.addData("Status", "Turning");

        timer.reset();
        while (timer.seconds() < 2.45 && opModeIsActive());

        drivetrain.tankDrive(.2, .2);
        telemetry.addData("Status", "Driving forward1 ");
        timer.reset();
        while (timer.seconds() < 3 && opModeIsActive());

        drivetrain.stop();

        beaconActivator.doBeacon(teamRed);
    }
}
