package com.example.game2connect;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activeplayer = 0;
    int[] gamestate = {2,2,2,2,2,2,2,2,2};
    boolean gameactive=true;
    int[][] winingPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void counter(View view){
        ImageView counter = (ImageView) view;
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if(gamestate[tappedcounter]==2 && gameactive) {
            gamestate[tappedcounter] = activeplayer;
            counter.setTranslationY(-1500);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1500).setDuration(200);
            for (int[] winingPostion : winingPositions) {
                if (gamestate[winingPostion[0]] == gamestate[winingPostion[1]] && gamestate[winingPostion[1]] == gamestate[winingPostion[2]] && gamestate[winingPostion[0]] != 2) {
                    String winner = "";
                    gameactive=false;
                    if (activeplayer == 1) {
                        winner = "Yellow is the Winner";
                    } else {
                        winner = "Red is the Winner";
                    }

                    Toast.makeText(this, winner, Toast.LENGTH_SHORT).show();
                    Button playagain = (Button)findViewById(R.id.PlayAgainbutton);
                    TextView winnertext = (TextView)findViewById(R.id.textView);
                    playagain.setVisibility(View.VISIBLE);
                    winnertext.setVisibility(View.VISIBLE);
                }
            }
        }
    }
    public void playagain(View view){
        Button playagain = (Button)findViewById(R.id.PlayAgainbutton);
        TextView winnertext = (TextView)findViewById(R.id.textView);
        playagain.setVisibility(View.INVISIBLE);
        winnertext.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout)findViewById(R.id.GridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        activeplayer = 0;
        gameactive=true;
        for(int i=0;i<gamestate.length;i++){
            gamestate[i] = 2;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
