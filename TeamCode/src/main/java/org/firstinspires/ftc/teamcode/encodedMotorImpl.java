package org.firstinspires.ftc.teamcode;

/**
 * Levels of abstraction at this point:
 *   at least 64
 */

public abstract class encodedMotorImpl implements encodedMotor {
    public boolean isEncoded = false;
    public int targetEncoCount = 0;

    public void prepareForEncoderDrive(int target){
        this.targetEncoCount = target;
        isEncoded = true;
    }

    public void clearEncoderState(){
        this.targetEncoCount = 0;
        isEncoded = false;
    }

}
