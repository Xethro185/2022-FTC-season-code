package org.firstinspires.ftc.teamcode.Autonomous;




/* Copyright (c) 2019 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

        import org.firstinspires.ftc.robotcore.external.ClassFactory;
        import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
        import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
        import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
        import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

        import java.util.List;

/**
 * This 2020-2021 OpMode illustrates the basics of using the TensorFlow Object Detection API to
 * determine the position of the Freight Frenzy game elements.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */
@TeleOp
public class BarcodeDetection extends LinearOpMode {

    int counter = 0;
    boolean MarkerDetected = false;
    float MarkerPos = 3;
    int Location = 0;
    int Location2Counter = 0;
    int Location3Counter = 0;
    int For_counter = 0;
    int Counter_if_statment = 0;

    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] LABELS = {
            "Ball",
            "Cube",
            "Duck",
            "Marker"
    };



    private static final String VUFORIA_KEY =
            " AR3EPAL/////AAABmXPb0JQ8qUwHnsHj7nkb+Zs6SEC+RlWYbPl0mJG4o3VrKI0aNhvDLVUj8Lt7xeThyotImJrdlE1s3ft0fKdGrUrxrWpzcAn44zSGjxZA71uE7dUI9Ybku4l0wour9e6LmbenouZ" +
                    "QW+dYioOxWapSzqOIV33yKRaekEdz/BlTTu2UPLR9ELdJMipXH1Eb8fzDnhNIi+masNsV2r/oshdpT1SRYEGVsDfhhgjmjRn3RPnrM15lQH37F29s3xXshpqObbPOCIyYpFzwr+k+eY5jLU2i/qFwtYeZ7SuSPXqnbfZIOQ6mTPtNRSkhysWJKdWKshprQF6BMde8vEpLEjOOBRjiBNkjslK74uCW2xPKdlAW";

    private VuforiaLocalizer vuforia;


    private TFObjectDetector tfod;

    @Override
    public void runOpMode() {
        initVuforia();
        initTfod();


        if (tfod != null) {
            tfod.activate();

            tfod.setZoom(1, 16.0/9.0);
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();



            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                Counter_if_statment++;
                telemetry.addData("# Object Detected", updatedRecognitions.size());
                int i = 0;
                for (Recognition recognition :  updatedRecognitions) {
                    i++;
                    For_counter++;
                    try {
                        Thread.sleep(1);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                    // check label to see if the camera now sees a Duck
                    if (recognition.getLabel().equals("Marker")) {


                        MarkerPos = recognition.getRight();
                        telemetry.addData("# Object Detected", MarkerPos);

                        if (MarkerPos < 250){

                            Location2Counter++;

                        } else if(MarkerPos > 250){

                            Location3Counter++;
                        }
                    } else {
                        MarkerDetected = false;
                    }

                    telemetry.addData("counter if loop", Counter_if_statment);
                    telemetry.addData("Location 2", Location2Counter);
                    telemetry.addData("Location 3", Location3Counter);
                    telemetry.addData("Counter", counter);
                    telemetry.addData("For loop counter", For_counter);
                    telemetry.update();

                    vuforia.setFrameQueueCapacity(0);


                }

            }
            try {
                Thread.sleep(2000);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        if (opModeIsActive()) {
            while (counter<1000) {
                if (updatedRecognitions != null) {
                    Counter_if_statment++;
                    telemetry.addData("# Object Detected", updatedRecognitions.size());
                    int i = 0;
                    for (Recognition recognition :  updatedRecognitions) {
                        i++;
                        For_counter++;
                        try {
                            Thread.sleep(1);
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }

                        // check label to see if the camera now sees a Duck
                        if (recognition.getLabel().equals("Marker")) {


                            MarkerPos = recognition.getRight();
                            telemetry.addData("# Object Detected", MarkerPos);

                            if (MarkerPos < 250){

                                Location2Counter++;

                            } else if(MarkerPos > 250){

                                Location3Counter++;
                            }
                        } else {
                            MarkerDetected = false;
                        }

                        telemetry.addData("counter if loop", Counter_if_statment);
                        telemetry.addData("Location 2", Location2Counter);
                        telemetry.addData("Location 3", Location3Counter);
                        telemetry.addData("Counter", counter);
                        telemetry.addData("For loop counter", For_counter);
                        telemetry.update();

                        vuforia.setFrameQueueCapacity(0);


                    }

                }

                counter++;

            }
            try {
                Thread.sleep(3000);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            if (Location3Counter > 600) {

                Location = 3;
                telemetry.addData("counter if loop", Counter_if_statment);
                telemetry.addData("Counter", counter);
                telemetry.addData("For loop counter", For_counter);
                telemetry.addData("End location:", Location);
                telemetry.addData("Location 2", Location2Counter);
                telemetry.addData("Location 3", Location3Counter);

            }
            else if (Location2Counter > 600) {

                Location = 2;
                telemetry.addData("counter if loop", Counter_if_statment);
                telemetry.addData("Counter", counter);
                telemetry.addData("End location:", Location);
                telemetry.addData("For loop counter", For_counter);
                telemetry.addData("Location 2", Location2Counter);
                telemetry.addData("Location 3", Location3Counter);
            }
            else{

                Location = 1;
                telemetry.addData("counter if loop", Counter_if_statment);
                telemetry.addData("Counter", counter);
                telemetry.addData("End location:", Location);
                telemetry.addData("For loop counter", For_counter);
                telemetry.addData("Location 2", Location2Counter);
                telemetry.addData("Location 3", Location3Counter);
            }
            telemetry.update();



            try {
                Thread.sleep(5000);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }

    }


    private void initVuforia() {

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        vuforia = ClassFactory.getInstance().createVuforia(parameters);
    }


    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.40f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }
}