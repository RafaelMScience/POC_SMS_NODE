package com.example.loginfirebase;

public class Users {

    private String login;
    private String senha;
    private String telefone;
    private String hash_telefone;

    public Users(String login, String senha, String telefone, String hash_telefone) {
        this.login = login;
        this.senha = senha;
        this.telefone = telefone;
        this.hash_telefone = hash_telefone;
    }
    public Users(){
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getHash_telefone() {
        return hash_telefone;
    }

    public void setHash_telefone(String hash_telefone) {
        this.hash_telefone = hash_telefone;
    }
}
