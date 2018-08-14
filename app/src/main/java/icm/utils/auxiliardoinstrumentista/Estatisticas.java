package icm.utils.auxiliardoinstrumentista;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 *  EM CONSTRUÇÃO
 */

public class Estatisticas extends AppCompatActivity {
    Context context;
    HinosBD mHinosHelper;

    SQLiteDatabase db;
    String[] projecao = {
            BaseColumns._ID,
            HinosContract.HinosRegistro.NOME
    };

    Cursor cursor;
    ArrayList<String> nomes = new ArrayList<>();

    ListView listView;
    ItemCustom adapter;

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
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            String nomeHino = cursor.getString(
                    cursor.getColumnIndexOrThrow(HinosContract.HinosRegistro.NOME));
            nomes.add(nomeHino);
        }
        cursor.close();

        listView = findViewById(R.id.listaBD);
        adapter = new ItemCustom(nomes, context);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        mHinosHelper.close();
        super.onDestroy();
    }
}
