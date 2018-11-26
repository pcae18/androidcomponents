package com.android.supay.test.db;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.android.supay.test.R;
import com.android.supay.test.db.adapter.DogShopAdapter;
import com.android.supay.test.db.adapter.DogShopClick;
import com.android.supay.test.db.adapter.DogShopLongClick;
import com.android.supay.test.model.dogshop.DogShop;
import com.android.supay.test.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RealmRecyclerSampleActivity extends AppCompatActivity implements DogShopClick, DogShopLongClick {

    public static String TAG = RealmRecyclerSampleActivity.class.getSimpleName();

    private ArrayList<DogShop> dogShops = new ArrayList<>();

    private DogShopAdapter dogShopAdapter;

    @BindView(R.id.shopsRecyclerView) RecyclerView mDogShopRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_recycler_sample);
        ButterKnife.bind(this);
        setUpRecyclerView(mDogShopRecyclerView);
    }

    @OnClick(R.id.floatingActionButton) public void addDommie(){
        dogShops.add(createDommie());
        dogShopAdapter.notifyDataSetChanged();
    }

    public DogShop createDommie(){
        return new DogShop("Test",
                "Calle Vidrio 1792",
                        Util.setRandomImage(),
                        String.valueOf(Util.getRandomID() ) );
    }

    private void setUpRecyclerView(RecyclerView mDogShopRecyclerView) {
        dogShopAdapter = new DogShopAdapter(dogShops, getApplicationContext(),this, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDogShopRecyclerView.setAdapter(dogShopAdapter);
        //mDogShopRecyclerView.setLayoutManager( new GridLayoutManager(this, 3));
        mDogShopRecyclerView.setLayoutManager(linearLayoutManager);

    }


    @Override
    public void onDogShopClickListener(DogShop dogShop) {
        Util.showToast("Click \n" + dogShop.toString(), getApplicationContext());
    }

    @Override
    public void onDogShopLongClickListener(DogShop dogShop) {
        Util.showToast("LongClick\n" + dogShop.toString(), getApplicationContext());
    }
}
