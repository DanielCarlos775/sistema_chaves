package com.dc_vb.sgc.model;

import java.sql.Timestamp;

public class Chave {
    private int idChave;
    private int idSala; //FK para a sala
    private String codigo;
    private String descricao;
    private boolean ativo;
    private Timestamp dataCriacao;
    private Timestamp dataAtualizacao;

    public Chave() {}

    // Construtor para Insert (sem Id / datas geradas no banco)
    public Chave(int idSala, String codigo, String descricao, boolean ativo) {
        this.idSala = idSala;
        this.codigo = codigo;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    // Getters e Setters
    public int getIdChave() {
        return idChave;
    }
    public void setIdChave(int idChave) {
        this.idChave = idChave;
    }

    public int getIdSala() { return idSala; }
    public void setIdSala(int idSala) { this.idSala = idSala; }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public Timestamp getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(Timestamp dataCriacao) { this.dataCriacao = dataCriacao; }

    public Timestamp getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(Timestamp dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }

    @Override
    public String toString() {
        return "Chave{" +
                "idChave=" + idChave +
                ", idSala=" + idSala +
                ", codigo='" + codigo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", ativo=" + ativo +
                ", dataCriacao=" + dataCriacao +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }
}
