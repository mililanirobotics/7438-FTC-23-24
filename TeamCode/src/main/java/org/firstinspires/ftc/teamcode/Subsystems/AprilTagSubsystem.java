package org.firstinspires.ftc.teamcode.Subsystems;

import java.util.List;
import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.teamcode.aSClib.SubsystemBase;

public class AprilTagSubsystem implements SubsystemBase {
    private AprilTagProcessor aprilTagProcessor;
    private Telemetry telemetry;
    private VisionPortal visionPortal;
    private List<AprilTagDetection> currentDetections;
    private int desiredID;

    public AprilTagSubsystem(OpMode opMode, Telemetry telemetry) {

        aprilTagProcessor = new AprilTagProcessor.Builder()
                .setTagLibrary(AprilTagGameDatabase.getCenterStageTagLibrary())
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setLensIntrinsics(578.272, 578.272, 402.145, 221.506)
                .build();

        this.telemetry = telemetry;
        VisionPortal.Builder builder = new VisionPortal.Builder();

        builder.setCamera(opMode.hardwareMap.get(WebcamName.class, "Webcam"));
        builder.setCameraResolution(new Size(640, 480));
        builder.enableLiveView(true);
        builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);
        builder.setAutoStopLiveView(false);
        builder.addProcessor(aprilTagProcessor);

        visionPortal = builder.build();
    }

    public void setDesiredAprilTag(int id) {
        desiredID = id;
    }

    public AprilTagDetection getAprilTag() {
        for (AprilTagDetection detection : currentDetections) {
            if (detection.id == desiredID) {
                return detection;
            }
        }
        return null;
    }

    public double getXOffset() {
        if (getAprilTag() == null) {
            return 0;
        }
        return getAprilTag().ftcPose.x;
    }

    public double getYOffset() {
        if (getAprilTag() == null) {
            return 0;
        }
        return getAprilTag().ftcPose.y;
    }

    public double getZOffset() {
        if (getAprilTag() == null) {
            return 0;
        }
        return getAprilTag().ftcPose.z;
    }

    public int getID() {
        if (getAprilTag() == null) {
            return 0;
        }
        return getAprilTag().id;
    }

    public boolean isAprilTagDetected() {
        if (getAprilTag() == null) {
            return false;
        }
        return true;
    }

    public void updateDetection() {
        currentDetections = aprilTagProcessor.getDetections();
    }

    @Override
    public void shutdown() {}

    @Override
    public void periodic() {
        setDesiredAprilTag(5);
        updateDetection();
        telemetry.addData("ID", getID());
        telemetry.addData("XOFFSET", getXOffset());
        telemetry.addData("YOFFSET", getYOffset());
        telemetry.addData("ZOFFSET", getZOffset());
    }
}
