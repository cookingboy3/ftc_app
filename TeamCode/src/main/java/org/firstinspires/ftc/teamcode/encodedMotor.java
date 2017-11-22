package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by jlemke.
 */

public interface encodedMotor extends DcMotor{
    /*
    boolean isEncoded = false;
    int targetEncoCount = 0;
    */

    void prepareForEncoderDrive(int target);

    void clearEncoderState();
}
