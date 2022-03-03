package com.example.tictaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer =0;  //0 - 0 , 1 - x 9 - empty
    int[] gameState = {9,9,9,9,9,9,9,9,9};
    int[][] winingPos = {{0,1,2},{3,4,5},{5,6,7},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean isGameFinished = false;
    int c = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {
        c++;
        ImageView counter = (ImageView) view;
        TextView informView = (TextView) findViewById(R.id.informView);
        Button button = (Button) findViewById(R.id.button) ;
        int index = Integer.parseInt((String) counter.getTag());

    if (gameState[index] == 9 && !isGameFinished) {
        counter.setTranslationY(-1500);

        if(activePlayer == 0) {
            counter.setImageResource(R.drawable.zero);
            activePlayer = 1;
            gameState[index] = 0;
        }
        else {
            counter.setImageResource(R.drawable.cross);
            activePlayer = 0;
            gameState[index] = 1;
        }

//        if(c == 9) {
//            button.animate().alpha(1).setDuration(300);
//            button.setClickable(true);
//            informView.setText("Draw");
//        }

        counter.animate().translationYBy(1500).rotation(3600).setDuration(400);
        for(int[] winingP  : winingPos) {
            if (gameState[winingP[0]] == gameState[winingP[1]] && gameState[winingP[1]] == gameState[winingP[2]] && gameState[winingP[0]] != 9) {
                if (gameState[winingP[0]] == 1) informView.setText("Player 2 won");
                else informView.setText("Player 1 won");
                button.animate().alpha(1).setDuration(300);
                button.setClickable(true);
                isGameFinished = true;


            }
        }
            }

    }

    public void reset(View view) {
        c = 0;
        for(int i = 0; i<9; i++) gameState[i] = 9;


        TextView informView = (TextView) findViewById(R.id.informView);
        Button button = (Button) findViewById(R.id.button) ;

        informView.setText("");
        button.animate().alpha(0).setDuration(300);
        button.setClickable(false);

        ImageView[] arr = new ImageView[9];
        int[] indexArr = {R.id.grid0,R.id.grid1,R.id.grid2,R.id.grid3,R.id.grid4,R.id.grid5,R.id.grid6,R.id.grid7,R.id.grid8};
        for(int i =0; i<9; i++) arr[i] = (ImageView) findViewById(indexArr[i]);

        for(ImageView v : arr) v.setImageDrawable(null);
        activePlayer = 0;
        isGameFinished = false;


    }
}