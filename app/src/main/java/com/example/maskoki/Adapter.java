package com.example.maskoki;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.maskoki.databinding.ItemResepBinding;
import com.example.maskoki.models.Post;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<Post> listMakanan;
    private ClickListener clickListener;

    public Adapter(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setListMakanan(List<Post> listMakanan) {
        this.listMakanan = listMakanan;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemResepBinding binding = ItemResepBinding.inflate(layoutInflater, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Post makanan = listMakanan.get(position);
        holder.bind(makanan, clickListener);
    }

    @Override
    public int getItemCount() {
        if(listMakanan == null){
            return 0;
        }
        return listMakanan.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemResepBinding binding;

        public MyViewHolder(ItemResepBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Post makanan, ClickListener clickListener){
            binding.setMakanan(makanan);
            Glide.with(binding.imgresep.getContext()).load(makanan.getImage()).into(binding.imgresep);
            binding.setClicklisterner(clickListener);
            binding.executePendingBindings();
        }
    }
}
