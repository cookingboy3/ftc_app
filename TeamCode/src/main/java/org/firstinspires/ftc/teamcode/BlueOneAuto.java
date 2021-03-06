package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous(name="Auto Blue One", group="Linear Auto")

public class BlueOneAuto extends AutoMaster {
    @Override
    public void runOpMode() {
        VuforiaPlagiarism vu = new VuforiaPlagiarism();
        VuforiaPlagiarism.type typee = VuforiaPlagiarism.type.ERROR;
        double box;
        robot.init(hardwareMap);
        waitForStart();
        telemetry.addData("skatin fast,", "eatin' ass");
        robot.arm.setPosition(1);
        wait(750);
        if (robot.color.red() > 1) {
            encode(5, 0.25, MoveType.STRAIGHT);
        } else {
            encode(5, -0.25, MoveType.STRAIGHT);
        }
        robot.arm.setPosition(0);
        wait(1000);

       /* typee = vu.getVuf(hardwareMap);
        if (typee == VuforiaPlagiarism.type.RIGHT) {
            box = 38.98;
        } else if (typee == VuforiaPlagiarism.type.CENTER) {
            box = 45.67;
        } else if (typee == VuforiaPlagiarism.type.LEFT) {
            box = 45.7;
        } else {
            box = 45.67;
        } */
        encode(17, -0.5, MoveType.STRAIGHT);
        encode(19, 0.5, MoveType.ROT);
        encode(18, 0.5, MoveType.LATERALLY);
        encode(10, 0.5, MoveType.STRAIGHT); // Ryan is dumb for making me do this for no reason
        wait(500);
        robot.gripper.setPower(-0.25);
        wait(1000);
        robot.gripper.setPower(0);
        encode(4, -0.25, MoveType.STRAIGHT);
        encode(5, 0.1, MoveType.STRAIGHT);
        encode(5, -0.2, MoveType.STRAIGHT);
    }
}
