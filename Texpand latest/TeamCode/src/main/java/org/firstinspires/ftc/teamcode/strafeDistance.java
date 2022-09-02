package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Robots.Robot;

@Autonomous
@Disabled
public class strafeDistance extends LinearOpMode {

    Robot StrafeBot = new Robot(hardwareMap);

    @Override
    public void runOpMode(){
        StrafeBot.init(hardwareMap);

        //initialize robot
        StrafeBot.init(hardwareMap);
        StrafeBot.ResetEncoders();

        double Strafe_cm = -10;
        double circumference = 25.1327;
        double wheel_turns_needed = Strafe_cm/circumference;
        int encoder_driving_target = (int)(1.2*wheel_turns_needed*565);

        waitForStart();

        StrafeBot.RF.setTargetPosition(-encoder_driving_target);
        StrafeBot.LF.setTargetPosition(encoder_driving_target);
        StrafeBot.RB.setTargetPosition(encoder_driving_target);
        StrafeBot.LB.setTargetPosition(-encoder_driving_target);

        StrafeBot.RF.setPower(0.75);
        StrafeBot.LF.setPower(0.75);
        StrafeBot.RB.setPower(0.75);
        StrafeBot.LB.setPower(0.75);

        StrafeBot.RF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        StrafeBot.LF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        StrafeBot.RB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        StrafeBot.LB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (StrafeBot.RF.isBusy()){
            telemetry.addData("Distance to target:",encoder_driving_target-StrafeBot.RF.getTargetPosition());
            telemetry.update();
            sleep(50);
        }

        StrafeBot.RF.setPower(0);
        StrafeBot.LF.setPower(0);
        StrafeBot.RB.setPower(0);
        StrafeBot.LB.setPower(0);

        telemetry.addData("Path:","Complete");
        telemetry.update();

    }
}
