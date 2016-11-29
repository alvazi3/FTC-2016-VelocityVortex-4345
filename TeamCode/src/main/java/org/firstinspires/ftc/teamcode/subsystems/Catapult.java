package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import static java.lang.Thread.sleep;

public class Catapult {

    private static final double TICKS_PER_ROTATION = 1000;

    private DcMotor chooChooMotor;
    private boolean isHolding;

    public Catapult(DcMotor chooChooMotor) {
        this.chooChooMotor = chooChooMotor;
        this.chooChooMotor.setDirection(DcMotor.Direction.FORWARD);

        isHolding = false;
        resetEncoder();
        stop();
    }

    public void rotate() {
        isHolding = false;
        chooChooMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        chooChooMotor.setPower(0.5);
    }

    public void stop() {
        chooChooMotor.setPower(0);
    }

    public void resetEncoder() {
        isHolding = false;
        chooChooMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        chooChooMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void catapultBall(double rotations, double power) {
        isHolding = false;
        resetEncoder();

        while(chooChooMotor.getCurrentPosition() < TICKS_PER_ROTATION * rotations) {
            chooChooMotor.setPower(power);
        }

        holdPosition();
    }

    public void holdPosition() {
        isHolding = true;
        chooChooMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        chooChooMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        chooChooMotor.setTargetPosition(0);
    }

    public int getPosition() {
        return chooChooMotor.getCurrentPosition();
    }

    public boolean isHolding() {
        return isHolding;
    }
}
