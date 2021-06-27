package br.edu.iancl.minhastarefas_dmo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.iancl.minhastarefas_dmo.R;
import br.edu.iancl.minhastarefas_dmo.model.Tarefa;
import br.edu.iancl.minhastarefas_dmo.view.RecyclerItemClickListener;

public class ItemTarefaConcluidaAdapter extends RecyclerView.Adapter<ItemTarefaConcluidaAdapter.ViewHolder>{

    private Context mContext;
    private List<Tarefa> mTarefasConcluidas;
    private static RecyclerItemClickListener mClickListener;

    public ItemTarefaConcluidaAdapter(Context context, List<Tarefa> concluidas){
        this.mContext = context;
        this.mTarefasConcluidas = concluidas;
    }

    @Override
    public int getItemCount() {
        return mTarefasConcluidas.size();
    }

    @Override
    public ItemTarefaConcluidaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_concluida, parent, false);
        ItemTarefaConcluidaAdapter.ViewHolder viewHolder = new ItemTarefaConcluidaAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemTarefaConcluidaAdapter.ViewHolder holder, int position) {
        Tarefa tarefa = mTarefasConcluidas.get(position);
        holder.tituloTextView.setText(tarefa.getTitulo());
        holder.dataTextView.setText(tarefa.getDataLimite());
    }

    public void setClickListener(RecyclerItemClickListener clickListener){
        this.mClickListener = clickListener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tituloTextView;
        public TextView dataTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.text_titulo);
            dataTextView = itemView.findViewById(R.id.text_data);
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
