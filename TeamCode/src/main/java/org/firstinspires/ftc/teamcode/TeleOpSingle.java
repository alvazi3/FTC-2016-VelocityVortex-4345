package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.*;

@TeleOp(name="Unnamed4345: TeleOp Single", group="unnamed4345")
public class TeleOpSingle extends OpMode {
    private ChooChoo chooChoo;
    private Drivetrain drivetrain;
    private Intake intake;
    
    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("left_drive"), hardwareMap.dcMotor.get("right_drive"));
        drivetrain.stop();

        intake = new Intake(hardwareMap.dcMotor.get("intake"));
        intake.stop();

        chooChoo = new ChooChoo(hardwareMap.dcMotor.get("catapult"));
        chooChoo.stop();
    }

    @Override
    public void start() {
        drivetrain.stop();
        intake.stop();
        chooChoo.stop();
    }

    @Override
    public void loop() {
        drivetrain.arcadeDrive(gamepad1.left_stick_y, gamepad1.right_stick_x);

        intakeControls();
        catapultControls();
        telemetry.addData("Catapult Position", chooChoo.getPosition());
    }

    public void stop() {
        drivetrain.stop();
        intake.stop();
        chooChoo.stop();
    }

    private void catapultControls() {
        if (gamepad1.x) {
            chooChoo.rotate();
            while(gamepad1.x);

        }
        else if (gamepad1.y) {
            chooChoo.catapultBall(1);
            while (gamepad1.y);
        }
        else if (gamepad1.right_bumper) {
            chooChoo.holdPosition();
            while (gamepad1.right_bumper);
        }
        else if (gamepad1.back) {
            chooChoo.resetEncoder();
        }
        else {
            if (!chooChoo.isHolding()) {
                chooChoo.holdPosition();
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
}
