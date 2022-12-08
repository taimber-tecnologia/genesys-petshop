package br.com.salomaotech.genesys.model.venda;

import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.servico.ServicoModelo;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.patterns.Command;
import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;
import java.util.List;
import static java.util.Objects.isNull;
import javax.swing.JComboBox;

public class ComboBoxItemVenda {

    private final JComboBox jComboBox;
    private final Command command;
    private long idItemSelecionado = 0;
    private final LinkedHashMap modeloLinkedHashMap = new LinkedHashMap();
    private final LinkedHashMap objecHashMap = new LinkedHashMap();

    public ComboBoxItemVenda(JComboBox jComboBox, Command command) {

        this.jComboBox = jComboBox;
        this.command = command;
        addEventos();

    }

    private void addEventos() {

        jComboBox.addActionListener((ActionEvent e) -> {

            try {

                idItemSelecionado = (long) modeloLinkedHashMap.get(jComboBox.getSelectedItem());

            } catch (Exception ex) {

                idItemSelecionado = 0;

            }

            if (!isNull(command)) {

                command.executar(idItemSelecionado);

            }

        });

    }

    private JPQL popularProdutoJpql() {

        JPQL jpql = new JPQL(new ProdutoModelo());
        jpql.addOrderBy("nome", "ASC");
        return jpql;

    }

    private JPQL popularServicoJpql() {

        JPQL jpql = new JPQL(new ServicoModelo());
        jpql.addOrderBy("nome", "ASC");
        return jpql;

    }

    public void preencher() {

        jComboBox.removeAllItems();
        jComboBox.addItem("");

        /* produto */
        List modeloList = new Repository(new ProdutoModelo()).getResults(popularProdutoJpql().construirSelect());
        for (Object objeto : modeloList) {

            ProdutoModelo produtoModelo = (ProdutoModelo) objeto;
            jComboBox.addItem(produtoModelo.getNome());
            modeloLinkedHashMap.put(produtoModelo.getNome(), produtoModelo.getId());
            objecHashMap.put(produtoModelo.getId(), produtoModelo);

        }

        /* servico */
        modeloList = new Repository(new ServicoModelo()).getResults(popularServicoJpql().construirSelect());
        for (Object objeto : modeloList) {

            ServicoModelo servicoModelo = (ServicoModelo) objeto;
            jComboBox.addItem(servicoModelo.getNome());
            modeloLinkedHashMap.put(servicoModelo.getNome(), servicoModelo.getId());
            objecHashMap.put(servicoModelo.getId(), servicoModelo);

        }

    }

    public void selecionarItemPorId(long idProduto) {

        jComboBox.setSelectedItem("");

        /* produto */
        List modeloList = new Repository(new ProdutoModelo()).getResults(popularProdutoJpql().construirSelect());
        for (Object objeto : modeloList) {

            ProdutoModelo produtoModelo = (ProdutoModelo) objeto;

            if (produtoModelo.getId() == idProduto) {

                jComboBox.setSelectedItem(produtoModelo.getNome());
                idItemSelecionado = idProduto;

                if (!isNull(this.command)) {

                    command.executar(idItemSelecionado);

                }
                
            }

        }

        /* servico */
        modeloList = new Repository(new ServicoModelo()).getResults(popularServicoJpql().construirSelect());
        for (Object objeto : modeloList) {

            ServicoModelo servicoModelo = (ServicoModelo) objeto;

            if (servicoModelo.getId() == idProduto) {

                jComboBox.setSelectedItem(servicoModelo.getNome());
                idItemSelecionado = idProduto;

                if (!isNull(this.command)) {

                    command.executar(idItemSelecionado);

                }
            }

        }

    }

    public long getIdSelecionado() {
        return idItemSelecionado;
    }

    public LinkedHashMap getObjecHashMap() {
        return objecHashMap;
    }

}
