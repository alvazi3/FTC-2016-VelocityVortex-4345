package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import static java.lang.Thread.sleep;

public class Catapult {

    private static final double TICKS_PER_ROTATION = 1000;

    private DcMotor chooChooMotor;
    private boolean isHolding;

    //creates catapult subsystem with chooChooMotor as drive motor
    public Catapult(DcMotor chooChooMotor) {
        this.chooChooMotor = chooChooMotor;
        this.chooChooMotor.setDirection(DcMotor.Direction.FORWARD);

        isHolding = false;
        resetEncoder();
        stop();
    }

    //rotates catapult - for bringing into firing position or manually firing
    public void rotate() {
        isHolding = false;
        chooChooMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        chooChooMotor.setPower(0.5);
    }

    //stops motor
    public void stop() {
        chooChooMotor.setPower(0);
    }

    //resets encoder - restarting from untensioned state
    public void resetEncoder() {
        isHolding = false;
        chooChooMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        chooChooMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    //sets catapult arm to position?
    public void catapultBall(double rotations, double power) {
        isHolding = false;
        resetEncoder();
        while(chooChooMotor.getCurrentPosition() < TICKS_PER_ROTATION * rotations) {
            chooChooMotor.setPower(power);
        }

        holdPosition();
    }

    //holds arm at position
    public void holdPosition() {
        isHolding = true;
        chooChooMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        chooChooMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        chooChooMotor.setTargetPosition(0);
    }

    //getter methods
    public int getPosition() {
        return chooChooMotor.getCurrentPosition();
    }

    public boolean isHolding() {
        return isHolding;
    }
}