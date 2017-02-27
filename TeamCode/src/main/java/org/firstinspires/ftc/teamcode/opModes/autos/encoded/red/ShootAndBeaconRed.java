package org.firstinspires.ftc.teamcode.opModes.autos.encoded.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.BeaconActivator;
import org.firstinspires.ftc.teamcode.subsystems.Catapult;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

@Autonomous(name="ENCODED: Shoot/Beacon Auto Red", group="legend27")
public class ShootAndBeaconRed extends LinearOpMode {

    private boolean redTeam = true;

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
        beaconActivator = new BeaconActivator(hardwareMap.colorSensor.get("sensor"), hardwareMap.deviceInterfaceModule.get("dim"), hardwareMap.led.get("led"));

        waitForStart();

        catapult.catapultBall(1.05, .25);

        timer.reset();
        while(timer.seconds() < 2 && opModeIsActive());

        catapult.catapultBall(1.05, .25);

        drivetrain.driveToP(30);

        timer.reset();
        while(timer.seconds() < 1);

        drivetrain.turnToP(-75);

        timer.reset();
        while(timer.seconds() < 1);

        drivetrain.driveToP(45);

        timer.reset();
        while(timer.seconds() < 1);

        drivetrain.turnToP(-65);

        timer.reset();
        while(timer.seconds() < 1);

        drivetrain.driveToP(12);

        timer.reset();
        while (timer.seconds() < 0.5);

        drivetrain.driveToP(2);

        timer.reset();
        while (timer.seconds() < 0.5);

        drivetrain.driveToP(-2);

        timer.reset();
        while (timer.seconds() < 0.5);

        if (beaconActivator.isRed() != redTeam) {
            drivetrain.driveToP(4);
        }

        drivetrain.driveToP(-10);

        timer.reset();
        while(timer.seconds() < 0.5);

        drivetrain.turnToP(95);

        timer.reset();
        while(timer.seconds() < 0.5);

        drivetrain.driveToP(44);

        timer.reset();
        while (timer.seconds() < 0.5);

        drivetrain.turnToP(-105);

        if (beaconActivator.isRed() != redTeam) {
            drivetrain.driveToP(4);
        }

        timer.reset();
        while (timer.seconds() < 8);

        drivetrain.driveTo(4);

        telemetry.addData("isRed", beaconActivator.isRed());
        telemetry.addData("Red", beaconActivator.getRawColors()[1]);
        telemetry.addData("Green", beaconActivator.getRawColors()[2]);
        telemetry.addData("Blue", beaconActivator.getRawColors()[3]);
        telemetry.update();


    }
}
