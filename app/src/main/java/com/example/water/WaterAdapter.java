package com.example.water;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.waterreminder.R;

import java.util.List;

public class WaterAdapter extends RecyclerView.Adapter<WaterAdapter.WaterViewHolder> {
    private List<Water> mWater;
    private OnItemClickListener mlistener;

    public WaterAdapter(List<Water> list, OnItemClickListener listener) {
        this.mWater = list;
        this.mlistener = listener;
    }

    @NonNull
    @Override
    public WaterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_water, parent, false);
        return new WaterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WaterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Water water = mWater.get(position);
        if (water == null) {
            return;
        }
        holder.tv_title.setText(water.getTitle());
        holder.tv_price.setText(water.getTime());
        holder.tv_des.setText(water.getDes());
        holder.img_item.setImageResource(water.getId());
        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlistener.onItemClick(water, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mWater != null) {
            return mWater.size();
        }
        return 0;
    }

    public class WaterViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_item;
        private TextView tv_title;
        private TextView tv_des;
        private TextView tv_price;
        private LinearLayout layout_item;

        public WaterViewHolder(@NonNull View itemView) {
            super(itemView);
            img_item = itemView.findViewById(R.id.img_item);
            tv_title = itemView.findViewById(R.id.title_item);
            tv_des = itemView.findViewById(R.id.des_item);
            tv_price = itemView.findViewById(R.id.time_item);
            layout_item = itemView.findViewById(R.id.layout_item);
        }
    }
}
