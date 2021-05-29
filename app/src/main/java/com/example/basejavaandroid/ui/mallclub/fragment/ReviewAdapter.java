package com.example.basejavaandroid.ui.mallclub.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.basejavaandroid.R;
import com.example.basejavaandroid.databinding.ItemReviewBinding;
import com.example.basejavaandroid.model.Review;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    Context context;
    ArrayList<Review> list = new ArrayList<>();

    public ReviewAdapter(Context context) {
        this.context = context;
    }

    public ReviewAdapter(Context context, ArrayList<Review> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(ArrayList<Review> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemReviewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_review, parent, false);
        return new ViewHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder holder, int position) {
        Review review = list.get(position);
        holder.binData(review);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemReviewBinding binding;

        public ViewHolder(ViewDataBinding itemView) {
            super(itemView.getRoot());
            this.binding = (ItemReviewBinding) itemView;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        public void binData(Review review) {
            if (review != null) {
                Glide.with(binding.ivTenant).load(review.getImgTenant()).into(binding.ivTenant);
                Glide.with(binding.ivAvatar).load(review.getImgTenant()).into(binding.ivAvatar);
                binding.tvTenant.setText(review.getNameTenant());
                binding.tvNameUser.setText(review.getNameAuthor());
                binding.tvTime.setText(review.getTimeReview());
                binding.tvContent.setText(review.getContent());
                binding.tvNumberLike.setText(review.getNumberLike() + "");
                SpannableString string = new SpannableString(review.getNumberStar() + "/5");
                string.setSpan(new ForegroundColorSpan(Color.RED), 0, String.valueOf(review.getNumberStar()).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                binding.tvNumberStar.setText(string);
                if (review.isLike()) {
                    binding.tvNumberLike.getCompoundDrawables()[0].setTint(Color.parseColor("#E30066"));
                } else {
                    binding.tvNumberLike.getCompoundDrawables()[0].setTint(Color.BLACK);
                }
            }
        }
    }
}
