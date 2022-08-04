package com.barmej.guesstheanswer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class shareActivity extends AppCompatActivity {
    private String mQuestionText;
    public EditText mEditTextShareTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        mEditTextShareTitle = findViewById(R.id.edit_text_question);
        mQuestionText = getIntent().getStringExtra(" question text extra");
        SharedPreferences sharedPreferences = getSharedPreferences("app pref" , MODE_PRIVATE);
        String questionTitle = sharedPreferences.getString("share title" , "");
        mEditTextShareTitle.setText(questionTitle);
    }
    public void onShareQuestionClicked(View view){
        String questionTitle = mEditTextShareTitle.getText().toString();
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT , questionTitle + "\n" + mQuestionText);
        shareIntent.setType("text/plain");
        startActivity(shareIntent);
    // انشاء متغير من نوع شريت بريفيرنتيس تاخذ معاملين اسم الاعدادات التي نريد انشاءها والثاني نوع الاعدادات التي نرويد انشاءها اي تكون مخصصة فقط لتطبيقتنا
        SharedPreferences sharepreferences = getSharedPreferences( "app pref" , MODE_PRIVATE );
        // استدعاء الدالة edit  يتم تعديل النص
        SharedPreferences.Editor editor = sharepreferences.edit();
        // تم انشاء عنوان تحت عنوان share title عندنا نريد ان انشاء جديد
        editor.putString("share title" , questionTitle);
        // استدعاءه ضروري بدونه لم يتم حفظ التغييرات
        editor.apply();

    }
}