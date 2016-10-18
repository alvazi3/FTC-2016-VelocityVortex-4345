package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.Servo;

public class BeaconActivator {

    private final double SERVO_LEFT_POSITION = 0, SERVO_RIGHT_POSITION = 1;

    private ColorSensor rgbSensor;
    private DeviceInterfaceModule cdim;
    private Servo buttonPusher;

    private int ledChannel;
    private boolean isLedLit;

    public BeaconActivator(ColorSensor rgbSensor, DeviceInterfaceModule cdim, int ledChannel, Servo buttonPusher) {
        this.rgbSensor = rgbSensor; 
        this.cdim = cdim;
        this.ledChannel = ledChannel;
        this.buttonPusher = buttonPusher;

        isLedLit = false;

        cdim.setDigitalChannelMode(this.ledChannel, DigitalChannelController.Mode.OUTPUT);
        cdim.setDigitalChannelState(this.ledChannel, isLedLit);

        buttonPusher.scaleRange(SERVO_LEFT_POSITION, SERVO_RIGHT_POSITION);
    }

    public void activateButton(boolean isTeamRed) {
        if(isRed()) {
            setLeft();
        }
        else {
            setRight();
        }
    }

    public void setLeft(){
        buttonPusher.setPosition(0);
    }
    public void setRight(){
        buttonPusher.setPosition(1);
    }
    public void setCenter(){
        buttonPusher.setPosition(0.5);
    }

    public boolean isRed() {
        return rgbSensor.red() > rgbSensor.blue();
    }

    public void setLed(boolean lit) {
        isLedLit = lit;
        cdim.setDigitalChannelState(ledChannel, isLedLit);
    }

    public boolean isLedLit() {
        return isLedLit;
    }

    public boolean toggleLed() {
        cdim.setDigitalChannelState(ledChannel, !isLedLit);
        isLedLit = !isLedLit;
        return isLedLit;
    }

    public int[] getRawColors() {
        return new int[]{
            rgbSensor.alpha(),
            rgbSensor.red(),
            rgbSensor.green(),
            rgbSensor.blue()
        };
    }
}
