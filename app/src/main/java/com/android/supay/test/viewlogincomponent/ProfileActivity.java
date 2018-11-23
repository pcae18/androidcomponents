package com.android.supay.test.viewlogincomponent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.supay.test.R;
import com.android.supay.test.util.Definitions;
import com.android.supay.test.util.Util;
import com.bumptech.glide.Glide;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.textViewWelcomeMessage)
    TextView textViewWelcomeMessage;

    @BindView(R.id.profileImage)
    CircleImageView profileImage;

    private String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind( this);
        Intent intent = getIntent();

        if( intent != null ){
            email = intent.getStringExtra(Definitions.KEY_PROFILE );
            textViewWelcomeMessage.setText( email );
            Util.setUpImageView( Definitions.SRC_IMG_ADVENGERS_IRON_MAN, getApplicationContext(), profileImage );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.profile_menu, menu );
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch( id ){
            case R.id.item1:
                Util.showToast(getString( R.string.menu_inicio ) + " Seleccionado", getApplicationContext( ) );
                break;
            case R.id.item2:
                Util.showToast(getString( R.string.menu_configuracion ) + " Seleccionado", getApplicationContext( ) );
                break;
            case R.id.item3:
                Util.showToast(getString( R.string.menu_cerrar_sesion ) + " Seleccionado", getApplicationContext( ) );
                Util.changeActivity( ProfileActivity.this, LoginActivity.class, new HashMap<>() );
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
