package icm.utils.auxiliardoinstrumentista;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.text.TextUtils;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class HinosBD extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "HinosGeral.db";
    private static final int DATABASE_VERSION = 1;

    public HinosBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<HinosEstrutura> pesquisa(String keyword) {
        long start = System.currentTimeMillis();
        List<HinosEstrutura> hinos = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            String[] projecao = {
                    BaseColumns._ID,
                    HinosContract.HinosRegistro.NUM_NOVO,
                    HinosContract.HinosRegistro.NUM_ANTIGO,
                    HinosContract.HinosRegistro.NOME
            };

            String selecao;
            if (TextUtils.isDigitsOnly(keyword)) {
                selecao = HinosContract.HinosRegistro.NUM_ANTIGO + " LIKE ? OR " + HinosContract.HinosRegistro.NUM_NOVO + " LIKE ?";
            } else {
                selecao = HinosContract.HinosRegistro.NOME + " LIKE ?";
            }
            String[] selecaoArg = {"%"+keyword+"%"};

            Cursor cursor = db.query(
                    HinosContract.HinosRegistro.TABLE_NAME,
                    projecao,
                    selecao,
                    selecaoArg,
                    null,
                    null,
                    null
            );

            hinos = new ArrayList<>();
            while (cursor.moveToNext()) {
                HinosEstrutura hino = new HinosEstrutura();
                hino.setNumAntigo(cursor.getInt(cursor.getColumnIndexOrThrow(HinosContract.HinosRegistro.NUM_ANTIGO)));
                hino.setNumNovo(cursor.getInt(cursor.getColumnIndexOrThrow(HinosContract.HinosRegistro.NUM_NOVO)));
                hino.setNome(cursor.getString(cursor.getColumnIndexOrThrow(HinosContract.HinosRegistro.NOME)));

                hinos.add(hino);
            }
            cursor.close();

        }catch (Exception e) {
            hinos = null;
        }
        long end = System.currentTimeMillis();
        System.out.println("query: "+String.valueOf(end-start));
        return hinos;
    }
}