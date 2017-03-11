package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestStorageActivity extends AppCompatActivity {

    @BindView(R.id.files_dir)
    TextView mFilesDir;

    @BindView(R.id.cache_dir)
    TextView mCacheDir;

    @BindView(R.id.file)
    TextView mFile;

    @BindView(R.id.image)
    ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_storage);
        ButterKnife.bind(this);

        setup();

        String fileName = "myfile";
        String string = "Hello world!";
        writeToFile(fileName, string);
        readFromFile(fileName);

        fileName = "myImage";
        saveImageToFile(fileName);
        setImageFromFile(fileName);
    }

    private void setImageFromFile(String fileName) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeStream(openFileInput(fileName), null, options);
            mImage.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveImageToFile(String fileName) {
        FileOutputStream outputStream;
        try {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d1);
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile(String fileName) {
        FileInputStream inputStream;
        String fileAsString;
        try {
            inputStream = openFileInput(fileName);
            BufferedReader buf = new BufferedReader(new InputStreamReader(inputStream));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            fileAsString = sb.toString();
            mFile.setText(fileAsString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(String fileName, String string) {
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setup() {
        mFilesDir.setText("Internal Dir: " + getFilesDir().getAbsolutePath());
        mCacheDir.setText("Cache Dir: " + getCacheDir().getAbsolutePath());
    }
}
