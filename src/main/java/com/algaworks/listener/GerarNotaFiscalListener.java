package com.algaworks.listener;

import com.algaworks.model.NotaFiscal;
import com.algaworks.model.Pedido;
import com.algaworks.service.NotaFiscalService;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class GerarNotaFiscalListener {

    private NotaFiscalService notaFiscalService = new NotaFiscalService();

    @PreUpdate
    @PrePersist
    public void gerar(Pedido pedido) {
        if(pedido.isPago() && pedido.getNotaFiscal() == null) {
            notaFiscalService.gerar(pedido);
        }
    }

}
