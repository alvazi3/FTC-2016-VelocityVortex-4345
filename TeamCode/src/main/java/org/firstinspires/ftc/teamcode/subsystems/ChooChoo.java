package org.firstinspires.ftc.teamcode.subsystems;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;
/**
 * Created by member on 10/17/16.
 */
public class ChooChoo {

    private DcMotor chooChooMotor;

    public ChooChoo(DcMotor chooChooMotor){
        this.chooChooMotor = chooChooMotor;
        this.chooChooMotor.setDirection(DcMotor.Direction.FORWARD);
        chooChooMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void catapultBall(double motorPower){
        chooChooMotor.setPower(motorPower);
    }

    public void stop(){
        chooChooMotor.setPower(0);
    }
}
