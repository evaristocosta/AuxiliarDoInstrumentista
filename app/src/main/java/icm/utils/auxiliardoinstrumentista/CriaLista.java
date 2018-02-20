package icm.utils.auxiliardoinstrumentista;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CriaLista extends Activity {
    private ArrayList<String> textos = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_lista);


        final ItemCustom adapter = new ItemCustom(textos, this);
        //final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, textos);

        ListView listaTextos = findViewById(R.id.listaTextos);
        listaTextos.setAdapter(adapter);


        Button add = findViewById(R.id.add);
        add.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {

                // recuperando o texto digitado pelo usuario
                EditText texto = (EditText) findViewById(R.id.editText);
                String hino = texto.getText().toString();

                // caso o texto for preenchido, adiciona na lista e atualiza o adapter
                // caso contrario exibe uma mensagem solicitando ao usuário que digite uma série
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
    }

    public void iniciarCulto(View view) {
        Intent iniciar = new Intent(this, Culto.class);
        iniciar.putStringArrayListExtra("listaDeHinos", textos);
        startActivity(iniciar);
    }
}

