package com.FacturaCliente2.Controller;

import com.FacturaCliente2.Exception.InvalidFieldException;

public class FacturaInput {
    private int codFra;
    private double total;
    private String mes;
    private int anyo;
    private String clienteDni;
    public FacturaInput() {
    }

    public FacturaInput(int codFra, double total, String mes, int anyo,String clienteDni) throws InvalidFieldException {
        if(codFra == 0) throw new InvalidFieldException("El codigo de la factura no puede ser nulo");
        if(codFra < 1 || codFra > 999) throw new InvalidFieldException("Debes introducir un codigo de factura entre el 1 y el 999");
        this.codFra = codFra;
        //if(total < 0) throw new InvalidFieldException("El total de la factura no puede ser negativo");
        this.total = total;
        if(mes == null) throw new InvalidFieldException("El mes no puede ser nulo");
        if(mes.isEmpty()) throw new InvalidFieldException("El mes no puede estar en blanco");
        this.mes = mes;
        if(anyo== 0) throw new InvalidFieldException("El año no puede ser nulo");
        this.anyo = anyo;
        this.clienteDni = clienteDni;
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

    public void setCodFra(int codFra) {
        this.codFra = codFra;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public String getClienteDni() {
        return clienteDni;
    }

    public void setClienteDni(String clienteDni) {
        this.clienteDni = clienteDni;
    }
}
