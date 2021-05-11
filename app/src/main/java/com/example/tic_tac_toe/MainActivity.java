package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //x and y coordinate array for board state
    //int 0 value for empty, 1 value for x, and 2 value for O inside the square
    private int[][] board;
    //int value 1 for player 1 turn, 2 for player 2 turn
    private int playerTurn;
    private boolean win;
    private boolean draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //board creation and initial values set to 0
        board = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = 0;
            }
        }
        win = false;
        draw = false;
        playerTurn = 1;
        TextView turn = findViewById(R.id.turnText);
        turn.setText("Player " + playerTurn + "'s Turn");
    }

    public void Move(int xsquare, int ysquare, int image) {

        //checks if chosen square is empty and that the game isn't over
        //if true then proceed with valid player move line
        if (board[xsquare][ysquare] == 0 && !win && !draw) {

            ImageView btnImage = findViewById(image);

            if (playerTurn == 1) {

                //sets button image to new image depending on player 1 or 2 for x or o
                btnImage.setImageResource(R.drawable.ttt_x);
                //sets board value to player 1 placement
                board[xsquare][ysquare] = 1;
                //checks for player win after placement
                CheckWin(xsquare, ysquare);

                //sets up board for next player unless win was found
                if (!win) {
                    playerTurn = 2;
                    TextView turn = findViewById(R.id.turnText);
                    turn.setText("Player " + playerTurn + "'s Turn");
                }
            }
            //same as above for player 2
            else {

                btnImage.setImageResource(R.drawable.ttt_o);
                board[xsquare][ysquare] = 2;
                CheckWin(xsquare, ysquare);

                if (!win) {
                    playerTurn = 1;
                    TextView turn = findViewById(R.id.turnText);
                    turn.setText("Player " + playerTurn + "'s Turn");
                }
            }
        }
        //checks for draw
        if (!draw) {
            CheckDraw();
        }
    }

    //method to check for draw
    public void CheckDraw() {

        //loops through board and if all values are not 0 then announce draw
        int drawcount = 0;
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if (board[i][j] != 0) {
                    drawcount++;
                }
            }
        }
        if (drawcount == 9) {
            draw = true;
            TextView drawtext = findViewById(R.id.announceText);
            drawtext.setText("Draw!");
            TextView turn = findViewById(R.id.turnText);
            turn.setText("Game Ended");
        }
    }

    //check win method for after player move
    public void CheckWin(int xsquare, int ysquare) {

        int wincount = 0;

        //checks for column win
        for (int i = 1; i < 4; i++) {
            if (board[xsquare][i] == playerTurn) {
                wincount++;
            }
        }

        if (wincount == 3) {
            win = true;
        } else {
            wincount = 0;
        }

        //checks for row win
        for (int i = 1; i < 4; i++) {
            if (board[i][ysquare] == playerTurn) {
                wincount++;
            }
        }

        if (wincount == 3) {
            win = true;
        } else {
            wincount = 0;
        }

        //checks for diagonal win
        if (board[xsquare][ysquare] == playerTurn) {
            if ((board[1][1] == playerTurn && board[3][3] == playerTurn) | (board[1][3] == playerTurn && board[3][1] == playerTurn)) {
                win = true;
            }
        }

        //announces win if found
        if (win) {
            TextView winner = findViewById(R.id.announceText);
            winner.setText("Player " + playerTurn + " Wins!");
            TextView turn = findViewById(R.id.turnText);
            turn.setText("Game Ended");
        }

    }

    public void square1(View view) {
        Move(1, 1, R.id.imageButton1);
    }

    public void square2(View view) {
        Move(2, 1, R.id.imageButton2);
    }

    public void square3(View view) {
        Move(3, 1, R.id.imageButton3);
    }

    public void square4(View view) {
        Move(1, 2, R.id.imageButton4);
    }

    public void square5(View view) {
        Move(2, 2, R.id.imageButton5);
    }

    public void square6(View view) {
        Move(3, 2, R.id.imageButton6);
    }

    public void square7(View view) {
        Move(1, 3, R.id.imageButton7);
    }

    public void square8(View view) {
        Move(2, 3, R.id.imageButton8);
    }

    public void square9(View view) {
        Move(3, 3, R.id.imageButton9);
    }

}


