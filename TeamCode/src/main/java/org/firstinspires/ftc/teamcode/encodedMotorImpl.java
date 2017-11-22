package org.firstinspires.ftc.teamcode;

import android.support.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorControllerEx;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
import com.qualcomm.robotcore.hardware.configuration.MotorConfigurationType;
import com.qualcomm.robotcore.util.RobotLog;

/**
 * Levels of abstraction at this point:
 *   at least 64
 */

public class encodedMotorImpl extends DcMotorImpl implements encodedMotor {
    public boolean isEncoded = false;
    public int targetEncoCount = 0;

    protected DcMotorController            controller = null;
    protected int                          portNumber = -1;
    protected Direction                    direction  = Direction.FORWARD;
    protected MotorConfigurationType       motorType  = null;

    //------------------------------------------------------------------------------------------------
    // Construction
    //------------------------------------------------------------------------------------------------

    public encodedMotorImpl(DcMotorController controller, int portNumber)
    {
        this(controller, portNumber, Direction.FORWARD);
    }

    public encodedMotorImpl(DcMotorController controller, int portNumber, Direction direction)
    {
        this(controller, portNumber, direction, MotorConfigurationType.getUnspecifiedMotorType());
    }

    public encodedMotorImpl(DcMotorController controller, int portNumber, Direction direction, @NonNull MotorConfigurationType motorType)
    {
        super(controller, portNumber, direction, motorType);
        this.controller = (DcMotorControllerEx)controller;
    }

    public void prepareForEncoderDrive(int target){
        this.targetEncoCount = target;
        isEncoded = true;
    }

    public void clearEncoderState(){
        this.targetEncoCount = 0;
        isEncoded = false;
    }

}
