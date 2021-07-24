package br.edu.iancl.meutreino.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.iancl.meutreino.R;
import br.edu.iancl.meutreino.constants.Constants;
import br.edu.iancl.meutreino.dao.TreinoContract;

public class TreinoActivity extends AppCompatActivity {

    private ImageView mImageView;
    private TextView mNomeView;
    private TextView mInstrucaoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treino);

        if(getActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mNomeView = findViewById(R.id.text_nome_treino);
        mImageView = findViewById(R.id.img_treino);
        mInstrucaoView = findViewById(R.id.text_instrucoes);

        Bundle bundle = getIntent().getExtras();
        mNomeView.setText(bundle.getString(TreinoContract.TreinoEntry.COLUMN_NOME));
        mInstrucaoView.setText(bundle.getString(TreinoContract.TreinoEntry.COLUMN_INSTRUCOES));
        buscarImagem(bundle.getString(TreinoContract.TreinoEntry.COLUMN_IMG));

    }

    private void buscarImagem(String img) {
        int id = mImageView.getContext().getResources().getIdentifier(img, "drawable", mImageView.getContext().getPackageName());
        if(id == 0){
            mImageView.setImageResource(R.drawable.ic_error);
        }
        else{
            mImageView.setImageResource(id);
        }


    }
}