package br.edu.iancl.meutreino.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.iancl.meutreino.R;
import br.edu.iancl.meutreino.model.Treino;
import br.edu.iancl.meutreino.view.RecyclerItemClickListener;

public class ItemTreinosAdapter extends RecyclerView.Adapter<ItemTreinosAdapter.ViewHolder> {

    private List<Treino> dataSource;
    private static RecyclerItemClickListener mClickListener;

    public ItemTreinosAdapter(List<Treino> dataSource, RecyclerItemClickListener listener) {
        this.dataSource = dataSource;
        mClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_treino, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemTreinosAdapter.ViewHolder holder, int position) {
        holder.mNomeTextView.setText(dataSource.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public void setDataSource(List<Treino> dataSource) {
        this.dataSource = dataSource;
        this.notifyDataSetChanged();
    }

    public List<Treino> getDataSource() {
        return dataSource;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mNomeTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNomeTextView = itemView.findViewById(R.id.text_item_nome);
            itemView.setOnClickListener(v -> mClickListener.onClick(getAdapterPosition()));
        }
    }
}
