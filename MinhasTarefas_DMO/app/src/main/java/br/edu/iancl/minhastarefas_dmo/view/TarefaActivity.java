package br.edu.iancl.minhastarefas_dmo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.Calendar;

import br.edu.iancl.minhastarefas_dmo.R;
import br.edu.iancl.minhastarefas_dmo.model.Constantes;

public class TarefaActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText tituloEditText;
    private EditText descricaoEditText;

    private DatePickerDialog picker;
    private EditText dataEditText;
    private LocalDateTime dataTarefa;

    private String mOldtitle;

    private Button mSalvarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);

        if(getActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tituloEditText = findViewById(R.id.edit_titulo);
        descricaoEditText = findViewById(R.id.edit_descricao);
        dataEditText = findViewById(R.id.edit_data);
        dataEditText.setOnClickListener(this);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            mOldtitle = bundle.getString(Constantes.KEY_TITULO);
            String descricao = bundle.getString(Constantes.KEY_DESCRICAO);
            String data = bundle.getString(Constantes.KEY_DATA);
            tituloEditText.setText(mOldtitle);
            descricaoEditText.setText(descricao);
            dataEditText.setText(data);
        }else{
            dataTarefa = LocalDateTime.now();
            dataEditText.setText(dataTarefa.getDayOfMonth() + "/" + dataTarefa.getMonthValue() + "/" + dataTarefa.getYear());
            mOldtitle = "";
        }

        mSalvarButton = findViewById(R.id.button_save);
        mSalvarButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view == dataEditText){
            pegarData();
        }
        if(view == mSalvarButton){
            Intent intent = new Intent();
            intent.putExtra(Constantes.KEY_OLD_TITLE, mOldtitle);
            intent.putExtra(Constantes.KEY_TITULO, tituloEditText.getText().toString());
            intent.putExtra(Constantes.KEY_DESCRICAO, descricaoEditText.getText().toString());
            intent.putExtra(Constantes.KEY_DATA, dataEditText.getText().toString());
            intent.putExtra(Constantes.KEY_PRIORIDADE, "BAIXA");
            setResult(Activity.RESULT_OK, intent);
            Toast.makeText(this, getString(R.string.success_message), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void pegarData(){
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        picker = new DatePickerDialog(TarefaActivity.this,
                (view, year1, monthOfYear, dayOfMonth) ->
                        dataEditText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1), year, month, day);
        picker.show();
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
