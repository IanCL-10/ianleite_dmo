package br.edu.iancl.github_dmos5.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.iancl.github_dmos5.R;
import br.edu.iancl.github_dmos5.adapter.ItemRepositorioAdapter;
import br.edu.iancl.github_dmos5.api.RetrofitService;
import br.edu.iancl.github_dmos5.constants.Constants;
import br.edu.iancl.github_dmos5.model.Repositorio;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositorioActivity extends AppCompatActivity {

    private List<Repositorio> mRepositorios;
    private RecyclerView mRepositoriosRecyclerView;
    private ItemRepositorioAdapter mItemRepositorioAdapter;
    private String mUsername;

    private static final int REQUEST_PERMISSION = 64;
    private static final String BASE_URL = "https://api.github.com/";

    private Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositorio);

        Bundle bundle = getIntent().getExtras();
        mUsername = bundle.getString(Constants.KEY_USERNAME);
        mRepositorios = new ArrayList<>();
        preencheLista();

        if(getActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mItemRepositorioAdapter = new ItemRepositorioAdapter(this, mRepositorios);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRepositoriosRecyclerView = findViewById(R.id.recycler_repositorios);
        mRepositoriosRecyclerView.setLayoutManager(layoutManager);
        mRepositoriosRecyclerView.setAdapter(mItemRepositorioAdapter);
    }

    private void preencheLista() {
        if(temPermissao()){
            buscarNomesRepositorio(mUsername);
        }else {
            solicitaPermissao();
            if(temPermissao()){
                buscarNomesRepositorio(mUsername);
            }else {
                Toast.makeText(this, "Sem permissão de Internet", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void buscarNomesRepositorio(String username) {
        mRetrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        if(username.isEmpty()){
            Toast.makeText(this, "Sem nome de usuario", Toast.LENGTH_SHORT).show();
        }
        else{
            RetrofitService mRetrofitService = mRetrofit.create(RetrofitService.class);
            Call<List<Repositorio>> call = mRetrofitService.getDados(username);

            call.enqueue(new Callback<List<Repositorio>>() {
                @Override
                public void onResponse(Call<List<Repositorio>> call, Response<List<Repositorio>> response) {
                    List<Repositorio> reps = response.body();
                    updateRecyclerView(reps);
                }

                @Override
                public void onFailure(Call<List<Repositorio>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Falha ao recuperar dados", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void updateRecyclerView(List<Repositorio> reps) {
        if(reps != null){
            mRepositorios = reps;
            mItemRepositorioAdapter.setRepositorios(mRepositorios);
        }
    }

    private boolean temPermissao(){
        return ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
    }

    private void solicitaPermissao(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {
            final Activity activity = this;
            new AlertDialog.Builder(this)
                    .setMessage(R.string.explicacao_permissao)
                    .setPositiveButton(R.string.botao_fornecer, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.INTERNET}, REQUEST_PERMISSION);
                        }
                    })
                    .setNegativeButton(R.string.botao_nao_fornecer, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            Manifest.permission.INTERNET
                    },
                    REQUEST_PERMISSION);
        }
    }



}