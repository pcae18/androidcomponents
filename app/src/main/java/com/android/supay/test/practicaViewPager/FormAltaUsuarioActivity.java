package com.android.supay.test.practicaViewPager;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.supay.test.R;
import com.android.supay.test.util.Definitions;
import com.android.supay.test.util.KeyDefinitions;
import com.android.supay.test.util.Util;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormAltaUsuarioActivity extends AppCompatActivity {

    @BindView(R.id.buttonGuardar)
    Button buttonGuardar;

    @BindView(R.id.textInputEditTextNombre)
    TextInputEditText inputTextNombre;

    @BindView(R.id.textInputEditTextApellido)
    TextInputEditText inputTextApellido;

    @BindView(R.id.textInputEditTextDireccion)
    TextInputEditText inputTextDireccion;

    @BindView(R.id.textInputEditTextEmail)
    TextInputEditText inputTextEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_alta_usuario);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonGuardar) public void onClickButtonGuardar( View view ){
        HashMap<String, String> dataForm = new HashMap<>();
        dataForm.put(KeyDefinitions.NAME, inputTextNombre.getText().toString());
        dataForm.put(KeyDefinitions.LAST_NAME, inputTextApellido.getText().toString());
        dataForm.put(KeyDefinitions.ADDRESS, inputTextDireccion.getText().toString());
        dataForm.put(KeyDefinitions.EMAIL, inputTextEmail.getText().toString());

        Util.changeActivity( FormAltaUsuarioActivity.this, ViewPagerAltaUsuarioActivity.class, dataForm);
    }
}
