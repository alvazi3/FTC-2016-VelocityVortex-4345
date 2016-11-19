package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.ChooChoo;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

/**
 * Created by mpols on 11/19/2016.
 */
@Autonomous(name="Unnamed4345: Shooting Auto", group="unnamed4345")
public class ShootingAuto extends LinearOpMode {

    private ChooChoo chooChoo;
    private Drivetrain drivetrain;
    private Intake intake;

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

        drivetrain.driveTo(12);

        for(int i = 0; i < 4; i++) {   //Multiple tries often needed
            chooChoo.catapultBall(1, 0.25);

            sleep(2000);
        }
    }
}
