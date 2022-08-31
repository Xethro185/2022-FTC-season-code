package org.firstinspires.ftc.teamcode.Driver;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robots.Robot;
import org.firstinspires.ftc.teamcode.Robots.Paralell_Driver;


@TeleOp
public class FullRobot extends OpMode {

    Robot DriverBot = new Robot();



    double vertical;
    double horizontal;
    double pivot;
    int levelIndicator = 3;
    double slow = 1;
    int currentPossition = 0;
    int slidePossition0 = 0;
    int slidePossition1 = 1000;
    int slidePossition2 = 1700;
    int slidePossition3 = 2550;
    boolean StartThread = true;






    @Override
    public void init() {
        DriverBot.init(hardwareMap);
        DriverBot.bucket.setPosition(0.002);
        DriverBot.lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }




    public void loop() {

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

        if(levelIndicator != 0){
            if(gamepad2.a || gamepad1.a){
                try {
                    DriverBot.Dumpbucket();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }


        if(gamepad2.b || gamepad1.b){
            DriverBot.carousel.setPower(-1);

        }

        else {
            DriverBot.carousel.setPower(0);
        }

        if(gamepad2.left_bumper || gamepad2.right_bumper || gamepad1.left_bumper || gamepad1.right_bumper) {
            try {
                Thread.sleep(50);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            if (gamepad2.left_bumper || gamepad1.left_bumper) {
                levelIndicator = 0;
                try {
                    Thread.sleep(50);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            if (gamepad2.right_bumper || gamepad1.right_bumper) {
                levelIndicator = levelIndicator - 1;
                try {
                    Thread.sleep(50);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            if (levelIndicator == -1) {
                levelIndicator = 3;
                try {
                    Thread.sleep(50);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            DriverBot.LiftSetPossition(levelIndicator);

        }
        try {
            Thread.sleep(100);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}

