package com.example.charlie.bullsgym;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Camera extends AppCompatActivity {

    private Button btnCapture;
    private TextureView textureView;

    //check state orientation of output image
    private static final SparseIntArray ORIENTATIONS= new SparseIntArray();
    static {
        ORIENTATIONS.append(Surface.ROTATION_0,90);
        ORIENTATIONS.append(Surface.ROTATION_90,0);
        ORIENTATIONS.append(Surface.ROTATION_180,270);
        ORIENTATIONS.append(Surface.ROTATION_270,180);
    }

    private String cameraId;
    private CameraDevice cameraDevice;
    private CameraCaptureSession cameraCaptureSessions;
    private CaptureRequest.Builder captureRequestBuilder;
    private Size imageDimension;
    private ImageReader imageReader;


    //save to file
    private File file;
    private static final int REQUEST_CAMERA_PERMISSION=200;
    private boolean mFlashSupported;
    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;

    CameraDevice.StateCallback stateCallback= new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            cameraDevice=camera;
            createCameraPreview();

        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            cameraDevice.close();
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            cameraDevice.close();
            cameraDevice=null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        btnCapture= (Button) findViewById(R.id.btnCapture);
        textureView= (TextureView) findViewById(R.id.textureView);

        assert textureView !=null;

        textureView.setSurfaceTextureListener(textureListener);

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
    }

    private void takePicture() {
        if(cameraDevice == null)
        return;
        CameraManager manager= (CameraManager)getSystemService(Context.CAMERA_SERVICE);
        try{
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraDevice.getId());
            Size[] jpegSizes= null;
            if(characteristics != null)
                jpegSizes= characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
                        .getOutputSizes(ImageFormat.JPEG);

            //capture images with custom size
            int width =640;
            int height = 480;

            if(jpegSizes != null && jpegSizes.length>0){
                width= jpegSizes[0].getWidth();
                height= jpegSizes[0].getHeight();
            }
            ImageReader reader= ImageReader.newInstance(width,height,ImageFormat.JPEG,1);
            List<Surface> outputSurface = new ArrayList<>(2);
            outputSurface.add(reader.getSurface());
            outputSurface.add(new Surface(textureView.getSurfaceTexture()));
            final CaptureRequest.Builder captureBuilder= cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            captureBuilder.addTarget(reader.getSurface());
            captureBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);

            //check orientation base on device
             int rotation = getWindowManager().getDefaultDisplay().getRotation();
             captureBuilder.set(CaptureRequest.JPEG_ORIENTATION,ORIENTATIONS.get(rotation));

             file= new File(Environment.getExternalStorageDirectory()+"/"+ UUID.randomUUID().toString()+".jpg");
             ImageReader.OnImageAvailableListener readerListener= new ImageReader.OnImageAvailableListener() {
                 @Override
                 public void onImageAvailable(ImageReader reader) {
                     Image image= null;
                     try{
                         image= reader.acquireLatestImage();
                         ByteBuffer buffer= image.getPlanes()[0].getBuffer();
                         byte[] bytes=new byte[buffer.capacity()];
                         buffer.get(bytes);
                         save(bytes);
                     }catch (FileNotFoundException e){
                         e.printStackTrace();
                     }catch (IOException e){
                         e.printStackTrace();
                     }finally {
                         if(image!=null){
                             image.close();
                         }
                     }
                 }

                 private void save(byte[] bytes) throws IOException {
                     OutputStream outputStream= null;
                     try{
                         outputStream= new FileOutputStream(file);
                         outputStream.write(bytes);
                     }finally {
                         if(outputStream !=null){
                             outputStream.close();
                         }
                     }
                 }
             };

             reader.setOnImageAvailableListener(readerListener,mBackgroundHandler);
             final CameraCaptureSession.CaptureCallback captureListener= new CameraCaptureSession.CaptureCallback() {
                 @Override
                 public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
                     super.onCaptureCompleted(session, request, result);
                     Toast.makeText(getApplicationContext(), "Saved "+file,Toast.LENGTH_LONG).show();
                     createCameraPreview();
                 }
             };

             cameraDevice.createCaptureSession(outputSurface, new CameraCaptureSession.StateCallback() {
                 @Override
                 public void onConfigured(@NonNull CameraCaptureSession session) {
                     try {
                         session.capture(captureBuilder.build(), captureListener,mBackgroundHandler);
                     } catch (CameraAccessException e) {
                         e.printStackTrace();
                     }
                 }

                 @Override
                 public void onConfigureFailed(@NonNull CameraCaptureSession session) {

                 }
             },mBackgroundHandler);

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    private void createCameraPreview() {
        try {
            SurfaceTexture texture =textureView.getSurfaceTexture();
            assert texture != null;
            texture.setDefaultBufferSize(imageDimension.getWidth(),imageDimension.getHeight());
            Surface surface= new Surface(texture);
            captureRequestBuilder= cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            captureRequestBuilder.addTarget(surface);
            cameraDevice.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession session) {
                    if(cameraDevice == null)
                        return;
                    cameraCaptureSessions = session;
                    updatePreview();
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession session) {
                    Toast.makeText(getApplicationContext(),"Changed" ,Toast.LENGTH_LONG).show();
                }
            },null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void updatePreview() {
        if(cameraDevice == null)
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
        captureRequestBuilder.set(CaptureRequest.CONTROL_MODE,CaptureRequest.CONTROL_MODE_AUTO);
        try{
            cameraCaptureSessions.setRepeatingRequest(captureRequestBuilder.build(),null,mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void openCamera() {
        CameraManager manager= (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId=manager.getCameraIdList()[0];
            CameraCharacteristics characteristics=manager.getCameraCharacteristics(cameraId);
            StreamConfigurationMap map= characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            assert map !=null;
            imageDimension= map.getOutputSizes(SurfaceTexture.class)[0];

            //check real-time permissions if run higher API 23
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                },REQUEST_CAMERA_PERMISSION);
                return;
            }
            manager.openCamera(cameraId,stateCallback,null);


        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    TextureView.SurfaceTextureListener textureListener= new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            openCamera();
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CAMERA_PERMISSION){
            if(grantResults[0] != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(),"You can't use this camera without permission",Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startBackgroundThread();
        if(textureView.isAvailable()){
            openCamera();
        }else{
            textureView.setSurfaceTextureListener(textureListener);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopBackgroundThread();
    }

    private void stopBackgroundThread() {
        mBackgroundThread.quitSafely();
        try{
            mBackgroundThread.join();
            mBackgroundThread=null;
            mBackgroundHandler=null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void startBackgroundThread() {
        mBackgroundThread= new HandlerThread("Camera Background");
        mBackgroundThread.start();
        mBackgroundHandler= new Handler(mBackgroundThread.getLooper());
    }
}
