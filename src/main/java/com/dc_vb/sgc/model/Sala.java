package com.dc_vb.sgc.model;

import java.sql.Timestamp;

public class Sala {
    private int idSala;
    private int idPredio; //FK para o prédio
    private String nome;
    private String descricao;
    private int capacidade;
    private boolean ativo;
    private Timestamp dataCriacao;
    private Timestamp dataAtualizacao;

    public Sala() {}

    // Construtor para Insert (sem id / datas geradas no banco)
    public Sala(int idPredio, String nome, int capacidade, boolean ativo) {
        this.idPredio = idPredio;
        this.nome = nome;
        this.capacidade = capacidade;
        this.ativo = ativo;
    }

    // Getters e Setters
    public int getIdSala() {
        return idSala;
    }
    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getIdPredio() {
        return idPredio;
    }
    public void setIdPredio(int idPredio) {
        this.idPredio = idPredio;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCapacidade() {
        return capacidade;
    }
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public Timestamp getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(Timestamp dataCriacao) { this.dataCriacao = dataCriacao; }

    public Timestamp getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(Timestamp dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }

    @Override
    public String toString() {
        return "Sala{" +
                "idSala=" + idSala +
                ", idPredio=" + idPredio +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", capacidade=" + capacidade +
                ", ativo=" + ativo +
                ", dataCriacao=" + dataCriacao +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }
}
