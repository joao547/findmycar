package br.edu.ifpe.tads.findmycar.dto;

import br.edu.ifpe.tads.findmycar.annotations.Conditional;
import br.edu.ifpe.tads.findmycar.entity.CarroMarcas;
import br.edu.ifpe.tads.findmycar.entity.Local;
import br.edu.ifpe.tads.findmycar.entity.ServicosBuscador;
import br.edu.ifpe.tads.findmycar.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

//@Conditional(selected = "tipo", values = {"consultor"}, required = {"precoDoServico", "areaDeAtuacao", "disponibilidade"})
public class UsuarioDto {
    private long id;

    @NotEmpty
    private String nome;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 8, message = "Senha deve ter ao menos 8 caracteres")
    private String senha;

    @NotNull
    private TipoUsuario tipo;

   // private Double precoDoServico;

    //private String areaDeAtuacao;

    //private String disponibilidade;


    private Set<Local> locais;

    private Set<CarroMarcas> carroMarcas;

    private Set<ServicosBuscador> servicosBuscador;

    private byte[] fotoPerfil;

    /* public UsuarioDto() {
    }

   public UsuarioDto(long id, String nome, String email, String senha, TipoUsuario tipo, double precoDoServico, String areaDeAtuacao, String disponibilidade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.precoDoServico = precoDoServico;
        this.areaDeAtuacao = areaDeAtuacao;
        this.disponibilidade = disponibilidade;
    }*/
   public UsuarioDto(long id, String nome, String email, String senha, TipoUsuario tipo, Set<Local> locais, Set<CarroMarcas> carroMarcas, Set<ServicosBuscador> servicosBuscador, byte[] fotoPerfil) {
       this.id = id;
       this.nome = nome;
       this.email = email;
       this.senha = senha;
       this.tipo = tipo;
       this.locais = locais;
       this.carroMarcas = carroMarcas;
       this.servicosBuscador = servicosBuscador;
       this.fotoPerfil = fotoPerfil;
   }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    /*public Double getPrecoDoServico() {
        return precoDoServico;
    }

    public void setPrecoDoServico(Double precoDoServico) {
        this.precoDoServico = precoDoServico;
    }

    public String getAreaDeAtuacao() {
        return areaDeAtuacao;
    }

    public void setAreaDeAtuacao(String areaDeAtuacao) {
        this.areaDeAtuacao = areaDeAtuacao;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }*/

    public Set<Local> getLocais() { return locais; }

    public void setLocais(Set<Local> locais) { this.locais = locais; }


    public Set<CarroMarcas> getCarroMarcas() {
        return carroMarcas;
    }

    public void setCarroMarcas(Set<CarroMarcas> carroMarcas) {
        this.carroMarcas = carroMarcas;
    }

    public Set<ServicosBuscador> getServicosBuscador() {
        return servicosBuscador;
    }

    public void setServicosBuscador(Set<ServicosBuscador> servicosBuscador) {
        this.servicosBuscador = servicosBuscador;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public byte[] getFotoPerfil() {return fotoPerfil;}

    public void setFotoPerfil(byte[] fotoPerfil) {this.fotoPerfil = fotoPerfil;}
}
