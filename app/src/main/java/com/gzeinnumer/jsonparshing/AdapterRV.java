package com.gzeinnumer.jsonparshing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterRV extends RecyclerView.Adapter<AdapterRV.MyHolder> {

    private Context mContext;
    private ArrayList<Hero> mHeroes;

    public AdapterRV(Context mContext, ArrayList<Hero> mHeroes) {
        this.mContext = mContext;
        this.mHeroes = mHeroes;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, viewGroup, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        Hero hero = mHeroes.get(i);
        Picasso.get().load(hero.getImageurl())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(myHolder.imgItem);

        myHolder.nameItem.setText(hero.getName());
        myHolder.realNameItem.setText(hero.getRealname());
        myHolder.teamItem.setText(hero.getTeam());
    }

    @Override
    public int getItemCount() {
        return mHeroes.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imgItem;
        TextView nameItem;
        TextView realNameItem;
        TextView teamItem;


        public MyHolder(@NonNull View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.imgItem);
            nameItem = itemView.findViewById(R.id.nameItem);
            realNameItem = itemView.findViewById(R.id.realNameItem);
            teamItem = itemView.findViewById(R.id.teamItem);
        }
    }
}










