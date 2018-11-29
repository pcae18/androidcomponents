package com.android.supay.test.ws;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.android.supay.test.R;
import com.android.supay.test.util.Util;
import com.android.supay.test.ws.config.Client;
import com.android.supay.test.ws.config.ServiceGenerator;
import com.android.supay.test.ws.model.StarWarsCharacterResponse;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebServiceSampleActivity extends AppCompatActivity {

    @BindView(R.id.textInputEditPokemonId)
    TextInputEditText textInputEditTextPokemonId;

    @BindView(R.id.textViewPokemonInfo)
    TextView textViewPokemonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service_sample);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonObtener) public void buttonObtener(View view){
        String id = textInputEditTextPokemonId.getText().toString();
        if( ! id.isEmpty()){
            Client client = ServiceGenerator.createService(Client.class);
            client.getCharacter(id)
                    .enqueue(new Callback<StarWarsCharacterResponse>() {
                        @Override
                        public void onResponse(Call<StarWarsCharacterResponse> call, Response<StarWarsCharacterResponse> response) {
                            try{
                                StarWarsCharacterResponse characterResponse = response.body();
                                textViewPokemonInfo.setText( characterResponse.getName() );
                            }catch(Exception e){
                             e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<StarWarsCharacterResponse> call, Throwable t) {
                            textViewPokemonInfo.setText("Error!.");
                            t.printStackTrace();
                        }
                    });
        }else{
        }
    }


}
