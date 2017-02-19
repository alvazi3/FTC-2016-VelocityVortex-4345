package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/*
 * IMPORTANT TO REMEMBER FOR COLOR SENSOR:
 * -I2C must be plugged in so that the black cable is aligned with black mark on CDIM
 * -LED cable should be plugged into the pin furthest from the black mark on the CDIM
 * -Must be registered as an "Adafruit Color Sensor" in hardware map
 */


public class BeaconActivator {

    private final double TIME_TO_EXTEND = 0.6;

    private ColorSensor rgbSensor;
    private LED onboardLed;
    private DeviceInterfaceModule cdim;

    private boolean isLedLit;

    public BeaconActivator(ColorSensor rgbSensor, DeviceInterfaceModule cdim, LED onboardLed) {
        this.cdim = cdim;
        this.rgbSensor = rgbSensor;
        this.onboardLed = onboardLed;

        isLedLit = false;

        onboardLed.enable(false);
    }

    public boolean isRed() {
        return rgbSensor.red() > rgbSensor.blue();
    }

    public void setLed(boolean lit) {
        isLedLit = lit;
        onboardLed.enable(isLedLit);
    }

    public boolean isLedLit() {
        return isLedLit;
    }

    public boolean toggleLed() {
        onboardLed.enable(!isLedLit);
        isLedLit = !isLedLit;
        return isLedLit;
    }

    public int[] getRawColors() {   //Values range 0-255
        return new int[]{
            rgbSensor.alpha() * 255 / 800,
            rgbSensor.red() * 255 / 800,
            rgbSensor.green() * 255 / 800,
            rgbSensor.blue() * 255 / 800
        };
    }
}
