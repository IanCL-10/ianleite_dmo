package br.edu.iancl.listadecontatos_dmo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.edu.iancl.listadecontatos_dmo.R;
import br.edu.iancl.listadecontatos_dmo.model.Constantes;
import br.edu.iancl.listadecontatos_dmo.model.Contato;
import br.edu.iancl.listadecontatos_dmo.view.RecyclerItemClickListener;

public class ItemContatoAdapter extends RecyclerView.Adapter<ItemContatoAdapter.ViewHolder>{

    private Context mContext;
    private List<Contato> mContatos;
    private List<Contato> mContatosFavoritos;
    private static RecyclerItemClickListener mClickListener;

    public ItemContatoAdapter(Context context, List<Contato> data, List<Contato> favoritos){
        this.mContext = context;
        this.mContatos = data;
        this.mContatosFavoritos = favoritos;
    }

    @Override
    public int getItemCount() {
        return mContatos.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_contato, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemContatoAdapter.ViewHolder holder, int position) {
        Contato contato = mContatos.get(position);
        holder.nomeTextView.setText(contato.getNome());
        holder.apelidoTextView.setText(contato.getApelido());
        if(contato.isFavorite()){
            holder.favoritoImageView.setColorFilter(mContext.getResources().getColor(R.color.red, mContext.getTheme()));
        }else{
            holder.favoritoImageView.setColorFilter(mContext.getResources().getColor(R.color.gray, mContext.getTheme()));
        }

        holder.favoritoImageView.setOnClickListener(v -> {
            if(v == holder.favoritoImageView){
                heartClick(position);
            }
        });
    }

    public void setClickListener(RecyclerItemClickListener clickListener){
        this.mClickListener = clickListener;
    }

    private void heartClick(int position) {
        Contato contato = mContatos.get(position);
        if(contato.isFavorite()){
            mContatosFavoritos.remove(contato);
        }
        else {
            if(mContatosFavoritos.size() < Constantes.MAX_FAVORITES){
                mContatosFavoritos.add(contato);
            }
            else{
                contato.setFavorite(!contato.isFavorite());
                Toast.makeText(mContext.getApplicationContext(), "Lista de favoritos cheia", Toast.LENGTH_SHORT).show();
            }
        }
        contato.setFavorite(!contato.isFavorite());
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nomeTextView;
        public TextView apelidoTextView;
        public ImageView favoritoImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.text_nome);
            apelidoTextView = itemView.findViewById(R.id.text_apelido);
            favoritoImageView = itemView.findViewById(R.id.img_icon_favorite);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mClickListener != null){
                mClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
