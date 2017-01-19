package org.firstinspires.ftc.teamcode.opModes.teleOps;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.*;

@TeleOp(name="Unnamed4345: TeleOp Single", group="unnamed4345")
public class TeleOpSingle extends OpMode {

    private Catapult catapult;
    private Drivetrain drivetrain;
    private Intake intake;
    private BeaconActivator beaconActivator;
    
    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("left_drive"), hardwareMap.dcMotor.get("right_drive"));
        intake = new Intake(hardwareMap.dcMotor.get("intake"));
        catapult = new Catapult(hardwareMap.dcMotor.get("catapult"));
        beaconActivator = new BeaconActivator(hardwareMap.colorSensor.get("sensor"),
                hardwareMap.deviceInterfaceModule.get("dim"),
                hardwareMap.led.get("led"),
                hardwareMap.dcMotor.get("button_pusher"));
    }

    @Override
    public void start() {
        drivetrain.stop();
        intake.stop();
        catapult.stop();
        beaconActivator.stop();
    }

    @Override
    public void loop() {
        drivetrain.arcadeDrive(gamepad1.left_stick_y, gamepad1.right_stick_x);

        intakeControls();
        catapultControls();
        beaconActivatorControls();
        telemetry.addData("Catapult Position", catapult.getPosition());
        telemetry.addData("Drive Position", drivetrain.getPosition());
    }

    public void stop() {
        drivetrain.stop();
        intake.stop();
        catapult.stop();
    }

    private void catapultControls() {
        if (gamepad1.x) {
            catapult.rotate();
            while(gamepad1.x);

        }
        else if (gamepad1.y) {
            catapult.catapultBall(1, 1);
            while (gamepad1.y);
        }
        else if (gamepad1.right_bumper) {
            catapult.holdPosition();
            while (gamepad1.right_bumper);
        }
        else if (gamepad1.back) {
            catapult.resetEncoder();
        }
        else {
            if (!catapult.isHolding()) {
                catapult.holdPosition();
            }
        }

    }

    private void intakeControls() {
        if (gamepad1.b) {
            intake.rollIn();
        }
        else if (gamepad1.a) {
            intake.rollOut();
        }

        else if (gamepad1.right_trigger > 0.1) {
            try {
                intake.rotate(1);
            } catch (InterruptedException e) {

            }

            while (gamepad1.right_trigger > 0.1);
        }

        else {
            intake.stop();
        }
    }

    private void beaconActivatorControls() {
        if (gamepad1.left_bumper) {
            beaconActivator.push();
        }
        else if (gamepad1.left_trigger > 0.1) {
            beaconActivator.pull();
        }
        else {
            beaconActivator.stop();
        }
    }
}
