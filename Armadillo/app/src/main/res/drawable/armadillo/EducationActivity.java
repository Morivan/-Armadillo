package com.example.armadillo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class EducationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
    }
    public void  lectureActivity(View v){
        Intent intent = new Intent(this, LectureActivity.class);
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