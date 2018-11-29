package com.coldbeer.entidades;

/**
 * Created by Fernando on 28/01/2018.
 */

public class Usuario {

    private long id;
    private String usuario;
    private String senha;

    public Usuario(String usuario, String senha){
        setUsuario(usuario);
        setSenha(senha);
    }
    public Usuario(long id, String usuario, String senha){
        setId(id);
        setUsuario(usuario);
        setSenha(senha);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
