package com.android.supay.test.practicaViewPager;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.supay.test.R;
import com.android.supay.test.goodviewpager.GoodPagerAdapter;
import com.android.supay.test.practicaViewPager.fragments.FragmentDatosUsuario;
import com.android.supay.test.practicaViewPager.fragments.FragmentDireccion;
import com.android.supay.test.practicaViewPager.fragments.FragmentTareas;
import com.android.supay.test.util.Definitions;
import com.android.supay.test.util.KeyDefinitions;
import com.android.supay.test.util.MenuDefinitions;
import com.android.supay.test.util.Util;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewPagerAltaUsuarioActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    public static String TAG = ViewPagerAltaUsuarioActivity.class.getSimpleName();

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.floatingActionButtonReturn)
    FloatingActionButton floatingActionButtonReturn;

    private HashMap<String, String> data;

    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_alta_usuario);
        Intent intent = getIntent();
        if(intent != null){
            data = new HashMap<>();
            data.put(KeyDefinitions.NAME, intent.getStringExtra(KeyDefinitions.NAME) );
            data.put(KeyDefinitions.LAST_NAME, intent.getStringExtra(KeyDefinitions.LAST_NAME) );
            data.put(KeyDefinitions.ADDRESS, intent.getStringExtra(KeyDefinitions.ADDRESS) );
            data.put(KeyDefinitions.EMAIL, intent.getStringExtra(KeyDefinitions.EMAIL) );

        }
        ButterKnife.bind(this);
        setUpViewPager();
    }

    private void  setUpViewPager(){
        GoodPagerAdapter pagerAdapter = new GoodPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(FragmentTareas.newInstance(), FragmentTareas.TAG);
        pagerAdapter.addFragment(FragmentDatosUsuario.newInstance(data), FragmentDatosUsuario.TAG);
        pagerAdapter.addFragment(FragmentDireccion.newInstance(data), FragmentDireccion.TAG);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }

    @OnClick(R.id.floatingActionButtonReturn)public void onClickReturn(View view){
        if(currentPage>0){
            Util.showLog(TAG, "Return to page : " + viewPager.getAdapter().getPageTitle(currentPage - 1));
            viewPager.setCurrentItem(currentPage - 1, true);
        }else{
            Util.showLog(TAG, "Return to login page." );
            Util.changeActivity(ViewPagerAltaUsuarioActivity.this, FormAltaUsuarioActivity.class, new HashMap<>());
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        Util.showLog(TAG, "Page Selected: " + i + " " + viewPager.getAdapter().getPageTitle(currentPage));
        currentPage = i;
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.frm_alta_usuario_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.inicio:
                Util.showLog(TAG, "Return to login page." );
                Util.changeActivity(ViewPagerAltaUsuarioActivity.this, FormAltaUsuarioActivity.class, new HashMap<>());
                break;

            case R.id.tareas:
                Util.showLog(TAG, "Return to page : " + viewPager.getAdapter().getPageTitle(MenuDefinitions.FRAGMMENT_INDEX_TAREAS));
                viewPager.setCurrentItem(MenuDefinitions.FRAGMMENT_INDEX_TAREAS, true);
                break;

            case R.id.datosUsuario:
                Util.showLog(TAG, "Return to page : " + viewPager.getAdapter().getPageTitle(MenuDefinitions.FRAGMMENT_INDEX_DATOS_USUARIO));
                viewPager.setCurrentItem(MenuDefinitions.FRAGMMENT_INDEX_DATOS_USUARIO, true);
                break;

            case R.id.direccion:
                Util.showLog(TAG, "Return to page : " + viewPager.getAdapter().getPageTitle(MenuDefinitions.FRAGMMENT_INDEX_DIRECCION));
                viewPager.setCurrentItem(MenuDefinitions.FRAGMMENT_INDEX_DIRECCION, true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
