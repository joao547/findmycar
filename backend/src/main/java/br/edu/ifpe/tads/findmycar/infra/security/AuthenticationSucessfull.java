package br.edu.ifpe.tads.findmycar.infra.security;

public class AuthenticationSucessfull {
    private String token;

    public AuthenticationSucessfull() {
    }

    public AuthenticationSucessfull(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
