package com.nechay.visualiser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class NewGraphActivity extends Activity {
    private Button btnCreate;
    private Button btnPlus;
    private Button btnMinus;
    private static CheckBox[][] matrix = new CheckBox[16][16];
    private TableRow[] rows = new TableRow[17];
    private TableLayout table;
    private static int countOfRows = 3;
    private Intent setAct;
    //TextView[] numberRow = new TextView[17];
    //TextView[] numberColumn = new TextView[17];
    private void expand(){
        if(countOfRows==12){
            return;
        }
        rows[countOfRows - 1].removeAllViews();
        //Добавляем новую строку
        rows[countOfRows] = new TableRow(NewGraphActivity.this);
        TableRow.LayoutParams tableRowLayoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        rows[countOfRows].setLayoutParams(tableRowLayoutParams);
        table.addView(rows[countOfRows]);
        //Добавляем пустые текстВью чтобы красиво стояло!
        TextView txtView;
        for(int i=0;i<countOfRows-1; i++){
            txtView = new TextView(NewGraphActivity.this);
            rows[countOfRows].addView(txtView);
        }

        //добавляем вертикальный индекс
        TextView numV = new TextView(NewGraphActivity.this);
        numV.setText("  " +Integer.toString(countOfRows-1));
        numV.setTextSize(18);
        numV.setGravity(0x11);
        rows[countOfRows-1].addView(numV);
        //Добавляем горизонтальный индекс
        TextView numH = new TextView(NewGraphActivity.this);
        numH.setText("  " +Integer.toString(countOfRows-1));
        numH.setTextSize(18);
        numV.setGravity(0x11);
        rows[0].addView(numH);
        //Добавляем нижнюю горизонталь чек-боксов
        for(int i=0;i<countOfRows-1;i++){
            CheckBox chk = new CheckBox(NewGraphActivity.this);
            rows[countOfRows-1].addView(chk);
            matrix[countOfRows-2][i]=chk;
        }
        rows[countOfRows].addView(btnMinus);
        rows[countOfRows].addView(btnPlus);
        //Добавляем правую вертикаль чек-боксов
        for(int i=1;i<countOfRows-1;i++) {
            CheckBox chk = new CheckBox(NewGraphActivity.this);
            rows[i].addView(chk);
            matrix[i - 1][countOfRows - 2] = chk;
        }
        countOfRows++;
    }

    private void reduce(){
        if(countOfRows ==3){
            return;
        }
        //Удаляем из интерфейса минус и плюс
        rows[countOfRows - 1].removeAllViews();
        //удаляем чекбоксы
        for(int i=0;i<=countOfRows-3;i++){
            rows[countOfRows - 2].removeView(matrix[countOfRows - 3][i]);
            matrix[countOfRows - 3][i]=null;
            if(i!=countOfRows - 3) {
                rows[i + 1].removeView(matrix[i][countOfRows - 3]);
                matrix[i][countOfRows - 3] = null;
            }
        }
        rows[countOfRows - 2].removeAllViews();
        TextView txtView;
        for(int i =0;i<countOfRows - 3;i++){
            txtView = new TextView(NewGraphActivity.this);
            rows[countOfRows-2].addView(txtView);
        }
        rows[countOfRows-2].addView(btnMinus);
        rows[countOfRows-2].addView(btnPlus);
        rows[0].removeViewAt(countOfRows-2);
        countOfRows--;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_new_graph);
        table = (TableLayout) findViewById(R.id.table);
        rows[0] =  (TableRow) findViewById(R.id.tableRow0);
        rows[1] =  (TableRow) findViewById(R.id.tableRow1);
        rows[2] =  (TableRow) findViewById(R.id.tableRow2);
        btnCreate = (Button) findViewById(R.id.create_graph);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        matrix[0][0] = (CheckBox) findViewById(R.id.checkBox);

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expand();
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reduce();
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAct = new Intent(NewGraphActivity.this, VisualaserActivity.class);
                startActivity(setAct);
            }
        });
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }
    public static boolean[][] convertM(){
        boolean[][] bmas = new boolean[countOfRows-2][countOfRows-2];
        for(int i=0;i<countOfRows-2;i++){
            for(int j=0;j<countOfRows-2;j++){
                bmas[i][j] = matrix[i][j].isChecked();
            }
        }
        return bmas;
    }

    public static int  getCountOfRows() {
        return countOfRows;
    }

    public static void  setCountOfRows(int c) {
        countOfRows = c;
    }
}