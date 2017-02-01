package org.firstinspires.ftc.teamcode.opModes.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Catapult;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

@Autonomous(name="legend27: Shooting Auto", group="legend27")
public class ShootingAuto extends LinearOpMode {

    private ElapsedTime timer;

    private Catapult catapult;
    private Drivetrain drivetrain;
    private Intake intake;

    @Override
    public void runOpMode() throws InterruptedException {
        timer = new ElapsedTime();

        catapult = new Catapult(hardwareMap.dcMotor.get("catapult"));
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("left_drive"), hardwareMap.dcMotor.get("right_drive"));
        intake = new Intake(hardwareMap.dcMotor.get("intake"));

        waitForStart();

        catapult.catapultBall(1.05, .25);

        intake.rotate(1);

        timer.reset();
        while(timer.seconds() < 2);

        catapult.catapultBall(1.05, .25);
    }
}
