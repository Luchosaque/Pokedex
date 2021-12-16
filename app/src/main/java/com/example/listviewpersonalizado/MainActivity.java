package com.example.listviewpersonalizado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    ListView listView;
    ArrayList<Pokemon> pokemons = new ArrayList<>();
    ArrayList<String> pkmNames = new ArrayList<>();
    static ArrayList<String> pkmImgUrl = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        
        AdaptadorPersonalizado adaptador = new AdaptadorPersonalizado(pokemons,this);
        listView.setAdapter(adaptador);

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Document pkm = Jsoup.connect("https://www.pokemon.com/es/pokedex/").get();
                    pkmNames = (ArrayList<String>) pkm.select("[href^=/es/pokedex/]").eachText();
                    pkmNames.remove(0);

                    for (int i =0;i<pkmNames.size();i++)
                    {
                        String pkmNum=String.format("%03d",i+1);
                        pkmImgUrl.add("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + pkmNum + ".png");
                        pokemons.add(new Pokemon(pkmNames.get(i)));
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        AdaptadorPersonalizado adapter = new AdaptadorPersonalizado(pokemons,MainActivity.this);
                        listView.setAdapter(adapter);
                    }
                });

            }
        }).start();

    }
}