package com.FacturaCliente2.Service;

import com.FacturaCliente2.Controller.FacturaCodTotalOutput;
import com.FacturaCliente2.Controller.FacturaInput;
import com.FacturaCliente2.Controller.FacturaOutput;
import com.FacturaCliente2.Domain.Cliente;
import com.FacturaCliente2.Domain.Factura;
import com.FacturaCliente2.Exception.ClientDoesntExistException;
import com.FacturaCliente2.Exception.FacturaDoesntExistException;
import com.FacturaCliente2.Exception.FacturaExistsException;
import com.FacturaCliente2.Exception.InvalidFieldException;
import com.FacturaCliente2.Repository.ClienteRepository;
import com.FacturaCliente2.Repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private ClienteRepository clienteRepository;


    public void addFactura(FacturaInput facturaInput) throws FacturaExistsException {
        if(facturaRepository.existsById(facturaInput.getCodFra()))
            throw new FacturaExistsException("La factura ya existe");
        Factura factura = new Factura(facturaInput.getCodFra(),facturaInput.getTotal(),facturaInput.getMes(), facturaInput.getAnyo(),facturaInput.getClienteDni());
        facturaRepository.save(factura);
    }

    public List<FacturaOutput> listFacturas() throws InvalidFieldException, FacturaDoesntExistException {
        List<Factura> facturas = facturaRepository.findAll();
        List<FacturaOutput> facturaOutput = new ArrayList<>();
        for(Factura item : facturas) {
            facturaOutput.add(FacturaOutput.getFactura(item));
        }
        return facturaOutput;
    }
    public List<FacturaCodTotalOutput> listFacturasCliente(String clienteDni) throws ClientDoesntExistException, InvalidFieldException {
        Cliente cliente = clienteRepository.findById(clienteDni).orElse(null);
        if(cliente == null) {
            throw new ClientDoesntExistException("El cliente con dni " + clienteDni + " no existe");
        }
        List<Factura> facturas = facturaRepository.findByClienteDni(clienteDni);
        List<FacturaCodTotalOutput>facturaCodTotalOutputs = new ArrayList<>();
        for(Factura item : facturas){

            facturaCodTotalOutputs.add(FacturaCodTotalOutput.getFactura(item));

        }
        return facturaCodTotalOutputs;
    }
    public List<FacturaOutput>listFacturaByAnyoAndMes(int anyo,String mes) throws FacturaDoesntExistException, InvalidFieldException {
        List<Factura> facturas = facturaRepository.findAllByAnyoAndMes(anyo, mes);
        List<FacturaOutput> facturaOutputs = new ArrayList<>();
        for (Factura item : facturas) {
            facturaOutputs.add(FacturaOutput.getFactura(item));
        }
        return facturaOutputs;

    }
}
