package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Robots.AutoBot;
import org.firstinspires.ftc.teamcode.Robots.Robot;

@Autonomous
public class turn_degrees extends LinearOpMode {


    ElapsedTime period  = new ElapsedTime();

    AutoBot TurnBot = new AutoBot();
    double distance_cm = 150;
    double circumference = 25.1327;
    double degrees = 360;
    double ticksperdegree = 12.54;

    double target_power = 1;
    double power = target_power/2;

    double currentmotorpos = 0;
    @Override
    public void runOpMode() throws InterruptedException {


        DriveDistance(150,1);
        DriveDistance(-150,1);
        TurnDegrees(360,1);
        StrafeDistnace(150,1);
        StrafeDistnace(-150,1);
        TurnDegrees(360,1);


    }

    public void DriveDistance(double distance_cm, double target_power){
        TurnBot.init(hardwareMap);

        double wheel_turns_needed = distance_cm/circumference;
        int encoder_driving_target = (int)(wheel_turns_needed*565);
        TurnBot.ResetEncoders();

        TurnBot.RF.setTargetPosition(encoder_driving_target);
        TurnBot.LF.setTargetPosition(encoder_driving_target);
        TurnBot.RB.setTargetPosition(encoder_driving_target);
        TurnBot.LB.setTargetPosition(encoder_driving_target);

        TurnBot.RF.setPower(power);
        TurnBot.LF.setPower(power);
        TurnBot.RB.setPower(power);
        TurnBot.LB.setPower(power);

        TurnBot.RF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        TurnBot.LF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        TurnBot.RB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        TurnBot.LB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (TurnBot.RF.isBusy()){



            currentmotorpos = TurnBot.RF.getCurrentPosition();
            if(currentmotorpos>(0.85*encoder_driving_target)){
                TurnBot.RF.setPower(power);
                TurnBot.LF.setPower(power);
                TurnBot.RB.setPower(power);
                TurnBot.LB.setPower(power);
                power = power - power*0.12;
            }else{
                TurnBot.RF.setPower(power);
                TurnBot.LF.setPower(power);
                TurnBot.RB.setPower(power);
                TurnBot.LB.setPower(power);
                power = power + power*0.08;
            }
            try {
                Thread.sleep(50);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        TurnBot.RF.setPower(0);
        TurnBot.LF.setPower(0);
        TurnBot.RB.setPower(0);
        TurnBot.LB.setPower(0);

    }

    public void StrafeDistnace(double distance_cm, double target_power){
        TurnBot.init(hardwareMap);

        double wheel_turns_needed = distance_cm/circumference;
        int encoder_driving_target = (int)(wheel_turns_needed*565);
        TurnBot.ResetEncoders();

        TurnBot.RF.setTargetPosition(-encoder_driving_target);
        TurnBot.LF.setTargetPosition(encoder_driving_target);
        TurnBot.RB.setTargetPosition(encoder_driving_target);
        TurnBot.LB.setTargetPosition(-encoder_driving_target);

        TurnBot.RF.setPower(power);
        TurnBot.LF.setPower(power);
        TurnBot.RB.setPower(power);
        TurnBot.LB.setPower(power);

        TurnBot.RF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        TurnBot.LF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        TurnBot.RB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        TurnBot.LB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (TurnBot.RF.isBusy()){



            currentmotorpos = TurnBot.RF.getCurrentPosition();
            if(currentmotorpos>(0.85*encoder_driving_target)){
                TurnBot.RF.setPower(power);
                TurnBot.LF.setPower(power);
                TurnBot.RB.setPower(power);
                TurnBot.LB.setPower(power);
                power = power - power*0.12;
            }else{
                TurnBot.RF.setPower(power);
                TurnBot.LF.setPower(power);
                TurnBot.RB.setPower(power);
                TurnBot.LB.setPower(power);
                power = power + power*0.08;
            }
            try {
                Thread.sleep(50);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        TurnBot.RF.setPower(0);
        TurnBot.LF.setPower(0);
        TurnBot.RB.setPower(0);
        TurnBot.LB.setPower(0);

    }
    public void TurnDegrees(double degrees, double target_power){
        TurnBot.init(hardwareMap);

        double wheel_turns_needed = distance_cm/circumference;
        int encoder_driving_target = (int)(wheel_turns_needed*565);
        int ticks = (int) (ticksperdegree*degrees);
        TurnBot.ResetEncoders();

        TurnBot.RF.setTargetPosition(encoder_driving_target);
        TurnBot.LF.setTargetPosition(encoder_driving_target);
        TurnBot.RB.setTargetPosition(encoder_driving_target);
        TurnBot.LB.setTargetPosition(encoder_driving_target);

        TurnBot.RF.setPower(power);
        TurnBot.LF.setPower(power);
        TurnBot.RB.setPower(power);
        TurnBot.LB.setPower(power);

        TurnBot.RF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        TurnBot.LF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        TurnBot.RB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        TurnBot.LB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (TurnBot.RF.isBusy()){



            currentmotorpos = TurnBot.RF.getCurrentPosition();
            if(currentmotorpos>(0.85*encoder_driving_target)){
                TurnBot.RF.setPower(power);
                TurnBot.LF.setPower(power);
                TurnBot.RB.setPower(power);
                TurnBot.LB.setPower(power);
                power = power - power*0.12;
            }else{
                TurnBot.RF.setPower(power);
                TurnBot.LF.setPower(power);
                TurnBot.RB.setPower(power);
                TurnBot.LB.setPower(power);
                power = power + power*0.08;
            }
            try {
                Thread.sleep(50);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        TurnBot.RF.setPower(0);
        TurnBot.LF.setPower(0);
        TurnBot.RB.setPower(0);
        TurnBot.LB.setPower(0);

    }


}
