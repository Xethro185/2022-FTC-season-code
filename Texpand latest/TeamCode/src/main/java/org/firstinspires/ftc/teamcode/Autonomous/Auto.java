package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Target;

@Autonomous
public class Auto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        AutoPipeline ZoneChooser = new AutoPipeline(hardwareMap, telemetry);
        waitForStart();
        Target target = ZoneChooser.getTarget();
        ZoneChooser.stop();

        switch (target){

            case A:
                telemetry.addData("End Zone:", "A");
                break;
            case B:
                telemetry.addData("End Zone", "B");
                break;
            case C:
                telemetry.addData("End Zone", "C");
                break;
        }
        telemetry.update();
    }

}
