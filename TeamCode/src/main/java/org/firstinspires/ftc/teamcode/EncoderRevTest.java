/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.encodedMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="more encoder tests", group="Test")
public class EncoderRevTest extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    HardwareTest robot = new HardwareTest();

    private final int PULSES_PER_REVOLUTION = 280; // on-board encoder for AndyMark motors has 7 ppr on a 40:1 gearbox
    private final double ENCODED_POWER = 0.25;

    @Override
    public void init(){
        robot.init(hardwareMap);
        robot.test.clearEncoderState();
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
        if(gamepad1.a){
            robot.test.prepareForEncoderDrive(280); //run encoder to 1 revolution
        }

        robot.test.loopMotor(ENCODED_POWER);
    }

    @Override
    public void stop() {
    }

    /**
     * Sets our encoder to run to a specific count of revolutions
     * within an _iterative_ OpMode
     *
     * @param revolutions number of revolutions to try and turn the motor to
     * @param motor       DcMotor to try and move.
     */
    private void setEncoderRunToRev(int revolutions, encodedMotor motor){
        motor.prepareForEncoderDrive(revolutions * PULSES_PER_REVOLUTION);
    }

    private void encoderLoop(encodedMotor motor){
        if (motor.isEncoded){
            if (Math.abs(motor.getCurrentPosition()) < Math.abs(motor.targetEncoCount)){ // we haven't reached target count yet.
                if (motor.targetEncoCount > 0){ // if encoder count is positive...
                    motor.setPower(ENCODED_POWER);
                } else { // if encoder count is negative
                    motor.setPower(ENCODED_POWER * -1);
                }
            } else { // we've reached our target. stop and clean up.
                motor.setPower(0);
                motor.clearEncoderState();
            }
        }
    }
}
