package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;

public class BeaconActivator {

    private ColorSensor rgbSensor;
    private DeviceInterfaceModule cdim;

    private int ledChannel;
    private boolean isLedLit;

    public BeaconActivator(ColorSensor rgbSensor, DeviceInterfaceModule cdim, int ledChannel) {
        this.rgbSensor = rgbSensor; 
        this.cdim = cdim;
        this.ledChannel = ledChannel;

        isLedLit = false;

        cdim.setDigitalChannelMode(this.ledChannel, DigitalChannelController.Mode.OUTPUT);
        cdim.setDigitalChannelState(this.ledChannel, isLedLit);
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
