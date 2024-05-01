package com.example.armadillo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EducationActivity extends AppCompatActivity {

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
    }
    public void  lectureActivity(View v){
        TextView titleEducationTextView = findViewById(R.id.title_education);
        String selectedTheme = titleEducationTextView.getText().toString();
        Intent intent = new Intent(this, LectureActivity.class);
        // Передаем значение через Intent
        intent.putExtra("theme", selectedTheme);
        startActivity(intent);
    }
    public void  taskActivity(View v){
        Intent intent = new Intent(this, TaskActivity.class);
        startActivity(intent);
    }
    public void  testActivity(View v){
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }
}