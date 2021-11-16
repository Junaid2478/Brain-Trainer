package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView resultsView;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumView;
    int correctAnswer;
    TextView pointsView;
    int numberOfQuestions = 0;
    TextView timerView;
    TextView highScoreTextView;
    int points;
    Button playAgainButton;
    public String operator;


    public void playAudio() {

        MediaPlayer mplayer= MediaPlayer.create(MainActivity.this,R.raw.hurt);
        mplayer.start();
    }

    public void playAgain (View view) {

        points = 0;
        numberOfQuestions = 0;
        timerView.setText("30s");
        pointsView.setText("0/0"+ "\n");
        resultsView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();

        new CountDownTimer(31000, 1000) {


            @Override
            public void onTick(long millisUntilFinished) {

                timerView.setText(String.valueOf(millisUntilFinished / 1000 + "s"));

            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                timerView.setText("0s");
                highScoreTextView.setText("Score");
                resultsView.setText("Your points:" + Integer.toString(points) +"/" + Integer.toString(numberOfQuestions));


            }
        }.start();
        playAudio();



    }

    public void generateQuestion() {
        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);

        correctAnswer = random.nextInt(4);
        int incorrectAnswer;

        int Result = random.nextInt(5);


        switch (Result) {
            case 1:
                operator = " + ";
                answers.clear();

                for (int i = 0; i < 4; i++) {

                    if (i == correctAnswer) {
                        answers.add(a + b);
                    } else {
                        incorrectAnswer = random.nextInt(41);

                        while (incorrectAnswer == a + b) {
                            incorrectAnswer = random.nextInt(41);
                        }
                        answers.add(incorrectAnswer);
                    }


                }
                break;

            case 2:
                operator = " x ";
                answers.clear();

                for (int i = 0; i < 4; i++) {

                    if (i == correctAnswer) {
                        answers.add(a * b);
                    } else {
                        incorrectAnswer = random.nextInt(41);

                        while (incorrectAnswer == a * b) {
                            incorrectAnswer = random.nextInt(41);
                        }
                        answers.add(incorrectAnswer);
                    }


                }
                break;

            case 3:
                operator = " - ";

                answers.clear();

                for (int i = 0; i < 4; i++) {

                    if (i == correctAnswer) {
                        answers.add(a - b);
                    } else {
                        incorrectAnswer = random.nextInt(41);

                        while (incorrectAnswer == a - b) {
                            incorrectAnswer = random.nextInt(41);
                        }
                        answers.add(incorrectAnswer);
                    }

                }
                break;

            case 4:
                operator = " ÷ ";

                answers.clear();

                for (int i = 0; i < 4; i++) {

                    if (i == correctAnswer) {
                        answers.add(a / b);
                    } else {
                        incorrectAnswer = random.nextInt(41);

                        while (incorrectAnswer == a / b) {
                            incorrectAnswer = random.nextInt(41);
                        }
                        answers.add(incorrectAnswer);
                    }

                }
                break;

        }
        sumView.setText(Integer.toString(a) + operator + Integer.toString(b));
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }


    public void chooseAnswer(View view) {

        if (view.getTag().toString().equals(Integer.toString(correctAnswer))) {

            points++;
            resultsView.setText("Correct!");
        }
        else {
            resultsView.setText("Wrong!");
        }
        numberOfQuestions++;
        pointsView.setText(Integer.toString(points) +"/" + Integer.toString(numberOfQuestions));
        generateQuestion();


    }



    public void goClicked(View view) {

        goButton.setVisibility(View.INVISIBLE);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = (Button) findViewById(R.id.goButton);

        sumView = (TextView) findViewById(R.id.sumTextView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);


        resultsView = (TextView) findViewById(R.id.resultTextView);
        pointsView = (TextView) findViewById(R.id.pointsTextView);
        highScoreTextView = (TextView) findViewById(R.id.highScoreTextView);
        timerView = (TextView) findViewById(R.id.timerTextView);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);

        playAgain(findViewById(R.id.playAgainButton));

    }
}