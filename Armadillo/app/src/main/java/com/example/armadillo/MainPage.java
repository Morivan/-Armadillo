package com.example.armadillo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        Button lecturesButton = findViewById(R.id.button_lectures);
        Button tasksButton = findViewById(R.id.button_tasks);

        lecturesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLecturesActivity();
            }
        });

        tasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTasksActiviy();
            }
        });
    }

    private void openLecturesActivity(){
        Intent intent = new Intent(MainPage.this, LecturesActivity.class);
        startActivity(intent);
    }

    private void openTasksActiviy(){
        Intent intent = new Intent(MainPage.this, TasksActivity.class);
        startActivity(intent);
    }
}