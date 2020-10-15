package com.example.math_game;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Level4 extends AppCompatActivity  implements View.OnClickListener{

    Dialog dialog, dialogend;
    private TextView question, textA, textB;
    private  Button optionA, optionB;
    private List<Questions> questionsList;
    int quesNum;
    private FirebaseFirestore firestore;
    private int score;
    private TextView scoreend, qCount;//counter right answers



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level4);


        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //dialog window
        dialog = new Dialog(this); //create new dialog window
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // hide the title
        dialog.setContentView(R.layout.previewdialog4); // path to layout
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//transparent background
        dialog.setCancelable(false); // window cant to be close with the  system back btn

        // button close'x'
        TextView btn_close = (TextView)dialog.findViewById(R.id.button_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level4.this, GameLevels.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
                dialog.dismiss();//close the dia window
            }
        });
        //

        //button 'continue'
        Button btn_continue = (Button)dialog.findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        // //button 'continue'-end
        dialog.show();

//_______________________________________________________________________________
        //dialog window in the end of level
        dialogend = new Dialog(this); //create new dialog window
        dialogend.requestWindowFeature(Window.FEATURE_NO_TITLE); // hide the title
        dialogend.setContentView(R.layout.dialogend4); // path to layout
        dialogend.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//transparent background
        dialogend.setCancelable(false); // window cant to be close with the  system back btn

        TextView score_end = (TextView)dialogend.findViewById(R.id.score);
        score_end.setText("0/10");
        scoreend = score_end;



        // button close'x'
        TextView btn_close2 = (TextView)dialogend.findViewById(R.id.button_close);
        btn_close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level4.this, GameLevels.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
                dialogend.dismiss();//close the dia window
            }
        });
        //

        //button 'continue'
        Button btn_continue2 = (Button)dialogend.findViewById(R.id.btn_continue);
        btn_continue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level4.this, Level5.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
                dialogend.dismiss();
            }
        });
        // //button 'continue'-end

        //button 'restart'
        Button btn_restart = (Button)dialogend.findViewById(R.id.btn_restart);
        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level4.this, Level4.class);
                    startActivity(intent);finish();
                }catch (Exception e){

                }
                dialogend.dismiss();
            }
        });
        // //button 'continue'-end
//___________________________________________________________________

        // button "back"
        Button btn_back = (Button)findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                   Intent intent = new Intent(Level4.this, GameLevels.class);
                    startActivity(intent);finish();
                }catch(Exception e){

                }
            }
        });
        // button "back" - end



        //________________________________________________
        question = findViewById(R.id.question);

        optionA = findViewById(R.id.buttonA);
        optionB = findViewById(R.id.buttonB);
        textA = findViewById(R.id.textA);
        textB = findViewById(R.id.textB);


        optionA.setOnClickListener(this);
        optionB.setOnClickListener(this);

        qCount = findViewById(R.id.count);

        firestore = FirebaseFirestore.getInstance();

        getQuestionList();

    }
    private void getQuestionList(){
        questionsList = new ArrayList<>();
        firestore
                .collection("Level4").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot questions = task.getResult();

                    for (QueryDocumentSnapshot doc : questions){
                        questionsList.add(new Questions(doc.getString("question"),
                                doc.getString("A"),
                                doc.getString("B"),
                                Integer.valueOf(doc.getString("answer")),
                                doc.getString("textA"),
                                doc.getString("textB")
                                ));
                    }

                    setQuestion();
                }else{
                    Toast.makeText(Level4.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
                dialogend.cancel();

            }
        });



    }

    private void setQuestion(){
        question.setText(questionsList.get(0).getQuestion());
        optionA.setText(questionsList.get(0).getOption1());
        optionB.setText(questionsList.get(0).getOption2());

        textA.setText(questionsList.get(0).getTextA());
        textB.setText(questionsList.get(0).getTextB());

        qCount.setText(String.valueOf(1) + "/" + String.valueOf(questionsList.size()));

        quesNum = 0;

    }

//___________________________________________________________________
    // System button - start
    @Override
    public void onBackPressed(){
        //start
        try {
            Intent intent = new Intent(Level4.this, GameLevels.class);
            startActivity(intent);finish();
        }catch (Exception e){

        }
        //end
    }//System button - end
 //______________________________________________________________________

    @Override
    public void onClick(View view) {
        int selectedOption = 0;
        switch (view.getId()){
            case  R.id.buttonA:
                selectedOption = 1;
                break;

            case  R.id.buttonB:
                selectedOption = 2;
                break;

            default:
        }

        checkAnswer(selectedOption, view);
    }

    private void checkAnswer(int selectedOption, View view){
        if(selectedOption ==questionsList.get(quesNum).getCorrectAns()){
            //right answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            score++;

        }else {
            //wrong answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        }


        changeQuestion();
    }
    private void changeQuestion(){
        if(quesNum <questionsList.size() - 1){

            quesNum++;

            playAnim(question, 0, 0);
            playAnim(optionA, 0, 1);
            playAnim(optionB, 0, 2);
            playAnim(textA, 0, 3);
            playAnim(textB, 0, 4);

            qCount.setText(String.valueOf(quesNum+1) + "/" + String.valueOf(questionsList.size()));



        }else {
//            Intent intent = new Intent(Level2.this,ScoreActivity.class);
//            intent.putExtra("SCORE", String.valueOf(score) + "/" + String.valueOf(questionsList.size()));
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
            scoreend.setText(String.valueOf(score) + "/" + String.valueOf(questionsList.size()));
            dialogend.show();
        }
    }

    private void playAnim(final View view, final int value, final int viewNum){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500)
                .setStartDelay(100).setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        if(value ==0){
                            switch (viewNum){
                                case 0:
                                    ((TextView)view).setText(questionsList.get(quesNum).getQuestion());
                                    break;

                                case 1:
                                    ((Button)view).setText(questionsList.get(quesNum).getOption1());
                                    break;
                                case 2:
                                    ((Button)view).setText(questionsList.get(quesNum).getOption2());
                                    break;
                                case 3:
                                    ((TextView)view).setText(questionsList.get(quesNum).getTextA());
                                    break;
                                case 4:
                                    ((TextView)view).setText(questionsList.get(quesNum).getTextB());
                                    break;
                            }

                            if (viewNum !=0){
                                view.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                            }

                            playAnim(view, 1, viewNum);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
    }

}