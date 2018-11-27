package com.android.supay.test.practicaRecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.supay.test.R;
import com.android.supay.test.db.adapter.DogShopAdapter;
import com.android.supay.test.model.PeliculasDC;
import com.android.supay.test.model.dogshop.DogShop;
import com.android.supay.test.practicaRecycler.adapter.DCAdapter;
import com.android.supay.test.practicaRecycler.adapter.DCClick;
import com.android.supay.test.practicaRecycler.adapter.DCLongClick;
import com.android.supay.test.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmList;

public class DCRecyclerActivity extends AppCompatActivity implements DCClick, DCLongClick {

    public static String TAG = DCRecyclerActivity.class.getSimpleName();

    private List<PeliculasDC> peliculas = new ArrayList<>();

    private DCAdapter dcAdapter;


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dcrecycler);
        ButterKnife.bind(this);
        setUpRecyclerView(recyclerView);
        initPeliculas();

    }

    private void setUpRecyclerView(RecyclerView recyclerView) {
        dcAdapter = new DCAdapter(peliculas, getApplicationContext(),this, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setAdapter(dcAdapter);
        //mDogShopRecyclerView.setLayoutManager( new GridLayoutManager(this, 3));
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void initPeliculas(){
        peliculas.clear();
        peliculas.add(new PeliculasDC("Batman", "Batman regresa", "1993"
                , "https://static.vix.com/es/sites/default/files/styles/large/public/btg/cine.batanga.com/files/curiosidades-batman-begins.jpg?itok=o6IHnBZF"));
        peliculas.add(new PeliculasDC("Batman", "Batman regresa 2", "1994"
                , "https://cdn.vox-cdn.com/thumbor/K1WKyMb31K-K1vvseGAyFsjfYYE=/0x0:1200x675/1200x800/filters:focal(478x31:670x223)/cdn.vox-cdn.com/uploads/chorus_image/image/60384393/0_c9S8ajFBpwX89ZuU.0.jpeg"));
        peliculas.add(new PeliculasDC("Batman", "Batman Beign Falls", "2016"
                , "https://imagesyoulike.com/images/00000/32x24/09723.jpg"));
        peliculas.add(new PeliculasDC("Mujer maravilla", "Mujer Maravilla regresa", "2018"
                , "https://laopinionla.files.wordpress.com/2017/08/96268248_2ca421dc-198a-4888-aeb3-db18c3fba916.gif?w=940"));
        dcAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPeliculaClickListener(PeliculasDC peliculasDC) {
        Util.showToast("Click \n" + peliculasDC.toString(), getApplicationContext());
    }

    @Override
    public void onPeliculaLongClickListener(PeliculasDC peliculasDC) {
        Util.showToast("LongClick\n" + peliculasDC.toString(), getApplicationContext());
    }
}
