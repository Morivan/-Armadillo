package com.example.armadillo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EducationActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String IMAGE_VISIBILITY_KEY = "imageVisibility";

    private boolean imageVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        // Получаем данные о теме из Intent
        String theme = getIntent().getStringExtra("theme");

        // Находим TextView
        TextView textView = findViewById(R.id.title_education);

        // Устанавливаем текст в TextView
        textView.setText(theme);

        // Restore image visibility state
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        imageVisible = prefs.getBoolean(IMAGE_VISIBILITY_KEY, false);
        updateImageVisibility();
    }

    private void updateImageVisibility() {
        // Находим ImageView и устанавливаем видимость изображения
        ImageView imageView = findViewById(R.id.imageView4);
        imageView.setVisibility(imageVisible ? View.VISIBLE : View.GONE);
    }

    public void lectureActivity(View v) {
        TextView titleEducationTextView = findViewById(R.id.title_education);
        String selectedTheme = titleEducationTextView.getText().toString();
        Intent intent = new Intent(this, LectureActivity.class);
        // Передаем значение через Intent
        intent.putExtra("theme", selectedTheme);
        startActivityForResult(intent, REQUEST_CODE);
    }

    public void taskActivity(View v) {
        Intent intent = new Intent(this, TaskActivity.class);
        startActivity(intent);
    }

    public void testActivity(View v) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            imageVisible = data.getBooleanExtra("showImage", false);
            updateImageVisibility();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Save image visibility state
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(IMAGE_VISIBILITY_KEY, imageVisible);
        editor.apply();
    }
}
