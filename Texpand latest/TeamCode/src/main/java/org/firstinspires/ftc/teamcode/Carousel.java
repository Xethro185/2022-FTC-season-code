package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp
@Disabled
public class Carousel extends OpMode {
    DcMotor carousel;

    @Override
    public void init() {
        carousel = hardwareMap.get(DcMotor.class,"carousel");
        carousel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Status","Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {

        if(gamepad1.x){
            carousel.setPower(0.75);
            telemetry.addData("Status","Motor On");
            telemetry.update();
        }

        else {
            carousel.setPower(0);
            telemetry.addData("Status","Motor Off");
            telemetry.update();
        }

    }
}