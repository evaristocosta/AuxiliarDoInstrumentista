package icm.utils.auxiliardoinstrumentista;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class CultoMainCheck extends Fragment{
    private static final String TAB = "CultoMainCheck";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_culto_main,container,false);

        ListView lv = (ListView) view.findViewById(R.id.list);
        lv.setAdapter(new CheckAdaptor());

        return view;
    }

    private class CheckAdaptor extends BaseAdapter {
        Intent reIntent = getActivity().getIntent();
        ArrayList<String> hinos = reIntent.getStringArrayListExtra("listaDeHinos");
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
}

