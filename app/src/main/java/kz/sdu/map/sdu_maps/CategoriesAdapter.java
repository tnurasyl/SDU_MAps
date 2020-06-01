package kz.sdu.map.sdu_maps;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kz.sdu.map.sdu_maps.models.CategoryModel;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.VHCategories> {

    private ArrayList<CategoryModel> items;
    private Context context;
    private OnClickListener listener;

    public CategoriesAdapter(ArrayList<CategoryModel> items, Context context, OnClickListener listener) {
        this.items = items;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VHCategories onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VHCategories(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category_item, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(@NonNull VHCategories holder, int position) {
        holder.tvName.setText(items.get(position).getName());
        holder.tvName.setTextColor(ContextCompat.getColor(context, items.get(position).getColor()));
        holder.ivLogo.setImageDrawable(ContextCompat.getDrawable(context, items.get(position).getMarkerIcon()));
        holder.ivLogo.setColorFilter(ContextCompat.getColor(context, items.get(position).getColor()), PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class VHCategories extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivLogo;
        TextView tvName;
        OnClickListener listener;

        VHCategories(View itemView, OnClickListener listener) {
            super(itemView);
            ivLogo = itemView.findViewById(R.id.ivLogo);
            tvName = itemView.findViewById(R.id.tvName);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onCategoryClicked(getAdapterPosition());
        }
    }

    interface OnClickListener {
        void onCategoryClicked(int categoryId);
    }
}
