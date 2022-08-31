package org.firstinspires.ftc.teamcode.Robots;

public class SlideAuto extends AutoBot implements Runnable{

    AutoBot RedBot2 = new AutoBot();


    @Override
    public void run (){
        RedBot2.init(hardwareMap);

        RedBot2.LiftSetPossition(RedBot2.Location);

    }
}
