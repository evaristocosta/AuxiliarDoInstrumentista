package icm.utils.auxiliardoinstrumentista;

import android.content.Context;
import android.content.Intent;

public class HinosEstrutura {
    private Integer numAntigo;
    private Integer numNovo;
    private String nome;

    public HinosEstrutura() {
    }

    public HinosEstrutura(Integer numAntigo, Integer numNovo, String nome) {
        this.numAntigo = numAntigo;
        this.numNovo = numNovo;
        this.nome = nome;
    }

    public int getNumAntigo() {
        return numAntigo;
    }

    public void setNumAntigo(int numAntigo) {
        this.numAntigo = numAntigo;
    }

    public int getNumNovo() {
        return numNovo;
    }

    public void setNumNovo(int numNovo) {
        this.numNovo = numNovo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
