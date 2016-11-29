package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import static java.lang.Thread.sleep;

public class Intake {

    private static final double ROTATIONS_PER_MINUTE = 147;

    private DcMotor intakeMotor;

    public Intake(DcMotor intakeMotor) {
        this.intakeMotor = intakeMotor;

        intakeMotor.setDirection(DcMotor.Direction.FORWARD);

        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        stop();
    }

    public void rollIn() {
        intakeMotor.setPower(1);
    }

    public void rollOut() {
        intakeMotor.setPower(-1);
    }

    public void stop() {
        intakeMotor.setPower(0);
    }

    public void rotate(double rotations) throws InterruptedException {
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        rollIn();
        while (timer.seconds() < 1 / (ROTATIONS_PER_MINUTE / 60) * rotations);
        stop();
    }
}
