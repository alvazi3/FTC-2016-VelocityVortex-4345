package org.firstinspires.ftc.teamcode.subsystems;
//imports info for DC motors
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import static java.lang.Thread.sleep;
//Creating class
public class Catapult {
//Says how many ticks there are per rotation
    private static final double TICKS_PER_ROTATION = 1000;
//Defines the DCMotor
    private DcMotor chooChooMotor;
    private boolean isHolding;
//set direction
    public Catapult(DcMotor chooChooMotor) {
        this.chooChooMotor = chooChooMotor;
        this.chooChooMotor.setDirection(DcMotor.Direction.FORWARD);
//if "isHolding" is false, reset the encoder and stop
        isHolding = false;
        resetEncoder();
        stop();
    }
//When the motor is to rotate, run the encoder and set power to 0.5
    public void rotate() {
        isHolding = false;
        chooChooMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        chooChooMotor.setPower(0.5);
    }
//when the motor is to stop, set the power to zero
    public void stop() {
        chooChooMotor.setPower(0);
    }
//then reset the encoder
    public void resetEncoder() {
        isHolding = false;
        chooChooMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        chooChooMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
//when the ball is catapulted, double the rotations and power, then reset the encoder
    public void catapultBall(double rotations, double power) {
        isHolding = false;
        resetEncoder();
//when the current position is less that the ticks per rotation times the rotations, move the motor forward, then hold the position
        while(chooChooMotor.getCurrentPosition() < TICKS_PER_ROTATION * rotations) {
            chooChooMotor.setPower(power);
        }

        holdPosition();
    }
//"holdPosition": reset the encoder, run to position, and then set target position to zero
    public void holdPosition() {
        isHolding = true;
        chooChooMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        chooChooMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        chooChooMotor.setTargetPosition(0);
    }
//get position and return the motor
    public int getPosition() {
        return chooChooMotor.getCurrentPosition();
    }
//its holding
    public boolean isHolding() {
        return isHolding;
    }
}
