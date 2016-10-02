package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.DigitalChannelController;

public class BeaconActivator {

    private ColorSensor rgbSensor;
    private DeviceInterfaceModule cdim;

    private int ledChannel;

    public BeaconActivator(ColorSensor rgbSensor, DeviceInterfaceModule cdim, int ledChannel) {
        this.rgbSensor = rgbSensor; 
        this.cdim = cdim;
        this.ledChannel = ledChannel;

        cdim.setDigitalChannelMode(this.ledChannel, DigitalChannelController.Mode.OUTPUT);
    }

    public boolean isRed(){
        return rgbSensor.red() > rgbSensor.blue();
    }

}
