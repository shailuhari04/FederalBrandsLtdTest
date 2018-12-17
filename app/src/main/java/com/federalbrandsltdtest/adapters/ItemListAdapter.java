package com.federalbrandsltdtest.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
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

        Glide.with(context)
                .load(photosRealmPojoList.get(position).getThumbnailUrl())
                .into(holder.imageView);

        try {
            holder.tv_title.setText(photosRealmPojoList.get(position).getTitle());
        } catch (Exception e) {
            e.printStackTrace();
            holder.tv_title.setText("");
        }

        try {
            holder.tv_url.setText(photosRealmPojoList.get(position).getUrl());
        } catch (Exception e) {
            e.printStackTrace();
            holder.tv_url.setText("");
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemDetailsActivity.class);
                intent.putExtra(Constants.TITLE, photosRealmPojoList.get(position).getTitle());
                intent.putExtra(Constants.URL, photosRealmPojoList.get(position).getUrl());
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) context, (CardView)holder.cardView, "row_item");
                context.startActivity(intent, options.toBundle());
                //context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photosRealmPojoList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView imageView;
        TextView tv_title;
        TextView tv_url;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            cardView = (CardView) itemView.findViewById(R.id.cv_row_item);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_url = (TextView) itemView.findViewById(R.id.tv_url);
        }
    }
}
