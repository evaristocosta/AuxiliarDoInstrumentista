package icm.utils.auxiliardoinstrumentista;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.app.Activity;

import java.util.ArrayList;


public class Culto extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culto);

        Intent reIntent = getIntent();
        ArrayList<String> hinos= reIntent.getStringArrayListExtra("listaDeHinos");
        String[] hinosArray = hinos.toArray(new String[0]);

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice,hinosArray));

        ListView lv = getListView();
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        final Button finish = findViewById(R.id.fini);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Toast botaoRetorno = Toast.makeText(this, "Opção inválida", Toast.LENGTH_SHORT);
        botaoRetorno.show();
    }
}
