package com.android.supay.test.practicaRecycler.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.supay.test.R;
import com.android.supay.test.db.adapter.DogShopAdapter;
import com.android.supay.test.model.PeliculasDC;
import com.android.supay.test.model.dogshop.DogShop;
import com.android.supay.test.util.Util;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DCAdapter extends RecyclerView.Adapter<DCAdapter.ViewHolderAdapter> {

    List<PeliculasDC> peliculas;
    Context contextAdapter;
    DCClick peliculaClick;
    DCLongClick peliculaLongClick;

    public DCAdapter(List<PeliculasDC> peliculas, Context contextAdapter, DCClick peliculaClick, DCLongClick peliculaLongClick) {
        this.peliculas = peliculas;
        this.contextAdapter = contextAdapter;
        this.peliculaClick = peliculaClick;
        this.peliculaLongClick = peliculaLongClick;
    }

    @NonNull
    @Override
    public ViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        contextAdapter = viewGroup.getContext();
        View view = LayoutInflater.from(contextAdapter).inflate(R.layout.item_pelicula_dc, viewGroup, false);
        return new DCAdapter.ViewHolderAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DCAdapter.ViewHolderAdapter viewHolderAdapter, int position) {
        PeliculasDC peliculaDC = peliculas.get(position);
        viewHolderAdapter.setTextsAndImage(peliculaDC);

        viewHolderAdapter.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peliculaClick.onPeliculaClickListener(peliculaDC);

            }
        });

        viewHolderAdapter.rootView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                peliculaLongClick.onPeliculaLongClickListener(peliculaDC);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public class ViewHolderAdapter extends RecyclerView.ViewHolder {

        @BindView(R.id.imageViewPelicula)
        ImageView mImgShop;

        @BindView(R.id.textViewNombreProtagonista)
        TextView mTvNombreProtagonista;

        @BindView(R.id.textViewTitulo)
        TextView mTvTitulo;

        @BindView(R.id.textViewAnio)
        TextView mTvAnio;


        View rootView;

        public ViewHolderAdapter(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            rootView = itemView;
        }

        public void setTextsAndImage(PeliculasDC peliculaDC){
            Util.setUpImageView(peliculaDC.getImage(), contextAdapter, mImgShop);
            mTvNombreProtagonista.setText(peliculaDC.getProtagonista());
            mTvTitulo.setText(peliculaDC.getTitulo());
            mTvAnio.setText(peliculaDC.getAnio());
        }
    }
}
