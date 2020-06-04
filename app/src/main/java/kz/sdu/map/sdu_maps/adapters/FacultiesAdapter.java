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
import kz.sdu.map.sdu_maps.models.FacultetsModel;

public class FacultiesAdapter extends RecyclerView.Adapter<FacultiesAdapter.FacultyVH> {

    private List<FacultetsModel> items;
    private OnFacultyClickListener listener;

    public FacultiesAdapter(List<FacultetsModel> items, OnFacultyClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FacultyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FacultyVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_faculty_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyVH holder, final int position) {
        holder.bind(items.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(items.get(position).getFacultetId(), items.get(position).getFacultetName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class FacultyVH extends RecyclerView.ViewHolder {

        ImageView ivFacultyLogo;
        TextView tvFacultyName;

        FacultyVH(@NonNull View itemView) {
            super(itemView);
            ivFacultyLogo = itemView.findViewById(R.id.ivFacultyLogo);
            tvFacultyName = itemView.findViewById(R.id.tvFacultyName);
        }

        void bind(FacultetsModel model) {
            ivFacultyLogo.setImageResource(model.getLogoId());
            tvFacultyName.setText(model.getFacultetName());
        }
    }

    public interface OnFacultyClickListener {
        void onClick(int facultyId, String facultyName);
    }
}
