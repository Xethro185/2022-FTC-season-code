package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TestThread extends LinearOpMode implements Runnable {

    Robot drive = new Robot();
    int levelIndicator = 3;

    @Override
    public void runOpMode() throws InterruptedException {

    }

    @Override
    public void run() {

        telemetry.addData("thread1", "Running");
        telemetry.update();


    }
}
