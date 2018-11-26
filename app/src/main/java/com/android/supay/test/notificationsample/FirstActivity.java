package com.android.supay.test.notificationsample;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.supay.test.R;
import com.android.supay.test.util.KeyDefinitions;
import com.android.supay.test.util.Util;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstActivity extends AppCompatActivity {

    @BindView(R.id.buttonSend)
    Button buttonSend;

    @BindView(R.id.editTextMessage)
    EditText mEditTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonSend) public void sendMessage(View view){
        HashMap<String, String > data = new HashMap<>();
        data.put(KeyDefinitions.MESSAGE, mEditTextMessage.getText().toString());
        if(!mEditTextMessage.getText().toString().isEmpty()){
            Util.changeActivity(FirstActivity.this, NotificationsActivity.class, data);
        }else{
            createSimpleDialog(FirstActivity.this);
        }
    }

    private void createSimpleDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.empty_input_message)
                .setMessage(getString(R.string.insert_a_message_to_send))
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();

    }
}
