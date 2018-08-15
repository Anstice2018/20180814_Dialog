package com.example.android.a20180814_dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements DialogInterface.OnClickListener,
                    MyDialogFragment.收結果{


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



        //AlertDialog Items
        // android.R.style.Theme_Holo_Light_Dialog_NoActionBar 是風格主題(可替換)
        public void click4 (View view){

            final String[] response = getResources().getStringArray(R.array.response);

            new AlertDialog.Builder(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar)
                    .setTitle("您覺得題目難易度如何？")
                    .setItems(response, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            tv.setText(response[i]);
                        }
                    })
                    .show();
        }



        //AlertDialog MultiChoice
        public void click5 (View view){
            final String[] response = getResources().getStringArray(R.array.response2);
            final boolean[] bbb = new boolean[response.length];

            new AlertDialog.Builder(this)
                    .setTitle("你喜歡的水果")
                    .setMultiChoiceItems(response, bbb, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                            // 可省略, boolean陣列會自動更新
                        }
                    })
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            StringBuilder result = new StringBuilder();

                            for (int i=0; i< bbb.length; i++){
                                if (bbb[i]){
                                    result.append(response[i]).append("\n");
                                }
                            }
                            tv.setText(result);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            tv.setText("無語");
                        }
                    })
                    .show();

        }



        //AlertDialog SingleChoice
        private int mChoice;    //欄位
        public void click6 (View view){
            final String[] response = getResources().getStringArray(R.array.response2);
            mChoice = 0;
            new AlertDialog.Builder(this)
                    .setTitle("挑一種水果")
                    .setSingleChoiceItems(response, mChoice, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mChoice = i; //此 onClick 執行完, i 會消失, 所以要記錄在欄位裡
                        }
                    })
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            tv.setText(response[mChoice]);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            tv.setText("無語");
                        }
                    })
                    .show();
        }



        //AlertDialog Fragment
        // 自訂 帥氣
        public void click7 (View view){
            DialogFragment dialog = new MyDialogFragment();
            dialog.show(getSupportFragmentManager(), "MyDialogFragment");
        }
        @Override
        public void ok() {
            tv.setText("登入");
        }

        @Override
        public void cancel() {
            tv.setText("取消登入");
        }

}
