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
    private DcMotor buttonPusher;

    private boolean isLedLit;

    public BeaconActivator(ColorSensor rgbSensor, DeviceInterfaceModule cdim, LED onboardLed, DcMotor buttonPusher) {
        this.cdim = cdim;
        this.rgbSensor = rgbSensor;
        this.onboardLed = onboardLed;
        this.buttonPusher = buttonPusher;

        isLedLit = false;

        onboardLed.enable(false);
    }

    public void push() {
	buttonPusher.setPower(.5);
    }
    public void pull() {
        buttonPusher.setPower(-.5);
    }
    public void stop() {
        buttonPusher.setPower(0);
    }
    public void pullIn() {
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        pull();
        while (timer.seconds() < TIME_TO_EXTEND);
        stop();
    }
    public void pushOut() {
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        push();
        while (timer.seconds() < TIME_TO_EXTEND);
        stop();
    }
    public void pushButton() {
        pushOut();
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        while (timer.seconds() < 0.25);
        pullIn();
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

    public void doBeacon(boolean onTeamRed) {
	    pushButton();
	    if(isRed() != onTeamRed) {
	        ElapsedTime timer = new ElapsedTime();
            timer.reset();
	        while (timer.seconds() < 8);
	        pushButton();
        }
    }
}
