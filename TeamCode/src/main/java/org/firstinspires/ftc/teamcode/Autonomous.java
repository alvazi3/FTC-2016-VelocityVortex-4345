package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.subsystems.*;
/**
 * Created by zacharyabraham on 10/3/16.
 */

public class Autonomous extends OpMode {
    private BeaconActivator beaconActivator;

    @Override
    public void init(){
        beaconActivator = new BeaconActivator(hardwareMap.colorSensor.get("color_sensor"), hardwareMap.deviceInterfaceModule.get("cdim"), 15 );
    }
    @Override
    public void start(){
        
    }
    @Override
    public void loop(){
    }
    @Override
    public void stop(){

    }
}
