package icm.utils.auxiliardoinstrumentista;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;
import android.view.KeyEvent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.woxthebox.draglistview.DragItem;
import com.woxthebox.draglistview.DragListView;
import com.woxthebox.draglistview.swipe.ListSwipeHelper;
import com.woxthebox.draglistview.swipe.ListSwipeItem;

public class CriaLista extends AppCompatActivity implements ForceUpdateChecker.OnUpdateNeededListener{
    private ArrayList<Pair<Long, String>> textos_mv = new ArrayList<>();
    private Integer i = 0;
    private DragListView mDragListView;

    private static final String TAG = CriaLista.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_lista);
        setTitle("Crie a Lista de Hinos");

        final FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();

        Map<String, Object> remoteConfigDefaults = new HashMap();
        remoteConfigDefaults.put(ForceUpdateChecker.KEY_UPDATE_REQUIRED, false);
        remoteConfigDefaults.put(ForceUpdateChecker.KEY_CURRENT_VERSION, "1.0.0");
        remoteConfigDefaults.put(ForceUpdateChecker.KEY_UPDATE_URL,
                "https://lucascostaportfolio.wordpress.com/2017/12/21/auxiliardoinstrumentista/");

        firebaseRemoteConfig.setDefaults(remoteConfigDefaults);
        firebaseRemoteConfig.fetch(60) // fetch every minutes
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "remote config is fetched.");
                            firebaseRemoteConfig.activateFetched();
                        }
                    }
                });


        ForceUpdateChecker.with(this).onUpdateNeeded(this).check();



        mDragListView = findViewById(R.id.listaTextos_mv);
        mDragListView.getRecyclerView().setVerticalScrollBarEnabled(true);

        mDragListView.setSwipeListener(new ListSwipeHelper.OnSwipeListenerAdapter() {
            @Override
            public void onItemSwipeEnded(ListSwipeItem item, ListSwipeItem.SwipeDirection swipedDirection) {
                if (swipedDirection == ListSwipeItem.SwipeDirection.RIGHT ||
                        swipedDirection == ListSwipeItem.SwipeDirection.LEFT ) {
                    Pair<Long, String> adapterItem = (Pair<Long, String>) item.getTag();
                    int pos = mDragListView.getAdapter().getPositionForItem(adapterItem);
                    mDragListView.getAdapter().removeItem(pos);
                }
            }
        });

        mDragListView.setLayoutManager(new LinearLayoutManager(this));
        final ItemAdapter listAdapter = new ItemAdapter(textos_mv, R.layout.single_item_mv,
                R.id.drag_image, false);
        mDragListView.setAdapter(listAdapter, true);
        mDragListView.setCanDragHorizontally(false);

        final EditText texto = findViewById(R.id.editText);

        Button add = findViewById(R.id.add);
        add.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {

                String hino = texto.getText().toString();
                if (hino.length() > 0) {
                    texto.setText("");
                    texto.findFocus();

                    textos_mv.add(new Pair<>((long) i, hino));

                    listAdapter.notifyDataSetChanged();
                    i += 1;
                } else {
                    Toast.makeText(CriaLista.this, "Digite o número ou nome do hino", Toast.LENGTH_SHORT).show();
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

                        textos_mv.add(new Pair<>((long) i, hino));

                        listAdapter.notifyDataSetChanged();
                        i += 1;
                    } else {
                        Toast.makeText(CriaLista.this, "Digite o número ou nome do hino", Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cria_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent sobre = new Intent(this, Sobre.class);
                startActivity(sobre);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void iniciarCulto(View view) {
        if(textos_mv.isEmpty()) {
            Toast.makeText(CriaLista.this, "Adicione pelo menos um hino", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent iniciar = new Intent(this, CultoMain.class);
            ArrayList<String> listaDeHinos = new ArrayList<>();
            for (Pair<Long, String> copiador : textos_mv){
                listaDeHinos.add(copiador.second);
            }
            iniciar.putStringArrayListExtra("listaDeHinos", listaDeHinos);
            startActivity(iniciar);
        }
    }

    // checagem de atualização
    @Override
    public void onUpdateNeeded(final String updateUrl) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Nova versão disponível!")
                .setMessage("Uma nova versão, com melhorias e correções, foi disponibilizada.")
                .setPositiveButton("Atualizar agora",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                redirectStore(updateUrl);
                            }
                        }).setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).create();

        dialog.show();
    }

    private void redirectStore(String updateUrl) {
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}

