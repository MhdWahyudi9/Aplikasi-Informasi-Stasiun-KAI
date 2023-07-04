package com.yudi.aplikasiinformasistasiunkai.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yudi.aplikasiinformasistasiunkai.R;
import com.yudi.aplikasiinformasistasiunkai.Utils.NumberFormatter;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    private String yNama, yLinkFoto, yKota, ySejarah, yLuas;
    private TextView tvNama, tvKota, tvLuas, tvSejarah;
    private ImageView ivFoto;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Detail Informasi Stasiun KAI");

        tvNama = findViewById(R.id.tvDetailNama);
        tvKota = findViewById(R.id.tvDetailKota);
        tvLuas = findViewById(R.id.tvDetailLuas);
        tvSejarah = findViewById(R.id.tvDetailSejarah);
        ivFoto = findViewById(R.id.ivDetailFoto);

        Intent getData = getIntent();
        yNama = getData.getStringExtra("xNama");
        yKota = getData.getStringExtra("xKota");
        yLuas = getData.getStringExtra("xLuas");
        ySejarah = getData.getStringExtra("xSejarah");
        yLinkFoto = getData.getStringExtra("xLinkFoto");

        tvNama.setText(yNama);
        tvSejarah.setText(ySejarah);
        tvLuas.setText(NumberFormatter.formatNumber(Integer.parseInt(yLuas)) + " Meter Persegi");
        tvKota.setText(yKota);
        Glide.with(this)
                .load(yLinkFoto)
                .placeholder(R.drawable.placeholder_image)
                .into(ivFoto);
    }
}