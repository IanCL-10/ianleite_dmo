package br.edu.iancl.github_dmos5.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.iancl.github_dmos5.R;
import br.edu.iancl.github_dmos5.model.Repositorio;

public class ItemRepositorioAdapter extends RecyclerView.Adapter<ItemRepositorioAdapter.ViewHolder> {


    private Context mContext;
    private List<Repositorio> Repositorios;

    public ItemRepositorioAdapter(Context context, List<Repositorio> data){
        this.mContext = context;
        this.Repositorios = data;
    }

    @Override
    public int getItemCount() {
        return Repositorios.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_repositorio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemRepositorioAdapter.ViewHolder holder, int position) {
        Repositorio repositorio = Repositorios.get(position);
        holder.nomeRepTextView.setText(repositorio.getName());
    }

    public void setRepositorios(List<Repositorio> mRepositorios) {
        this.Repositorios = mRepositorios;
        this.notifyDataSetChanged();
    }

    public List<Repositorio> getRepositorios() {
        return Repositorios;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nomeRepTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nomeRepTextView = itemView.findViewById(R.id.text_nome_rep);
        }
    }
}
