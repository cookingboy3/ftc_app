package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.LinkedList;
import java.util.List;


/**
 * ToggleableControl defines a toggleable control class and a method to check through all toggleable
 * keys for changes.
 */

public class ToggleableControl {
    private ElapsedTime runtime = new ElapsedTime();
    List registeredToggleables = new LinkedList();

    // we'll just borrow these :)
    private Gamepad gamepad1, gamepad2;

    /**
     * General constructor to give an opmode it's P O W E R.
     */
    public void ToggleableControl (Gamepad gamepad1, Gamepad gamepad2){
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }


    public void Toggleable(Gamepad key, Servo servo, double open_pos, double close_pos) {

    }
    class servoToggleable{




        public servoToggleable(boolean key, Servo servo, double open_pos, double close_pos){

            // add ourselves to the list of toggleables
            registeredToggleables.add(this);
        }
        private void checkState(){
            if () {}

        }

    }

    /**
     * Iterates through the list of added toggleables, and checks their
     * state. If their state has changed, force a change.
     */
    static void checkToggleables(){

    }
}
