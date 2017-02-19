package org.firstinspires.ftc.teamcode.opModes.autos.encoded.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.BeaconActivator;
import org.firstinspires.ftc.teamcode.subsystems.Catapult;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

@Autonomous(name="ENCODED: Shoot/Beacon Auto Blue", group="legend27")
public class ShootAndBeaconBlue extends LinearOpMode {

    private boolean redTeam = false;

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

        drivetrain.turnToP(45);

        timer.reset();
        while(timer.seconds() < 1);

        drivetrain.driveToP(35);

        timer.reset();
        while(timer.seconds() < 1);

        drivetrain.turnToP(45);

        timer.reset();
        while(timer.seconds() < 1);

        drivetrain.driveToP(10);

        timer.reset();
        while (timer.seconds() < 1);

        drivetrain.driveToP(-2);
        drivetrain.driveToP(7);

        timer.reset();
        while (timer.seconds() < 8);

        if (beaconActivator.isRed() != redTeam) {
            drivetrain.driveToP(-2);
            drivetrain.driveToP(4);
        }
    }
}
