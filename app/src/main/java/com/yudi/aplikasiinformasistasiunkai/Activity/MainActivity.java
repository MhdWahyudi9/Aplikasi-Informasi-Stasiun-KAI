package com.yudi.aplikasiinformasistasiunkai.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yudi.aplikasiinformasistasiunkai.API.ApiRequest;
import com.yudi.aplikasiinformasistasiunkai.API.RetroServer;
import com.yudi.aplikasiinformasistasiunkai.Adapter.StasiunkaiAdapter;
import com.yudi.aplikasiinformasistasiunkai.Model.StasiunKAI;
import com.yudi.aplikasiinformasistasiunkai.Model.StasiunKAIResponse;
import com.yudi.aplikasiinformasistasiunkai.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvStasiunKai;
    private ProgressBar pbLoading;

    private RecyclerView.Adapter adapterStasiunKai;
    private RecyclerView.LayoutManager layoutManagerStasiunKai;
    private List<StasiunKAI> listStasiunKai = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvStasiunKai = findViewById(R.id.rvStasiunKai);
        pbLoading = findViewById(R.id.pbLoading);

        layoutManagerStasiunKai = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvStasiunKai.setLayoutManager(layoutManagerStasiunKai);
    }

    @Override
    protected void onResume() {
        super.onResume();
        readData();
    }

    public void readData(){
        pbLoading.setVisibility(View.VISIBLE);
        ApiRequest Api = RetroServer.connectRetrofit().create(ApiRequest.class);
        Call<StasiunKAIResponse> readData = Api.stasiunkaiRead();

        readData.enqueue(new Callback<StasiunKAIResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<StasiunKAIResponse> call, Response<StasiunKAIResponse> response) {
                pbLoading.setVisibility(View.GONE);
                String message = response.message();
                Log.d("ResponDataSukses", "onResponse: " + message);
                if (response.body() != null) {
                    listStasiunKai = response.body().getData();

                    adapterStasiunKai = new StasiunkaiAdapter(MainActivity.this, listStasiunKai);
                    rvStasiunKai.setAdapter(adapterStasiunKai);
                    adapterStasiunKai.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<StasiunKAIResponse> call, Throwable t) {
                pbLoading.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Gagal menghubungi server!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}