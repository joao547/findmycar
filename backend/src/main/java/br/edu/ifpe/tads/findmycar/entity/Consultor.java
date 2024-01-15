package br.edu.ifpe.tads.findmycar.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("Consultor")
public class Consultor extends Usuario {
    // -------------------------------------------

    // camposBuscador
    @ManyToMany
    private List<ServicosBuscador> servicosBuscador; // carros_antigos, carros_raros, carros_comuns

    /*
    const optionsBuscador = [
        { label: 'carros antigos', value: 'carros antigos' },
        { label: 'carros raros', value: 'carros raros' },
        { label: 'carros comuns', value: 'carros comuns' },
        { label: 'carros econômicos', value: 'carros econômicos' },
        { label: 'carros esportivos', value: 'carros esportivos' },
        { label: 'carros zero', value: 'carros zero' },
    ]
    */

    @ManyToMany
    private List<CarroMarcas> carroMarcas;
    private double precoServicoBuscador;
    // camposMecanico
    private double precoServicoMecanico;


    /*
    export const marcaOptions: readonly MarcaOption[] = [
      { value: 'BMW', label: 'BMW' },
      { value: 'Chevrolet', label: 'Chevrolet' },
      { value: 'Citroen', label: 'Citroen' },
      { value: 'FIAT', label: 'FIAT' },
      { value: 'Ford', label: 'Ford'},
      { value: 'Honda', label: 'Honda'},
      { value: 'Hyundai', label: 'Hyundai' },
      { value: 'Peugout', label: 'Peugout' },
      { value: 'Renault', label: 'Renault' },
      { value: 'Volkswagen', label: 'Volkswagen' },
    ];
    */

    @ElementCollection
    @CollectionTable(name="user_listConsultant")
    private Set<String> areasConsultor = new HashSet<>();

    @ElementCollection
    @CollectionTable(name="user_listSeeker")
    private Set<String> areasBuscador = new HashSet<>();

    @ManyToMany
    @Cascade(CascadeType.ALL)
    @JoinTable(
        name = "consultor_locais",
        joinColumns = @JoinColumn(name = "consultor_id"),
        inverseJoinColumns = @JoinColumn(name = "local_id")
    )
    private Set<Local> locais;

    @OneToMany(mappedBy = "consultor")
    private Set<Consulta> consultas;
    @OneToMany(mappedBy = "consultor")
    private Set<Proposta> propostas;
}
