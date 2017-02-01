package org.firstinspires.ftc.teamcode.opModes.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.BeaconActivator;
import org.firstinspires.ftc.teamcode.subsystems.Catapult;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

@Autonomous(name="legend27: SDB Red", group="legend27")
public class ShootDriveBeaconRed extends LinearOpMode {

    private boolean teamRed = true;

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


        catapult.catapultBall(1.05, .25);
        timer.reset();
        while(timer.seconds() < 2 && opModeIsActive());

        catapult.catapultBall(1.05, .25);
        timer.reset();
        while(timer.seconds() < 2 && opModeIsActive());

        drivetrain.tankDrive(1, 1);
        timer.reset();
        while (timer.seconds() < 1.75 && opModeIsActive());

        if(teamRed) {
            drivetrain.tankDrive(-0.25, 0.25);
        }
        else {
            drivetrain.tankDrive(0.25, -0.25);
        }
        timer.reset();
        while (timer.seconds() < 0.5 && opModeIsActive());

        drivetrain.tankDrive(1, 1);
        timer.reset();
        while (timer.seconds() < 0.5 && opModeIsActive());

	    drivetrain.stop();

	    beaconActivator.doBeacon(teamRed);
    }
}