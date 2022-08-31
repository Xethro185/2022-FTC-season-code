package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robots.Robot;

@Autonomous

public class TurnDegrees extends LinearOpMode {
    Robot turnbot = new Robot();

    double ticksperdegree = 12.54;
    int degrees = 90;
    boolean leftright = true;
    int ticks = (int) (ticksperdegree*degrees);


    @Override
    public void runOpMode() throws InterruptedException {

        turnbot.init(hardwareMap);
        turnbot.ResetEncoders();

        if (leftright) {
            telemetry.addData("turning","right");
            telemetry.update();
        }
        else {
            ticks = ticks*-1;
            telemetry.addData("turning","left");
            telemetry.update();
        }
        waitForStart();

        turnbot.RF.setTargetPosition(-ticks);
        turnbot.LF.setTargetPosition(ticks);
        turnbot.RB.setTargetPosition(-ticks);
        turnbot.LB.setTargetPosition(ticks);

        turnbot.RF.setPower(0.5);
        turnbot.LF.setPower(0.5);
        turnbot.RB.setPower(0.5);
        turnbot.LB.setPower(0.5);

        turnbot.RF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        turnbot.LF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        turnbot.RB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        turnbot.LB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (turnbot.RF.isBusy()){

            telemetry.addData("turning",degrees);
            telemetry.update();
        }
    }
}

