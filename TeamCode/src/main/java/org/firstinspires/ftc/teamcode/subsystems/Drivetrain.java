package org.firstinspires.ftc.teamcode.subsystems;
//import encoders
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
//define class
public class Drivetrain {
//define set ticks per rotation to 1000, state the the wheel diameter is 4in, and that the bot diameter is 16in
    private final int TICKS_PER_ROTATION = 1000;
    private final int WHEEL_DIAMETER = 4;   //Inches
    private final int BOT_DIAMETER = 15;    //Inches
    private final double kP = 10.00436;
//this class contains a left and right DcMotor
    private DcMotor leftDriveMotor, rightDriveMotor;
//define motors
    public Drivetrain(DcMotor leftDriveMotor, DcMotor rightDriveMotor) {
        this.leftDriveMotor = leftDriveMotor;
        this.rightDriveMotor = rightDriveMotor;
//set the left motor to forward, and the right one to deverse
        this.leftDriveMotor.setDirection(DcMotor.Direction.FORWARD);
        this.rightDriveMotor.setDirection(DcMotor.Direction.REVERSE);

        leftDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        resetEncoder();
        stop();
    }
//in tank dive, double the left and right motos
    public void tankDrive(double leftPower, double rightPower) {
        leftDriveMotor.setPower(leftPower);
        rightDriveMotor.setPower(rightPower);
    }
//in arcade drive, double power and turn. Set the range of the two motors (-1 - 1)
    public void arcadeDrive(double power, double turn) {
        double leftPower = Range.clip(power + turn, -1, 1);
        double rightPower = Range.clip(power - turn, -1, 1);

        tankDrive(leftPower, rightPower);
    }
//set the tank drive to 0,0
    public void stop() {
        tankDrive(0, 0);
    }
//reset the encoder: reset the encoder for both motors and then restart it
    public void resetEncoder() {
        leftDriveMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDriveMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDriveMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
//get the current position for both motors
    public int[] getPosition() {
        return new int[] {
                leftDriveMotor.getCurrentPosition(),
                rightDriveMotor.getCurrentPosition()
        };
    }
//"driveTop": double the position. The rotation is the wheel position / the wheel diameter * Pi. Bothe wheels are set to full power forward. An error is rotations * ticks per rotation for both
    public void driveToP(double position) {
        double rotations = position / (WHEEL_DIAMETER * Math.PI);
        double[] power = {1, 1};
        double[] error = {rotations * TICKS_PER_ROTATION, rotations * TICKS_PER_ROTATION};

        resetEncoder();
//while the position of both motors is less than Math.bs(rotations * ticks per rotation, move the motor to get it back into position and the stop it
        while(Math.abs(leftDriveMotor.getCurrentPosition()) < Math.abs(rotations * TICKS_PER_ROTATION) && Math.abs(rightDriveMotor.getCurrentPosition()) < Math.abs(rotations * TICKS_PER_ROTATION)) {
            error[0] = rotations * TICKS_PER_ROTATION - leftDriveMotor.getCurrentPosition();
            error[1] = rotations * TICKS_PER_ROTATION - rightDriveMotor.getCurrentPosition();

            power[0] = Range.clip(error[0] * kP, -1, 1);
            power[1] = Range.clip(error[1] * kP, -1, 1);

            tankDrive(power[0], power[1]);
        }

        stop();
    }

//"turnTop" reset the encoder then have the rotations = the angle/ 180*3.9. Then reset the encoder
    public void turnToP(double angle) {
        resetEncoder();
        double rotations = angle / 180 * 3.9;
        double power = 1;
        double error = rotations * TICKS_PER_ROTATION;

        resetEncoder();
//while the position of the left motor is less than Math.abs(roations * ticks per rotation, move the motor into position and then stop it
        while(Math.abs(leftDriveMotor.getCurrentPosition()) < Math.abs(rotations * TICKS_PER_ROTATION)) {
            error = rotations * TICKS_PER_ROTATION - leftDriveMotor.getCurrentPosition();

            power = Range.clip(error * kP, -1, 1);

            tankDrive(power, 0);
        }

        stop();
    }
}
