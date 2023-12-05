package br.edu.ifpe.tads.findmycar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.Set;

@Entity
@Table(name = "local")
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(unique = true)
    private Long ibgeCode;
    private String uf;
    private String name;

    @ManyToMany(mappedBy = "locais")
    @Cascade(CascadeType.REFRESH)
    @JsonIgnore
    private Set<Consultor> consultor;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getIbgeCode() { return ibgeCode; }

    public void setIbgeCode(Long ibgeCode) { this.ibgeCode = ibgeCode; }

    public String getUf() { return uf; }

    public void setUf(String uf) { this.uf = uf; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Set<Consultor> getConsultor() {return consultor; }

    public void setConsultor(Set<Consultor> consultor) { this.consultor = consultor; }
}
