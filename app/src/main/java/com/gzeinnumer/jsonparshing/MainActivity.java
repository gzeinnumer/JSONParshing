package com.gzeinnumer.jsonparshing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    AdapterRV adapter;

    List<Hero> heroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Hero>> call = api.getBio();

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {

                heroes = response.body();

//                for (Hero h : heroes) {
//                    Log.d("name", h.getName());
//                    Log.d("realname", h.getRealname());
//                    Log.d("imageurl", h.getImageurl());
//                }

                ArrayList<Hero> arrayHeroes = new ArrayList<>();
                for (int i =0; i<heroes.size(); i++){
                    arrayHeroes.add(new Hero(
                            heroes.get(i).getCreatedby(),
                            heroes.get(i).getFirstappearance(),
                            heroes.get(i).getImageurl(),
                            heroes.get(i).getName(),
                            heroes.get(i).getPublisher(),
                            heroes.get(i).getBio(),
                            heroes.get(i).getTeam(),
                            heroes.get(i).getRealname()));
                }
                adapter = new AdapterRV(getApplicationContext(), arrayHeroes);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }


}
