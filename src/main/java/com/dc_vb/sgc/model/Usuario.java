package com.dc_vb.sgc.model;

import java.sql.Timestamp;

public class Usuario {
    private int idUsuario;
    private String nome;
    private String email;
    private String senha;
    private String tipoUsuario;
    private int idPredio;
    private int idSala;
    private boolean ativo;
    private Timestamp dataCriacao;
    private Timestamp dataAtualizacao;

    public Usuario(){}

    // Construtor Para Insert (sem id / datas geradas no banco)
    public Usuario(String nome, String email, String senha, String tipoUsuario, int idPredio, int idSala, boolean ativo) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.idPredio = idPredio;
        this.idSala = idSala;
        this.ativo = ativo;
    }

    // Getters e Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int id) { this.idUsuario = idUsuario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public int getIdPredio() { return idPredio; }
    public void setIdPredio(int idPredio) { this.idPredio = idPredio; }

    public int getIdSala() { return idSala; }
    public void setIdSala(int idSala) { this.idSala = idSala; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public Timestamp getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(Timestamp dataCriacao) { this.dataCriacao = dataCriacao; }

    public Timestamp getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(Timestamp dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }

    @Override
    public String toString() {
        //intencionalmente NÃO expõe a senha
        return "Usuario{idUsuario=" + idUsuario +
                ", nome'" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                ", idPredio=" + idPredio +
                ", idSala=" + idSala +
                ", ativo=" + ativo +
                ", dataCriacao=" + dataCriacao +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }
}