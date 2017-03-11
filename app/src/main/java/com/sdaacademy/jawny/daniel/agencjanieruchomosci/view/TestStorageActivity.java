package com.sdaacademy.jawny.daniel.agencjanieruchomosci.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdaacademy.jawny.daniel.agencjanieruchomosci.R;
import com.sdaacademy.jawny.daniel.agencjanieruchomosci.model.Product;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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

    @BindView(R.id.object)
    TextView mObject;

    @BindView(R.id.share_preferences_text)
    TextView mSharePreferencesText;

    @BindView(R.id.string)
    EditText mString;


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

        fileName = "myObject";
        saveObjectToFile(fileName);
        readObjectFromFile(fileName);


    }

    private void readObjectFromFile(String fileName) {
        Product product;
        FileInputStream fileInputStream;
        try {
            fileInputStream = openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            product = (Product) objectInputStream.readObject();
            mObject.setText(product.toString());
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveObjectToFile(String fileName) {
        Product product = new Product(1, "dom1", 9999, "d1");
        ObjectOutputStream objectOutputStream;
        try {
            objectOutputStream = new ObjectOutputStream(openFileOutput(fileName, Context.MODE_PRIVATE));
            objectOutputStream.writeObject(product);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
