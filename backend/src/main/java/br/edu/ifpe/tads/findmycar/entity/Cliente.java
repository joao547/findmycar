package br.edu.ifpe.tads.findmycar.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
@DiscriminatorValue("Cliente")
public class Cliente extends Usuario{
}
