package icm.utils.auxiliardoinstrumentista;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CultoMainCheck extends Fragment{
    private static final String TAB = "CultoMainCheck";
    NotificationCompat.Builder notif;
    NotificationManager notifManager;
    ListView lv;

    //recupera lista de hinos adicionados na activity anterior e passa pra string
    public class hinosGet {
        Intent reIntent = getActivity().getIntent();
        ArrayList<String> hinos;
        String[] hinosArray;
        public hinosGet(){
            hinos = reIntent.getStringArrayListExtra("listaDeHinos");
            hinosArray = hinos.toArray(new String[0]);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_culto_main,container,false);

        String[] notifyArray = new hinosGet().hinosArray;
        int limit = notifyArray.length;
        notifManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        lv = view.findViewById(R.id.list);

        //cria notificações
        while(limit > 0){
            String hinoNum = Integer.toString(limit)+"º hino da lista";
            notif = new NotificationCompat.Builder(getContext())
                    .setSmallIcon(R.drawable.shopping_notif)
                    .setContentTitle(notifyArray[limit-1])
                    .setContentText(hinoNum);

            notifManager.notify(limit, notif.build());
            limit--;
        }

        lv.setAdapter(new CheckAdaptor());
        return view;
    }

    //adaptador personalizado
    private class CheckAdaptor extends BaseAdapter {
        String[] hinosArray = new hinosGet().hinosArray;

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

