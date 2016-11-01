package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;
/**
 * Created by member on 10/17/16.
 */
public class ChooChoo {

    private static final int TICKS_PER_ROTATION = 1440;

    private DcMotor chooChooMotor;

    public ChooChoo(DcMotor chooChooMotor){
        this.chooChooMotor = chooChooMotor;
        this.chooChooMotor.setDirection(DcMotor.Direction.FORWARD);
        chooChooMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        chooChooMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void catapultBall(double motorPower){
        chooChooMotor.setPower(motorPower);
    }

    public void rotate(double rotations) {
        chooChooMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        catapultBall(1);
        while (chooChooMotor.getCurrentPosition() < rotations * TICKS_PER_ROTATION);
        stop();
    }

    public void stop(){
        chooChooMotor.setPower(0);
    }
}
