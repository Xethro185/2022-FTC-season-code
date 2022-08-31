package org.firstinspires.ftc.teamcode.Robots;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Driver.FullRobot;

@TeleOp
public class Thread_Starter extends OpMode{

    Robot DriverBot = new Robot();

    double vertical;
    double horizontal;
    double pivot;
    int levelIndicator = 3;
    double slow = 1;
    double Counter = 0;



    TestThread thread = new TestThread();
    Thread thread1 = new Thread(thread);


    @Override
    public void init() {
        thread1.start();
        telemetry.addData("thread ", "started");
        telemetry.update();
        DriverBot.init(hardwareMap);
        DriverBot.bucket.setPosition(0.002);
        DriverBot.lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void loop() {


        thread1.start();

            telemetry.addData("thread ", "Running");
            telemetry.update();

            vertical = -gamepad1.right_stick_y;
            horizontal = gamepad1.right_stick_x;
            pivot = gamepad1.left_stick_x;

            DriverBot.RF.setPower(slow * (-pivot + (vertical - horizontal)));
            DriverBot.RB.setPower(slow * (-pivot + (vertical + horizontal)));
            DriverBot.LF.setPower(slow * (pivot + (vertical + horizontal)));
            DriverBot.LB.setPower(slow * (pivot + (vertical - horizontal)));

            if (gamepad1.dpad_down && slow == 1) {
                slow = 0.5;
            } else if (gamepad1.dpad_down && slow == 0.5) {
                slow = 1;
            }

            if (gamepad2.x || gamepad1.x) {
                DriverBot.Toggleintake(1);
            }
            if (gamepad2.y || gamepad1.y) {
                DriverBot.Toggleintake(-1);
            }

            if (levelIndicator != 0) {
                if (gamepad2.a || gamepad1.a) {
                    try {
                        DriverBot.Dumpbucket();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }


            if (gamepad2.b || gamepad1.b) {
                DriverBot.carousel.setPower(-1);

            } else {
                DriverBot.carousel.setPower(0);
            }

    }

}


