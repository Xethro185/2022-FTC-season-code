package org.firstinspires.ftc.teamcode.Driver;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Carousel;
import org.firstinspires.ftc.teamcode.Robots.Robot;
import org.firstinspires.ftc.teamcode.Robots.Paralell_Driver;


@TeleOp
public class FullRobot extends OpMode {

    // this is a test to check if Josh's github is working
    // this is a test to check if Josh's github is working

    //subsytems
    private Robot DriverBot;
    private Carousel carousel;

    //Motor values
    private double vertical;
    private double horizontal;
    private double pivot;
    private double slow = 1;
  
    //Linear slide
    private int levelIndicator = 0;

    @Override
    public void init() {
        carousel = new Carousel(hardwareMap);
        DriverBot = new Robot(hardwareMap);

        DriverBot.init(hardwareMap);

        dataInit();

        Driverbot.lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        DriverBot.bucket.setPosition(0.002);
    }


    public void loop() {

        //Drivetrain
        vertical = -gamepad1.right_stick_y;
        horizontal = gamepad1.right_stick_x;
        pivot = gamepad1.left_stick_x;

        DriverBot.RF.setPower(slow*(-pivot + (vertical - horizontal)));
        DriverBot.RB.setPower(slow*(-pivot + (vertical + horizontal)));
        DriverBot.LF.setPower(slow*(pivot + (vertical + horizontal)));
        DriverBot.LB.setPower(slow*(pivot + (vertical - horizontal)));

        if(gamepad1.dpad_down && slow == 1){
            slow = 0.5;
        }else if(gamepad1.dpad_down && slow == 0.5){
            slow = 1; 
        }

        if(gamepad2.x || gamepad1.x){
            DriverBot.Toggleintake(1);
        }
        if(gamepad2.y || gamepad1.y){
            DriverBot.Toggleintake(-1);
        }

        //Carousel
        if(gamepad1.x){
            carousel.setCarsouelPower(0.75);
        }
        else {
            carousel.setCarsouelPower(0.75);
        }

        //Linear slide
        if(gamepad2.left_bumper || gamepad2.right_bumper || gamepad1.left_bumper || gamepad1.right_bumper) 
        {
            try {
                Thread.sleep(50);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            if (gamepad2.left_bumper || gamepad1.left_bumper) 
            {
                this.levelIndicator = 0;
                update();
                
                try {
                    Thread.sleep(50);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            if (gamepad2.right_bumper || gamepad1.right_bumper) {

                this.levelIndicator -= 1;
                update();

                try {
                    Thread.sleep(50);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            if (levelIndicator == -1) {
                levelIndicator = 3;
                update();
                try {
                    Thread.sleep(50);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }

            if(levelIndicator != 0){
                if(gamepad2.a || gamepad1.a){
                    try {
                        DriverBot.Dumpbucket();
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
    
                }
            }

            DriverBot.LiftSetPossition(this.levelIndicator);
        }

        try {
            Thread.sleep(100);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void update(){

        telemetry.update();
    }

    private void dataInit(){
        telemetry.addData("levelIndicator:",levelIndicator);
        telemetry.addData("Moving too position:",levelIndicator);
    }
}

