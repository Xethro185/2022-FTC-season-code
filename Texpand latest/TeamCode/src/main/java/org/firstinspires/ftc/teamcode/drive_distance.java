package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class drive_distance extends LinearOpMode {
    DcMotor intake;
    DcMotor lift;
    DcMotor RF;
    DcMotor LF;
    DcMotor RB;
    DcMotor LB;
    Servo bucket;
    static final int MOTOR_TICK_COUNT = 500;
    double circumference = 25.1327;
    double ticks_per_cm = MOTOR_TICK_COUNT/ circumference;
    double distance = 15;
    int encoder_driving_target = (int)(distance*ticks_per_cm);

    @Override
    public void runOpMode() {
        telemetry.addData("Status","Initialized");
        telemetry.update();

        RF = hardwareMap.get(DcMotor.class,"RF");
        LF = hardwareMap.get(DcMotor.class,"LF");
        RB = hardwareMap.get(DcMotor.class,"RB");
        LB = hardwareMap.get(DcMotor.class,"LB");
        lift = hardwareMap.get(DcMotor.class,"lift");
        intake = hardwareMap.get(DcMotor.class,"intake");
        bucket = hardwareMap.servo.get("bucket");

        RB.setDirection(DcMotorSimple.Direction.REVERSE);
        LF.setDirection(DcMotorSimple.Direction.REVERSE);

        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        bucket.setPosition(0.002);

        telemetry.addData("Status", "Running");
        telemetry.update();


        waitForStart();

        RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        RF.setTargetPosition(-encoder_driving_target);
        LF.setTargetPosition(-encoder_driving_target);
        RB.setTargetPosition(-encoder_driving_target);
        LB.setTargetPosition(-encoder_driving_target);

        RF.setPower(0.5);
        LF.setPower(0.5);
        RB.setPower(0.5);
        LB.setPower(0.5);

        RF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while(RF.isBusy() || LF.isBusy()){
            telemetry.addData("Status", "Driving");
            telemetry.update();
        }
        RF.setPower(0);
        LF.setPower(0);
        RB.setPower(0);
        LB.setPower(0);




    }

}