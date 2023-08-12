package com.example.targetdate.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.targetdate.R;
import com.example.targetdate.constants.GlobalMethods;
import com.example.targetdate.models.Categories;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private ArrayList<Categories> categoriesLsit;
    private Context context;
    CategoriesAdapter.OnItemClickListener mItemClickListener;

    public CategoriesAdapter(Context context, ArrayList<Categories> categoriesList) {
        this.categoriesLsit = categoriesList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item, viewGroup, false);
        return new CategoriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriesAdapter.ViewHolder viewHolder, int i) {


        if (GlobalMethods.isNull(categoriesLsit.get(i).getTitle()))
            viewHolder.catetoryName.setText(categoriesLsit.get(i).getCategory().replace("&nbsp;",""));

        if(GlobalMethods.isNull(categoriesLsit.get(i).getIcon())){
            Glide.with(context)
                    .load(categoriesLsit.get(i).getIcon())
                    .into(viewHolder.icon);
        }

        //Log.e("seller title",sellerList.get(i).getShopTitle());
    }


    @Override
    public int getItemCount() {
        return categoriesLsit.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView catetoryName;
        private AppCompatImageView icon;

        public ViewHolder(View view) {
            super(view);

            catetoryName = (TextView) view.findViewById(R.id.catetoryName);
            icon = (AppCompatImageView)view.findViewById(R.id.icon) ;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(v, getAdapterPosition(), categoriesLsit.get(getAdapterPosition()));
        }


    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position, Categories categories);
    }

    public void SetOnItemClickListener( OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
