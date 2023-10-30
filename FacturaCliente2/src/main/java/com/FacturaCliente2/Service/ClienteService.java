package com.FacturaCliente2.Service;

import com.FacturaCliente2.Controller.ClienteDniNombreOutput;
import com.FacturaCliente2.Domain.Cliente;
import com.FacturaCliente2.Domain.Factura;
import com.FacturaCliente2.Exception.*;
import com.FacturaCliente2.Repository.ClienteRepository;
import com.FacturaCliente2.Repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private FacturaRepository facturaRepository;

    public void addCliente(Cliente clienteInput) throws ClientExistsException {
        if(clienteRepository.existsById(clienteInput.getDni()))
            throw new ClientExistsException("El cliente ya existe");
        Cliente cliente = new Cliente(clienteInput.getDni(),clienteInput.getNombre(),clienteInput.getPais(),clienteInput.isPremium(),clienteInput.getFechaNac());
        clienteRepository.save(cliente);
    }
    public void addFacturaToCliente(int codFra,String dni) throws ClientDoesntExistException, FacturaDoesntExistException, FacturaExistsException {
        Cliente cliente = clienteRepository.findById(dni).orElse(null);
        if(cliente == null){
            throw new ClientDoesntExistException("El cliente con dni " + dni + " no existe");
        }
        Factura factura = facturaRepository.findById(codFra).orElse(null);
        if(factura == null){
            throw new FacturaDoesntExistException("La factura con codigo " + codFra + " no existe");
        }
        if(factura.getClienteDni() != null){
            throw new FacturaExistsException("La factura con codigo " + codFra + " ya está agregada al cliente");
        }
        factura.setClienteDni(dni);
        facturaRepository.save(factura);
    }

    public List<ClienteDniNombreOutput> listClienteByPremiumAndPais(Boolean premium, String pais) throws ClientDoesntExistException, InvalidFieldException {
        List<Cliente> clientes = clienteRepository.findByPremiumAndPaisOrderByNombre(premium, pais);
        List<ClienteDniNombreOutput> clienteDniNombreOutput = new ArrayList<>();
        if(clientes.isEmpty()) throw new ClientDoesntExistException("No hay clientes");

        for(Cliente item : clientes){
            clienteDniNombreOutput.add(ClienteDniNombreOutput.getCliente(item));
        }
        return clienteDniNombreOutput;
    }
}
