package kz.sdu.map.sdu_maps.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kz.sdu.map.sdu_maps.R;
import kz.sdu.map.sdu_maps.models.RoomModel;

//
// Created by  on 04.06.20.
//
public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.RoomVH> {

    List<RoomModel> items;

    public RoomsAdapter(List<RoomModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RoomVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RoomVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_faculty_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoomVH holder, int position) {
        holder.render(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateData(List<RoomModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    class RoomVH extends RecyclerView.ViewHolder {

        ImageView ivLogo;
        TextView tvName;
        ImageView ivNext;

        public RoomVH(@NonNull View itemView) {
            super(itemView);
            ivLogo = itemView.findViewById(R.id.ivFacultyLogo);
            tvName = itemView.findViewById(R.id.tvFacultyName);
            ivNext = itemView.findViewById(R.id.ivNext);
        }

        public void render(RoomModel model) {
            ivNext.setVisibility(View.GONE);
            tvName.setText(model.getRoomName());
            ivLogo.setImageResource(model.getLogoId());
        }
    }
}
