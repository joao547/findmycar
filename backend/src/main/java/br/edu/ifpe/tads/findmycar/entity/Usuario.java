package br.edu.ifpe.tads.findmycar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TP_USUARIO", discriminatorType = DiscriminatorType.STRING, length = 20)
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;
  @Column(unique = true)
  private String email;
  private String senha;
  private String fotoPerfilBase64;
  @OneToMany(mappedBy = "avaliador")
  @JsonIgnore
  private Set<Avaliacao> avaliacoesFeitas;
  @OneToMany(mappedBy = "avaliado")
  @JsonIgnore
  private Set<Avaliacao> avaliacoesRecebidas;
  private String tipoUsuario;
  public String getTipoUsuario() {
    return tipoUsuario;
  }

  public void setTipoUsuario(String tipoUsuario) {
    this.tipoUsuario = tipoUsuario;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Set<Avaliacao> getAvaliacoesFeitas() {
    return avaliacoesFeitas;
  }

  public void setAvaliacoesFeitas(Set<Avaliacao> avaliacoesFeitas) {
    this.avaliacoesFeitas = avaliacoesFeitas;
  }

  public Set<Avaliacao> getAvaliacoesRecebidas() {
    return avaliacoesRecebidas;
  }

  public void setAvaliacoesRecebidas(Set<Avaliacao> avaliacoesRecebidas) {
    this.avaliacoesRecebidas = avaliacoesRecebidas;
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

  @Override
  public String toString() {
    return String.format("nome: %s, email:%s, senha: %s", this.nome, this.email, this.senha);
  }

  public String getFotoPerfil() {
    return fotoPerfilBase64;
  }

  public void setFotoPerfil(String fotoPerfilBase64) {
    this.fotoPerfilBase64 = fotoPerfilBase64;
  }
}
