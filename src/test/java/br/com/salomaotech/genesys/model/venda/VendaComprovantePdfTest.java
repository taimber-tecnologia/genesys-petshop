package br.com.salomaotech.genesys.model.venda;

import br.com.salomaotech.sistema.jpa.Repository;
import org.junit.Test;
import static org.junit.Assert.*;

public class VendaComprovantePdfTest {

    private final String pathLocal = System.getProperty("user.dir") + "/target/arquivos/testes_remover/";
    private final String pastaDeTeste = pathLocal + "venda_relatorio_pdf/";
    private final VendaModelo vendaModelo = new VendaModelo();
    private final VendaComprovantePdf vendaComprovantePdf;

    public VendaComprovantePdfTest() {

        /* simula cadastro de venda */
        new Repository(new VendaModelo()).deleteTodos();
        vendaModelo.setCpfCliente("000.000.000-00");
        new Repository(vendaModelo).save();

        /* gerador de comprovante PDF */
        vendaComprovantePdf = new VendaComprovantePdf(pastaDeTeste, vendaModelo.getId());

    }

    @Test
    public void testGerar() {

        System.out.println("Testando classe VendaRelatorioPdf metodo: gerar");
        assertEquals(true, vendaComprovantePdf.gerar());

    }

}
