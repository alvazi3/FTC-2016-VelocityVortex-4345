package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class Drivetrain {

    private final int TICKS_PER_ROTATION = 1000;
    private final int WHEEL_DIAMETER = 4;   //Inches
    private final int BOT_DIAMETER = 15;    //Inches
    private final double kP = 10.00436;

    private DcMotor leftDriveMotor, rightDriveMotor;

    //creates new drivetrain subsystem from motors
    public Drivetrain(DcMotor leftDriveMotor, DcMotor rightDriveMotor) {
        this.leftDriveMotor = leftDriveMotor;
        this.rightDriveMotor = rightDriveMotor;

        this.leftDriveMotor.setDirection(DcMotor.Direction.FORWARD);
        this.rightDriveMotor.setDirection(DcMotor.Direction.REVERSE);

        leftDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        resetEncoder();
        stop();
    }

    //sets power using tank drive control scheme
    public void tankDrive(double leftPower, double rightPower) {
        leftDriveMotor.setPower(leftPower);
        rightDriveMotor.setPower(rightPower);
    }

    //sets power using arcade drive control scheme
    public void arcadeDrive(double power, double turn) {
        double leftPower = Range.clip(power + turn, -1, 1);
        double rightPower = Range.clip(power - turn, -1, 1);

        tankDrive(leftPower, rightPower);
    }

    //stops motors
    public void stop() {
        tankDrive(0, 0);
    }

    //reset encoder settings
    public void resetEncoder() {
        leftDriveMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDriveMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    //gets position of motors as an int array of size 2
    public int[] getPosition() {
        return new int[] {
                leftDriveMotor.getCurrentPosition(),
                rightDriveMotor.getCurrentPosition()
        };
    }

    //drives forward a certain distance
    public void driveToP(double position) {
        double rotations = position / (WHEEL_DIAMETER * Math.PI);
        double[] power = {1, 1};
        double[] error = {rotations * TICKS_PER_ROTATION, rotations * TICKS_PER_ROTATION};

        resetEncoder();

        while(Math.abs(leftDriveMotor.getCurrentPosition()) < Math.abs(rotations * TICKS_PER_ROTATION) && Math.abs(rightDriveMotor.getCurrentPosition()) < Math.abs(rotations * TICKS_PER_ROTATION)) {
            error[0] = rotations * TICKS_PER_ROTATION - leftDriveMotor.getCurrentPosition();
            error[1] = rotations * TICKS_PER_ROTATION - rightDriveMotor.getCurrentPosition();

            power[0] = Range.clip(error[0] * kP, -1, 1);
            power[1] = Range.clip(error[1] * kP, -1, 1);

            tankDrive(power[0], power[1]);
        }

        stop();
    }

    //rotates to certain angle
    public void turnToP(double angle) {
        resetEncoder();
        double rotations = angle / 180 * 3.9;
        double power = 1;
        double error = rotations * TICKS_PER_ROTATION;

        resetEncoder();

        while(Math.abs(leftDriveMotor.getCurrentPosition()) < Math.abs(rotations * TICKS_PER_ROTATION)) {
            error = rotations * TICKS_PER_ROTATION - leftDriveMotor.getCurrentPosition();

            power = Range.clip(error * kP, -1, 1);

            tankDrive(power, 0);
        }

        stop();
    }
}
