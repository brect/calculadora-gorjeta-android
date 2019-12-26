package br.com.blimas.calculadoragorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText valorConta;
    private TextView textoPorcentagem;
    private TextView textoGorjeta;
    private TextView valorTotal;
    private SeekBar seekGorjeta;

    //porcentagem inicial da gorjeta
    private double porcentagem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valorConta = findViewById(R.id.valor_digitado);
        textoPorcentagem = findViewById(R.id.texto_porcentagem);
        seekGorjeta  = findViewById(R.id.seekBar_porcentagem);
        textoGorjeta = findViewById(R.id.valor_gorjeta);
        valorTotal = findViewById(R.id.valor_total);



        //controla seekbar
        seekGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentagem = seekBar.getProgress();
                textoPorcentagem.setText(Math.round(porcentagem) + "%");
                calcular();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                System.out.println("Start Seekbar");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                System.out.println("Stop Seekbar");
            }
        });
    }

    public void calcular(){
        //Recupera valor digitado
        Double valorDigitado = Double.parseDouble(valorConta.getText().toString());

        //calcula gorjeta
        double gorjeta = valorDigitado * (porcentagem/100);
        double total = gorjeta + valorDigitado;

        //exibe a gorjeta
        textoGorjeta.setText("R$ " + Math.round(gorjeta));
        valorTotal.setText("R$ " + total);
    }
}
