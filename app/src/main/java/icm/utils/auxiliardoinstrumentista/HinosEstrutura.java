package icm.utils.auxiliardoinstrumentista;

import android.content.Context;
import android.content.Intent;

public class HinosEstrutura {
    private Integer numAntigo;
    private Integer numNovo;
    private String nome;
    private String categoria;
    private Integer qtdCantado;

    public HinosEstrutura() {
    }

    public HinosEstrutura(Integer numAntigo, Integer numNovo, String nome) {
        this.numAntigo = numAntigo;
        this.numNovo = numNovo;
        this.nome = nome;
    }

    public HinosEstrutura(Integer numAntigo, Integer numNovo, String nome, String categoria, Integer qtdCantado) {
        this.numAntigo = numAntigo;
        this.numNovo = numNovo;
        this.nome = nome;
        this.categoria = categoria;
        this.qtdCantado = qtdCantado;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getQtdCantado() {
        return qtdCantado;
    }

    public void setQtdCantado(Integer qtdCantado) {
        this.qtdCantado = qtdCantado;
    }
}
