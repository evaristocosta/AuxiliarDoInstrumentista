package icm.utils.auxiliardoinstrumentista;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class HinosBuscaAdaptador extends ArrayAdapter<HinosEstrutura>{
    private Context context;
    private List<HinosEstrutura> hinos;
    private int LIMITE = 5;

    public HinosBuscaAdaptador(Context context, List<HinosEstrutura> hinos) {
        super(context, R.layout.hinos_sugestao_layout, hinos);
        this.context = context;
        this.hinos = hinos;
    }

    @Override
    public int getCount() {
        return Math.min(LIMITE, hinos.size());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.hinos_sugestao_layout, null);
        HinosEstrutura hino = hinos.get(position);

        TextView textViewNome = view.findViewById(R.id.textViewNome);
        textViewNome.setText(hino.getNome());

        TextView textViewNumAntigo = view.findViewById(R.id.textViewNumAntigo);
        if (hino.getNumAntigo() == 0) {
            textViewNumAntigo.setText(" - ");
        } else {
            textViewNumAntigo.setText(context.getString(R.string.antigo, hino.getNumAntigo()));
        }

        TextView textViewNumNovo = view.findViewById(R.id.textViewNumNovo);
        if (hino.getNumNovo() == 0) {
            textViewNumNovo.setText(" - ");
        } else {
            textViewNumNovo.setText(context.getString(R.string.novo, hino.getNumNovo()));
        }

        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new HinosFiltro(this, context);
    }

    private class HinosFiltro extends Filter {
        private HinosBuscaAdaptador hinosBuscaAdaptador;
        private Context context;

        public HinosFiltro(HinosBuscaAdaptador hinosBuscaAdaptador, Context context) {
            this.hinosBuscaAdaptador = hinosBuscaAdaptador;
            this.context = context;
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            hinosBuscaAdaptador.hinos.clear();
            FilterResults filterResults = new FilterResults();
            if (charSequence == null || charSequence.length() == 0) {
                filterResults.values = new ArrayList<HinosEstrutura>();
                filterResults.count = 0;
            } else {

                HinosBD hinosBD = new HinosBD(context);
                List<HinosEstrutura> hinos = hinosBD.pesquisa(removeDiacriticalMarks(charSequence.toString()).toUpperCase());
                filterResults.values = hinos;
                filterResults.count = hinos.size();
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            hinosBuscaAdaptador.hinos.clear();
            hinosBuscaAdaptador.hinos.addAll((List<HinosEstrutura>) filterResults.values);
            hinosBuscaAdaptador.notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            HinosEstrutura hinosEstrutura = (HinosEstrutura) resultValue;
            return hinosEstrutura.getNome();
        }

        // método de remoção de acentos
        String removeDiacriticalMarks(String string) {
            String newString = string.replaceAll(",","");

            return Normalizer.normalize(newString, Normalizer.Form.NFD)
                    .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        }
    }
}
