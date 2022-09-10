package br.com.salomaotech.genesys.model.venda.cliente;

import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.sistema.jpa.Repository;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class VendaComprovantePdfTest {

    private final String pathLocal = System.getProperty("user.dir") + "/target/arquivos/testes_remover/";
    private final String pastaDeTeste = pathLocal + "venda_relatorio_pdf/";
    private final VendaModelo vendaModelo = new VendaModelo();
    private final ClienteModelo clienteModelo = new ClienteModelo();
    private final VendaComprovantePdf vendaComprovantePdf;

    public VendaComprovantePdfTest() {

        /* simula cadastro de cliente */
        new Repository(new ClienteModelo()).deleteTodos();
        clienteModelo.setNome("Teste");
        clienteModelo.setCpf("000.000.000-00");
        new Repository(clienteModelo).save();

        /* simula cadastro de venda */
        new Repository(new VendaModelo()).deleteTodos();
        vendaModelo.setIdCliente(clienteModelo.getId());
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
