package com.android.supay.test.ws.config;

import com.android.supay.test.ws.model.StarWarsCharacterResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Client {
    @GET(ClientEndPoints.getPeople)
    Call<StarWarsCharacterResponse> getCharacter(@Path("id") String id);
}
