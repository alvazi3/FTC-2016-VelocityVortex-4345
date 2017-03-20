package org.firstinspires.ftc.teamcode.subsystems;
//import the needed encoders
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import static java.lang.Thread.sleep;
//define the intake class
public class Intake {
//set as 147 rpm
    private static final double ROTATIONS_PER_MINUTE = 147;
//name the motor "intakeMotor"
    private DcMotor intakeMotor;
//define the motor, set it to forward and run it without an encoder
    public Intake(DcMotor intakeMotor) {
        this.intakeMotor = intakeMotor;

        intakeMotor.setDirection(DcMotor.Direction.FORWARD);

        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        stop();
    }
//roll in has full power forward, roll out has full power backwards. Stop sets the power to zero
    public void rollIn() {
        intakeMotor.setPower(1);
    }

    public void rollOut() {
        intakeMotor.setPower(-1);
    }

    public void stop() {
        intakeMotor.setPower(0);
    }
//rotate the motor and then stop it
    public void rotate(double rotations) throws InterruptedException {
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        rollIn();
        while (timer.seconds() < 1 / (ROTATIONS_PER_MINUTE / 60) * rotations);
        stop();
    }
}
