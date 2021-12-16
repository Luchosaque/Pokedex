package com.example.listviewpersonalizado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorPersonalizado extends BaseAdapter
{
    ArrayList<Pokemon>pokemons ;
    Context context;

    public AdaptadorPersonalizado(ArrayList<Pokemon> pokemons, Context context)
    {
        this.pokemons = pokemons;
        this.context = context;
    }

    @Override
    public int getCount()
    {

        return pokemons.size();
    }

    @Override
    public Pokemon getItem(int i)
    {
        return pokemons.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }
    //se ejecuta de forma automatica al  renderizar cada uno de los items
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //genera new view a partir del layout item_pkm generado
        View viewInflado = LayoutInflater.from(context).inflate(R.layout.item_pkm,null);
        TextView nombre = viewInflado.findViewById(R.id.nombrePkm);
        ImageView img = viewInflado.findViewById(R.id.imgPkm);
        nombre.setText(pokemons.get(position).getNombre());
        Picasso.get().load(MainActivity.pkmImgUrl.get(position)).into(img);

        return viewInflado;
    }
}
