package com.FacturaCliente2.Controller;

import com.FacturaCliente2.Domain.Cliente;
import com.FacturaCliente2.Exception.*;
import com.FacturaCliente2.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController

public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @PostMapping("/clientes")
    public ResponseEntity<String> addClient(@RequestBody ClienteInput clienteInput){
        try{
            //Convirtiendo la fecha del formato String a formato Date.
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date fechaNac = dateFormat.parse(clienteInput.getFechaNac());
            Cliente cliente = new Cliente(clienteInput.getDni(),clienteInput.getNombre(),clienteInput.getPais(),clienteInput.isPremium(),clienteInput.getFechaNac());

            clienteService.addCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (ClientExistsException e){
            return ResponseEntity.status(HttpStatus.IM_USED).build();
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }
    }
    @PostMapping("/clientes/{clienteDni}/facturas")
    public ResponseEntity<String> addFacturaToCliente(@PathVariable String clienteDni, @RequestBody FacturaInput facturaInput) {
        try {
            clienteService.addFacturaToCliente(facturaInput.getCodFra(), clienteDni);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ClientDoesntExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (FacturaDoesntExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (FacturaExistsException e) {
            return ResponseEntity.status(HttpStatus.IM_USED).build();
        }
    }
    @GetMapping("clientes")
    public ResponseEntity<List<ClienteDniNombreOutput>>listClienteByTipoAndCountry(@RequestParam (name = "premium")Boolean premium, @RequestParam(name = "pais")String pais){
        try {
            List<ClienteDniNombreOutput> clientes = clienteService.listClienteByPremiumAndPais(premium, pais);
            return ResponseEntity.ok(clientes);
        }catch (ClientDoesntExistException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (InvalidFieldException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }
    }
}
