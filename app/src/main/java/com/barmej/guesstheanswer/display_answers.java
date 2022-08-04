package com.barmej.guesstheanswer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class display_answers extends AppCompatActivity {
    private TextView mtextviewquestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_answers);
        mtextviewquestion = findViewById(R.id.correct_answer);
        String answer = getIntent().getStringExtra("question_answer");
        if (answer != null) {
            mtextviewquestion.setText(answer);

        }

    }

    public void onReturnClicked(View view) {

        finish();
    }
}
