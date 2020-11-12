package com.example.starwarsapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.starwarsapi.models.starWars;
import com.example.starwarsapi.R;

import java.util.List;

public class ListaStarWarsAdapter extends BaseAdapter {

    private final Context context;
    private final List<starWars> people;

    public ListaStarWarsAdapter(Context context, List<starWars> people) {
        this.context = context;
        this.people = people;
    }


    @Override
    public int getCount() {
        return this.people != null ? this.people.size() : 0;
    }

    @Override
    public starWars getItem(int i) {
        return this.people.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_starwarspeople,
                    viewGroup, false);
        }

        starWars people = getItem(position);

        TextView
                txtItemName,
                txtItemBirthYear,
                txtItemEyeColor,
                txtItemGender,
                txtItemHairColor,
                txtItemHeight,
                txtItemMass,
                txtItemSkinColor,
                txtItemHomeworld,
                txtItemCreated,
                txtItemEdited;



        try {
            txtItemName = view.findViewById(R.id.textName);
            txtItemBirthYear = view.findViewById(R.id.textBirth_year);
            txtItemEyeColor = view.findViewById(R.id.textEye_color);
            txtItemGender = view.findViewById(R.id.textGender);
            txtItemHairColor = view.findViewById(R.id.textHair_color);
            txtItemHeight = view.findViewById(R.id.textHeight);
            txtItemMass = view.findViewById(R.id.textMass);
            txtItemSkinColor = view.findViewById(R.id.textSkin_color);
            txtItemHomeworld = view.findViewById(R.id.textHomeworld);
            txtItemCreated = view.findViewById(R.id.textCreated);
            txtItemEdited = view.findViewById(R.id.textEdited);

            txtItemName.setText(people.getName());
            txtItemBirthYear.setText(String.valueOf(people.getBirth_year()));
            txtItemEyeColor.setText(people.getEye_color());
            txtItemGender.setText(people.getGender());
            txtItemHairColor.setText(people.getHair_color());
            txtItemHeight.setText(String.valueOf(people.getHeight()));
            txtItemMass.setText(String.valueOf(people.getMass()));
            txtItemSkinColor.setText(people.getSkin_color());
            txtItemHomeworld.setText(people.getHomeworld());
            txtItemCreated.setText(people.getCreated());
            txtItemEdited.setText(people.getEdited());

        } catch (Exception e) {
            System.out.println("-- Erro ao listar, verifique com seu DEV. --");
            e.printStackTrace();
        }
        return view;
    }
}

