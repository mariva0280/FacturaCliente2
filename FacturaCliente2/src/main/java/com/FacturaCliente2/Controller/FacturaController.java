package com.FacturaCliente2.Controller;

import com.FacturaCliente2.Exception.ClientDoesntExistException;
import com.FacturaCliente2.Exception.FacturaDoesntExistException;
import com.FacturaCliente2.Exception.FacturaExistsException;
import com.FacturaCliente2.Exception.InvalidFieldException;
import com.FacturaCliente2.Service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class FacturaController {
    @Autowired
    private FacturaService facturaService;

    @PostMapping("/facturas")
    public ResponseEntity<String> addFactura(@RequestBody FacturaInput facturaInput){
        try{
            facturaService.addFactura(facturaInput);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (FacturaExistsException e){
            return ResponseEntity.status(HttpStatus.IM_USED).build();
        }
    }
    @GetMapping("/facturas")
    public ResponseEntity<List<FacturaOutput>>listFacturas(){
        List<FacturaOutput> facturas = null;
        try{
            facturas = facturaService.listFacturas();
        }catch (InvalidFieldException e){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }catch (FacturaDoesntExistException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facturas);
    }
    @GetMapping("/facturas/{clienteDni}/clientes")
    public ResponseEntity<List<FacturaCodTotalOutput>>listFacturasCliente(@PathVariable String clienteDni){
        try {
            List<FacturaCodTotalOutput> facturaCodTotalOutputs = facturaService.listFacturasCliente(clienteDni);
            return ResponseEntity.ok(facturaCodTotalOutputs);
        }catch (ClientDoesntExistException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InvalidFieldException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).build();
        }
    }
    @GetMapping("/facturas/{anyo}/{mes}")
    public ResponseEntity<List<FacturaOutput>>listFacturasByAnyoAndMes(@PathVariable int anyo, @PathVariable String mes){
        try{
            List<FacturaOutput>facturaOutputs = facturaService.listFacturaByAnyoAndMes(anyo, mes);
            if(facturaOutputs.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }else{
                return ResponseEntity.ok(facturaOutputs);
            }
        }catch (FacturaDoesntExistException | InvalidFieldException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
