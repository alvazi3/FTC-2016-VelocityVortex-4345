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

    public int[] getCurrentPosition() {
        return new int[] {
                leftDriveMotor.getCurrentPosition(),
                rightDriveMotor.getCurrentPosition()
        };
    }

    public void resetEncoders() {
        leftDriveMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDriveMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void driveTo(double position) {
        leftDriveMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDriveMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        double rotations = position / (WHEEL_DIAMETER * Math.PI);

        leftDriveMotor.setTargetPosition((int) (position * TICKS_PER_ROTATION));
        rightDriveMotor.setTargetPosition((int) (position * TICKS_PER_ROTATION));

        while(Math.abs(rotations * TICKS_PER_ROTATION - leftDriveMotor.getCurrentPosition()) > ACCEPTABLE_THRESHOLD || Math.abs(rotations * TICKS_PER_ROTATION - rightDriveMotor.getCurrentPosition()) > ACCEPTABLE_THRESHOLD);

        leftDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void turnTo(double angle) {
        leftDriveMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDriveMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        double rotations = (BOT_DIAMETER / WHEEL_DIAMETER) * (-1 * angle / 360);

        leftDriveMotor.setTargetPosition((int) (rotations * TICKS_PER_ROTATION));
        rightDriveMotor.setTargetPosition((int) (rotations * TICKS_PER_ROTATION));

        while(Math.abs(rotations * TICKS_PER_ROTATION - leftDriveMotor.getCurrentPosition()) > ACCEPTABLE_THRESHOLD || Math.abs(rotations * TICKS_PER_ROTATION - rightDriveMotor.getCurrentPosition()) > ACCEPTABLE_THRESHOLD);

        leftDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
