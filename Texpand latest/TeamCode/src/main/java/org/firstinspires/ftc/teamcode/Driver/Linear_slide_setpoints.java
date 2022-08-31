package org.firstinspires.ftc.teamcode.Driver;

import android.transition.Slide;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robots.Robot;

@TeleOp
@Disabled
public class Linear_slide_setpoints extends OpMode{
    Robot SlideBot = new Robot();

    int levelIndicator = 0;

    @Override
    public void loop() {

        if(gamepad1.left_bumper){
            levelIndicator = 0;
            telemetry.addData("levelIndicator:",levelIndicator);
            telemetry.update();
        }
        if(gamepad1.right_bumper){
            levelIndicator = levelIndicator - 1;
            telemetry.addData("levelIndicator:",levelIndicator);
            telemetry.update();

        }
        if(levelIndicator == -1){
            levelIndicator = 3;
            telemetry.addData("levelIndicator:",levelIndicator);
            telemetry.update();
        }

        SlideBot.LiftSetPossition(levelIndicator);
        telemetry.addData("Moving too possition:",levelIndicator);
        telemetry.update();

    }


    @Override
    public void init() {

        SlideBot.init(hardwareMap);
        SlideBot.lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
}
