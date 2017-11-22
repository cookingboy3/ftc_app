package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by jlemke.
 */

abstract class encodedMotor implements DcMotor{
    public boolean isEncoded;
    public int targetEncoCount;

    public void prepareForEncoderDrive(int target){
        this.targetEncoCount = target;
        isEncoded = true;
    }

    public void clearEncoderState(){
        this.targetEncoCount = 0;
        isEncoded = false;
    }
}
