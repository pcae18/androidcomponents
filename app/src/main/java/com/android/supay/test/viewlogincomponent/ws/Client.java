package com.android.supay.test.viewlogincomponent.ws;

import com.android.supay.test.viewlogincomponent.ws.model.LoginAuthResponse;
import com.android.supay.test.ws.config.ClientEndPoints;
import com.android.supay.test.ws.model.StarWarsCharacterResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Client {
    @GET(ClientEndPoints.getPeople)
    Call<StarWarsCharacterResponse> getCharacter(@Path("id") String id);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginAuthResponse> login(@Field("email") String email, @Field("password") String password);

}
