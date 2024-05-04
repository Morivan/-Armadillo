package com.example.armadillo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class TestActivity extends AppCompatActivity {

    private Button button1, button2, button3, button4;

    // Correct answer
    private final String correctAnswer = "Гай Юлий Цезарь";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        button1 = findViewById(R.id.button_lectures2);
        button2 = findViewById(R.id.button_lectures3);
        button3 = findViewById(R.id.button_lectures4);
        button4 = findViewById(R.id.button_lectures5);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(button1.getText().toString().trim());
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(button2.getText().toString().trim());
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(button3.getText().toString().trim());
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(button4.getText().toString().trim());
            }
        });
    }

    private void checkAnswer(String selectedAnswer) {
        if (selectedAnswer.equalsIgnoreCase(correctAnswer)) {
            DialogFragment dialog = new CorrectDialogFragment();
            dialog.show(getSupportFragmentManager(), "CorrectDialogFragment");
        } else {
            DialogFragment dialog = new IncorrectDialogFragment();
            dialog.show(getSupportFragmentManager(), "IncorrectDialogFragment");
        }
    }

    private void showCorrectDialog() {
        DialogFragment dialog = new CorrectDialogFragment();
        dialog.show(getSupportFragmentManager(), "CorrectDialogFragment");
    }

    private void showIncorrectDialog() {
        DialogFragment dialog = new IncorrectDialogFragment();
        dialog.show(getSupportFragmentManager(), "IncorrectDialogFragment");
    }
}
