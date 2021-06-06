package com.example.basejavaandroid.ui.mallclub.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.basejavaandroid.R;
import com.example.basejavaandroid.databinding.ItemLoadingBinding;
import com.example.basejavaandroid.databinding.ItemReviewBinding;
import com.example.basejavaandroid.model.Review;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<Review> list = new ArrayList<>();
    public static int TYPE_REVIEW = 123;
    public static int TYPE_LOADING = 456;
    ReviewCallBack callBack;


    public ReviewAdapter(Context context) {
        this.context = context;
    }

    public ReviewAdapter(Context context, ArrayList<Review> list) {
        this.context = context;
        this.list = list;
    }

    public void setCallBack(ReviewCallBack callBack) {
        this.callBack = callBack;
    }

    public void setList(ArrayList<Review> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void showNullData(boolean isShow) {
        if (list != null && list.size() > 0) {
            if (isShow) {
                list.add(null);
                notifyItemInserted(list.size() - 1);
            } else {
                if (list.get(list.size() - 1) == null) {
                    list.remove(list.size() - 1);
                    notifyItemRemoved(list.size() - 1);
                }
            }
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_REVIEW) {
            ItemReviewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_review, parent, false);
            return new MYViewHolder(binding);
        } else {
            ItemLoadingBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_loading, parent, false);
            return new LoadingHoder(binding);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LoadingHoder) {

        }
        if (holder instanceof MYViewHolder) {
            Review review = list.get(position);
            ((MYViewHolder) holder).binData(review);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? TYPE_LOADING : TYPE_REVIEW;
    }

    public interface ReviewCallBack {
        void onclickItem(int pos);

        void onClickLike(int pos);
    }

    public class LoadingHoder extends RecyclerView.ViewHolder {
        ItemLoadingBinding binding;

        public LoadingHoder(@NonNull @NotNull ItemLoadingBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    public class MYViewHolder extends RecyclerView.ViewHolder {
        ItemReviewBinding binding;

        public MYViewHolder(ViewDataBinding itemView) {
            super(itemView.getRoot());
            this.binding = (ItemReviewBinding) itemView;
            binding.layoutReview.setOnClickListener(v -> {
                if (callBack != null) {
                    callBack.onclickItem(getAdapterPosition());
                }
            });
            binding.tvNumberLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (callBack != null) {
                        callBack.onClickLike(getAdapterPosition());
                    }
                }
            });
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
