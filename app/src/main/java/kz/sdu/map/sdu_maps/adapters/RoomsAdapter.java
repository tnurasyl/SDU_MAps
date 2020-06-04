package kz.sdu.map.sdu_maps.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kz.sdu.map.sdu_maps.R;
import kz.sdu.map.sdu_maps.listeners.OnRoomSelectedListener;
import kz.sdu.map.sdu_maps.models.RoomModel;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.RoomVH> {

    private List<RoomModel> items;
    private OnRoomSelectedListener listener;

    public RoomsAdapter(List<RoomModel> items, OnRoomSelectedListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RoomVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RoomVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_faculty_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RoomVH holder, final int position) {
        holder.render(items.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelected = !items.get(position).isSelected();
                for (int i = 0; i < items.size(); i++) {
                    if (i == position)
                        items.get(i).setSelected(isSelected);
                    else items.get(i).setSelected(false);
                }
                listener.onRoomSelected(items.get(position));
                notifyDataSetChanged();
            }
        });
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
        LinearLayout llContainer;

        RoomVH(@NonNull View itemView) {
            super(itemView);
            ivLogo = itemView.findViewById(R.id.ivFacultyLogo);
            tvName = itemView.findViewById(R.id.tvFacultyName);
            ivNext = itemView.findViewById(R.id.ivNext);
            llContainer = itemView.findViewById(R.id.llContainer);
        }

        void render(RoomModel model) {
            ivNext.setVisibility(View.GONE);
            tvName.setText(model.getRoomName());
            ivLogo.setImageResource(model.getLogoId());
            llContainer.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), model.getBackgroundColor()));
        }
    }
}
