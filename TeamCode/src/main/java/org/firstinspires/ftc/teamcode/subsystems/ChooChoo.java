package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import static java.lang.Thread.sleep;

public class ChooChoo {

    private static final double ROTATIONS_PER_SECONDS = 147 / 60;

    private DcMotor chooChooMotor;

    public ChooChoo(DcMotor chooChooMotor) {
        this.chooChooMotor = chooChooMotor;

        this.chooChooMotor.setDirection(DcMotor.Direction.FORWARD);

        chooChooMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void rotate() {
        chooChooMotor.setPower(1);
    }

    public void stop() {
        chooChooMotor.setPower(0);
    }

    public void catapultBall(double rotations) {
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        chooChooMotor.setPower(1);
        while(timer.seconds() < rotations / ROTATIONS_PER_SECONDS);
        chooChooMotor.setPower(0);
    }
}
