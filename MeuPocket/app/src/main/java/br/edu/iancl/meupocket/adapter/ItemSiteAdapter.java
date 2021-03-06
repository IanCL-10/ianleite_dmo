package br.edu.iancl.meupocket.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.edu.iancl.meupocket.R;
import br.edu.iancl.meupocket.model.Site;
import br.edu.iancl.meupocket.view.RecyclerItemClickListener;

public class ItemSiteAdapter extends RecyclerView.Adapter<ItemSiteAdapter.ViewHolder> {

    private Context mContext;
    private List<Site> mSites;
    //private LayoutInflater inflater;

    private static RecyclerItemClickListener mClickListener;

    public ItemSiteAdapter(Context context, List<Site> data){
        //super(context, R.layout.item_list_pocket, data);
        //this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mContext = context;
        this.mSites = data;
    }

    @Override
    public int getItemCount() {
        return mSites.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_pocket, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemSiteAdapter.ViewHolder holder, int position) {
        Site site = mSites.get(position);
        holder.tituloTextView.setText(site.getTitle());
        holder.enderecoTextView.setText(site.getUrl());
        if(site.isFavorite()){
            holder.favoritoImageView.setColorFilter(mContext.getResources().getColor(R.color.red, mContext.getTheme()));
        }else{
            holder.favoritoImageView.setColorFilter(mContext.getResources().getColor(R.color.gray, mContext.getTheme()));
        }

        holder.favoritoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == holder.favoritoImageView){
                    heartClick(position);
                }
            }
        });

        holder.openImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(v == holder.openImageView){
                    openClick(position);
                }
            }
        });
    }

    private void openClick(int position){
        Site site = mSites.get(position);

        String url = site.getUrl().trim().replace(" ", "");
        if(!url.startsWith("http://")){
            url = "http://" + url;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        mContext.startActivity(intent);
    }

    public void setClickListener(RecyclerItemClickListener clickListener){
        this.mClickListener = clickListener;
    }

    /*@NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_list_pocket, null);
            holder = new ViewHolder();
            holder.tituloTextView = convertView.findViewById(R.id.text_titulo);
            holder.enderecoTextView = convertView.findViewById(R.id.text_endereco);
            holder.favoritoImageView = convertView.findViewById(R.id.img_icon_favorite);
            holder.favoritoImageView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(v == holder.favoritoImageView){
                        heartClick(position);
                    }
                }
            });
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Site site = (Site) getItem(position);
        holder.tituloTextView.setText(site.getTitle());
        holder.enderecoTextView.setText(site.getUrl());
        if(site.isFavorite()){
            holder.favoritoImageView.setColorFilter(convertView.getResources().getColor(R.color.red, convertView.getContext().getTheme()));
        }else{

            holder.favoritoImageView.setColorFilter(convertView.getResources().getColor(R.color.gray, convertView.getContext().getTheme()));
        }
        return convertView;
    }*/

    private void heartClick(int position) {
        Site site = mSites.get(position);
        site.setFavorite(!site.isFavorite());
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tituloTextView;
        public TextView enderecoTextView;
        public ImageView favoritoImageView;
        public ImageView openImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.text_titulo);
            enderecoTextView = itemView.findViewById(R.id.text_endereco);
            favoritoImageView = itemView.findViewById(R.id.img_icon_favorite);
            openImageView = itemView.findViewById(R.id.img_icon_open);

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
