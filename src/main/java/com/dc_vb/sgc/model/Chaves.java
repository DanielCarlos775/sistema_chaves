package com.dc_vb.sgc.model;

public class Chaves {
    private int id_chave;
    private String codigo;
    private int id_sala; // FK para Sala

    public Chaves() {}

    public Chaves(int id_chave, String codigo, int id_sala) {
        this.id_sala = id_chave;
        this.codigo = codigo;
        this.id_sala = id_sala;
    }

    public int getId_chave() {
        return id_chave;
    }

    public void setId_chave(int id_chave) {
        this.id_chave = id_chave;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }
}
