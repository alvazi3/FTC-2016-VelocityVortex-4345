package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import static java.lang.Thread.sleep;

public class ChooChoo {

    private static final double ROTATIONS_PER_MINUTE = 147;

    private DcMotor chooChooMotor;

    public ChooChoo(DcMotor chooChooMotor) {
        this.chooChooMotor = chooChooMotor;

        this.chooChooMotor.setDirection(DcMotor.Direction.FORWARD);

        chooChooMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void catapultBall() {
        chooChooMotor.setPower(1);
    }

    public void stop() {
        chooChooMotor.setPower(0);
    }

    public void rotate(double rotations) throws InterruptedException {
        catapultBall();
        sleep((long)(1000 * (1 / (ROTATIONS_PER_MINUTE / 60) * rotations)));
        stop();
    }
}
