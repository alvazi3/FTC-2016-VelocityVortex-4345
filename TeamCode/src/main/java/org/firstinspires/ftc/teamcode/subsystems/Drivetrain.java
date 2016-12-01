package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class Drivetrain {

    private final int TICKS_PER_ROTATION = 1000;
    private final int WHEEL_DIAMETER = 4;   //Inches
    private final int BOT_DIAMETER = 15;    //Inches
    private final double ACCEPTABLE_THRESHOLD = 1;  //Inches

    private DcMotor leftDriveMotor, rightDriveMotor;

    public Drivetrain(DcMotor leftDriveMotor, DcMotor rightDriveMotor) {
        this.leftDriveMotor = leftDriveMotor;
        this.rightDriveMotor = rightDriveMotor;

        this.leftDriveMotor.setDirection(DcMotor.Direction.FORWARD);
        this.rightDriveMotor.setDirection(DcMotor.Direction.REVERSE);

        leftDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void tankDrive(double leftPower, double rightPower) {
        leftDriveMotor.setPower(leftPower);
        rightDriveMotor.setPower(rightPower);
    }

    public void arcadeDrive(double power, double turn) {
        double leftPower = Range.clip(power + turn, -1, 1);
        double rightPower = Range.clip(power - turn, -1, 1);

        tankDrive(leftPower, rightPower);
    }

    public void stop() {
        tankDrive(0, 0);
    }

    public void resetEncoders() {
        leftDriveMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public int[] getPosition() {
        int[] output = {leftDriveMotor.getCurrentPosition(), rightDriveMotor.getCurrentPosition()};
        return output;
    }

    public void driveTo(double position) {
        resetEncoders();
        double rotations = position / (WHEEL_DIAMETER * Math.PI);

        arcadeDrive(-0.25, 0);

        while(rotations * TICKS_PER_ROTATION - Math.abs(leftDriveMotor.getCurrentPosition()) > ACCEPTABLE_THRESHOLD && rotations * TICKS_PER_ROTATION - Math.abs(rightDriveMotor.getCurrentPosition()) > ACCEPTABLE_THRESHOLD);

        stop();
    }

    public void turnTo(double angle) {
        resetEncoders();
        double rotations = (BOT_DIAMETER / WHEEL_DIAMETER) * (-1 * angle / 360);

        if (angle > 0) {
            tankDrive(-0.5, 0.5);
        } else {
            tankDrive(0.5, -0.5);
        }

        while(Math.abs(rotations * TICKS_PER_ROTATION - leftDriveMotor.getCurrentPosition()) > ACCEPTABLE_THRESHOLD && rotations * TICKS_PER_ROTATION - Math.abs(rightDriveMotor.getCurrentPosition()) > ACCEPTABLE_THRESHOLD);

        stop();
    }
}
