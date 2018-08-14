package com.example.android.a20180814_dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements DialogInterface.OnClickListener{


        private TextView tv;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            init();
        }
        private void init() {
            tv=(TextView)findViewById(R.id.textView);
        }


        // 將 onClick 事件委託給 當前的Activity(居中協調)
        public void click1(View view){
            new AlertDialog.Builder(this)
                    .setMessage("1 + 1 = 2 ?")
                    .setPositiveButton("yes", this)
                    .show();
        }
        @Override
            public void onClick(DialogInterface dialog , int which){
            tv.setText("YES");
        }


        //將兩個按鈕交給同一個 listener 來處理
        public void click2 (View view){
            YESNO listener = new YESNO();
            new AlertDialog.Builder(this)
                    .setMessage("1 + 2 = 3 ?")
                    .setPositiveButton("yes",listener)
                    .setNegativeButton("no",listener)
                    .show();
        }
        private class YESNO implements DialogInterface.OnClickListener{
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case DialogInterface.BUTTON_POSITIVE:
                        tv.setText("Bingo");
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        tv.setText("NO");
                        break;
                }
            }
        }


        //採用匿名內部類別，直接實作程式碼，省去另外寫類別實作
        public void click3 (View view) {
            new AlertDialog.Builder(this)
                    .setMessage("再來一題")
                    .setPositiveButton("好", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            tv.setText("好");
                        }
                    })
                    .setNegativeButton("不要", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            tv.setText("不要");
                        }
                    })
                    .setNeutralButton("提示", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            tv.setText("提示");
                        }
                    })
                    .show();
        }
}
