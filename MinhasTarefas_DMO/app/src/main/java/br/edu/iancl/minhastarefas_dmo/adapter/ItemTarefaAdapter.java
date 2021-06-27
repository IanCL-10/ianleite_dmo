package br.edu.iancl.minhastarefas_dmo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.util.List;

import br.edu.iancl.minhastarefas_dmo.R;
import br.edu.iancl.minhastarefas_dmo.model.PrioridadeEnum;
import br.edu.iancl.minhastarefas_dmo.model.Tarefa;
import br.edu.iancl.minhastarefas_dmo.view.RecyclerItemClickListener;

public class ItemTarefaAdapter extends RecyclerView.Adapter<ItemTarefaAdapter.ViewHolder>{

    private Context mContext;
    private List<Tarefa> mTarefas;
    private List<Tarefa> mTarefasConcluidas;
    private static RecyclerItemClickListener mClickListener;

    public ItemTarefaAdapter(Context context, List<Tarefa> data, List<Tarefa> concluidas){
        this.mContext = context;
        this.mTarefas = data;
        this.mTarefasConcluidas = concluidas;
    }

    @Override
    public int getItemCount() {
        return mTarefas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_tarefa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemTarefaAdapter.ViewHolder holder, int position) {
        Tarefa tarefa = mTarefas.get(position);
        holder.tituloTextView.setText(tarefa.getTitulo());
        holder.dataTextView.setText(tarefa.getDataLimite());
        switch (tarefa.getPrioridade()){
            case BAIXA:
                holder.prioridadeImageView.setColorFilter(mContext.getResources().getColor(R.color.green, mContext.getTheme()));
                break;
            case MEDIA:
                holder.prioridadeImageView.setColorFilter(mContext.getResources().getColor(R.color.yellow, mContext.getTheme()));
                break;
            case ALTA:
                holder.prioridadeImageView.setColorFilter(mContext.getResources().getColor(R.color.red, mContext.getTheme()));
                break;
        }

        holder.prioridadeImageView.setOnClickListener(v -> {
            if(v == holder.prioridadeImageView){
                prioridadeClick(position);
            }
        });

        holder.checkImageView.setOnClickListener(v -> {
            if(v == holder.checkImageView){
                checkClick(position);
            }
        });
    }

    public void setClickListener(RecyclerItemClickListener clickListener){
        this.mClickListener = clickListener;
    }

    private void checkClick(int position){
        LocalDateTime agora = LocalDateTime.now();
        Tarefa tarefa = mTarefas.get(position);
        tarefa.setDataLimite(agora.getDayOfMonth() + "/" + agora.getMonthValue() + "/" + agora.getYear());
        mTarefasConcluidas.add(tarefa);
        mTarefas.remove(tarefa);
        notifyDataSetChanged();
    }

    private void prioridadeClick(int position){
        Tarefa tarefa = mTarefas.get(position);
        if(tarefa.getPrioridade() == PrioridadeEnum.BAIXA){
            tarefa.setPrioridade(PrioridadeEnum.MEDIA);
        }
        else if(tarefa.getPrioridade() == PrioridadeEnum.MEDIA){
            tarefa.setPrioridade(PrioridadeEnum.ALTA);
        }
        else if(tarefa.getPrioridade() == PrioridadeEnum.ALTA){
            tarefa.setPrioridade(PrioridadeEnum.BAIXA);
        }

        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tituloTextView;
        public TextView dataTextView;
        public ImageView prioridadeImageView;
        public ImageView checkImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.text_titulo);
            dataTextView = itemView.findViewById(R.id.text_data);
            prioridadeImageView = itemView.findViewById(R.id.img_icon_prioridade);
            checkImageView = itemView.findViewById(R.id.img_icon_check);

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
