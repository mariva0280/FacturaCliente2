package com.FacturaCliente2.Repository;

import com.FacturaCliente2.Domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface FacturaRepository  extends JpaRepository<Factura,Integer> {
    List<Factura> findByClienteDni(String clienteDni);
    List<Factura> findAllByAnyoAndMes(int anyo, String mes);
}
