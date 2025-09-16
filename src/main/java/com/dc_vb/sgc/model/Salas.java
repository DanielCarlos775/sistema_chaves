package com.dc_vb.sgc.model;

public class Salas {
    private int id_sala;
    private int id_predio;
    private String nome;
    private String descricao;
    private int capacidade;

    public Salas(int id_sala, String nome, int id_predio) {}

    public Salas(int id_sala, int id_predio, String nome, String descricao, int capacidade) {
        this.id_sala = id_sala;
        this.id_predio = id_predio;
        this.nome = nome;
        this.descricao = descricao;
        this.capacidade = capacidade;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
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

    public int getId_Predio() {
        return id_predio;
    }
}
