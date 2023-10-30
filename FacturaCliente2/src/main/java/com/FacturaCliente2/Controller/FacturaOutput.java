package com.FacturaCliente2.Controller;

import com.FacturaCliente2.Domain.Factura;
import com.FacturaCliente2.Exception.InvalidFieldException;

public class FacturaOutput {
    private int codFra;
    private double total;
    private String mes;
    private int anyo;

    public FacturaOutput(int codFra, double total, String mes, int anyo) throws InvalidFieldException {
        if(codFra == 0) throw new InvalidFieldException("El codigo de la factura no puede ser nulo");
        this.codFra = codFra;
        if(total < 1) throw new InvalidFieldException("El total de la factura no puede ser negativo");
        this.total = total;
        if(mes == null) throw new InvalidFieldException("El mes no puede ser nulo");
        if(mes.isEmpty()) throw new InvalidFieldException("El mes no puede estar vacio");
        this.mes = mes;
        if(anyo== 0) throw new InvalidFieldException("El año no puede ser nulo");
        this.anyo = anyo;
    }
    public static FacturaOutput getFactura(Factura factura) throws InvalidFieldException {
        return new FacturaOutput(factura.getCodFra(), factura.getTotal(), factura.getMes(), factura.getAnyo());
    }

    public int getCodFra() {
        return codFra;
    }

    public double getTotal() {
        return total;
    }

    public String getMes() {
        return mes;
    }

    public int getAnyo() {
        return anyo;
    }
}
