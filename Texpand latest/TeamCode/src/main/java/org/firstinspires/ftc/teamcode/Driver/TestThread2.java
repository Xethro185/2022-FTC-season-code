package org.firstinspires.ftc.teamcode.Driver;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


public class TestThread2 extends FullRobot implements Runnable {

    int counter;

    @Override
    public void run(){
        telemetry.addData("thread2", "running" );
        telemetry.update();

        while (counter < 20);{
            telemetry.addData("thread2", "running" );
            telemetry.update();
        }

    }
}
