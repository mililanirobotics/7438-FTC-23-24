package org.firstinspires.ftc.teamcode.Subsystems;

import java.util.List;
import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;
import org.firstinspires.ftc.teamcode.aSClib.SubsystemBase;

public class TensorflowSubsystem implements SubsystemBase {
    private TfodProcessor tfod;
    private VisionPortal visionPortal;
    private List<Recognition> currentRecognitions;
    private String desiredObject;

    public TensorflowSubsystem(OpMode opMode, Telemetry telemetry) {
        tfod = new TfodProcessor.Builder()
//                .setModelAssetName(TFOD_MODEL_ASSET)
//                .setModelFileName(TFOD_MODEL_FILE)
//                .setModelLabels(LABELS)
                .setIsModelTensorFlow2(true)
                .setIsModelQuantized(true)
                .setModelInputSize(300)
                .setModelAspectRatio(16.0 / 9.0)
                .build();

        VisionPortal.Builder builder = new VisionPortal.Builder();
        builder.setCamera(opMode.hardwareMap.get(WebcamName.class, "Webcam"));

        builder.setCameraResolution(new Size(640, 480));
        builder.enableLiveView(true);
        builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);
        builder.setAutoStopLiveView(false);
        builder.addProcessor(tfod);

        visionPortal = builder.build();

        tfod.setMinResultConfidence(0.75f);
        visionPortal.setProcessorEnabled(tfod, true);
    }

    public void setDesiredObject(String desiredObject) {
        this.desiredObject = desiredObject;
    }

    public Recognition getRecognition() {
        for (Recognition recognition : currentRecognitions) {
            if (recognition.getLabel() == desiredObject) {
                return recognition;
            }
        }
        return null;
    }

    public double getXPosition() {
        return (getRecognition().getLeft() + getRecognition().getRight())/2;
    }

    public double getYPosition() {
        return (getRecognition().getBottom() + getRecognition().getTop())/2;
    }

    public double getConfidence() {
        return getRecognition().getConfidence();
    }

    public void updateRecognition() {
        currentRecognitions = tfod.getRecognitions();
    }

    @Override
    public void shutdown() {}

    @Override
    public void periodic() {updateRecognition();}
}
