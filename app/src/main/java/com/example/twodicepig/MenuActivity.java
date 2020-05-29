package com.example.twodicepig;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
//    public static final String EXTRA_STRING_Pig1 = "com.example.twodicepig.MenuActivity.EXTRA_STRING_Pig1";
//    public static final String EXTRA_STRING_Pig2 = "com.example.twodicepig.MenuActivity.EXTRA_STRING_Pig2";

    EditText pig1;
    EditText pig2;
    Button playButton;
    Button howToButton;
    Button aboutButton;
    ImageView infoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Assigns the corresponding views of each component
//        pig1 = findViewById(R.id.pig1_name);
//        pig2 = findViewById(R.id.pig2_name);
        playButton = findViewById(R.id.play_button);
        howToButton = findViewById(R.id.info_button);
        aboutButton = findViewById(R.id.about_button);
        infoImage = findViewById(R.id.info_image);

        //Opens Main Activity and sends user given player names
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String player1 = pig1.getText().toString();
//                String player2 = pig2.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                intent.putExtra("pig_1_name", player1);
//                intent.putExtra("pig_2_name", player2);
                startActivity(intent);
            }
        });


        howToButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGameTutorial();
            }
        });

        infoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGameTutorial();
            }
        });



    }


    private void showGameTutorial() {

        AlertDialog.Builder info = new AlertDialog.Builder(this)
                .setTitle("How To Play")
                .setMessage("IF:\nDice 1 rolls ONE &\nDice 2 rolls any OTHER number\nTHEN:\nYour current turn is skipped and you lose all your Turn Points.\n\nIF:\nDice 1 & 2 both\nroll ONE\nTHEN:\nYour current turn is skipped and you lose your Overall Points.\n\nIF:\nDice 1 & 2 both\nroll SAME number\nbut NOT ONE\nTHEN:\nYou are forced to roll again\n\n1st person to a score of 50 wins the game. Good luck.\n")
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        info.show();
    }





}
