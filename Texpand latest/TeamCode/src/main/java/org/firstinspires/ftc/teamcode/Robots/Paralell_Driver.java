package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Driver.FullRobot;


public class Paralell_Driver extends FullRobot implements Runnable {

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

        telemetry.addData("Thread 2 Status", "Running");
        telemetry.update();

        try {
            Thread.sleep(5000);
        }catch (Exception e){
            System.out.println(e.getMessage());
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
