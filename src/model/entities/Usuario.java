package model.entities;

import java.util.Objects;

public class Usuario {

    private String nome;
    private String sobrenome;
    private String login;
    private String senha;
    private String confirmacaoSenha;
    private String adm;

    public Usuario() {

    }

    public Usuario(String nome, String sobrenome, String login, String senha, String confirmacaoSenha, String adm) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.login = login;
        this.senha = senha;
        this.confirmacaoSenha = confirmacaoSenha;
        this.adm = adm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public String getAdm() {
        return adm;
    }

    public void setAdm(String adm) {
        this.adm = adm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nome, usuario.nome) && Objects.equals(sobrenome, usuario.sobrenome) && Objects.equals(login, usuario.login) && Objects.equals(senha, usuario.senha) && Objects.equals(confirmacaoSenha, usuario.confirmacaoSenha) && Objects.equals(adm, usuario.adm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, sobrenome, login, senha, confirmacaoSenha, adm);
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome='" + nome + '\'' + ", sobrenome='" + sobrenome + '\'' + ", login='" + login + '\'' + ", senha='" + senha + '\'' + ", confirmacaoSenha='" + confirmacaoSenha + '\'' + ", adm=" + adm + '}';
    }
}
