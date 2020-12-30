package com.example.maskoki;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maskoki.databinding.ItemKomenBinding;
import com.example.maskoki.models.Komen;

import java.util.List;

public class AdapterKomen extends RecyclerView.Adapter<AdapterKomen.MyViewHolder> {
    private List<Komen> listkomen;
    private DeleteClick deleteClick;
    private String username;

    public AdapterKomen(DeleteClick deleteClick, String token) {
        this.deleteClick = deleteClick;
        this.username = token;
    }

    public void setListMakanan(List<Komen> listkomen) {
        this.listkomen = listkomen;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemKomenBinding binding = ItemKomenBinding.inflate(layoutInflater, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Komen komen = listkomen.get(position);
        holder.bind(komen, deleteClick);
    }

    @Override
    public int getItemCount() {
        if(listkomen == null){
            return 0;
        }
        return listkomen.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemKomenBinding binding;

        public MyViewHolder(ItemKomenBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Komen komen, DeleteClick deleteClick){
            binding.setKomen(komen);
            binding.setDelete(deleteClick);
            if (komen.getUser().getUsername().equals(username)){
                binding.hpsKomen.setVisibility(View.VISIBLE);
            }else{
                binding.hpsKomen.setVisibility(View.GONE);
            }
            binding.executePendingBindings();
        }
    }
}
