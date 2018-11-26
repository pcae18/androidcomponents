package com.android.supay.test.db;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.supay.test.R;
import com.android.supay.test.db.adapter.DogShopAdapter;
import com.android.supay.test.db.adapter.DogShopClick;
import com.android.supay.test.db.adapter.DogShopLongClick;
import com.android.supay.test.model.dogshop.DogShop;
import com.android.supay.test.util.Definitions;
import com.android.supay.test.util.KeyDefinitions;
import com.android.supay.test.util.Util;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RealmRecyclerSampleActivity extends AppCompatActivity implements DogShopClick, DogShopLongClick {

    public static String TAG = RealmRecyclerSampleActivity.class.getSimpleName();

    private RealmList<DogShop> dogShops = new RealmList<>();

    private DogShopAdapter dogShopAdapter;

    @BindView(R.id.shopsRecyclerView) RecyclerView mDogShopRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_recycler_sample);
        ButterKnife.bind(this);
        setUpRecyclerView(mDogShopRecyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getRealmObject();
    }

    private void getRealmObject() {
        dogShops.clear();
        dogShopAdapter.notifyDataSetChanged();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<DogShop> dogShopRealmResults = realm.where(DogShop.class).findAll();
        dogShops.addAll(dogShopRealmResults.subList(0, dogShopRealmResults.size()));
        dogShopAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.floatingActionButton) public void addDogStore(){
        HashMap<String, String > data = new HashMap<>();
        data.put(KeyDefinitions.MODE_KEY, String.valueOf( Definitions.CREATE_MODE));
        Util.changeActivity(RealmRecyclerSampleActivity.this, EditShopActivity.class, data, false);
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
        HashMap<String, String > data = new HashMap<>();
        data.put(KeyDefinitions.DOG_SHOP_ID, dogShop.getDogShopId());
        data.put(KeyDefinitions.MODE_KEY, String.valueOf( Definitions.EDIT_MODE));
        Util.changeActivity(RealmRecyclerSampleActivity.this, EditShopActivity.class, data, false);
    }

    @Override
    public void onDogShopLongClickListener(DogShop dogShop) {
        Util.showToast("LongClick\n" + dogShop.toString(), getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realmTransaction) {
                dogShops.remove(dogShop);
                dogShopAdapter.notifyDataSetChanged();
                RealmResults<DogShop> shops = realmTransaction.where(DogShop.class)
                        .equalTo(KeyDefinitions.DOG_SHOP_ID, dogShop.dogShopId)
                        .findAll();
                shops.deleteAllFromRealm();
            }
        });
    }
}
