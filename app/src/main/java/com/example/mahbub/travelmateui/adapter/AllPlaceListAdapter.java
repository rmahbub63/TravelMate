package com.example.mahbub.travelmateui.adapter;

/**
 * Created by MAHBUB on 31-Oct-17.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mahbub.travelmateui.R;
import com.example.mahbub.travelmateui.ShowSelectedPlaceActivity;
import com.example.mahbub.travelmateui.model.PlaceModel;
import com.example.mahbub.travelmateui.model.PopularPlacesModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class AllPlaceListAdapter extends
        RecyclerView.Adapter<AllPlaceListAdapter.HorizontalViewHolderPopularPlace>{

    private Context context;
    private ArrayList<PlaceModel> placeModels;

    public AllPlaceListAdapter(Context context, ArrayList<PlaceModel> placeModels) {
        this.context = context;
        this.placeModels = placeModels;
    }

    @Override
    public HorizontalViewHolderPopularPlace onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_place_list_item, null);
        HorizontalViewHolderPopularPlace viewHolder = new HorizontalViewHolderPopularPlace(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HorizontalViewHolderPopularPlace holder, final int position) {
        //setting the values
        FirebaseAuth myAuth = FirebaseAuth.getInstance();
        FirebaseUser user = myAuth.getCurrentUser();

        //setting the values
        if (placeModels.get(position).getmainImageUrl() != null){
            String  photoUrl = placeModels.get(position).getmainImageUrl().toString();
            Glide
                    .with(context)
                    .load(photoUrl).into(holder.placeImage);
        }
        holder.placeName.setText(placeModels.get(position).getPlaceName());
        holder.placeRating.setRating(placeModels.get(position).getRating());
        if(!placeModels.get(position).getReviews().isEmpty() && placeModels.get(position).getReviews() != null){
            if (placeModels.get(position).getReviews().get(0).length()== 0){
                holder.placeReview.setText("0 Reviews");
            } else {
                holder.placeReview.setText(placeModels.get(position).getReviews().size() + " Reviews");
            }
        } else {
            holder.placeReview.setText("0 Reviews");
        }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String placeID = placeModels.get(position).getPlaceId();
                String placeName = placeModels.get(position).getPlaceName();

                Intent intent = new Intent(context, ShowSelectedPlaceActivity.class);
                intent.putExtra("place_id", placeID);
                intent.putExtra("place_name", placeName);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return placeModels.size();
    }

    public class HorizontalViewHolderPopularPlace extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView placeImage;
        TextView placeName;
        RatingBar placeRating;
        TextView placeReview;

        public HorizontalViewHolderPopularPlace(final View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_view);
            placeImage = itemView.findViewById(R.id.full_image_main);
            placeName = itemView.findViewById(R.id.name_text_view);
            placeRating = itemView.findViewById(R.id.ratingBar);
            placeReview = itemView.findViewById(R.id.review_text_view);
        }
    }
}
