package org.firstinspires.ftc.teamcode.opModes.autos.timed;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Catapult;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

@Autonomous(name="TIMED: Delayed Shooting/Drive Auto", group="legend27")
public class DelayedShootingAndDriveAuto extends LinearOpMode {

    private final double DELAY = 100;

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

        timer.reset();
        while(timer.seconds() < 2 && opModeIsActive());

        catapult.catapultBall(1.05, .25);

        timer.reset();
        while(timer.seconds() < DELAY && opModeIsActive()) {
            telemetry.addData("Waiting for:", DELAY - timer.seconds());
            telemetry.update();
        }

        drivetrain.tankDrive(1, 1);

        timer.reset();
        while (timer.seconds() < 2 && opModeIsActive()) ;

        drivetrain.stop();
    }
}
