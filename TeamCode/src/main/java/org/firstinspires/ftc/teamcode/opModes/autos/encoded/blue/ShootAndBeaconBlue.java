package org.firstinspires.ftc.teamcode.opModes.autos.encoded.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.subsystems.Catapult;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

@Autonomous(name="ENCODED: Shoot/Beacon Auto Blue", group="legend27")
public class ShootAndBeaconBlue extends LinearOpMode {

    //sets team to blue
    private boolean redTeam = false;

    private ElapsedTime timer;

    private Catapult catapult;
    private Drivetrain drivetrain;
    private Intake intake;
    private ColorSensor sensor;
    private DeviceInterfaceModule dim;
    private LED led;

    //initiates subsystems and sensors - unused?
    /*public void setUp() {
        timer = new ElapsedTime();
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("left_drive"), hardwareMap.dcMotor.get("right_drive"));
        intake = new Intake(hardwareMap.dcMotor.get("intake"));
        catapult = new Catapult(hardwareMap.dcMotor.get("catapult"));
        dim = hardwareMap.deviceInterfaceModule.get("dim");
        sensor = hardwareMap.colorSensor.get("sensor");
        led = hardwareMap.led.get("led");
    }*/

    //initiates subsystems
    @Override
    public void runOpMode() throws InterruptedException {
        timer = new ElapsedTime();

        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("left_drive"), hardwareMap.dcMotor.get("right_drive"));
        intake = new Intake(hardwareMap.dcMotor.get("intake"));
        catapult = new Catapult(hardwareMap.dcMotor.get("catapult"));

        //waits for start button press
        waitForStart();

        //shoots 2 balls with 2 second delay in between
        catapult.catapultBall(1.05, .25);

        timer.reset();
        while (timer.seconds() < 2 && opModeIsActive()) ;

        catapult.catapultBall(1.05, .25);

        //drives forward 30 inches
        drivetrain.driveToP(30);

        //waits 1 second
        timer.reset();
        while (timer.seconds() < 1) ;

        //turns to heading -75 deg
        drivetrain.turnToP(-75);

        //wait 1 second
        timer.reset();
        while (timer.seconds() < 1) ;

        //drives forward 45 inches
        drivetrain.driveToP(45);

        //wait 1 second
        timer.reset();
        while (timer.seconds() < 1) ;

        //turn to heading -65 deg
        drivetrain.turnToP(-65);

        //wait 0.5 seconds
        timer.reset();
        while (timer.seconds() < 0.5) ;

        //drives forward 14 inches
        drivetrain.driveToP(14);

        //wait 0.5 seconds
        timer.reset();
        while (timer.seconds() < 0.5) ;

        //drives backwards 2 inches
        drivetrain.driveToP(-2);

        //wait 0.5 seconds
        timer.reset();
        while (timer.seconds() < 0.5) ;

        //check if beacon is right color
        if (sensor.red() > sensor.blue() != redTeam) {
            drivetrain.driveToP(4);
        }

        //drives backwards 48 inches
        drivetrain.driveToP(-48);
    }
}
