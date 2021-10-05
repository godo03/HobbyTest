package com.thesis.let.webservicedemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.thesis.let.webservicedemo.databases.DatabaseHelper;
import com.thesis.let.webservicedemo.classes.HttpsTrustManager;
import com.thesis.let.webservicedemo.databases.DatabaseHelperAnime;
import com.thesis.let.webservicedemo.databases.MyAdapter;
import com.thesis.let.webservicedemo.classes.QuestionClass;
import com.thesis.let.webservicedemo.classes.ReviewDatabaseHelper;
import com.thesis.let.webservicedemo.screens.Home;
import com.thesis.let.webservicedemo.screens.ResultActivity;
import com.thesis.let.webservicedemo.screens.ResultActivity5;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity5 extends AppCompatActivity {
    DatabaseHelperAnime myDb;
    ReviewDatabaseHelper rDb;
    int score=0;
    int qid=0;
    int qcount=0;
    String url,playerName, choice, answer;
    TextView txtQuestion, tvTimer, tvScore, tvQuestionNumber, playerName_tv, id_tv;
    Button opt1,opt2,opt3,opt4;
    QuestionClass currentQ, cansQ;

    CountDownTimer countDownTimer;
    Integer timeValue = 20;
    String category = "Manga/Anime";


    String token;
    boolean qtype=true;
    ArrayList<QuestionClass> question = new ArrayList<QuestionClass>();
    ArrayList<QuestionClass> cquestion = new ArrayList<QuestionClass>();
    int cat=31;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelperAnime(this);
        rDb = new ReviewDatabaseHelper(this);
        txtQuestion=findViewById(R.id.tvQuestion);
        tvTimer=findViewById(R.id.tvTimer);
        tvQuestionNumber=findViewById(R.id.tvQuestionNumber);
        tvScore = findViewById(R.id.tvScore);
        playerName_tv = findViewById(R.id.playerName_tv_list);
        id_tv=findViewById(R.id.id_item_list);

        opt1 =  findViewById(R.id.btnOptionA);
        opt2 =  findViewById(R.id.btnOptionB);
        opt3 =  findViewById(R.id.btnOptionC);
        opt4 =  findViewById(R.id.btnOptionD);

        final Intent intent = getIntent();
        playerName = intent.getStringExtra("PlayerName");

        playerName_tv.setText(playerName);

        rDb.deleteAll();

        loadQ();

        countDownTimer = new CountDownTimer(22000, 1000) {
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("Time Left: "+String.valueOf(timeValue) + "\"");
                timeValue -= 1;



                //if (txtQuestion.toString().isEmpty() && timeValue == -1){
                //    meh();

                //  }

                if (timeValue == -1 && qid > 9) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bundleClick();
                            addData();


                        }
                    }, 0);
                }

                else if (txtQuestion.getText().toString().isEmpty()){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            onStop();
                            Toast.makeText(MainActivity5.this, "Loading questions... Please wait", Toast.LENGTH_LONG).show();

                        }
                    },0);
                }


                else if (timeValue == -1){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestionView();

                        }
                    },0);
                }
                else if(timeValue < 5){
                    tvTimer.setTextColor(Color.RED);
                }
                else if(timeValue >= 5){
                    tvTimer.setTextColor(Color.WHITE);
                }



            }
            public void onFinish() {
                setQuestionView();
                //showDialog(false);
            }
        }.start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        countDownTimer.start();
    }

    //When activity is destroyed then this will cancel the timer
    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }
    public void bundleClick(){
        Intent i = new Intent(MainActivity5.this, ResultActivity5.class);
        qcount=0;
        i.putExtra("PlayerName", playerName);
        i.putExtra("Score",score);
        i.putExtra("Category",category);
        i.putExtra("token",token);
        startActivityForResult(i,1);
    }
    public void addData(){

        //boolean isInserted =
        myDb.insertData(playerName_tv.getText().toString(), tvScore.getText().toString());

       /* if(isInserted
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Data not Inserted", Toast.LENGTH_LONG).show();

        */
    }
    public void insertData(){
        boolean isInserted = rDb.insertData(cansQ.toString(),  choice);
        /*if(isInserted)
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Data not Inserted", Toast.LENGTH_LONG).show();

         */
    }



    public void clickOption1(View v){
        if(txtQuestion.getText().toString().isEmpty()) {
            Toast.makeText(this, "Loading questions. Please make sure you are connected to the Internet", Toast.LENGTH_LONG).show();

        }else {
            handleButtonClick(1);
            choice = opt1.getText().toString();
        }
    }
    public void clickOption2(View v){
        if(txtQuestion.getText().toString().isEmpty()) {
            Toast.makeText(this, "Loading questions. Please make sure you are connected to the Internet", Toast.LENGTH_LONG).show();

        }else {
            handleButtonClick(2);
            choice = opt2.getText().toString();
        }
    }
    public void clickOption3(View v){
        if(txtQuestion.getText().toString().isEmpty()) {
            Toast.makeText(this, "Loading questions. Please make sure you are connected to the Internet", Toast.LENGTH_LONG).show();

        }else {
            handleButtonClick(3);
            choice = opt3.getText().toString();
        }
    }
    public void clickOption4(View v){
        if(txtQuestion.getText().toString().isEmpty()) {
            Toast.makeText(this, "Loading questions. Please make sure you are connected to the Internet", Toast.LENGTH_LONG).show();

        }else {
            handleButtonClick(4);
            choice = opt4.getText().toString();
        }
    }


    void handleButtonClick(int b)
    {


        opt1.setEnabled(false);
        opt2.setEnabled(false);
        opt3.setEnabled(false);
        opt4.setEnabled(false);

        switch(b) {
            case 1: if (opt1.getText().toString().equals(cquestion.get(qid - 1).getAnswer())) {
                score++;
                if(timeValue >5){
                    score = score + 999;
                    Toast.makeText(this, "+1000 points!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    score = score + 499;
                    Toast.makeText(this, "+500 points!!", Toast.LENGTH_SHORT).show();
                }
                tvScore.setText("Score: " + score);
            }else{
                Toast.makeText(this, "Incorrect :(", Toast.LENGTH_SHORT).show();

            }
                break;
            case 2: if (opt2.getText().toString().equals(cquestion.get(qid - 1).getAnswer())) {
                score++;
                if(timeValue >5){
                    score = score + 999;
                    Toast.makeText(this, "+1000 points!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    score = score + 499;
                    Toast.makeText(this, "+500 points!!", Toast.LENGTH_SHORT).show();
                }
                tvScore.setText("Score: " + score);

            }else{
                Toast.makeText(this, "Incorrect :(", Toast.LENGTH_SHORT).show();
            }
                break;
            case 3: if (opt3.getText().toString().equals(cquestion.get(qid - 1).getAnswer())){
                score++;
                if(timeValue >5){
                    score = score + 999;
                    Toast.makeText(this, "+1000 points!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    score = score + 499;
                    Toast.makeText(this, "+500 points!!", Toast.LENGTH_SHORT).show();
                }
                tvScore.setText("Score: " + score);
            }else{
                Toast.makeText(this, "Incorrect :(", Toast.LENGTH_SHORT).show();

            }
            case 4: if (opt4.getText().toString().equals(cquestion.get(qid - 1).getAnswer())){
                score++;
                if(timeValue >5){
                    score = score + 999;
                    Toast.makeText(this, "+1000 points!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    score = score + 499;
                    Toast.makeText(this, "+500 points!!", Toast.LENGTH_SHORT).show();
                }
                tvScore.setText("Score: " + score);
            }else{
                Toast.makeText(this, "Incorrect :(", Toast.LENGTH_SHORT).show();

            }

        }
        if(qid>9){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    addData();
                    bundleClick();
                    onStop();

                }


            }, 0);
        }
        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    insertData();
                    setQuestionView();

                }
            },0);
        }
    }

    void parseJson(String out) {
        try {
            JSONObject resJson = new JSONObject(out);
            String rescode=	resJson.getString("response_code");
            int code =Integer.parseInt(rescode);
            if(code==0){
                String result=	resJson.getString("results");
                JSONArray resArray = new JSONArray(result);
                for (int i=0; i<resArray.length();i++){
                    JSONObject qjson = resArray.getJSONObject(i);
                    String q = qjson.getString("question");
                    String cans = qjson.getString("correct_answer");
                    String ians = qjson.getString("incorrect_answers");
                    JSONArray ans = new JSONArray(ians);
                    String ians1 = ans.getString(0);

                    String ians2;
                    String ians3;
                    if(cans.equalsIgnoreCase("True") || cans.equals("False")){
                        ians2 = "";
                        ians3="";
                    }else{
                        ians2 = ans.getString(1);
                        ians3 = ans.getString(2);
                    }
                    Random rand = new Random();
                    int  n = rand.nextInt(4) + 1;
                    QuestionClass que=null;
                    switch(n){
                        case 1 : que = new QuestionClass(q,ians1,ians2,ians3,cans);
                            break;
                        case 2 :  que = new QuestionClass(q,ians1,ians2,cans,ians3);
                            break;
                        case 3 :  que = new QuestionClass(q,ians1,cans,ians3,ians2);
                            break;
                        case 4 :  que = new QuestionClass(q,cans,ians2,ians3,ians1);
                            break;
                    }
                    QuestionClass cq = new QuestionClass(q,cans);
                    question.add(que);
                    cquestion.add(cq);
                }
                setQuestionView();
            }
            else {
                Log.d("May Mali Dito","No response");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("May Mali Dito","JSON Error");
        }
    }
    private void setQuestionView(){
        tvQuestionNumber.setText("Question#: "+ (1+qid));
        currentQ=question.get(qid);
        cansQ = cquestion.get(qid);
        txtQuestion.setText(Html.fromHtml(currentQ.getQuestion()));

        insertData();
        //   opt1.setText(Html.fromHtml(currentQ.getOptionA()));
        //  opt2.setText(Html.fromHtml(currentQ.getOptionB()));

        opt1.setText(Html.fromHtml(currentQ.getOptionA()));
        opt2.setText(Html.fromHtml(currentQ.getOptionB()));
        opt3.setText(Html.fromHtml(currentQ.getOptionC()));
        opt4.setText(Html.fromHtml(currentQ.getOptionD()));



        if(opt1.getText().toString().equals("")) {
            opt1.setVisibility(View.GONE);
            qtype=false;
        }
        else {
            opt1.setVisibility(View.VISIBLE);
        }
        if(opt2.getText().toString().equals("")) {
            opt2.setVisibility(View.GONE);
            qtype=false;
        }
        else {
            opt2.setVisibility(View.VISIBLE);
        }
        if(opt3.getText().toString().equals("")) {
            qtype=false;
            opt3.setVisibility(View.GONE);
        }
        else {
            opt3.setVisibility(View.VISIBLE);

        }
        if(opt4.getText().toString().equals("")) {
            qtype=false;
            opt4.setVisibility(View.GONE);
        }
        else {
            opt4.setVisibility(View.VISIBLE);
        }


        //opt1.setAlpha(0.5f);
        //opt2.setAlpha(0.5f);
        //opt3.setAlpha(0.5f);
        //opt4.setAlpha(0.5f);
        //opt1.setBackgroundResource(R.drawable.button_b);
        //opt2.setBackgroundResource(R.drawable.button_b);
        //opt3.setBackgroundResource(R.drawable.button_b);
        //opt4.setBackgroundResource(R.drawable.button_b);

        opt1.setEnabled(true);
        opt2.setEnabled(true);
        opt3.setEnabled(true);
        opt4.setEnabled(true);

        qcount++;
        qid++;


        timeValue = 20;
        countDownTimer.cancel();
        countDownTimer.start();

    }























    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = getIntent().getExtras();
        cat = bundle.getInt("cat");
        loadQ();
    }
    void loadQ()
    {
        HttpsTrustManager.allowAllSSL();
        score=0;
        if ((token!=null))
            url =  "token="+token+"&amount=10"+"&category="+cat;
        else
            url ="&category=31";
        url = "https://opentdb.com/api.php?amount=10&category=31"+url;

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        Log.d("TAG",url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject json = new JSONObject(response);

                            if(response!=null)
                            {
                                parseJson(response);
                                Log.d("TAG",response);
                            }

                            else{
                                networkDialog();
                                Log.d("TAG",response);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("TAG",e.getMessage());
                            Toast.makeText(getApplicationContext(),"Loading... Please wait..."+response,Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG","Volley Error");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void networkDialog() {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        loadQ();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setMessage("Network error occured: Retry?").setPositiveButton("Yes",dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
    public void showDialog(Boolean correct) {
        final Dialog dialog = new Dialog(MainActivity5.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (dialog.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            dialog.getWindow().setBackgroundDrawable(colorDrawable);
        }
        dialog.setContentView(R.layout.dialog_correct);
        dialog.setCancelable(false);
        dialog.show();
        onPause();
        TextView dialogText = dialog.findViewById(R.id.correctText);
        Button buttonNext = dialog.findViewById(R.id.dialogNext);
        if (correct) {
            dialogText.setText("Correct!!!");
        } else {
            dialogText.setText("incorrect!!\n the Correct is \n " + (cquestion.get(qid - 1).getAnswer()));
        }
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (qid < question.size() - 1) {
                    qid++;

                    //   resetColor();
                    //   enableButton();
                } else {
                    endGameDialog(score);
                }
            }
        });

    }

    public void endGameDialog(Integer score) {
        final Dialog endDialog = new Dialog(MainActivity5.this);
        endDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (endDialog.getWindow() != null) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.TRANSPARENT);
            endDialog.getWindow().setBackgroundDrawable(colorDrawable);
        }
        endDialog.setContentView(R.layout.dialog_correct);
        endDialog.setCancelable(false);
        endDialog.show();
        onPause();
        TextView correctText = endDialog.findViewById(R.id.correctText);
        correctText.setText("Your Score is "+score.toString());
        Button buttonNext = endDialog.findViewById(R.id.dialogNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                endDialog.dismiss();

                finish();
            }
        });
    }

    public void endGame(View v){
        onStop();
        Intent intention = new Intent(this, Home.class);
        startActivity(intention);
    }


    public static class SplashScreen extends AppCompatActivity {
        private ImageView iv;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash_screen);

            iv = findViewById(R.id.iv);
            Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
            iv.startAnimation(myanim);
            final Intent i = new Intent(this, Home.class);
            Thread timer = new Thread (){
                public void run(){
                    try{
                        sleep(1000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    finally {
                        startActivity(i);
                    }
                }
            };timer.start();


        }
    }
}
