package com.bitzscript.dentaldeck;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by naimesh on 2/16/2017.
 */
public class Quiz extends Activity {
    DBHandler dbHelper = null;
    List<Question_DB> quesList;
    int qid = 0;
    Question_DB currentQ;
    TextView txtQuestion;
    RadioButton rda, rdb, rdc, rdd, rde, rdf;
    TextView txtExplain;
    Button btnNext;
    private SQLiteDatabase myDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);
        dbHelper = new DBHandler(this, getFilesDir().getAbsolutePath());
        try {
            dbHelper.prepareDatabase();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

        quesList = dbHelper.getAllQuesions();
        currentQ = quesList.get(qid);
        txtQuestion = (TextView) findViewById(R.id.question_View);
        rda = (RadioButton) findViewById(R.id.answer1);
        rdb = (RadioButton) findViewById(R.id.answer2);
        rdc = (RadioButton) findViewById(R.id.answer3);
        rdd = (RadioButton) findViewById(R.id.answer4);
        rde = (RadioButton) findViewById(R.id.answer5);
        rdf = (RadioButton) findViewById(R.id.answer6);
        txtExplain = (TextView) findViewById(R.id.explanation_View);
        btnNext = (Button) findViewById(R.id.button_Next);
        setQuestionView();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioGroup grp = (RadioGroup) findViewById(R.id.answers_Group);
                RadioButton answer = (RadioButton) findViewById(grp.getCheckedRadioButtonId());
                currentQ = quesList.get(qid);
                setQuestionView();
            }
        });
    }

    private void setQuestionView() {
        txtQuestion.setText(currentQ.getQuestion());
        rda.setText(currentQ.getAnswer1());
        rdb.setText(currentQ.getAnswer2());
        rdc.setText(currentQ.getAnswer3());
        rdd.setText(currentQ.getAnswer4());
        rde.setText(currentQ.getAnswer5());
        rdf.setText(currentQ.getAnswer6());
        qid++;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
