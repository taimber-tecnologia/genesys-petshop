package br.com.salomaotech.genesys.controller.venda.venda_visualiza;

import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.venda.VendaModelo;
import br.com.salomaotech.genesys.model.venda.VendaModeloItem;
import br.com.salomaotech.genesys.view.JFvendaVisualiza;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class VendaVisualizaMetodos {

    private final JFvendaVisualiza view;

    public VendaVisualizaMetodos(JFvendaVisualiza view) {
        this.view = view;
    }

    private void exibirProdutosSelecionados(List<VendaModeloItem> vendaModeloItemList) {

        DefaultTableModel defaultTableModel = (DefaultTableModel) view.jTprodutoSelecionado.getModel();
        defaultTableModel.setNumRows(0);
        int contador = 0;
        BigDecimal valorTotal = new BigDecimal(0);

        for (VendaModeloItem vendaModeloItem : vendaModeloItemList) {

            ProdutoModelo produtoModelo = (ProdutoModelo) new Repository(new ProdutoModelo()).findById(vendaModeloItem.getIdProduto());
            BigDecimal preco = vendaModeloItem.getValor().multiply(vendaModeloItem.getQuantidade()).subtract(vendaModeloItem.getDesconto());

            Object[] linhaDefaultTableModel = new Object[]{
                vendaModeloItem.getQuantidade(),
                produtoModelo.getNome(),
                ConverteNumeroParaMoedaBr.converter(vendaModeloItem.getDesconto().toString()),
                ConverteNumeroParaMoedaBr.converter(preco.toString())

            };

            valorTotal = valorTotal.add(preco);
            defaultTableModel.insertRow(contador, linhaDefaultTableModel);
            contador++;

        }

    }

    public void popularFormulario(VendaModelo vendaModelo) {

        /* modelos auxiliares */
        ClienteModelo clienteModelo = (ClienteModelo) new Repository(new ClienteModelo()).findById(vendaModelo.getIdCliente());

        /* popula formulário */
        view.setId(vendaModelo.getId());
        view.jTcodigo.setText(String.valueOf(vendaModelo.getId()));
        view.jDdata.setDate(vendaModelo.getData().getTime());
        view.jTclienteNome.setText(clienteModelo.getNome());
        view.jTclienteCpf.setText(clienteModelo.getCpf());
        view.jTpagamentoForma.setText(vendaModelo.getFormaPagamento());
        view.jTpagamentoParcelas.setText(String.valueOf(vendaModelo.getNumeroParcelas()));
        view.jTpagamentoValor.setText(ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString()));
        exibirProdutosSelecionados(vendaModelo.getVendaModeloItemList());

    }

    public void habilitarCampos() {

        view.jBcadastroExcluir.setEnabled(view.getId() != 0);

    }

    public void abrirCadastro(long id) {

        VendaModelo vendaModelo = (VendaModelo) new Repository(new VendaModelo()).findById(id);
        popularFormulario(vendaModelo);

    }

    public boolean excluir() {

        return new Repository(new VendaModelo()).delete(view.getId());

    }

}
