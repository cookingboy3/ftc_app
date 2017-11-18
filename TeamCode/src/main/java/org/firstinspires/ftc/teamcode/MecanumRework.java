package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import static java.lang.Double.NaN;

/**
 * concept for reworked adv. mecanum drive
 *
 * @author jlemke
 *
 * this opmode is a new take on a lot of our previous ideas.
 * particularly, I wanted to work with understanding <i>magnitude.</i>
 * if we have full control over angle, what's the point of trying to tell
 * the robot to go backwards?
 *
 * l8nite.
 */

@TeleOp(name="Mecanum Rework", group="TeleOp")
public class MecanumRework extends OpMode {
    Hardware750 robot = new Hardware750();
    private ElapsedTime runtime = new ElapsedTime();
    // declare class locals for loop
    double speed = 0;
    double angle = 0;
    double rotate = 0;
    double gp1y;
    double[] voltageMultiplier = {0,0,0,0};

    @Override
    public void init() {
        telemetry.addData("Status", "Uninitialized...");
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        // invert y axis to make sense. if this causes problems, remove the -1 or refactor
        // to get rid of the variable all together. (replace it with gamepad1.left_stick_y)
        gp1y = (-1 * gamepad1.left_stick_y);
        // calculations bloc
        // speed - while our technical value is within the range of [-1,1], we'll just keep it
        // positive.
        if (gamepad1.right_bumper) {
            speed = -1 * gamepad1.right_trigger;
        } else {
            speed = gamepad1.right_trigger;
        }
        // angle - this is calculated using the direction our stick is pointing. should be in a
        // range of [0,2pi] like a unit circle. terrible things, unit circles.
        // this means the stick held right should be 2pi.
        angle = Math.atan2(gp1y, gamepad1.left_stick_x);
        // corrections to ensure our angle is like a unit circle instead of delivering a coordinate
        if (angle <= 0) {
            angle += Math.PI * 2;
        }
        /*
        if (gamepad1.left_stick_x == 0 && gamepad1.left_stick_y == 0){
            angle = (Math.PI)/2;
        }
        */

        // rotation magnitude
        if (gamepad1.left_bumper){
            rotate = gamepad1.left_trigger * -1;
        } else  {
            rotate = gamepad1.left_trigger;
        }
        // alternative rotation mag.
        if (gamepad1.right_stick_x != 0){
            rotate = gamepad1.right_stick_x;
        }


        //DEBUG: dump outputs
        telemetry.addData("REQUIRED INFORMATION", "");
        telemetry.addData("speed:", speed);
        telemetry.addData("angle:", angle);
        telemetry.addData("angle (pi):", angle/Math.PI);
        telemetry.addData("rot. magnitude:", rotate);
        // calc voltage multipliers
        if (gamepad1.left_stick_x == 0 && gamepad1.left_stick_y == 0) {
            speed = 0;
        }
        voltageMultiplier[0] = speed * Math.cos(angle - (Math.PI/4)) + rotate;
        voltageMultiplier[1] = speed * Math.sin(angle - (Math.PI/4)) - rotate;
        voltageMultiplier[2] = speed * Math.sin(angle - (Math.PI/4)) + rotate;
        voltageMultiplier[3] = speed * Math.cos(angle - (Math.PI/4)) - rotate;
        if (rotate == 0) {
            for (int i = 0; i < 4; i++) {
                voltageMultiplier[i] *= Math.sqrt(2);
            }
        }
        //DEBUG: voltage multiplier output
        telemetry.addData("VOLTAGE MULTIPLIERS (unnormalized)", "");
        telemetry.addData("VM1", voltageMultiplier[0]);
        telemetry.addData("VM2", voltageMultiplier[1]);
        telemetry.addData("VM3", voltageMultiplier[2]);
        telemetry.addData("VM4", voltageMultiplier[3]);
        // begin normalization process
        // store the biggest voltage multiplier
        double topStore = 1;
        for (int x = 0; x < 4; x++){
            if (Math.abs(voltageMultiplier[x]) > topStore) {
                topStore = Math.abs(voltageMultiplier[x]);
            }
        }
        // followed by dividing them all by the top one
        // additionally, do a sanity check that ensures we don't actually do this
        // if our top stored number is "0" because that would make NaN and do bad
        // things ;~
        for (int x = 0; x < 4; x++) {
            voltageMultiplier[x] /= topStore;
        }

        telemetry.addData("VOLTAGE MULTIPLIERS (normalized)", "");
        telemetry.addData("VM1", voltageMultiplier[0]);
        telemetry.addData("VM2", voltageMultiplier[1]);
        telemetry.addData("VM3", voltageMultiplier[2]);
        telemetry.addData("VM4", voltageMultiplier[3]);

        if (gamepad1.b) {
            for (int i = 1; i < 4; i++) {
                voltageMultiplier[i] = 0;
            }
        }

        robot.flDrive.setPower(voltageMultiplier[0]);
        robot.frDrive.setPower(voltageMultiplier[1]);
        robot.rlDrive.setPower(voltageMultiplier[2]);
        robot.rrDrive.setPower(voltageMultiplier[3]);

        telemetry.addData("s er v o", robot.arm.getPosition());
    }

    @Override
    public void stop() {
    }
}
