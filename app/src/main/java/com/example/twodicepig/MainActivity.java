package com.example.twodicepig;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int scoreLimit = 50;
    int overallPointsP1 = 0;
    int overallPointsP2 = 0;
    int turnPoints = 0;
    boolean p1Turn = true;
    int playerImage1;
    int playerImage2;

    String player1 = "Player 1";
    String player2 = "Player 2";
    String winner = "";

    Random rand = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Holds all the dice images
        final int[] diceArray = {
                R.drawable.dice_1,
                R.drawable.dice_2,
                R.drawable.dice_3,
                R.drawable.dice_4,
                R.drawable.dice_5,
                R.drawable.dice_6,
        };


        //Holds default player icon images
        final int[] playerImages = {
                R.drawable.ant_eater, R.drawable.bear, R.drawable.beaver, R.drawable.boar, R.drawable.bull,
                R.drawable.camel, R.drawable.chameleon, R.drawable.cheetah, R.drawable.crocodile, R.drawable.donkey,
                R.drawable.duck, R.drawable.eagle, R.drawable.elk, R.drawable.goat, R.drawable.goat_1,
                R.drawable.goose, R.drawable.hamster, R.drawable.hippo, R.drawable.horse, R.drawable.kangaroo,
                R.drawable.lemur, R.drawable.lion, R.drawable.llama, R.drawable.meerkat, R.drawable.monkey,
                R.drawable.ostrich, R.drawable.owl, R.drawable.panda, R.drawable.parrot, R.drawable.penguin,
                R.drawable.polar_bear, R.drawable.rabbit, R.drawable.raccoon, R.drawable.sloth, R.drawable.snake,
                R.drawable.tiger, R.drawable.walrus, R.drawable.zebra, R.drawable.deer, R.drawable.elephant,
                R.drawable.fox, R.drawable.frog, R.drawable.giraffe, R.drawable.gorilla, R.drawable.hedgehog,
                R.drawable.koala, R.drawable.puma, R.drawable.rhinoceros, R.drawable.turtle, R.drawable.wolf,
        };

        //Holds default player names
        final String[] playerNames = {
                "Ant Eater", "Bear", "Beaver", "Boar", "Bull", "Camel", "Chameleon", "Cheetah", "Crocodile", "Donkey",
                "Duck", "Eagle", "Elk", "Goat", "Cool Goat", "Goose", "Hamster", "Hippo", "Horse", "Kangaroo",
                "Lemur", "Lion", "Llama", "Meerkat", "Monkey", "Ostrich", "Owl", "Panda", "Parrot", "Penguin",
                "Polar Bear", "Rabbit", "Raccoon", "Sloth", "Snake", "Tiger", "Walrus", "Zebra", "Deer", "Elephant",
                "Fox", "Frog", "Giraffe", "Gorilla", "Hedgehog", "Koala", "Puma", "Rhinoceros", "Turtle", "Wolf"
        };


        //Adds all the components to the project
        final Button rollButton;
        final Button holdButton;
        final TextView turnPointsText;
        final TextView overallPointsText;
        final TextView topBarText;
        final ImageView leftDice;
        final ImageView rightDice;
        final TextView noteText;
        final Button nextPlayerButton;
        final ImageView player_image;
        final RecyclerView recyclerView;
        final Button playAgain;
        final ImageView infoImage;


        //Assigns the corresponding views of each component
        rollButton = findViewById(R.id.roll_button);
        holdButton = findViewById(R.id.hold_button);
        turnPointsText = findViewById(R.id.turn_points);
        overallPointsText = findViewById(R.id.overall_points);
        topBarText = findViewById(R.id.top_bar_title);
        leftDice = findViewById(R.id.left_dice);
        rightDice = findViewById(R.id.right_dice);
        noteText = findViewById(R.id.note_text);
        nextPlayerButton = findViewById(R.id.next_button);
        player_image = findViewById(R.id.player_image);
        recyclerView = findViewById(R.id.recyclerView);
        playAgain = findViewById(R.id.play_again);
        infoImage = findViewById(R.id.info_image);


        //Assigns names to each player
        playerImage1 = rand.nextInt(50);
        player1 = playerNames[playerImage1];
        do {
            playerImage2 = rand.nextInt(50);
        } while (playerImage2 == playerImage1);
        player2 = playerNames[playerImage2];
        player_image.setImageResource(playerImages[playerImage1]);


        //Gets the data from MenuActivity and assigns it to proper variables
//        Intent intent = getIntent();
//        if (!intent.getStringExtra("pig_1_name").isEmpty())
//        {
//            player1 = intent.getStringExtra("pig_1_name");
//        }
//        if (!intent.getStringExtra("pig_2_name").isEmpty())
//        {
//            player2 = intent.getStringExtra("pig_2_name");
//        }



        //Sets up initial look of the screen
        leftDice.setVisibility(View.INVISIBLE);
        rightDice.setVisibility(View.INVISIBLE);
        nextPlayerButton.setVisibility(View.INVISIBLE);
        player_image.setVisibility(View.VISIBLE);
        playAgain.setVisibility(View.INVISIBLE);

        turnPointsText.setText(String.valueOf(turnPoints));
        overallPointsText.setText(String.valueOf(overallPointsP1));
        topBarText.setText(player1);
        noteText.setText("");

        recyclerView.setBackgroundColor(Color.YELLOW);



        //Switches all the component views for the next player's turn
        nextPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Resets turn points
                turnPoints = 0;
                noteText.setText("");
                turnPointsText.setText(String.valueOf(turnPoints));

                //Resets to initial look of the screen
                nextPlayerButton.setVisibility(View.INVISIBLE);
                holdButton.setVisibility(View.VISIBLE);
                rollButton.setVisibility(View.VISIBLE);
                leftDice.setVisibility(View.INVISIBLE);
                rightDice.setVisibility(View.INVISIBLE);
                player_image.setVisibility(View.VISIBLE);

                //Updates views and variables for next player's turn
                if (p1Turn) {
                    p1Turn = false;
                    topBarText.setText(player2);
                    overallPointsText.setText(String.valueOf(overallPointsP2));
                    player_image.setImageResource(playerImages[playerImage2]);
                    recyclerView.setBackgroundColor(Color.GREEN);
                }
                else {
                    p1Turn = true;
                    topBarText.setText(player1);
                    overallPointsText.setText(String.valueOf(overallPointsP1));
                    player_image.setImageResource(playerImages[playerImage1]);
                    recyclerView.setBackgroundColor(Color.YELLOW);
                }
            }
        });



        //Rolls dice and updates component views
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Updates visibility of appropriate components
                leftDice.setVisibility(View.VISIBLE);
                rightDice.setVisibility(View.VISIBLE);
                player_image.setVisibility(View.INVISIBLE);
                noteText.setText("");
                if (holdButton.getVisibility() == View.INVISIBLE)
                {
                    holdButton.setVisibility(View.VISIBLE);
                }

                //If there is no winner
                if (winner.isEmpty()) {

                    //Gets random numbers for each dice
                    int leftNum = rand.nextInt(6);
                    int rightNum = rand.nextInt(6);

                    //Assigns appropriate dice images according to the random numbers
                    leftDice.setImageResource(diceArray[leftNum]);
                    rightDice.setImageResource(diceArray[rightNum]);

                    //Updates turn points
                    turnPoints += leftNum + rightNum + 2;
                    turnPointsText.setText(String.valueOf(turnPoints));

//_________________________________________________________________________________________
//_________________________________________________________________________________________
//_________________________________Rules of the game_______________________________________
//_________________________________________________________________________________________
//_________________________________________________________________________________________

                    //If one dice rolls 1 and other dice rolls any other number
                    //Then player loses turn and turn points
                    if ((leftNum == 0 && rightNum != 0) || (leftNum != 0 && rightNum == 0))
                    {
                        holdButton.setVisibility(View.INVISIBLE);
                        rollButton.setVisibility(View.INVISIBLE);
                        nextPlayerButton.setVisibility(View.VISIBLE);
                        noteText.setText("You lose a turn for rolling 1 once");
                    }

                    //If both dice roll 1
                    //Then player loses turn and overall points
                    else if (leftNum == 0 && rightNum == 0)
                    {
                        holdButton.setVisibility(View.INVISIBLE);
                        rollButton.setVisibility(View.INVISIBLE);
                        nextPlayerButton.setVisibility(View.VISIBLE);
                        noteText.setText("You lose a turn & overall score for rolling snake eyes");

                        if (p1Turn)
                        {
                            overallPointsP1 = 0;
                            overallPointsText.setText(String.valueOf(overallPointsP1));
                        } else {
                            overallPointsP2 = 0;
                            overallPointsText.setText(String.valueOf(overallPointsP2));
                        }
                    }

                    //If both dice roll same number except of 1
                    //Then turn points are added to overall points, but player must roll again
                    else if (leftNum == rightNum)
                    {
                        holdButton.setVisibility(View.INVISIBLE);
                        noteText.setText("You must roll again for rolling double");
                    }
//_________________________________________________________________________________________
//_________________________________________________________________________________________
//_________________________________Rules of the game_______________________________________
//_________________________________________________________________________________________
//_________________________________________________________________________________________
                }
            }
        });



        //Holds appropriate points and activates next player button
        holdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Updates visibility of appropriate components
                leftDice.setVisibility(View.INVISIBLE);
                rightDice.setVisibility(View.INVISIBLE);
                player_image.setVisibility(View.VISIBLE);
                holdButton.setVisibility(View.INVISIBLE);
                rollButton.setVisibility(View.INVISIBLE);
                turnPointsText.setText("0");

                //If player 1 won
                //Then updated player 1's overall points
                if (p1Turn && winner.isEmpty())
                {

                    overallPointsP1 += turnPoints;
                    overallPointsText.setText(String.valueOf(overallPointsP1));
                    if (overallPointsP1 >= scoreLimit)
                    {
                        winner = "YOU WON!\n" + player1 + " scored: " + overallPointsP1 + "\n" + player2 + " scored: " + overallPointsP2;
                    } else {
                        noteText.setText("Your turn is over");
                    }

                //Else if player 2 won
                //Then update player 2's overall points
                } else if (!p1Turn && winner.isEmpty()) {
                    overallPointsP2 += turnPoints;
                    overallPointsText.setText(String.valueOf(overallPointsP2));
                    if (overallPointsP2 >= scoreLimit)
                    {
                        winner = "YOU WON!\n"  + player1 + " scored: " + overallPointsP1 + "\n" + player2 + " scored: " + overallPointsP2;
                    } else {
                        noteText.setText("Your turn is over");
                    }
                }

                //If there is a winner
                //Then update components to end the game
                if (!winner.isEmpty())
                {
                    noteText.setText(winner);
                    playAgain.setVisibility(View.VISIBLE);

                //Else if no winner
                //Then show the next player button
                } else {
                    nextPlayerButton.setVisibility(View.VISIBLE);
                }
            }
        });



        //Ends Main Activity and shows Menu Activity
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
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
