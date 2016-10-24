package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.*;

@TeleOp(name="Unnamed4345: TeleOp Single", group="unnamed4345")
public class TeleOpSingle extends OpMode {
    private ChooChoo chooChoo;
    private Drivetrain drivetrain;
    
    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("left_drive"), hardwareMap.dcMotor.get("right_drive"));
        chooChoo = new ChooChoo(hardwareMap.dcMotor.get("catapult"));
        drivetrain.stop();
    }

    @Override
    public void start() {
        drivetrain.stop();
        chooChoo.stop();
    }

    @Override
    public void loop() {
        drivetrain.arcadeDrive(gamepad1.left_stick_y, gamepad1.left_stick_x);
        if (gamepad1.a) chooChoo.catapultBall(1);

        else chooChoo.stop();
    }

    public void stop() {
        drivetrain.stop();
        chooChoo.stop();
    }
}
