package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


@TeleOp
@Disabled
public class Carousel extends OpMode {
    //motors
    private DcMotor carouselMotor;

    public Carousel(HardwareMap hwMap){
        hardwareMap = hwMap;
        
        carouselMotor = hardwareMap.get(DcMotor.class,"carousel");

        carouselMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        carouselMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        dataInit();
        telemetry.update();
    }

    @Override
    public void init() {
    }

    @Override
    public void loop() {
    }

    public setCarsouelPower(double power)
    {
        carouselMotor.setPower(power);
        telemetry.update();
    }

    private void dataInit()
    {
        telemetry.addData("Carousel Motor","Initialized");
        telemetry.addData("Status","Motor Off");
    }
}