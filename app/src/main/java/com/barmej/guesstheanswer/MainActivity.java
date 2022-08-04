package com.barmej.guesstheanswer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView mtextviewquestion;

    private  String[] QUESTIONS;
    private static final boolean[] ANSWERS = {
      true ,
       true,
       false,
       false
    };

    private String[] ANSWERS_DETAILS;




    private String mCurrentQuestion , mCurrentAnswerDetails ;
    private boolean mCurrentAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // تضع التطبيق على حالته الافتراضية في حالة لم يتم تغيير اللغة او تم تغييرها ثم لما يشتغل التطبيق يكون اللغة المحطوطة سابقا
        SharedPreferences sharedPreferences = getSharedPreferences("app_pref" , MODE_PRIVATE);
        String appLang = sharedPreferences.getString("app_lang" , "");
        if(!appLang.equals(""))
         LocaleHelper.setLocale(this ,appLang);


        setContentView(R.layout.activity_main);
        mtextviewquestion = findViewById(R.id.text_view_question);
        QUESTIONS = getResources().getStringArray(R.array.question);
        ANSWERS_DETAILS = getResources().getStringArray(R.array.answers_details);
        showNewQuestion();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu , menu);
        return true;

    }

  public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId() == R.id.menuChangeLang){
            showLanguageDialog(); return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }

  }
    private void showLanguageDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.change_lang_text)
                .setItems(R.array.languages ,new  DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialogInterface , int which){
                        String language = "ar";
                        switch (which){
                            case 0:
                                language ="ar";
                                break;
                            case 1:
                                language ="en";
                                break;
                        }
                        saveLanguage(language);
                        LocaleHelper.setLocale(MainActivity.this , language);
                        Intent intent = new Intent(getApplicationContext() , MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

            }
        }).create();
        alertDialog.show();
    }
    private void saveLanguage(String lang){
        SharedPreferences sharedPreferences = getSharedPreferences("app name " , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("app_lang" , lang);
        editor.apply();
    }

    private void showNewQuestion(){
    // كانشأ كائن جديد من نووع راندوم
        Random random = new Random();
        // كانشاة مصفوفة تكون اصغر من او يساوي الصفر وايضا اصغر من مصفوفة الاسئلة
        int randomQuestionIndex = random.nextInt(QUESTIONS.length);
        mCurrentQuestion = QUESTIONS[randomQuestionIndex];
       mCurrentAnswer = ANSWERS[randomQuestionIndex];
       mCurrentAnswerDetails = ANSWERS_DETAILS[randomQuestionIndex];
        mtextviewquestion.setText(mCurrentQuestion);

    }
    public void OnChangeQuestionClicked(View view){
        showNewQuestion();
    }
    public void onTrueClicked (View view){
        if (mCurrentAnswer == true) {
            Toast.makeText(this, "اجابة صحيحة" , Toast.LENGTH_SHORT).show();
            showNewQuestion();
        }
        else {
        Toast.makeText(this , "اجابة خاطئة" , Toast.LENGTH_SHORT).show();
            // قمنا في ال الاكتيفيتي الحالية بارسالها التي نريد الانتقال منها الى الاكتيفيتي التي نريد الانتقال اليها
            Intent intent = new Intent(MainActivity.this , display_answers.class);
            // استدعاء الدالة putEXTRA وتتكون من استرينق اي مفتاح الخاص بالمعلومة التي نريد اضافتها و المعامل الثاني يمثل القيمة الحقيقية التي نريد تمريرها
            intent.putExtra("question_answer" , mCurrentAnswerDetails);
            startActivity(intent);


        }

        }
        public void onFalseClicked(View view){
        if (mCurrentAnswer == false){
            Toast.makeText(this , "اجابة صحيحة " , Toast.LENGTH_SHORT).show();
        }
            else {
                Toast.makeText(this , "اجابة خاطئة ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this , display_answers.class);
            // استدعاء الدالة putEXTRA وتتكون من استرينق اي مفتاح الخاص بالمعلومة التي نريد اضافتها و المعامل الثاني يمثل القيمة الحقيقية التي نريد تمريرها
            intent.putExtra("question_answer" , mCurrentAnswerDetails);
            startActivity(intent);
            }


        }
        public void onShareQuestionClicked(View view){
        Intent intent = new Intent(MainActivity.this , shareActivity.class);
        intent.putExtra(" question text extra" , mCurrentQuestion);
        startActivity(intent);

        }
    }


