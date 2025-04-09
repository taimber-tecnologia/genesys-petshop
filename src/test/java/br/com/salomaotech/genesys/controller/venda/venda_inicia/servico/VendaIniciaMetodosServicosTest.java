package br.com.salomaotech.genesys.controller.venda.venda_inicia.servico;

import br.com.salomaotech.genesys.model.servico.ServicoModelo;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class VendaIniciaMetodosServicosTest {

    private final JFvendaInicia view = new JFvendaInicia();
    private ServicoModelo servicoModelo = new ServicoModelo();
    private final VendaIniciaMetodosServicos vendaIniciaMetodosServicos = new VendaIniciaMetodosServicos(view);

    public VendaIniciaMetodosServicosTest() {

        /* remove cadastros antigos */
        new Repository(new ServicoModelo()).deleteTodos();

        /* simula novos cadastros */
        for (int i = 1; i <= 3; i++) {

            servicoModelo = new ServicoModelo();
            servicoModelo.setNome("Mouse " + i);
            servicoModelo.setValor(new BigDecimal(25));
            servicoModelo.setDescricao("Teste descrição");
            new Repository(servicoModelo).save();

        }

        /* simula novos cadastros */
        for (int i = 1; i <= 4; i++) {

            servicoModelo = new ServicoModelo();
            servicoModelo.setNome("Teclado " + i);
            servicoModelo.setValor(new BigDecimal(15));
            servicoModelo.setDescricao("Teste descrição");
            new Repository(servicoModelo).save();

        }

        /* simula novos cadastros */
        servicoModelo = new ServicoModelo();
        servicoModelo.setNome("Teste");
        servicoModelo.setValor(new BigDecimal(4.5));
        servicoModelo.setDescricao("Teste descrição");
        new Repository(servicoModelo).save();

    }

    @Test
    public void testPesquisarServicos() {

        // Etapa 01
        view.jTpesquisaNomeServico.setText("Mouse");
        vendaIniciaMetodosServicos.pesquisarServicos();

        System.out.println("Testando a classe VendaIniciaMetodosServicos método pesquisarServicos etapa 01");
        assertEquals(true, view.jTlistaDeServicos.getRowCount() == 3);

        // Etapa 02
        view.jTpesquisaNomeServico.setText("Teclado");
        vendaIniciaMetodosServicos.pesquisarServicos();

        System.out.println("Testando a classe VendaIniciaMetodosServicos método pesquisarServicos etapa 02");
        assertEquals(true, view.jTlistaDeServicos.getRowCount() == 4);

        // Etapa 03 - Pesquisa vazia
        view.jTpesquisaNomeServico.setText("");
        vendaIniciaMetodosServicos.pesquisarServicos();
        assertTrue("Deveria retornar todos os servicos", view.jTlistaDeServicos.getRowCount() >= 8);

    }

    @Test
    public void testExibirServicoSelecionado() {

        // Etapa 01 - Servico válido
        vendaIniciaMetodosServicos.exibirServicoSelecionado(servicoModelo.getId());
        System.out.println("Testando a classe VendaIniciaMetodosServicos método exibirServicoSelecionado etapa 01");
        assertEquals(true, view.jBservicoAdicionaItem.isEnabled());
        assertEquals(false, view.jBcalcularGranel.isEnabled());
        assertEquals(true, view.jTitemQuantidade.isEnabled());
        assertEquals("R$ 0,00", view.jTitemTotal.getText());

        // Verifica se a imagem não foi carregada (OBS: imagem não adicionada) (verifica se o painel foi modificado)
        assertTrue(view.jPdadosPerfilFoto.getComponentCount() == 0);

        // Etapa 02 - Servico inválido (ID 0)
        vendaIniciaMetodosServicos.exibirServicoSelecionado(0);
        System.out.println("Testando a classe VendaIniciaMetodosServicos método exibirServicoSelecionado etapa 02");
        assertEquals(false, view.jBservicoAdicionaItem.isEnabled());
        assertEquals(false, view.jBcalcularGranel.isEnabled());
        assertEquals(true, view.jTitemQuantidade.isEnabled());
        assertEquals("R$ 0,00", view.jTitemTotal.getText());

        // Verifica se a imagem foi removida
        assertEquals("O painel da imagem deveria estar vazio", 0, view.jPdadosPerfilFoto.getComponentCount());

    }

    @Test
    public void testLimparServicoSelecionado() {

        // Primeiro exibe um servico para depois limpar
        vendaIniciaMetodosServicos.exibirServicoSelecionado(servicoModelo.getId());
        vendaIniciaMetodosServicos.limparServicoSelecionado();

        System.out.println("Testando a classe VendaIniciaMetodosServicos método limparServicoSelecionado");
        assertEquals("", view.jTitemQuantidade.getText());
        assertEquals("", view.jTitemTotal.getText());
        assertEquals(-1, view.jTlistaDeServicos.getSelectedRow());
        assertEquals(-1, view.jTlistaDeServicos.getSelectedRow());
        assertEquals(false, view.jBservicoAdicionaItem.isEnabled());
        assertEquals(false, view.jBcalcularGranel.isEnabled());
        assertEquals("O painel da imagem deveria estar vazio", 0, view.jPdadosPerfilFoto.getComponentCount());

    }

    @Test
    public void testAdicionarServicoNaLista() {

        System.out.println("Testando a classe VendaIniciaMetodosServicos método adicionarServicoNaLista");
        vendaIniciaMetodosServicos.adicionarServicoNaLista();

        // Campos que devem estar limpos (ativados ou desativados)
        assertEquals("", view.jTitemQuantidade.getText());
        assertEquals("", view.jTitemTotal.getText());
        assertEquals(-1, view.jTlistaDeServicos.getSelectedRow());
        assertEquals(-1, view.jTlistaDeServicos.getSelectedRow());
        assertEquals(false, view.jBservicoAdicionaItem.isEnabled());
        assertEquals(false, view.jBcalcularGranel.isEnabled());

        // Campos onde o servico será adicionado
        assertEquals(false, view.jBservicoAdicionaItem.isEnabled());
        assertEquals(false, view.jBcalcularGranel.isEnabled());
        assertEquals(true, view.jTitemQuantidade.isEnabled());

        /**
         * Já que nenhum item está selecionado, então o botão de remoção deve
         * estar desabilitado
         */
        assertEquals(false, view.jBremoveItemSelecionadoLista.isEnabled());

    }

    @Test
    public void testCalcularServicoSelecionado() {

        view.jTitemQuantidade.setText("3");

        System.out.println("Testando a classe VendaIniciaMetodosServicos método calcularServicoSelecionado");
        assertEquals(new BigDecimal("13.50"), vendaIniciaMetodosServicos.calcularServicoSelecionado(servicoModelo.getId()));

        // Teste com quantidade zero
        view.jTitemQuantidade.setText("0");
        assertEquals(new BigDecimal("0.00"), vendaIniciaMetodosServicos.calcularServicoSelecionado(servicoModelo.getId()));

        // Teste com quantidade decimal
        view.jTitemQuantidade.setText("1.5");
        assertEquals(new BigDecimal("6.750"), vendaIniciaMetodosServicos.calcularServicoSelecionado(servicoModelo.getId()));

    }

}
