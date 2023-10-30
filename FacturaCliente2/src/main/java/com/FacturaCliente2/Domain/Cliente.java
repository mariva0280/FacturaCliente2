package com.FacturaCliente2.Domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class Cliente {
    @Id

    private String dni;
    private String nombre;
    private String pais;
    private boolean premium;
    private String fechaNac;

    public Cliente() {
    }

    public Cliente(String dni, String nombre, String pais, boolean premium, String fechaNac) {
        this.dni = dni;
        this.nombre = nombre;
        this.pais = pais;
        this.premium = premium;
        this.fechaNac = fechaNac;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public boolean isPremium() {
        return premium;
    }

    public String getFechaNac() {
        return fechaNac;
    }

}
