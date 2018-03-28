package icm.utils.auxiliardoinstrumentista;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lucas on 19/02/18.
 */

public class ItemCustom extends BaseAdapter implements ListAdapter {
    private ArrayList<String> lista = new ArrayList<>();
    private Context contexto;

    public ItemCustom(ArrayList<String> lista, Context contexto) {
        this.lista = lista;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int pos) {
        return lista.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.single_item, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
        listItemText.setText(lista.get(position));
/*
        //Handle buttons and add onClickListeners
        ImageButton deleteBtn = view.findViewById(R.id.delete_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                lista.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });
*/
        return view;
    }
}