package com.example.midterm_alternative_assignments;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private EditText userInputEditText;
    private int totalCols = 8;
    private int numRows = 0;
    private int numCols = 0;
    private List<String> userInput = new ArrayList<>();
    private Button addButton;
    private Button delButton;
    private ImageButton checkButton;

    private boolean checkFlag = false;

    private int buttonSize = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout = findViewById(R.id.tableLayout);
        userInputEditText = findViewById(R.id.userInputEditText);
        addButton = findViewById(R.id.addButton);
        delButton = findViewById(R.id.delButton);
        checkButton = findViewById(R.id.checkButton);
    }

    public void onAddButtonClick(View view) {
        // "Add" 버튼을 눌렀을 때의 동작 구현
        String inputText = userInputEditText.getText().toString();
        userInput.add(inputText); // 사용자 입력을 변수에 저장

        userInputEditText.getText().clear(); // 입력란 지우기

        numCols++;

        if (numCols > totalCols) {
            numCols = 1;
            numRows+=2;
            TableRow tableRow = new TableRow(this);
            TableRow tableRow2 = new TableRow(this);
            for (int j = 0; j < numCols; j++) {
                ImageButton imageButton = new ImageButton(this);
                imageButton.setImageResource(R.drawable.green_button); // 이미지 설정
                imageButton.setLayoutParams(new TableRow.LayoutParams(30, 100));
                tableRow.addView(imageButton);
            }
            tableLayout.addView(tableRow, numRows);
            for (int j = 0; j < numCols; j++) {
                TextView textView = new TextView((this));
                textView.setText(userInput.get((numRows/2)*totalCols+j));
                textView.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                tableRow2.addView(textView);
            }
            tableLayout.addView(tableRow2, numRows+1);
        }
        else {
            if (numRows > 0 || numCols > 1) {
                tableLayout.removeViewAt(numRows+1);
                tableLayout.removeViewAt(numRows); // 마지막 행 삭제
            }
            TableRow tableRow = new TableRow(this);
            TableRow tableRow2 = new TableRow(this);
            for (int j = 0; j < numCols; j++) {
                ImageButton imageButton = new ImageButton(this);
                imageButton.setImageResource(R.drawable.green_button); // 이미지 설정
                imageButton.setLayoutParams(new TableRow.LayoutParams(buttonSize, buttonSize));
                tableRow.addView(imageButton);
            }
            if (numRows==0){
                for (int j = numCols; j < totalCols; j++) {
                    ImageView imageView = new ImageView(this);
                    imageView.setImageResource(R.drawable.free_button);
                    imageView.setLayoutParams(new TableRow.LayoutParams(100, 100));
                    tableRow.addView(imageView);
                }
            }
            tableLayout.addView(tableRow, numRows);
            for (int j = 0; j < numCols; j++) {
                TextView textView = new TextView((this));
                textView.setText(userInput.get((numRows/2)*totalCols+j));
                textView.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                tableRow2.addView(textView);
            }
            if (numRows==0) {
                for (int j = numCols; j < totalCols; j++) {
                    TextView textView = new TextView((this));
                    textView.setText("");
                    textView.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    tableRow2.addView(textView);
                }
            }
            tableLayout.addView(tableRow2, numRows+1);
        }
    }

    public void onDeleteButtonClick(View view) {
        // "Delete" 버튼을 눌렀을 때의 동작 구현
        if (numRows > 0 || numCols > 0) {
            numCols--;
            userInput.remove(userInput.size()-1);
            if (numCols == 0) {
                tableLayout.removeViewAt(numRows+1);
                tableLayout.removeViewAt(numRows); // 마지막 행 삭제
                if (numRows > 0) {
                    numRows-=2;
                    numCols = totalCols;
                }
                else numCols = 0;
            }
            else {
                tableLayout.removeViewAt(numRows+1);
                tableLayout.removeViewAt(numRows); // 마지막 행 삭제
                TableRow tableRow = new TableRow(this);
                TableRow tableRow2 = new TableRow(this);
                for (int j = 0; j < numCols; j++) {
                    ImageButton imageButton = new ImageButton(this);
                    imageButton.setImageResource(R.drawable.green_button); // 이미지 설정
                    imageButton.setLayoutParams(new TableRow.LayoutParams(buttonSize, buttonSize));
                    tableRow.addView(imageButton);
                }
                if (numRows==0){
                    for (int j = numCols; j < totalCols; j++) {
                        ImageView imageView = new ImageView(this);
                        imageView.setImageResource(R.drawable.free_button);
                        imageView.setLayoutParams(new TableRow.LayoutParams(100, 100));
                        tableRow.addView(imageView);
                    }
                }
                tableLayout.addView(tableRow, numRows);
                for (int j = 0; j < numCols; j++) {
                    TextView textView = new TextView((this));
                    textView.setText(userInput.get((numRows/2)*totalCols+j));
                    textView.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    tableRow2.addView(textView);
                }
                if (numRows==0){
                    for (int j = numCols; j < totalCols; j++) {
                        TextView textView = new TextView((this));
                        textView.setText("");
                        textView.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        tableRow2.addView(textView);
                    }
                }
                tableLayout.addView(tableRow2, numRows+1);
            }
        }
    }

    public void onCheckButtonClick(View view) {
        if (!checkFlag) {
            addButton.setEnabled(false);
            delButton.setEnabled(false);
            checkFlag = true;
        }
        else{
            showAlertDialog();
        }

    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("주의");
        builder.setMessage("확인을 누르면 버튼 2가 활성화됩니다.");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // "확인"을 누르면 버튼 2를 활성화
                addButton.setEnabled(true);
                delButton.setEnabled(true);
                checkFlag = false;
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {

           }
        });
        builder.setCancelable(false);
        builder.show();
    }
}