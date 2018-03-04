package icm.utils.auxiliardoinstrumentista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lucas on 19/02/18.
 */

public class SugestItemCustom extends BaseAdapter  {
    private ArrayList<String> lista = new ArrayList<>();
    private Context contexto;

    public SugestItemCustom(ArrayList<String> lista, Context contexto) {
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
        return lista.get(pos).hashCode();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sugest_item, null);
        }

        TextView listItemText = (TextView) convertView.findViewById(R.id.list_item_sugest);
        listItemText.setText(lista.get(position));

        return convertView;
    }
}