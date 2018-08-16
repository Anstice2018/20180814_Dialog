package com.example.android.a20180814_dialog;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends DialogFragment {

    //合約
    public interface 收結果{
        public abstract void ok(String s);
        public abstract void cancel();
    }

    private EditText m_ed_username;
    private EditText m_ed_password;

    // 無參數建構子是必須的
    public MyDialogFragment() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        // 透過inflater, 讀取 fragment.xml 資源來生成 View

        // 取得 inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // 從 fragment.xml 取得自定畫面
        // inflate(resource, viewGroup)
        View view = inflater.inflate(R.layout.fragment_my_dialog, null);

        //初始化 edit_view
        m_ed_username = (EditText)view.findViewById(R.id.ed_username);
        m_ed_password = (EditText)view.findViewById(R.id.ed_password);

        // 建立 AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Activity activity = getActivity();
                        if (activity instanceof 收結果){
                            收結果 x = (收結果) activity;
                            x.ok(m_ed_username.getText().toString());
                        }else{
                            throw new RuntimeException("搞什麼,Activity 沒有實作 收結果");
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Activity activity = getActivity();
                        if (activity instanceof 收結果){
                            收結果 x = (收結果) activity;
                            x.cancel();
                        }else{
                            throw new RuntimeException("搞什麼,Activity 沒有實作 收結果");
                        }
                    }
                });
        return builder.create();

    }
}
