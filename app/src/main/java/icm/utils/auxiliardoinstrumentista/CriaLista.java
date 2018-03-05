package icm.utils.auxiliardoinstrumentista;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.view.KeyEvent;

public class CriaLista extends AppCompatActivity{
    private ArrayList<String> textos = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_lista);
        setTitle("Crie a Lista de Hinos");

        final ItemCustom adapter = new ItemCustom(textos, this);

        ListView listaTextos = findViewById(R.id.listaTextos);
        listaTextos.setAdapter(adapter);

        final EditText texto = findViewById(R.id.editText);


        Button add = findViewById(R.id.add);
        add.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {

                String hino = texto.getText().toString();
                if (hino.length() > 0) {
                    texto.setText("");
                    texto.findFocus();
                    textos.add(hino);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(CriaLista.this, "Digite o número do hino", Toast.LENGTH_SHORT).show();
                }
            }
        });

        texto.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String hino = texto.getText().toString();
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode ==
                        KeyEvent.KEYCODE_ENTER)) {
                    if (hino.length() > 0) {
                        texto.setText("");
                        texto.findFocus();
                        textos.add(hino);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(CriaLista.this, "Digite o número do hino", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        this.finish();
    }


    public void iniciarCulto(View view) {
        if(textos.isEmpty()) {
            Toast.makeText(CriaLista.this, "Adicione pelo menos um hino", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent iniciar = new Intent(this, CultoMain.class);
            iniciar.putStringArrayListExtra("listaDeHinos", textos);
            startActivity(iniciar);
        }
    }
}

