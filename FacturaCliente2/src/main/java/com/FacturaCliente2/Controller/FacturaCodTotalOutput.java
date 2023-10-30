package com.FacturaCliente2.Controller;

import com.FacturaCliente2.Domain.Factura;
import com.FacturaCliente2.Exception.InvalidFieldException;

public class FacturaCodTotalOutput {
    private int codFra;
    private double total;

    public FacturaCodTotalOutput() {
    }

    public FacturaCodTotalOutput(int codFra, double total)throws InvalidFieldException {
        if(codFra == 0) throw new InvalidFieldException("El codigo de la factura no puede ser nulo");
        this.codFra = codFra;
        if(total < 1) throw new InvalidFieldException("El total de la factura no puede ser negativo");
        this.total = total;
    }
    public static FacturaCodTotalOutput getFactura(Factura factura) throws InvalidFieldException {
        return new FacturaCodTotalOutput(factura.getCodFra(), factura.getTotal());
    }


    public int getCodFra() {
        return codFra;
    }

    public double getTotal() {
        return total;
    }
}
