package companion.apps.menubook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import companion.apps.menubook.databinding.ActivityScannerViewBinding;

public class ScannerViewActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener {

    ActivityScannerViewBinding binding;
    Context context = ScannerViewActivity.this;
    MediaPlayer mediaPlayer;

    int counter = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setStatusBarColor(ContextCompat.getColor(context,R.color.colorTransparent));
        binding = ActivityScannerViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.qrdecoderview.setOnQRCodeReadListener(this);

        // Use this function to enable/disable decoding
        binding.qrdecoderview.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        binding.qrdecoderview.setAutofocusInterval(500L);

        // Use this function to enable/disable Torch
        binding.qrdecoderview.setTorchEnabled(true);

        // Use this function to set front camera preview
        binding.qrdecoderview.setFrontCamera();

        // Use this function to set back camera preview
        binding.qrdecoderview.setBackCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.qrdecoderview.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.qrdecoderview.stopCamera();
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {

        if(counter == -1) {
            mediaPlayer = MediaPlayer.create(ScannerViewActivity.this, R.raw.scan_sound);
            mediaPlayer.start();
            if (text.equals("Table No: 10")) {
                counter++;
                Intent intent = new Intent(ScannerViewActivity.this, MenuScreen.class);
                intent.putExtra("tableNo",text);
                startActivity(intent);
                finish();
            } else {
                counter++;
                Toast.makeText(ScannerViewActivity.this, "Invalid QR", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}