package com.yudi.aplikasiinformasistasiunkai.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yudi.aplikasiinformasistasiunkai.API.ApiRequest;
import com.yudi.aplikasiinformasistasiunkai.API.RetroServer;
import com.yudi.aplikasiinformasistasiunkai.Activity.AddActivity;
import com.yudi.aplikasiinformasistasiunkai.Activity.DetailActivity;
import com.yudi.aplikasiinformasistasiunkai.Activity.MainActivity;
import com.yudi.aplikasiinformasistasiunkai.Activity.UpdateActivity;
import com.yudi.aplikasiinformasistasiunkai.Model.StasiunKAI;
import com.yudi.aplikasiinformasistasiunkai.Model.StasiunKAIResponse;
import com.yudi.aplikasiinformasistasiunkai.R;
import com.yudi.aplikasiinformasistasiunkai.Utils.NumberFormatter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StasiunkaiAdapter extends RecyclerView.Adapter<StasiunkaiAdapter.VHStasiunKAI> {

    private Context ctx;
    private List<StasiunKAI> listStasiunKAI;

    public StasiunkaiAdapter(Context ctx, List<StasiunKAI> listStasiunKAI) {
        this.ctx = ctx;
        this.listStasiunKAI = listStasiunKAI;
    }

    @NonNull
    @Override
    public StasiunkaiAdapter.VHStasiunKAI onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.list_item_stasiun_kai, parent, false);
        return new VHStasiunKAI(varView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull StasiunkaiAdapter.VHStasiunKAI holder, int position) {
        StasiunKAI stasiunKAI = listStasiunKAI.get(position);
        holder.tvId.setText(stasiunKAI.getId().toString());
        holder.tvNama.setText(stasiunKAI.getNama());
        holder.tvKota.setText(stasiunKAI.getKota());
        holder.tvSejarah.setText(stasiunKAI.getSejarah());
        holder.tvLuas.setText(NumberFormatter.formatNumber(stasiunKAI.getLuas()) + " Meter Persegi");
        Glide.with(ctx)
                .load(stasiunKAI.getFoto())
                .placeholder(R.drawable.placeholder_image)
                .into(holder.ivFoto);
        holder.linkFoto = stasiunKAI.getFoto();
        holder.luas = stasiunKAI.getLuas().toString();
    }

    @Override
    public int getItemCount() {
        return listStasiunKAI.size();
    }

    public class VHStasiunKAI extends RecyclerView.ViewHolder {
        ImageView ivFoto;
        TextView tvId, tvNama, tvSejarah, tvKota, tvLuas;

        String linkFoto;

        String luas;
        public VHStasiunKAI(@NonNull View itemView) {
            super(itemView);

            ivFoto = itemView.findViewById(R.id.ivFoto);
            tvId = itemView.findViewById(R.id.tvId);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvKota = itemView.findViewById(R.id.tvKota);
            tvLuas = itemView.findViewById(R.id.tvLuas);
            tvSejarah = itemView.findViewById(R.id.tvSejarah);

            itemView.setOnClickListener(view -> {
                Intent sendData = new Intent(ctx, DetailActivity.class);
                sendData.putExtra("xId", tvId.getText().toString());
                sendData.putExtra("xNama", tvNama.getText().toString());
                sendData.putExtra("xKota", tvKota.getText().toString());
                sendData.putExtra("xLuas", luas);
                sendData.putExtra("xSejarah", tvSejarah.getText().toString());
                sendData.putExtra("xLinkFoto", linkFoto);
                ctx.startActivity(sendData);
            });

            itemView.setOnLongClickListener(view -> {
                AlertDialog.Builder message = new AlertDialog.Builder(ctx);
                message.setTitle("Pehatian");
                message.setMessage("Anda memilih  " + tvNama.getText().toString() + ".\nOperasi apa yang ingin dilakukan ?");

                message.setPositiveButton("Ubah Data", (dialogInterface, i) -> {
                    Intent sendData = new Intent(ctx, UpdateActivity.class);
                    sendData.putExtra("xId", tvId.getText().toString());
                    sendData.putExtra("xNama", tvNama.getText().toString());
                    sendData.putExtra("xKota", tvKota.getText().toString());
                    sendData.putExtra("xLuas", luas);
                    sendData.putExtra("xSejarah", tvSejarah.getText().toString());
                    sendData.putExtra("xLinkFoto", linkFoto);
                    ctx.startActivity(sendData);
                });

                message.setNegativeButton("Hapus Data", (dialogInterface, i) -> {
                    deleteData(Integer.valueOf(tvId.getText().toString()));
                });

                message.show();
                return false;
            });
        }

        void deleteData(Integer id) {
            ApiRequest Api = RetroServer.connectRetrofit().create(ApiRequest.class);
            Call<StasiunKAIResponse> deleteData = Api.stasiunkasiDelete(id);

            deleteData.enqueue(new Callback<StasiunKAIResponse>() {
                @Override
                public void onResponse(@NonNull Call<StasiunKAIResponse> call, @NonNull Response<StasiunKAIResponse> response) {
                    if (response.body() != null) {
                        String message = response.body().getMessage();
                        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
                        ((MainActivity) ctx).readData();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<StasiunKAIResponse> call, @NonNull Throwable t) {
                    Toast.makeText(ctx, "Gagal menghubungi server !", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
