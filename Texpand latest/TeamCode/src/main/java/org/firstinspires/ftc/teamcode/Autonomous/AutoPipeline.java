package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Target;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;


public class AutoPipeline extends OpenCvPipeline {
    private OpenCvCamera cam;
    private Mat mat = new Mat();
    private Rect upperROI = new Rect(new Point(240,120), new Point(304, 145));
    private Rect lowerROI = new Rect(new Point(240,145), new Point(304, 170));
    private Mat upperMat;
    private Mat lowerMat;
    private Target target;
    private Telemetry telemetry;


    public AutoPipeline(HardwareMap hwMap, Telemetry t){
        telemetry = t;
        int camMonViewId = hwMap.appContext.getResources().getIdentifier(
                "cameraMonitorViewId",
                "id",
                hwMap.appContext.getPackageName()
        );
        cam = OpenCvCameraFactory.getInstance().createWebcam(
                hwMap.get(WebcamName.class, "Webcam 1"),
                camMonViewId
        );
        cam.setPipeline(this);


    }
    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input,mat, Imgproc.COLOR_RGBA2RGB);
        Imgproc.cvtColor(mat,mat,Imgproc.COLOR_RGB2HSV);
        Scalar lowerBound = new Scalar(15.0 / 2, 100, 100);
        Scalar upperBound = new Scalar(45.0 / 2, 255, 255);
        Core.inRange(mat, lowerBound, upperBound, mat);

        upperMat = mat.submat(upperROI);
        lowerMat = mat.submat(lowerROI);

        double upperValue = Math.round(Core.mean(upperMat).val[2] / 255);
        double lowerValue = Math.round(Core.mean(lowerMat).val[2] / 255);

        upperMat.release();
        lowerMat.release();
        mat.release();

        final double THRESHHOLD = 10;

        if (upperValue > THRESHHOLD){
            target = Target.C;
        }
        else if (lowerValue > THRESHHOLD){
            target = Target.B;
        }
        else{
            target = Target.A;
        }
        
        return null;
    }

    public Target getTarget() {
        return target;
    }
    public void stop(){
        cam.closeCameraDeviceAsync( () -> {});
    }
}
