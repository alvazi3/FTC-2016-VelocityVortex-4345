package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Unnamed4345: Beacon Activator Test", group="unnamed4345")

public class DrivetrainTest extends OpMode {

    private Drivetrain drivetrain;

    @Override
    public void init() {
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("left_drive"), hardwareMap.dcMotor.get("right_drive"));
        drivetrain.stop();
    }

    @Override
    public void loop() {
        drivetrain.driveTo(12);
    }
}
