package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import static java.lang.Thread.sleep;

public class ChooChoo {

    private static final double TICKS_PER_ROTATION = 1000;

    private DcMotor chooChooMotor;

    public ChooChoo(DcMotor chooChooMotor) {
        this.chooChooMotor = chooChooMotor;
        this.chooChooMotor.setDirection(DcMotor.Direction.FORWARD);

        resetEncoder();
    }

    public void rotate() {
        chooChooMotor.setPower(0.5);
    }

    public void stop() {
        chooChooMotor.setPower(0);
    }

    public void resetEncoder() {
        chooChooMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        chooChooMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void catapultBall(double rotations) {
        resetEncoder();

        while(chooChooMotor.getCurrentPosition() < TICKS_PER_ROTATION * rotations) {
            chooChooMotor.setPower(0.75);
        }

        chooChooMotor.setPower(0);
    }

    public int getPosition() {
        return chooChooMotor.getCurrentPosition();
    }
}
