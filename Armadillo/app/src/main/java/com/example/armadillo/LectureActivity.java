package com.example.armadillo;

import android.os.Bundle;
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
    }
}