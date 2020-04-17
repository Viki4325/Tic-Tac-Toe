package com.example.gameconnnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //2 to represent empty
    int[] gameState={2,2,2,2,2,2,2,2,2};

    //create array of array of winning positions
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    //0: yellow //1: red
    int activePlayer=0;

    boolean gameActive=true;


    public void dropIn(View view)
    {
        //we want to access the variable view passed in the parameter
        //so that we know which one was tapped on
        ImageView counter= (ImageView) view;

        int tappedCounter= Integer.parseInt(counter.getTag().toString());

        //play the game only if the spot is empty
        //cannot re-tap the same spot again and again
        //and the game hs to be active to continue play
        //if someone has already won the players can't continue

        if(gameState[tappedCounter]==2 && gameActive)
        {
            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if(activePlayer==0)
            {
                counter.setImageResource(R.drawable.yellow);

                activePlayer=1;
            }
            else
            {
                counter.setImageResource(R.drawable.red);

                activePlayer=0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);


            for(int[] winningPosition: winningPositions)
            {
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]] && gameState[winningPosition[1]]==gameState[winningPosition[2]] && gameState[winningPosition[0]] !=2)
                {
                    //someone did win
                    gameActive=false;

                    String winner="";
                    if(activePlayer == 1)
                    {
                        winner="Yellow";
                    }
                    else
                    {
                         winner="Red";
                    }

                    Button playAgainBtn=(Button) findViewById(R.id.PlayAgainBtn);
                    TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner+ " has Won!");

                    playAgainBtn.setVisibility(view.VISIBLE);
                    winnerTextView.setVisibility(view.VISIBLE);
                }


            }
        }
        else if(gameState[tappedCounter]!=2 && gameActive)
        {
            Button playAgainBtn=(Button) findViewById(R.id.PlayAgainBtn);
            TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);
            winnerTextView.setText("Game Tied!!");

            playAgainBtn.setVisibility(view.VISIBLE);
            winnerTextView.setVisibility(view.VISIBLE);
        }
    }

    public void playAgain(View view)
    {

        Button playAgainButton= findViewById(R.id.PlayAgainBtn);
        TextView winnerTextView=findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(view.INVISIBLE);
        winnerTextView.setVisibility(view.INVISIBLE);

        //loop through all objects in the layout and remove them
        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);
        for (int i=0; i<gridLayout.getChildCount();i++)
        {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            //take the image view and remove the image source
            counter.setImageDrawable(null);
        }

        Log.i("INFO: ","Play Agan working");

        //reset all the game variables that were used in the game
        for (int i=0;i<gameState.length;i++)
        {
            gameState[i]= 2;
        }
        activePlayer=0;

        gameActive=true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
