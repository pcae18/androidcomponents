package com.android.supay.test.db;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.supay.test.R;
import com.android.supay.test.model.dogshop.DogShop;
import com.android.supay.test.util.Definitions;
import com.android.supay.test.util.KeyDefinitions;
import com.android.supay.test.util.Util;

import java.security.Key;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmQuery;

import static com.android.supay.test.util.Util.setRandomImage;

public class EditShopActivity extends AppCompatActivity {

    @BindView(R.id.imageViewDogShop)
    ImageView mImgShop;

    @BindView(R.id.textInputLayout)
    TextInputLayout mTextInputLayoutName;

    @BindView(R.id.etName)
    EditText mEtName;

    @BindView(R.id.textInputLayoutAddress)
    TextInputLayout mTinLAddress;

    @BindView(R.id.etAddress)
    EditText mEtAddress;

    @BindView(R.id.parent)
    ConstraintLayout parentLayout;

    private int MODE;

    private String SHOP_ID;

    private DogShop dogShop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shop);
        ButterKnife.bind(this);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        MODE = Integer.parseInt( getIntent().getStringExtra(KeyDefinitions.MODE_KEY) );
        setUpMode(MODE);
    }

    private void setUpMode(int mode) {
        if(mode == Definitions.EDIT_MODE){
            updateMode();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
            break;
        }
        return true;
    }

    private void updateMode() {
        SHOP_ID = getIntent().getStringExtra(KeyDefinitions.DOG_SHOP_ID);
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<DogShop> query = realm.where(DogShop.class);
        query.equalTo(KeyDefinitions.DOG_SHOP_ID, SHOP_ID);
        dogShop = query.findFirst();
        if(dogShop != null){
            mEtName.setText(dogShop.getName());
            mEtAddress.setText(dogShop.getAddress());
            Util.setUpImageView(dogShop.getImage(),this, mImgShop);
        }
    }

    @OnClick(R.id.buttonEnd) public void finalize(View view){
        if(noEmptyFields()){
            if(noEmptyAddress() && noEmptyName()){
                if(MODE==Definitions.CREATE_MODE){
                    createShop();
                }else{
                    updateShop();
                }
            }
        }else{
            Util.showSnackBar( "Campos vacíos.", parentLayout);
        }
    }

    private void updateShop() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction( realm1 -> {
            dogShop.address = mEtAddress.getText().toString();
            dogShop.name = mEtName.getText().toString();
            realm1.copyToRealmOrUpdate(dogShop);
        });
        finish();
    }

    private void createShop() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realmTransaction) {
                realmTransaction.copyToRealm(new DogShop(
                        mEtName.getText().toString(),
                        mEtAddress.getText().toString(),
                        setRandomImage(),
                        String.valueOf(Util.getRandomID())
                ));
            }
        });
        finish();
    }

    private boolean validateFields() {
        if(noEmptyName() && noEmptyAddress()){
            return true;
        }else{
            return false;
        }
    }

    private boolean noEmptyAddress() {
        if(mEtAddress.getText().toString().isEmpty()){
            mTinLAddress.setError(getString(R.string.message_error_required_address));
            return false;
        }else{
            return true;
        }
    }

    private boolean noEmptyName() {
        if(mEtName.getText().toString().isEmpty()){
            mTextInputLayoutName.setError(getString(R.string.message_error_required_address));
            return false;
        }else{
            return true;
        }
    }

    private boolean noEmptyFields() {
        return !mEtName.getText().toString().isEmpty()&&!mEtAddress.getText().toString().isEmpty();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
