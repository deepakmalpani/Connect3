package com.example.admin.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {
    int activeplayer=0;
    boolean active=true;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winpositions={{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{0,3,6},{1,4,7},{2,5,8}};
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedcounter=Integer.parseInt(counter.getTag().toString());
        if(gamestate[tappedcounter]==2 && active) {
            counter.setAlpha(0f);
            gamestate[tappedcounter]=activeplayer;
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.naruto2);
                counter.animate().alpha(1f).rotation(360f).setDuration(500);
                activeplayer = 1;
            } else if (activeplayer == 1) {
                activeplayer = 0;
                counter.setImageResource(R.drawable.tictactoe2);
                counter.animate().alpha(1f).rotation(360f).setDuration(500);
            }

        }
        counter.animate().alpha(1f).rotation(360f).setDuration(500);
        for(int[] winposition :winpositions){
            if(gamestate[winposition[0]]==gamestate[winposition[1]] && gamestate[winposition[1]]==gamestate[winposition[2]] &&
            gamestate[winposition[0]]!=2){
                active=false;
                System.out.println("Someone has won");
                String win="Player 1";
                if(gamestate[winposition[0]]==1){
                    win="Player 2";
                }
                System.out.println(gamestate[winposition[0]]);
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(win+" has won");
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
                linearLayout.setAlpha(1f);
                linearLayout.setVisibility(VISIBLE);
            }
            else{
                boolean gameover=true;
                for(int i:gamestate){
                    if(i==2){
                        gameover=false;
                    }
                }
                if(gameover){
                    TextView textView = (TextView) findViewById(R.id.textView);
                    textView.setText("Its a draw");
                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
                    linearLayout.setVisibility(view.VISIBLE);

                }
            }
        }

    }
    public void playagain(View view){
        active=true;
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        linearLayout.setAlpha(0f);
        linearLayout.setVisibility(view.INVISIBLE);
        activeplayer=0;
        for(int i=0;i<gamestate.length;i++){
            gamestate[i]=2;
        }
        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
