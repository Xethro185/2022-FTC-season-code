package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Robots.AutoBot;

@Autonomous
public class Turn_IMU extends LinearOpMode {

    AutoBot TurnBot = new AutoBot();

    BNO055IMU imu;
    Orientation angles;

    @Override
    public void runOpMode() throws InterruptedException {

        TurnBot.init(hardwareMap);

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        imu = hardwareMap.get(BNO055IMU .class, "imu");
        imu.initialize(parameters);

        waitForStart();

        angles = imu.getAngularOrientation(AxesReference.EXTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        TurnBot.init(hardwareMap);

        waitForStart();

        turnLeft(-90,25);

    }

    void turnLeft(double turnAngle, double timeoutS) {
        sleep(250);
        Angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.YXY, AngleUnit.DEGREES);
        double speed = 0.25;
        double oldDegreesLeft = turnAngle;
        double scaledSpeed = speed;
        double targetHeading = Angles.firstAngle + turnAngle;
        double oldAngle = Angles.firstAngle;
        if (targetHeading < -180) {
            targetHeading += 360;
        }
        if (targetHeading > 180) {
            targetHeading -= 360;
        }
        double degreesLeft = ((Math.signum(Angles.firstAngle - targetHeading) + 1) / 2) * (360 - Math.abs(Angles.firstAngle - targetHeading)) + (Math.signum(targetHeading - Angles.firstAngle) + 1) / 2 * Math.abs(Angles.firstAngle - targetHeading);
        resetStartTime();
        while (opModeIsActive() && time < timeoutS && degreesLeft > 1 && oldDegreesLeft - degreesLeft >= 0) {
            scaledSpeed = degreesLeft / (100 + degreesLeft) * speed;
            if (scaledSpeed > 1) {
                scaledSpeed = .1;
            }
            TurnBot.LB.setPower(scaledSpeed);
            TurnBot.RB.setPower(-scaledSpeed);
            TurnBot.LF.setPower(scaledSpeed);
            TurnBot.RF.setPower(-scaledSpeed);
            Angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.YXY, AngleUnit.DEGREES);
            degreesLeft = ((Math.signum(Angles.firstAngle - targetHeading) + 1) / 2) * (360 - Math.abs(Angles.firstAngle - targetHeading)) + (Math.signum(targetHeading - Angles.firstAngle) + 1) / 2 * Math.abs(Angles.firstAngle - targetHeading);
            if (Math.abs(Angles.firstAngle - oldAngle) < 1) {speed *= 1.1;}
        }
        TurnBot.setALLPower(0);
        TurnBot.LF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        TurnBot.RF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        TurnBot.LB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        TurnBot.RB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    Orientation Angles;


}
