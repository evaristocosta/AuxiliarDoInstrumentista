package icm.utils.auxiliardoinstrumentista;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lucas on 19/02/18.
 */

public class ItemCustom_mv extends BaseAdapter implements ListAdapter {
    private ArrayList<Pair<Long, String>> lista = new ArrayList<>();
    private Context contexto;

    public ItemCustom_mv(ArrayList<Pair<Long, String>> lista, Context contexto) {
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
        return lista.get(pos).first;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.single_item, null);
        }

        //Handle TextView and display string from your list
        final TextView listItemText = (TextView) view.findViewById(R.id.list_item_string);
        listItemText.setText(lista.get(position).second);
/*
        //Handle buttons and add onClickListeners
        ImageButton deleteBtn = view.findViewById(R.id.delete_btn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });
*/
        return view;
    }
}