package org.firstinspires.ftc.teamcode.Driver;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TestThread1 extends OpMode {


    TestThread2 test = new TestThread2();
    Thread thread = new Thread(test);

    @Override
    public void init() {

    }

    public void main (){ thread.start();
    }

    @Override
    public void loop() {
        telemetry.addData("thread1", "running" );
        telemetry.update();
    }
}