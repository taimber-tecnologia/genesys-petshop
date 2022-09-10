package br.com.salomaotech.genesys.model.produto;

import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.patterns.Command;
import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;
import java.util.List;
import static java.util.Objects.isNull;
import javax.swing.JComboBox;

public class ComboBoxProduto {

    private final JComboBox jComboBox;
    private List<ProdutoModelo> produtoModeloList;
    private final LinkedHashMap produtoMap = new LinkedHashMap();
    private final Command command;
    private long idProdutoSelecionado;

    public ComboBoxProduto(JComboBox jComboBox) {

        this(jComboBox, null);

    }

    public ComboBoxProduto(JComboBox jComboBox, Command command) {

        this.jComboBox = jComboBox;
        this.command = command;
        this.idProdutoSelecionado = 0;
        addEventos();

    }

    private JPQL popularJpql() {

        JPQL jpql = new JPQL(new ProdutoModelo());
        jpql.addOrderBy("nome", "ASC");
        return jpql;

    }

    private void addEventos() {

        jComboBox.addActionListener((ActionEvent e) -> {

            try {

                idProdutoSelecionado = (long) produtoMap.get(jComboBox.getSelectedItem());

            } catch (Exception ex) {

                idProdutoSelecionado = 0;

            }

            if (!isNull(this.command)) {

                command.executar(idProdutoSelecionado);

            }

        });

    }

    public void preencher() {

        jComboBox.removeAllItems();
        jComboBox.addItem("");

        produtoModeloList = new Repository(new ProdutoModelo()).getResults(popularJpql().construirSelect());
        produtoModeloList.forEach(produto -> {

            jComboBox.addItem(produto.getNome());
            produtoMap.put(produto.getNome(), produto.getId());

        });

    }

    public void selecionarItemPorId(long idProduto) {

        jComboBox.setSelectedItem("");

        produtoModeloList = new Repository(new ProdutoModelo()).getResults(popularJpql().construirSelect());
        produtoModeloList.forEach(produto -> {

            if (produto.getId() == idProduto) {

                jComboBox.setSelectedItem(produto.getNome());
                idProdutoSelecionado = idProduto;

                if (!isNull(this.command)) {

                    command.executar(idProdutoSelecionado);

                }

            }

        });

    }

    public long getIdSelecionado() {
        return idProdutoSelecionado;
    }

}
