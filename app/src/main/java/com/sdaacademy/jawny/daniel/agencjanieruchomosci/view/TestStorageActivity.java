package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestStorageActivity extends AppCompatActivity {

    @BindView(R.id.files_dir)
    TextView mText1;

    @BindView(R.id.cache_dir)
    TextView mText2;

    @BindView(R.id.file)
    TextView mFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_storage);
        ButterKnife.bind(this);

        setup();

        String fileName = "myfile";
        writeFile(fileName);
        readFile(fileName);
    }

    private void readFile(String fileName) {
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

    private void writeFile(String fileName) {

        String string = "Hello world!";
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
        mText1.setText("Internal Dir: " + getFilesDir().getAbsolutePath());
        mText2.setText("Cache Dir: " + getCacheDir().getAbsolutePath());
    }
}
