package br.edu.iancl.bancoimobiliariodmo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.iancl.bancoimobiliariodmo.R;
import br.edu.iancl.bancoimobiliariodmo.model.Jogador;
import br.edu.iancl.bancoimobiliariodmo.model.ListaJogadores;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final double PRO_LABORE = 2000.00;
    private EditText mDinheiroEdit;
    private Button mMButton, mInterrogacaoButton, mSetaButton, mKButton;
    private Button m7Button, m8Button, m9Button;
    private Button m4Button, m5Button, m6Button;
    private Button m1Button, m2Button, m3Button;
    private Button mCButton, m0Button, mPontoButton;
    private List<Jogador> jogadores;
    //private ListaJogadores jogadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        jogadores = new ArrayList<>();
        jogadores.add(new Jogador(1));
        jogadores.add(new Jogador(2));
        //jogadores = new ListaJogadores();

        mDinheiroEdit = findViewById(R.id.edit_dinheiro);
        mDinheiroEdit.setShowSoftInputOnFocus(false);
        mDinheiroEdit.setOnClickListener(this);

        //Buttons com funcionalidades
        mMButton = findViewById(R.id.button_m);
        mInterrogacaoButton = findViewById(R.id.button_interrogacao);
        mSetaButton = findViewById(R.id.button_seta);
        mKButton = findViewById(R.id.button_k);
        mCButton = findViewById(R.id.button_c);
        mMButton.setOnClickListener(this);
        mInterrogacaoButton.setOnClickListener(this);
        mSetaButton.setOnClickListener(this);
        mKButton.setOnClickListener(this);
        mCButton.setOnClickListener(this);

        //Buttons de numeros e ponto
        m0Button = findViewById(R.id.button_0);
        m1Button = findViewById(R.id.button_1);
        m2Button = findViewById(R.id.button_2);
        m3Button = findViewById(R.id.button_3);
        m4Button = findViewById(R.id.button_4);
        m5Button = findViewById(R.id.button_5);
        m6Button = findViewById(R.id.button_6);
        m7Button = findViewById(R.id.button_7);
        m8Button = findViewById(R.id.button_8);
        m9Button = findViewById(R.id.button_9);
        mPontoButton = findViewById(R.id.button_ponto);
        m0Button.setOnClickListener(this);
        m1Button.setOnClickListener(this);
        m2Button.setOnClickListener(this);
        m3Button.setOnClickListener(this);
        m4Button.setOnClickListener(this);
        m5Button.setOnClickListener(this);
        m6Button.setOnClickListener(this);
        m7Button.setOnClickListener(this);
        m8Button.setOnClickListener(this);
        m9Button.setOnClickListener(this);
        mPontoButton.setOnClickListener(this);
    }

    private void atualizarTexto(String stringAdicionar){
        limparPadrao();
        String stringAtual = mDinheiroEdit.getText().toString();
        int posicaoCursor = mDinheiroEdit.getSelectionStart();
        String stringEsquerda = stringAtual.substring(0, posicaoCursor);
        String stringDireita = stringAtual.substring(posicaoCursor);
        mDinheiroEdit.setText(String.format("%s%s%s", stringEsquerda, stringAdicionar, stringDireita));
        mDinheiroEdit.setSelection(posicaoCursor+1);
    }

    private void limpar(){
        mDinheiroEdit.setText("");
    }

    private void limparPadrao(){
        if(getString(R.string.dinheiro).equals(mDinheiroEdit.getText().toString())){
            mDinheiroEdit.setText("");
        }
    }

    private void funcaoM() {
        limparPadrao();
        if(!(mDinheiroEdit.getText().toString().equals(""))){
            double valorEmDouble = Double.parseDouble(mDinheiroEdit.getText().toString());
            valorEmDouble = valorEmDouble*1000;
            fazTransacao(valorEmDouble, jogadores.get(0), jogadores.get(1));
        }
    }

    private void fazTransacao(double valor, Jogador jogadorEnvia, Jogador jogadorRecebe) {
        if(valor > 0 && jogadorEnvia.getDinheiro() >= valor){
            jogadorEnvia.enviar(valor);
            jogadorRecebe.receber(valor);
            String transacao = String.format("O jogador %d enviou %.2f ao jogador %d",jogadorEnvia.getId(), valor, jogadorRecebe.getId());
            Toast.makeText(getApplicationContext(), transacao, Toast.LENGTH_LONG).show();
            mDinheiroEdit.setText(String.valueOf(jogadorEnvia.getDinheiro()));
        }
        else{
            Toast.makeText(getApplicationContext(), "Dinheiro insuficiente", Toast.LENGTH_LONG).show();
            mDinheiroEdit.setText("");
        }
    }


    private void funcaoK() {
        limparPadrao();
        if(!(mDinheiroEdit.getText().toString().equals(""))){
            double valorEmDouble = Double.parseDouble(mDinheiroEdit.getText().toString());
            fazTransacao(valorEmDouble, jogadores.get(0), jogadores.get(1));
        }
    }

    private void funcaoSeta(){
        limparPadrao();
        Jogador jogador = jogadores.get(0);
        jogador.setDinheiro(jogador.getDinheiro() + PRO_LABORE);
        String valorPago = String.format(
                "O jogador %d recebeu seu pró-labore de %.2f, O jogador tem %.2f reais",
                jogador.getId(), PRO_LABORE, jogador.getDinheiro()
        );
        Toast.makeText(getApplicationContext(), valorPago, Toast.LENGTH_LONG).show();
        mDinheiroEdit.setText("");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_0:
                atualizarTexto("0");
                break;
            case R.id.button_1:
                atualizarTexto("1");
                break;
            case R.id.button_2:
                atualizarTexto("2");
                break;
            case R.id.button_3:
                atualizarTexto("3");
                break;
            case R.id.button_4:
                atualizarTexto("4");
                break;
            case R.id.button_5:
                atualizarTexto("5");
                break;
            case R.id.button_6:
                atualizarTexto("6");
                break;
            case R.id.button_7:
                atualizarTexto("7");
                break;
            case R.id.button_8:
                atualizarTexto("8");
                break;
            case R.id.button_9:
                atualizarTexto("9");
                break;
            case R.id.button_ponto:
                atualizarTexto(".");
                break;
            case R.id.button_c:
                limpar();
                break;
            case R.id.button_m:
                funcaoM();
                break;
            case R.id.button_seta:
                funcaoSeta();
                break;
            case R.id.button_interrogacao:
                Toast.makeText(getApplicationContext(),"Notícia", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_k:
               funcaoK();
                break;
            case R.id.edit_dinheiro:
                limparPadrao();
                break;
        }
    }


}

/*
Implementação bacana, porém existem algumas considerações:

1. Não é possível definir quais os cartões que estão enviando ou recebendo os valores.

2. Não é possível saber o saldo de um cartão.

3. Todas as operações são realizadas pelo cartão 1.

Nota: 5,0
 */