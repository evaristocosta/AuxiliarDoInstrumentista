package icm.utils.auxiliardoinstrumentista;

import android.provider.BaseColumns;

public final class HinosContract {
    private HinosContract() {}

    public static class HinosRegistro implements BaseColumns {
        public static final String TABLE_NAME = "hinos";
        public static final String ID = "_id";
        public static final String NUM_ANTIGO = "numAntigo";
        public static final String NUM_NOVO = "numNovo";
        public static final String NOME = "nome";
        public static final String NOME_ALT = "nomeAlt";
        public static final String CATEGORIA = "categoria";
        public static final String QTD_CANTADO = "qtdCantado";
    }
}