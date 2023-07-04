package com.yudi.aplikasiinformasistasiunkai.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yudi.aplikasiinformasistasiunkai.API.ApiRequest;
import com.yudi.aplikasiinformasistasiunkai.API.RetroServer;
import com.yudi.aplikasiinformasistasiunkai.Model.StasiunKAIResponse;
import com.yudi.aplikasiinformasistasiunkai.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddActivity extends AppCompatActivity {

    private ProgressBar pbLoading;
    private EditText etNama, etLinkFoto, etKota, etLuas, etSejarah;
    private String nama, linkFoto, kota, sejarah;
    private Integer luas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        pbLoading = findViewById(R.id.pbLoading);

        etNama = findViewById(R.id.etNama);
        etLinkFoto = findViewById(R.id.etLinkFoto);
        etKota = findViewById(R.id.etKota);
        etLuas = findViewById(R.id.etLuas);
        etSejarah = findViewById(R.id.etSejarah);

        Button btnSave = findViewById(R.id.btnSimpan);

        btnSave.setOnClickListener(view -> {
            nama = etNama.getText().toString();
            linkFoto = etLinkFoto.getText().toString();
            kota = etKota.getText().toString();
            luas = Integer.valueOf(etLuas.getText().toString());
            sejarah = etSejarah.getText().toString();

            if (nama.trim().equals("")) {
                etNama.setError("Field nama harus diisi !");
            }
            else if (linkFoto.trim().equals("")) {
                etLinkFoto.setError("Field Link Foto harus diisi !");
            }
            else if (kota.trim().equals("")) {
                etKota.setError("Field Kota harus diisi !");
            }
            else if (luas.toString().equals("")) {
                etLuas.setError("Field Luas harus diisi !");
            }
            else if (sejarah.trim().equals("")) {
                etSejarah.setError("Field Sejarah harus diisi !");
            }
            else {
                addStasiunKai();
            }
        });
    }

    private void addStasiunKai() {
        pbLoading.setVisibility(View.VISIBLE);
        ApiRequest Api = RetroServer.connectRetrofit().create(ApiRequest.class);
        Call<StasiunKAIResponse> addData = Api.stasiunkasiCreate(nama, linkFoto, sejarah, kota, luas);

        addData.enqueue(new Callback<StasiunKAIResponse>() {
            @Override
            public void onResponse(@NonNull Call<StasiunKAIResponse> call, @NonNull Response<StasiunKAIResponse> response) {
                pbLoading.setVisibility(View.GONE);
                if (response.body() != null) {
                    String message = response.body().getMessage();
                    Toast.makeText(AddActivity.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(@NonNull Call<StasiunKAIResponse> call, @NonNull Throwable t) {
                pbLoading.setVisibility(View.GONE);
                Toast.makeText(AddActivity.this, "Gagal menghubungi server !", Toast.LENGTH_SHORT).show();
            }
        });
    }
}