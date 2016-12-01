package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.LED;

@Autonomous(name="Unnamed4345: Shooting/Drive Auto", group="unnamed4345")
public class ShootingAndDriveAuto extends LinearOpMode {

    private ChooChoo chooChoo;
    private Drivetrain drivetrain;
    private Intake intake;

    private ColorSensor rgbSensor;
    private LED onboardLed;
    private DeviceInterfaceModule cdim;
    private Servo buttonPusher;

    public void initialize() {
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("left_drive"), hardwareMap.dcMotor.get("right_drive"));
        drivetrain.stop();
        drivetrain.resetEncoder();

        intake = new Intake(hardwareMap.dcMotor.get("intake"));
        intake.stop();

        chooChoo = new ChooChoo(hardwareMap.dcMotor.get("catapult"));
        chooChoo.stop();
        chooChoo.resetEncoder();
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();

        waitForStart();

        chooChoo.catapultBall(1.05, .25);

        intake.rotate(1);

        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        while(timer.seconds() < 2 && opModeIsActive());

        chooChoo.catapultBall(1.05, .25);

        timer.reset();
        while(timer.seconds() < 2 && opModeIsActive());

        intake.rotate(0.25);
        drivetrain.driveTo(60);
        //Hit the Ball
        drivetrain.turnTo(-90);
        //Turn to Beacon
        drivetrain.driveTo(24);
        //Drive Toward Beacon
        drivetrain.BeaconActivator(rgbSensor, cdim, onboardLed, buttonPusher);

        timer.reset();
        while (timer.seconds() < 3 && opModeIsActive());

        drivetrain.stop();
    }
}
