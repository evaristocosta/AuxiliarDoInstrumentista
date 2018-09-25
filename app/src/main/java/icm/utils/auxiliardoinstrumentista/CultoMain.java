package icm.utils.auxiliardoinstrumentista;


import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import java.util.ArrayList;

public class CultoMain extends AppCompatActivity {

    private static final String TAG = "CultoMain";

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    private AlertDialog voltar;

    HinosContract.HinosRegistro hinosRegistro;
    Context context;
    HinosBD mHinosHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culto_main);
        Log.d(TAG, "onCreate: Starting.");
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        context = this;
        mHinosHelper = new HinosBD(context);
        db = mHinosHelper.getWritableDatabase();

        final Button finish = findViewById(R.id.fini);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (String individual : ListaDeHinos.lista) {
                    db.execSQL("UPDATE " + hinosRegistro.TABLE_NAME + " SET " + hinosRegistro.QTD_CANTADO + " " +
                            "= " + hinosRegistro.QTD_CANTADO + "+1 WHERE " +
                            hinosRegistro.NOME + " = ?", new String[] {individual.substring(individual.indexOf('-')+2)});
                }

                finish();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new CultoMainCheck(), "Lista");
        adapter.addFragment(new CultoMainSugest(), "Sugestões");
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder construtor = new AlertDialog.Builder(this);
        construtor.setTitle("Concluir culto");
        construtor.setMessage("Deseja concluir o culto?");
        construtor.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        construtor.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        voltar = construtor.create();
        voltar.show();
    }
}
