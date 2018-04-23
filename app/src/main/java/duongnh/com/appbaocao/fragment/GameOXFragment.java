package duongnh.com.appbaocao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import duongnh.com.appbaocao.R;
import duongnh.com.appbaocao.activity.GameActivity;

/**
 * Created by Admin on 4/23/2018.
 */

public class GameOXFragment extends Fragment implements View.OnClickListener {
    private GameActivity main;
    private Button[][] buttons = new Button[3][3];
    private Button btn00, btn01, btn02, btn10,btn11,btn12, btn20,btn21,btn22;

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.game_ox_fragment, container,false);
        main = (GameActivity) getActivity();
        textViewPlayer1 = view.findViewById(R.id.text_view_p1);
        textViewPlayer2 = view.findViewById(R.id.text_view_p2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", "duongnh.com.appbaocao");
                buttons[i][j] = view.findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
//        btn00 = view.findViewById(R.id.button_00);
//        btn01 = view.findViewById(R.id.button_01);
//        btn02 = view.findViewById(R.id.button_02);
//        btn10 = view.findViewById(R.id.button_10);
//        btn11 = view.findViewById(R.id.button_11);
//        btn12 = view.findViewById(R.id.button_12);
//        btn20 = view.findViewById(R.id.button_20);
//        btn21 = view.findViewById(R.id.button_21);
//        btn22 = view.findViewById(R.id.button_22);
//
//        btn00.setOnClickListener(this);
//        btn01.setOnClickListener(this);
//        btn02.setOnClickListener(this);
//        btn10.setOnClickListener(this);
//        btn11.setOnClickListener(this);
//        btn12.setOnClickListener(this);
//        btn20.setOnClickListener(this);
//        btn21.setOnClickListener(this);
//        btn22.setOnClickListener(this);
        Button buttonReset = view.findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }
    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins() {
        player1Points++;
        Toast.makeText(main, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText(main, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(main, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        textViewPlayer1.setText("Player 1: " + player1Points);
        textViewPlayer2.setText("Player 2: " + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }
}
