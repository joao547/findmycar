package br.edu.ifpe.tads.findmycar.enums;

public enum TipoUsuario {
    CLIENTE("cliente"),
    CONSULTOR("consultor");

    public final String name;

    TipoUsuario(String name){
        this.name = name.toLowerCase();
    }

    public String getName() {
        return name;
    }
}
