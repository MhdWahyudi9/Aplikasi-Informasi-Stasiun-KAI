package com.yudi.aplikasiinformasistasiunkai.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yudi.aplikasiinformasistasiunkai.Model.StasiunKAI;
import com.yudi.aplikasiinformasistasiunkai.R;
import com.yudi.aplikasiinformasistasiunkai.Utils.NumberFormatter;

import java.util.List;

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
        holder.tvNama.setText(stasiunKAI.getNama());
        holder.tvKota.setText(stasiunKAI.getKota());
        holder.tvSejarah.setText(stasiunKAI.getSejarah());
        holder.tvLuas.setText(NumberFormatter.formatNumber(stasiunKAI.getLuas()) + " Meter Persegi");
        Glide.with(ctx)
                .load(stasiunKAI.getFoto())
                .placeholder(R.drawable.placeholder_image)
                .into(holder.ivFoto);
    }

    @Override
    public int getItemCount() {
        return listStasiunKAI.size();
    }

    public class VHStasiunKAI extends RecyclerView.ViewHolder {
        ImageView ivFoto;
        TextView tvNama, tvSejarah, tvKota, tvLuas;
        public VHStasiunKAI(@NonNull View itemView) {
            super(itemView);

            ivFoto = itemView.findViewById(R.id.ivFoto);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvKota = itemView.findViewById(R.id.tvKota);
            tvLuas = itemView.findViewById(R.id.tvLuas);
            tvSejarah = itemView.findViewById(R.id.tvSejarah);
        }
    }
}
