package org.firstinspires.ftc.teamcode.opModes.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Catapult;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

@Autonomous(name="Unnamed4345: Shooting/Drive Auto", group="unnamed4345")
public class ShootingAndDriveAuto extends LinearOpMode {

    private ElapsedTime timer;

    private Catapult catapult;
    private Drivetrain drivetrain;
    private Intake intake;

    @Override
    public void runOpMode() throws InterruptedException {
        timer = new ElapsedTime();

        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("left_drive"), hardwareMap.dcMotor.get("right_drive"));
        intake = new Intake(hardwareMap.dcMotor.get("intake"));
        catapult = new Catapult(hardwareMap.dcMotor.get("catapult"));

        waitForStart();

        catapult.catapultBall(1.05, .25);

        intake.rotate(1);

        timer.reset();
        while(timer.seconds() < 2 && opModeIsActive());

        catapult.catapultBall(1.05, .25);

        timer.reset();
        while(timer.seconds() < 2 && opModeIsActive());

        intake.rotate(0.25);
        drivetrain.tankDrive(-1, -0.9);

        timer.reset();
        while (timer.seconds() < 3 && opModeIsActive());

        drivetrain.stop();
    }
}
