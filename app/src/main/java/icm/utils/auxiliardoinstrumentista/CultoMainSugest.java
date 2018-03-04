package icm.utils.auxiliardoinstrumentista;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by lucas on 23/02/18.
 */

public class CultoMainSugest extends Fragment {
    private static final String TAB = "CultoMainSugest";
    private ArrayList<String> textos = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_culto_main,container,false);

        Toast.makeText(getActivity(), "Pressione e segure para remover sugestão", Toast.LENGTH_LONG).show();

        final SugestItemCustom adapter = new SugestItemCustom(textos, getActivity());

        ListView listaTextos = view.findViewById(R.id.listaTextosSugest);
        listaTextos.setAdapter(adapter);

        final EditText texto = view.findViewById(R.id.editSugest);

        Button add = view.findViewById(R.id.addSugest);
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String hino = texto.getText().toString();
                if (hino.length() > 0) {
                    texto.setText("");
                    texto.findFocus();
                    textos.add(hino);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "Digite o número do hino", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getActivity(), "Digite o número do hino", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });

        listaTextos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                textos.remove(position);
                adapter.notifyDataSetChanged();;
                return true;
            }
        });

        return view;
    }
}
