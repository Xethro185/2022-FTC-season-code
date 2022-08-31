package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Driver.FullRobot;

public class Paralell_Driver2 extends FullRobot implements Runnable {

    Robot DriveBot2 = new Robot();

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
        DriveBot2.init(hardwareMap);
        DriveBot2.bucket.setPosition(0.002);
        DriveBot2.lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void run() {
        DriveBot2.init(hardwareMap);

        telemetry.addData("Driver1", "Starting");
        telemetry.update();

        try {
            Thread.sleep(2000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        vertical = -gamepad1.right_stick_y;
        horizontal = gamepad1.right_stick_x;
        pivot = gamepad1.left_stick_x;

        DriveBot2.RF.setPower(slow*(-pivot + (vertical - horizontal)));
        DriveBot2.RB.setPower(slow*(-pivot + (vertical + horizontal)));
        DriveBot2.LF.setPower(slow*(pivot + (vertical + horizontal)));
        DriveBot2.LB.setPower(slow*(pivot + (vertical - horizontal)));

        if(gamepad1.dpad_down && slow == 1){
            slow = 0.5;
        }else if(gamepad1.dpad_down && slow == 0.5){
            slow = 1;
        }

        if(gamepad2.x || gamepad1.x){
            DriveBot2.Toggleintake(1);
        }
        if(gamepad2.y || gamepad1.y){
            DriveBot2.Toggleintake(-1);
        }

        if(levelIndicator != 0){
            if(gamepad2.a || gamepad1.a){
                try {
                    DriveBot2.Dumpbucket();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }


        if(gamepad2.b || gamepad1.b){
            DriveBot2.carousel.setPower(-1);

        }

        else {
            DriveBot2.carousel.setPower(0);
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
            DriveBot2.LiftSetPossition(levelIndicator);

        }
        try {
            Thread.sleep(100);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }



}
