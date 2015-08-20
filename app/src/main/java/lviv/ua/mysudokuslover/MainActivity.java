package lviv.ua.mysudokuslover;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private static final String DIALOG_NUMBERS = "NUMBERS";

    private Button[][] mButtons;
    private Button mClearButton;
    private Button mSolveButton;
    private Solver mSolver;

    private TextView numberOne;
    private TextView numberTwo;
    private TextView numberThree;
    private TextView numberFour;
    private TextView numberFive;
    private TextView numberSix;
    private TextView numberSeven;
    private TextView numberEight;
    private TextView numberNine;
    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSolver = new Solver();
        mClearButton = (Button) findViewById(R.id.clean_button);
        mSolveButton = (Button) findViewById(R.id.solve_button);

        int[] linear_layout_ids = {R.id.row1, R.id.row2, R.id.row3,
                R.id.row4, R.id.row5, R.id.row6,
                R.id.row7, R.id.row8, R.id.row9};

        LinearLayout[] v = new LinearLayout[9];
        for (int i = 0; i < linear_layout_ids.length; i++) {
            v[i] = (LinearLayout) findViewById(linear_layout_ids[i]);
        }


        mButtons = new Button[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                final Button b = (Button) ((ViewGroup) v[i]).getChildAt(j);
                b.setText(String.valueOf(i) + '_' + String.valueOf(j));
                //b.setText("");b.setHeight(b.getWidth());
                mButtons[i][j] = b;

                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Dialog dialog = new Dialog(MainActivity.this);
                        dialog.setContentView(R.layout.keypad_fragment);
                        dialog.setTitle(R.string.chose_namber_title);

                        initNumbers(dialog);

                        View.OnClickListener listener = new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                switch (v.getId()) {
                                    case R.id.oneNum:
                                        b.setText("1");
                                        dialog.dismiss();
                                        break;
                                    case R.id.twoNum:
                                        b.setText("2");
                                        dialog.dismiss();
                                        break;
                                    case R.id.threeNum:
                                        b.setText("3");
                                        dialog.dismiss();
                                        break;
                                    case R.id.fourNum:
                                        b.setText("4");
                                        dialog.dismiss();
                                        break;
                                    case R.id.fiveNum:
                                        b.setText("5");
                                        dialog.dismiss();
                                        break;
                                    case R.id.sixNum:
                                        b.setText("6");
                                        dialog.dismiss();
                                        break;
                                    case R.id.sevenNum:
                                        b.setText("7");
                                        dialog.dismiss();
                                        break;
                                    case R.id.eightNum:
                                        b.setText("8");
                                        dialog.dismiss();
                                        break;
                                    case R.id.nineNum:
                                        b.setText("9");
                                        dialog.dismiss();
                                        break;
                                }
                            }
                        };
                        numberOne.setOnClickListener(listener);
                        numberTwo.setOnClickListener(listener);
                        numberThree.setOnClickListener(listener);
                        numberFour.setOnClickListener(listener);
                        numberFive.setOnClickListener(listener);
                        numberSix.setOnClickListener(listener);
                        numberSeven.setOnClickListener(listener);
                        numberEight.setOnClickListener(listener);
                        numberNine.setOnClickListener(listener);


                        dialog.show();
                    }
                });
            }
        }

        mClearButton.setOnClickListener(this);
        mSolveButton.setOnClickListener(this);

        clean();
    }


    private void initNumbers(Dialog dialog) {
        numberOne = (TextView) dialog.findViewById(R.id.oneNum);
        numberTwo = (TextView) dialog.findViewById(R.id.twoNum);
        numberThree = (TextView) dialog.findViewById(R.id.threeNum);
        numberFour = (TextView) dialog.findViewById(R.id.fourNum);
        numberFive = (TextView) dialog.findViewById(R.id.fiveNum);
        numberSix = (TextView) dialog.findViewById(R.id.sixNum);
        numberSeven = (TextView) dialog.findViewById(R.id.sevenNum);
        numberEight = (TextView) dialog.findViewById(R.id.eightNum);
        numberNine = (TextView) dialog.findViewById(R.id.nineNum);

    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clean_button:
                clean();
                break;
            case R.id.solve_button:
                onSolve();
                break;
        }
    }

    private void onSolve() {
        int[][] sudoku = new int[9][9];
        String s;
        int n;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                s = mButtons[i][j].getText().toString();
                if (s == "") {
                    n = 0;
                } else {
                    n = Integer.parseInt(s);
                }
                sudoku[i][j] = n;
            }
        }


        if (mSolver.solve(sudoku)) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    mButtons[i][j].setText(String.valueOf(sudoku[i][j]));
                }
            }
        } else {
            Toast.makeText(this, "No solution", Toast.LENGTH_LONG).show();
        }
    }

    public void clean() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                mButtons[i][j].setText("");
            }
        }

    }
}