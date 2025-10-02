package com.dc_vb.sgc.model;

import java.time.LocalDateTime;

public class Usuario {
    private int idUsuario;
    private String nome;
    private String email;
    private String senha;
    private String tipoUsuario;
    private Integer idPredio;
    private Integer idSala;
    private boolean ativo;
    private boolean senhaAlterada;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public Usuario(){}

    // Construtor Para Insert (sem id / datas geradas no banco)
    public Usuario(String nome, String email, String senha, String tipoUsuario, Integer idPredio, Integer idSala) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.idPredio = idPredio;
        this.idSala = idSala;
        this.ativo = true;
        this.senhaAlterada = false;
    }

    // Getters e Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public Integer getIdPredio() { return idPredio; }
    public void setIdPredio(Integer idPredio) { this.idPredio = idPredio; }

    public Integer getIdSala() { return idSala; }
    public void setIdSala(Integer idSala) { this.idSala = idSala; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }

    public boolean isSenhaAlterada() { return senhaAlterada; }
    public void setSenhaAlterada(boolean senhaAlterada) { this.senhaAlterada = senhaAlterada; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }

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