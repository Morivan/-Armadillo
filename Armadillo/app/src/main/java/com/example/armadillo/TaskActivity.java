package com.example.armadillo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class TaskActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;

    // Correct answer
    private final String correctAnswer = "Duh brx wrr, Euxwxv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        editText = findViewById(R.id.answer);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the user input matches the correct answer
                String userInput = editText.getText().toString().trim();
                if (userInput.equalsIgnoreCase(correctAnswer)) {
                    // Show the correct dialog fragment
                    showCorrectDialog();
                } else {
                    // Show the incorrect dialog fragment
                    showIncorrectDialog();
                    editText.getText().clear();
                }
            }
        });
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

