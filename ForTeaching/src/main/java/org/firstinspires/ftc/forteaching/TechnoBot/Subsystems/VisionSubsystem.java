package org.firstinspires.ftc.forteaching.TechnoBot.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.vision.hardware.Webcam;

import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class VisionSubsystem implements Subsystem, Loggable {
    @Config
    public static class VisionSubsystemConstants {
        public static int WIDTH = 320;
        public static int HEIGHT = 240;
        public static OpenCvCameraRotation ROTATION = OpenCvCameraRotation.UPRIGHT;
    }

    public Webcam camera;
    public VisionPipeline visionPipeline;

    public VisionSubsystem(Webcam c) {
        camera = c;
        visionPipeline = new VisionPipeline();
    }

    public void startStreaming() {
        camera.startStreaming(
                VisionSubsystemConstants.WIDTH,
                VisionSubsystemConstants.HEIGHT,
                VisionSubsystemConstants.ROTATION);
    }

    public void startVisionPipeline() {
        camera.setPipeline(visionPipeline);
        camera.openCameraDeviceAsync(this::startStreaming, i -> startVisionPipeline());
    }

    public void stopVisionPipeline() {
        camera.setPipeline(null);
        camera.closeCameraDeviceAsync(() -> {
            /* Do we need to do anything to stop the vision pipeline? */
        });
    }

}