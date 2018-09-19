package demon_munchies.paint;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class PaintingMunchies extends AppCompatActivity {

    private int WRITE_PERMISSION_CODE = 1;

    private PaintView paintView;
    Button backButton;
    Button clearButton;
    Button saveButtonPNG;
    Button saveButtonJPEG;
    SeekBar redBar;
    SeekBar greenBar;
    SeekBar blueBar;

    int redVal = 0;
    int greenVal = 0;
    int blueVal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painting_munchies);
        paintView = (PaintView) findViewById(R.id.paintView);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);

        backButton = (Button) findViewById(R.id.back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(goBack);
            }
        });

        clearButton = (Button) findViewById(R.id.clear);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintView.clear();
            }
        });

        saveButtonPNG = (Button) findViewById(R.id.savePNG);

        saveButtonPNG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(PaintingMunchies.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                    try
                    {
                        String directory = Environment.getExternalStorageDirectory().toString();
                        File f = new File(directory + "/MunchyPaint");
                        if(!f.exists())
                        {
                            f.mkdirs();
                        }
                        String filename = "ColorMunchies" + (int)(Math.random()*10000000) + ".png";
                        if(f.exists())
                        {
                            f.delete();
                        }
                        File file = new File(directory, filename);
                        FileOutputStream fs = new FileOutputStream(file);
                        Bitmap bitmap = paintView.getmBitmap();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 10, fs);
                        fs.flush();
                        fs.close();
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    requestSavingPermissions();
                }

            }
        });

        saveButtonJPEG = (Button) findViewById(R.id.saveJPEG);

        saveButtonJPEG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(PaintingMunchies.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                {
                    try
                    {
                        String directory = Environment.getExternalStorageDirectory().toString();
                        File f = new File(directory + "/MunchyPaint");
                        if(!f.exists())
                        {
                            f.mkdirs();
                        }
                        String filename = "ColorMunchies" + (int)(Math.random()*10000000) + ".jpeg";
                        if(f.exists())
                        {
                            f.delete();
                        }
                        File file = new File(directory, filename);
                        FileOutputStream fs = new FileOutputStream(file);
                        Bitmap bitmap = paintView.getmBitmap();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, fs);
                        fs.flush();
                        fs.close();
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    requestSavingPermissions();
                }

            }
        });


        redBar = (SeekBar) findViewById(R.id.red);
        redBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                redVal = redBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                paintView.updateColor(redVal, greenVal, blueVal);
            }
        });

        greenBar = (SeekBar) findViewById(R.id.green);
        greenBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                greenVal = greenBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                paintView.updateColor(redVal, greenVal, blueVal);
            }
        });

        blueBar = (SeekBar) findViewById(R.id.blue);
        blueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                blueVal = blueBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                paintView.updateColor(redVal, greenVal, blueVal);
            }
        });
    }


    public void requestSavingPermissions()
    {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE))
        {
            new AlertDialog.Builder(this)
                    .setTitle("Permission Needed")
                    .setMessage("This permission is needed to save the File.")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(PaintingMunchies.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_PERMISSION_CODE);
                        }

                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                        dialog.dismiss();
                        }
                    })
                    .create().show();
        }
        else
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == WRITE_PERMISSION_CODE)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
