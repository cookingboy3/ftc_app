package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Auto Blue NonRelRec", group="Linear Auto")
public class NonRelRecSideBlue extends AutoMaster {
    @Override
    public void runOpMode() {
        VuforiaPlagiarism vu = new VuforiaPlagiarism();
        VuforiaPlagiarism.type typee = VuforiaPlagiarism.type.ERROR;
        double vufSpeed = 0;
        typee = vu.getVuf(hardwareMap);

        robot.init(hardwareMap);
        waitForStart();
        telemetry.addData("skatin fast,", "eatin' ass");

        //Knocks off the right ball
        robot.arm.setPosition(CSENSOR_ARM_DOWN);
        wait(750);
        if (robot.color.red() > 0) {
            encode(5, 0.25, MoveType.ROT);
            robot.arm.setPosition(CSENSOR_ARM_UP);
            wait(1000);
            encode(5, -0.25, MoveType.ROT);
        } else {
            encode(5, -0.25, MoveType.ROT);
            robot.arm.setPosition(CSENSOR_ARM_UP);
            wait(1000);
            encode(5, 0.25, MoveType.ROT);
        }

        //Finds Vuforia
        if (typee == VuforiaPlagiarism.type.RIGHT) {
            vufSpeed = 0.5;
        } else if (typee == VuforiaPlagiarism.type.CENTER) {
            vufSpeed = 0;
        } else if (typee == VuforiaPlagiarism.type.LEFT) {
            vufSpeed = -0.5;
        } else {
            vufSpeed = 0;
        }

        //Lines up the proper box
        encode(25, -0.5, MoveType.STRAIGHT);
        encode(57, 0.5, MoveType.ROT);
        encode(13.5, 0.5, MoveType.STRAIGHT);
        encode(19, -0.5, MoveType.ROT);
        if (vufSpeed != 0) {
            encode(VUF_DISTANCE, vufSpeed, MoveType.LATERALLY);
        }

        //Final approach
        encode(12, 0.5, MoveType.STRAIGHT);

        //Place block
        long startTime = System.currentTimeMillis();
        robot.rotateBlockGrabber(Hardware750.Direction.OUT);
        robot.rlDrive.setPower(-0.15);
        robot.rrDrive.setPower(-0.15);
        robot.flDrive.setPower(-0.15);
        robot.frDrive.setPower(-0.15);
        while (System.currentTimeMillis() < (startTime + 2000)) {

        }
        robot.setAllDriveMotors(0);
        robot.rotateBlockGrabber(Hardware750.Direction.STOP);
        encode(4, 0.2, MoveType.STRAIGHT);
    }
}
