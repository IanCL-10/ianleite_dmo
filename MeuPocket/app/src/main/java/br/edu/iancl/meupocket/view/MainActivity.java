package br.edu.iancl.meupocket.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.edu.iancl.meupocket.R;
import br.edu.iancl.meupocket.adapter.ItemSiteAdapter;
import br.edu.iancl.meupocket.controller.SiteController;
import br.edu.iancl.meupocket.dao.SiteDAO;
import br.edu.iancl.meupocket.model.Constantes;
import br.edu.iancl.meupocket.model.Site;

public class MainActivity extends AppCompatActivity {

    private List<Site> mSites;
    //private ListView mListView;
    //private ArrayAdapter<Site> mSiteArrayAdapter;

    private RecyclerView mSitesRecyclerView;
    private ItemSiteAdapter mItemSiteAdapter;
    private FloatingActionButton mActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSites = SiteController.allSites(this);
        //mSiteArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mSites);
        mItemSiteAdapter = new ItemSiteAdapter(this, mSites);

        mItemSiteAdapter.setClickListener(position -> updateSite(position));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mSitesRecyclerView = findViewById(R.id.recycler_sites);
        mSitesRecyclerView.setLayoutManager(layoutManager);
        mSitesRecyclerView.setAdapter(mItemSiteAdapter);

        //mListView = findViewById(R.id.list_sites);
        //mListView.setAdapter(mSiteArrayAdapter);
        //mListView.setAdapter(mItemSiteAdapter);
        //mListView.setOnItemClickListener((adapterView, view, i, l) -> updateSite(i));

        mActionButton = findViewById(R.id.fab_add_site);
        mActionButton.setOnClickListener(v -> newSite());
    }

    private void newSite() {
        Intent intent = new Intent(this, SiteActivity.class);
        startActivityForResult(intent, Constantes.REQUEST_CODE_NEW_SITE);
    }

    private void updateSite(int position){
        Bundle bundle = new Bundle();
        bundle.putString(Constantes.KEY_TITLE, mSites.get(position).getTitle());
        bundle.putString(Constantes.KEY_URL, mSites.get(position).getUrl());

        Intent intent = new Intent(this, SiteActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, Constantes.REQUEST_CODE_UPDATE_SITE);
    }

    private void updateAdapter(){
        //mSiteArrayAdapter.notifyDataSetChanged();
        mItemSiteAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constantes.REQUEST_CODE_NEW_SITE:
                    SiteController.addSite(
                            this,
                            data.getStringExtra(Constantes.KEY_TITLE),
                            data.getStringExtra(Constantes.KEY_URL)
                    );
                    break;

                case Constantes.REQUEST_CODE_UPDATE_SITE:
                    String o = data.getStringExtra(Constantes.KEY_OLD_TITLE);
                    String t = data.getStringExtra(Constantes.KEY_TITLE);
                    String u = data.getStringExtra(Constantes.KEY_URL);
                    SiteController.updateSite(this, o, t, u);
                    break;
            }
            updateAdapter();
        }
    }


}