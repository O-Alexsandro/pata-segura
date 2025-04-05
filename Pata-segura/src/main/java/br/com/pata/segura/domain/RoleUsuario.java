package br.com.pata.segura.domain;

public enum RoleUsuario {
    ADMINISTRADOR("Administrador"),
    USUARIO("Usuario");

    final String role;

    RoleUsuario(String role){
        this.role = role;
    }
    }
