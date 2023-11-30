package br.edu.ifpe.tads.findmycar.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "agendamento")
public class Agendamento extends Servico{
    private String local;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date data;

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
