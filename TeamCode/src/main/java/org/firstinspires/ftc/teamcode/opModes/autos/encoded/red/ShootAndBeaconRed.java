package org.firstinspires.ftc.teamcode.opModes.autos.encoded.red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Catapult;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;

@Autonomous(name="ENCODED: Shoot/Beacon Auto Red", group="legend27")
public class ShootAndBeaconRed extends LinearOpMode {

    private boolean redTeam = true;

    private ElapsedTime timer;

    private Catapult catapult;
    private Drivetrain drivetrain;
    private Intake intake;
    private ColorSensor sensor;
    private DeviceInterfaceModule dim;
    private LED led;

    //unused
    /*public void setUp() {
        timer = new ElapsedTime();
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("left_drive"), hardwareMap.dcMotor.get("right_drive"));
        intake = new Intake(hardwareMap.dcMotor.get("intake"));
        catapult = new Catapult(hardwareMap.dcMotor.get("catapult"));
        dim = hardwareMap.deviceInterfaceModule.get("dim");
        sensor = hardwareMap.colorSensor.get("sensor");
        led = hardwareMap.led.get("led");
    }*/

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        catapult.catapultBall(1.05, .25);

        timer.reset();
        while(timer.seconds() < 2 && opModeIsActive());

        catapult.catapultBall(1.05, .25);

        drivetrain.driveToP(30);

        timer.reset();
        while(timer.seconds() < 1);

        drivetrain.turnToP(-75);

        timer.reset();
        while(timer.seconds() < 1);

        drivetrain.driveToP(45);

        timer.reset();
        while(timer.seconds() < 1);

        drivetrain.turnToP(-65);

        timer.reset();
        while (timer.seconds() < 0.5);

        drivetrain.driveToP(14);

        timer.reset();
        while (timer.seconds() < 0.5);

        drivetrain.driveToP(-2);

        timer.reset();
        while (timer.seconds() < 0.5);

        if (sensor.red() > sensor.blue() != redTeam) {
            drivetrain.driveToP(4);
        }

        drivetrain.driveToP(-48);

    }
}
