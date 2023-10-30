package com.FacturaCliente2.Controller;

import com.FacturaCliente2.Domain.Cliente;
import com.FacturaCliente2.Exception.InvalidFieldException;

public class ClienteDniNombreOutput {
    private String dni;
    private String nombre;

    public ClienteDniNombreOutput(String dni, String nombre) throws InvalidFieldException{
        if(dni.isEmpty()) throw new InvalidFieldException("El dni no puede estar vacio");
        if(dni == null) throw new InvalidFieldException("El dni no puede ser nulo");
        this.dni = dni;
        if(nombre.isEmpty()) throw new InvalidFieldException("El nombre no puede estar vacío");
        if(nombre == null) throw new InvalidFieldException("El nombre no puede ser nulo");
        this.nombre = nombre;
    }
    public static ClienteDniNombreOutput getCliente (Cliente cliente) throws InvalidFieldException {
        return new ClienteDniNombreOutput(cliente.getDni(), cliente.getNombre());
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }
}
