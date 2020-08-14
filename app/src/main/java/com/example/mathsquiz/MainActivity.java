package com.example.mathsquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    CountDownTimer t;
    EditText answer;
    TextView question;
    TextView counttime;
    int check=0;
    TextView score;
    int ogans;
    int soln;
    int solnop;
    int c=0,tc=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Question();
    }
    public void Question()
    {
        check=0;
        int n1, n2;
        soln = 0;
        solnop = 0;
        String s="";
        Random rand = new Random();
        n1 = rand.nextInt(50);
        n2 = rand.nextInt(50);
        String sn1=String.valueOf(n1);
        String sn2=String.valueOf(n2);
        ogans = rand.nextInt(6);
        question = findViewById(R.id.question);
        if (ogans == 0) {
            soln = n1 + n2;
            s = sn1 + " + " + sn2;
        } else if (ogans == 1) {
            if (n1 > n2) {
                soln = n1 - n2;
                s = sn1 + " - " + sn2;
            } else {
                soln = n2 - n1;
                s = sn2 + " - " + sn1;
            }
        } else if (ogans == 2) {
            soln = n1 * n2;
            s = sn1 + " * " + sn2;
        } else if (ogans == 3) {
            if (n1 > n2) {
                soln = n1 / n2;
                s = sn1 + " / " + sn2;
            } else {
                soln = n2 / n1;
                s = sn2+ " / " + sn1;
            }
        } else if (ogans == 4) {
            soln = (int) Math.pow(n1, n2);
            s = sn1 + " ^ " + sn2;
        } else if (ogans == 5) {
            if (n1 > n2) {
                soln = n1 % n2;
                s = sn1+ " % " + sn2;
            } else {
                soln = n2 % n1;
                s = sn2 + " % " + sn1;
            }
        }
        solnop = soln;
        question.setText(s);
        callTimer();
    }

    public void Submit(View view)
    {
        t.cancel();
        check=1;
        answer=findViewById(R.id.answer);
        String value= answer.getText().toString();
        int finalValue=Integer.parseInt(value);
        if(finalValue==solnop)
        {
            c++;
            tc++;
            Toast toast = Toast.makeText(getApplicationContext(),
                    "CORRECT ANSWER",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        {
            tc++;
            Toast toast = Toast.makeText(getApplicationContext(),
                    "WRONG ANSWER",
                    Toast.LENGTH_SHORT);
            toast.show();
        }
        score = findViewById(R.id.score);
        String s1=String.valueOf(c) + "/" + String.valueOf(tc);
        score.setText(s1);
        answer.setText("");
        Question();
    }
    public void miss()
    {
        tc++;
        score = findViewById(R.id.score);
        String s1=String.valueOf(c) + "/" + String.valueOf(tc);
        Question();
    }
    public void callTimer()
    {
       t=new CountDownTimer(30000,1000){
          @Override
           public void onTick(long i)
          {
              counttime=findViewById(R.id.counttime);
              counttime.setText(String.valueOf((int)(i)/1000));
          }
           public void onFinish()
           {
             miss();
           }
       }.start();
    }

}