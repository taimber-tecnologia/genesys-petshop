package br.com.salomaotech.genesys.model.produto;

import br.com.salomaotech.sistema.algoritmos.ValidaStringIsEmpty;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.LinkedHashSet;
import java.util.List;
import javax.swing.JComboBox;

public class ComboBoxProdutoCategoria {

    private LinkedHashSet<String> nomesDeCategoriasSet;
    private final JComboBox jComboBox;

    public ComboBoxProdutoCategoria(JComboBox jComboBox) {

        this.jComboBox = jComboBox;

    }

    private JPQL popularJpql() {

        JPQL jpql = new JPQL(new ProdutoModelo());
        jpql.addOrderBy("categoria", "ASC");
        return jpql;

    }

    public void preencher() {

        jComboBox.removeAllItems();
        jComboBox.addItem("");

        List<ProdutoModelo> produtoModeloList = new Repository(new ProdutoModelo()).getResults(popularJpql().construirSelect());
        nomesDeCategoriasSet = new LinkedHashSet();

        produtoModeloList.forEach(produto -> {

            if (!ValidaStringIsEmpty.isEmpty(produto.getCategoria())) {

                nomesDeCategoriasSet.add(produto.getCategoria());

            }

        });

        nomesDeCategoriasSet.forEach(categoria -> {

            jComboBox.addItem(categoria);

        });

    }

}
