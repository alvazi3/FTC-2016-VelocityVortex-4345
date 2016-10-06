package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class Intake {

    private DcMotor intakeMotor;

    public Intake(DcMotor intakeMotor) {
        this.intakeMotor = intakeMotor;

        this.intakeMotor.setDirection(DcMotor.Direction.FORWARD);

        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void rollIn() {
        intakeMotor.setPower(1);
    }

    public void rollOut() {
        intakeMotor.setPower(-1);
    }

    public void stop() {
        intakeMotor.setPower(0);
    }
}
