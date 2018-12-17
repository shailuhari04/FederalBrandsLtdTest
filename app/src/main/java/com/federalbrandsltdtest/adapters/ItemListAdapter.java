package com.federalbrandsltdtest.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.federalbrandsltdtest.R;
import com.federalbrandsltdtest.pojos.PhotosRealmPojo;
import com.federalbrandsltdtest.ui.detail.ItemDetailsActivity;
import com.federalbrandsltdtest.utils.Constants;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder> {


    private Context context;
    private List<PhotosRealmPojo> photosRealmPojoList;

    public ItemListAdapter(Context context, List<PhotosRealmPojo> photosRealmPojoList) {
        this.context = context;
        this.photosRealmPojoList = photosRealmPojoList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        holder.tv_title.setText(photosRealmPojoList.get(position).getTitle());
        holder.tv_url.setText(photosRealmPojoList.get(position).getUrl());

        holder.materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemDetailsActivity.class);
                intent.putExtra(Constants.TITLE, photosRealmPojoList.get(position).getTitle());
                intent.putExtra(Constants.URL, photosRealmPojoList.get(position).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photosRealmPojoList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView materialCardView;
        ImageView imageView;
        TextView tv_title;
        TextView tv_url;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            materialCardView = (MaterialCardView) itemView.findViewById(R.id.cv_row_item);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_url = (TextView) itemView.findViewById(R.id.tv_url);
        }
    }
}
