package icm.utils.auxiliardoinstrumentista;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import java.util.ArrayList;


public class Culto extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culto);
        setListAdapter(new CheckAdaptor());

        final Button finish = findViewById(R.id.fini);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class CheckAdaptor extends BaseAdapter {
        Intent reIntent = getIntent();
        ArrayList<String> hinos= reIntent.getStringArrayListExtra("listaDeHinos");
        String[] hinosArray = hinos.toArray(new String[0]);

        @Override
        public int getCount() {
            return hinosArray.length;
        }
        @Override
        public String getItem(int position) {
            return hinosArray[position];
        }

        @Override
        public long getItemId(int position) {
            return hinosArray[position].hashCode();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
            }

            ((TextView) convertView.findViewById(android.R.id.text1))
                    .setText(getItem(position));
            return convertView;
        }

    }

    @Override
    public void onBackPressed()
    {
        Toast botaoRetorno = Toast.makeText(this, "Opção inválida", Toast.LENGTH_SHORT);
        botaoRetorno.show();
    }
}
