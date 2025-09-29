package com.dc_vb.sgc.model;

import java.sql.Timestamp;

public class Predio {

    private int idPredio;
    private String nome;
    private String descricao;
    private boolean ativo;
    private Timestamp dataCriacao;
    private Timestamp dataAtualizacao;

    public Predio() {}

    // Construtor para Insert (Sem id / datas geradas no banco)
    public Predio(String nome, String descricao, boolean ativo) {
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    // Getters e Setters


    public int getIdPredio() { return idPredio; }
    public void setIdPredio(int idPredio) { this.idPredio = idPredio; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public Timestamp getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(Timestamp dataCriacao) { this.dataCriacao = dataCriacao; }

    public Timestamp getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(Timestamp dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }

    @Override
    public String toString() {
        return "Predio{" +
                "idPredio=" + idPredio +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", ativo=" + ativo +
                ", dataCriacao" + dataCriacao +
                ", dataAtualizacao" + dataAtualizacao +
                '}';
    }
}
