//Name of code
package org.firstinspires.ftc.teamcode.opModes.teleOps;
//import neccesery libraries
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//Import subsyistems
import org.firstinspires.ftc.teamcode.subsystems.Catapult;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
//The robot is names "Legend27"
@TeleOp(name="legend27: TeleOp Single NO COLOR SENSOR", group="legend27")
public class TeleOpSingle extends OpMode {
//Says which classes it will be using
    private Catapult catapult;
    private Drivetrain drivetrain;
    private Intake intake;
//state what the different systems will be refered to
    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("left_drive"), hardwareMap.dcMotor.get("right_drive"));
        intake = new Intake(hardwareMap.dcMotor.get("intake"));
        catapult = new Catapult(hardwareMap.dcMotor.get("catapult"));
    }
//Stop everything
    @Override
    public void start() {
        drivetrain.stop();
        intake.stop();
        catapult.stop();
    }
//Defines what the different sticks do in arcade drive
    @Override
    public void loop() {
        drivetrain.arcadeDrive(-1 * gamepad1.left_stick_y, gamepad1.right_stick_x);

        intakeControls();
        catapultControls();
        telemetry.addData("Catapult Position", catapult.getPosition());
        telemetry.addData("Drive Position", drivetrain.getPosition()[0] + ", " + drivetrain.getPosition()[1]);
    }
//stop everything
    public void stop() {
        drivetrain.stop();
        intake.stop();
        catapult.stop();
    }
//if x pressed shoot the catapult at the end reset the encoder
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
//if b is pressed, take balls in via the intake, if a is pressed roll it out of the intake
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
