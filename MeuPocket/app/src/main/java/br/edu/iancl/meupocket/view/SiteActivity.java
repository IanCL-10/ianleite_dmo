package br.edu.iancl.meupocket.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.iancl.meupocket.R;
import br.edu.iancl.meupocket.model.Constantes;

public class SiteActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mTitleEditText;
    private EditText mUrlEditText;
    private Button mButton;

    private String mOldtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);

        if(getActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mTitleEditText = findViewById(R.id.edit_title);
        mUrlEditText = findViewById(R.id.edit_url);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            mOldtitle = bundle.getString(Constantes.KEY_TITLE);
            String u = bundle.getString(Constantes.KEY_URL);
            mTitleEditText.setText(mOldtitle);
            mUrlEditText.setText(u);
        }else{
            mOldtitle = "";
        }

        mButton = findViewById(R.id.button_save);
        mButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == mButton){
            Intent intent = new Intent();
            intent.putExtra(Constantes.KEY_TITLE, mTitleEditText.getText().toString());
            intent.putExtra(Constantes.KEY_URL, mUrlEditText.getText().toString());
            intent.putExtra(Constantes.KEY_OLD_TITLE, mOldtitle);
            setResult(Activity.RESULT_OK, intent);
            Toast.makeText(this, getString(R.string.success_message), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            setResult(RESULT_CANCELED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}