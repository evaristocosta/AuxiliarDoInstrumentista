package icm.utils.auxiliardoinstrumentista;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

/**
 *  EM CONSTRUÇÃO
 */

public class Estatisticas extends AppCompatActivity {
    Context context;
    HinosBD mHinosHelper;

    SQLiteDatabase db;
    String[] projecao = {
            BaseColumns._ID,
            HinosContract.HinosRegistro.NOME,
            HinosContract.HinosRegistro.QTD_CANTADO
    };
    String selecao = HinosContract.HinosRegistro.QTD_CANTADO + " > ?";
    String[] argumento = {"0"};
    Cursor cursor;

    ArrayList<HinosEstrutura> hinos = new ArrayList<>();
    HinosEstrutura hino = new HinosEstrutura();

    ListView listView;
    EstatisticasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatisticas);

        context = this;
        mHinosHelper = new HinosBD(context);
        db = mHinosHelper.getReadableDatabase();

        cursor = db.query(
                HinosContract.HinosRegistro.TABLE_NAME,
                projecao,
                selecao,
                argumento,
                null,
                null,
                HinosContract.HinosRegistro.NOME
        );

        while (cursor.moveToNext()) {
            int numHino = cursor.getInt(
                    cursor.getColumnIndexOrThrow(HinosContract.HinosRegistro.QTD_CANTADO));
            String nomeHino = cursor.getString(
                    cursor.getColumnIndexOrThrow(HinosContract.HinosRegistro.NOME));

            hino.setNome(nomeHino);
            hino.setQtdCantado(numHino);
            hinos.add(hino);

            hino = new HinosEstrutura();
        }
        cursor.close();

        listView = findViewById(R.id.listaBD);
        adapter = new EstatisticasAdapter(hinos, context);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        mHinosHelper.close();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_estatisticas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_limpa_dados:
                context = this;
                mHinosHelper = new HinosBD(context);
                db = mHinosHelper.getWritableDatabase();

                db.execSQL("UPDATE " + HinosContract.HinosRegistro.TABLE_NAME + " SET " +
                        HinosContract.HinosRegistro.QTD_CANTADO + " = 0 WHERE " +
                        HinosContract.HinosRegistro.QTD_CANTADO + " >= ?", new String[] {"1"});

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
