package br.com.salomaotech.genesys.controller.venda.venda_inicia.servico;

import br.com.salomaotech.genesys.model.servico.ServicoModelo;
import br.com.salomaotech.genesys.model.venda.VendaServicoPesquisa;
import br.com.salomaotech.genesys.model.venda.ItemVenda;
import br.com.salomaotech.genesys.view.JFvendaInicia;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import java.math.BigDecimal;

public class VendaIniciaMetodosServicos {

    private final JFvendaInicia view;

    public VendaIniciaMetodosServicos(JFvendaInicia view) {
        this.view = view;
    }

    public void pesquisarServicos() {

        VendaServicoPesquisa vendaServicoPesquisa = new VendaServicoPesquisa(view.jTlistaDeServicos);
        vendaServicoPesquisa.setNome(view.jTpesquisaNomeServico.getText());
        vendaServicoPesquisa.pesquisar();

    }

    public void exibirServicoSelecionado(long id) {

        ItemVenda itemVenda = new ItemVenda(id, new ServicoModelo());
        view.jTitemTotal.setText(ConverteNumeroParaMoedaBr.converter(calcularServicoSelecionado(id).toString()));
        habilitarCamposDeAdicionarServico(itemVenda);

    }

    public void limparServicoSelecionado() {

        view.jTitemQuantidade.setText(null);
        view.jTitemTotal.setText(null);
        view.jTlistaDeServicos.clearSelection();
        view.jBservicoAdicionaItem.setEnabled(false);
        view.jBcalcularGranel.setEnabled(false);
        view.jPdadosPerfilFoto.removeAll();
        view.jPdadosPerfilFoto.repaint();

    }

    public void adicionarServicoNaLista() {

        habilitarCamposDeAdicionarServico(new ItemVenda(0, new ServicoModelo()));
        limparServicoSelecionado();
        habilitarCamposDeExcluirServicoAdicionado();

    }

    public BigDecimal calcularServicoSelecionado(long id) {

        ItemVenda itemVenda = new ItemVenda(id, new ServicoModelo());
        BigDecimal quantidade = BigDecimais.formatarParaBigDecimal(view.jTitemQuantidade.getText());
        return itemVenda.getValor().multiply(quantidade);

    }

    private void habilitarCamposDeAdicionarServico(ItemVenda itemVenda) {

        view.jBservicoAdicionaItem.setEnabled(itemVenda.getId() != 0);
        view.jBcalcularGranel.setEnabled(false);
        view.jPdadosPerfilFoto.removeAll();
        view.jPdadosPerfilFoto.repaint();

    }

    private void habilitarCamposDeExcluirServicoAdicionado() {

        view.jBremoveItemSelecionadoLista.setEnabled(view.jTitensSelecionados.getSelectedRow() != -1);

    }

}
