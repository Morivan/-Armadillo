package com.example.armadillo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LectureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture);

        // Получаем переданный текст из Intent
        String selectedTheme = getIntent().getStringExtra("theme");

        // Находим TextView заголовка и устанавливаем текст
        TextView titleTextView = findViewById(R.id.title_lecture);
        titleTextView.setText(selectedTheme);

        // Получаем текст лекции из утилиты LectureUtils
        String lectureText = LectureUtils.getLecture(selectedTheme);

        TextView bodyTextView = findViewById(R.id.body_lecture);
        bodyTextView.setText(lectureText);

        // Находим кнопку завершения лекции
        Button backButton = findViewById(R.id.backButton);

        // Устанавливаем слушатель кликов для кнопки
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Завершаем текущую активность и возвращаемся на предыдущий экран
                backButtonClicked(v);
            }
        });
    }

    public void backButtonClicked(View v) {
        // Завершаем текущую активность и возвращаемся на предыдущий экран
        Intent intent = new Intent();
        intent.putExtra("showImage", true); // Set to true to show the image in EducationActivity
        setResult(RESULT_OK, intent);
        finish();
    }
}
