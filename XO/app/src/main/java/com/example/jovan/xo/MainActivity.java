package com.example.jovan.xo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0 = X 1 = O

    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameIsActive = true;
    //2 = unplayed



    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        int tapCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tapCounter] == 2 && gameIsActive){

            gameState[tapCounter] = activePlayer;



        counter.setTranslationY(-1000f);
        if(activePlayer == 0){

            counter.setImageResource(R.drawable.iks);
            activePlayer=1;
        }else {
            counter.setImageResource(R.drawable.oks);
            activePlayer=0;
        }
        counter.animate().translationYBy(1000f).rotation(1080).setDuration(400);

            for(int[] winningPosition: winningPositions){
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2){

                    gameIsActive = false;

                        String winner = "Tac";


                    if(gameState[winningPosition[0]] == 0) {

                        winner = "Tic";
                    }

                    TextView text = (TextView) findViewById(R.id.textView);
                    text.setText(winner + " has won!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
                    layout.setVisibility(View.VISIBLE);


                } else {

                    boolean gameIsOver = true;

                    for (int counterState: gameState){
                        if(counterState == 2)
                            gameIsOver = false;
                    }

                          if (gameIsOver){
                            TextView text = (TextView) findViewById(R.id.textView);
                            text.setText("It's a draw!");
                            LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
                            layout.setVisibility(View.VISIBLE);

                        }

                    }
                }


            }


        }



    public void playAgain(View view){

        gameIsActive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
        layout.setVisibility(View.INVISIBLE);
         activePlayer = 0;
         for(int i = 0; i < gameState.length;i++ ){

             gameState[i]=2;
         }
        GridLayout grid = (GridLayout)findViewById(R.id.gridLayout);
        for(int i = 0; i < grid.getChildCount();i++){

            ((ImageView) grid.getChildAt(i)).setImageResource(0);

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
