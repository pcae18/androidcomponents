package com.android.supay.test.viewlogincomponent;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.android.supay.test.R;
import com.android.supay.test.util.Definitions;
import com.android.supay.test.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * {@Link android.app.Activity} que muestra
 * un formulario de login.
 */
public class LoginActivity extends AppCompatActivity {

    private String TAG = LoginActivity.class.getSimpleName();

    @BindView( R.id.textInputLayoutEmail )
    TextInputLayout mTextInputLayoutEmail;

    @BindView( R.id.textInputLayoutPassword )
    TextInputLayout mTextInputLayoutPassword;

    @BindView( R.id.textInputEditTextEmail)
    TextInputEditText textInputEditTextEmail;

    @BindView( R.id.textInputEditTextPassword)
    TextInputEditText textInputEditTextPassword;

    @BindView( R.id.buttonLogin)
    Button buttonLogin;

    private String email;

    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind( this);
    }

    @OnClick( R.id.buttonLogin ) public void onClickButton( View view ){
        email = textInputEditTextEmail.getText().toString();
        password = textInputEditTextPassword.getText().toString();
        if( validInputs( ) ) {
            successLogin( );
        }

    }

    public Boolean validInputs(){
        Boolean valid = true;
        mTextInputLayoutEmail.setError(null);
        mTextInputLayoutPassword.setError(null);

        if( email.isEmpty( ) && password.isEmpty( ) ){
            Util.showToast( getString( R.string.message_error_required_email ) + "\n" +
                    getString( R.string.message_error_required_password ) , getApplicationContext( ) );
            valid = false;
        }else{
            if( email.isEmpty( ) ){
                mTextInputLayoutEmail.setError( getString( R.string.message_error_required_email ) );
                valid = false;
            }else if( !Util.validEmail( email )){
                mTextInputLayoutEmail.setError( getString( R.string.message_error_invalid_email ) );
                valid = false;

            }

            if( password.isEmpty( ) ){
                mTextInputLayoutPassword.setError( getString( R.string.message_error_required_password ) );
                valid = false;

            }else if( !Util.validPassword( password ) ){
                mTextInputLayoutPassword.setError( getString( R.string.message_error_invalid_password ) );
                valid = false;

            }
        }
        return valid;
    }

    public void successLogin(){
        Util.showToast( getString( R.string.message_successfull_login ), getApplicationContext( ) );
        Intent intent = new Intent( LoginActivity.this, ProfileActivity.class);
        intent.putExtra( Definitions.KEY_PROFILE, email);
        startActivity(intent);
        LoginActivity.this.finish();

    }

}
