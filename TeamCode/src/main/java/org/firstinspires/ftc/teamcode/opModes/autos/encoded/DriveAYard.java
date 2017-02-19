package org.firstinspires.ftc.teamcode.opModes.autos.encoded;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

@Autonomous(name="ENCODED: Drive A Yard", group="legend27")
public class DriveAYard extends LinearOpMode {
    private Drivetrain drivetrain;
    private ElapsedTime timer = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        drivetrain = new Drivetrain(hardwareMap.dcMotor.get("left_drive"), hardwareMap.dcMotor.get("right_drive"));
        waitForStart();

        //drivetrain.driveToP(12 * 3);
        drivetrain.turnToP(90);
    }
}

