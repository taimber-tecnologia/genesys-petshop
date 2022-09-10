package br.com.salomaotech.genesys.model.fornecedor;

import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JComboBox;

public class ComboBoxFornecedores {

    private final JComboBox jComboBox;
    private List<FornecedorModelo> fornecedorModeloList = new ArrayList();
    private final LinkedHashMap cadastroDeFornecedorsMap = new LinkedHashMap();
    private long idFornecedorSelecionado;

    public ComboBoxFornecedores(JComboBox jComboBox) {

        this.jComboBox = jComboBox;
        this.idFornecedorSelecionado = 0;
        addEventos();

    }

    private JPQL popularJpql() {

        JPQL jpql = new JPQL(new FornecedorModelo());
        jpql.addOrderBy("nome", "ASC");
        return jpql;

    }

    private void addEventos() {

        jComboBox.addActionListener((ActionEvent e) -> {

            try {

                idFornecedorSelecionado = (long) cadastroDeFornecedorsMap.get(jComboBox.getSelectedItem());

            } catch (Exception ex) {

                idFornecedorSelecionado = 0;

            }

        });

    }

    public void preencher() {

        jComboBox.removeAllItems();
        jComboBox.addItem("");

        fornecedorModeloList = new Repository(new FornecedorModelo()).getResults(popularJpql().construirSelect());
        fornecedorModeloList.forEach(fornecedor -> {

            jComboBox.addItem(fornecedor.getNome());
            cadastroDeFornecedorsMap.put(fornecedor.getNome(), fornecedor.getId());

        });

    }

    public void selecionarItemPorId(long idFornecedor) {

        jComboBox.setSelectedItem("");

        fornecedorModeloList = new Repository(new FornecedorModelo()).getResults(popularJpql().construirSelect());
        fornecedorModeloList.forEach(fornecedor -> {

            if (fornecedor.getId() == idFornecedor) {

                jComboBox.setSelectedItem(fornecedor.getNome());
                idFornecedorSelecionado = idFornecedor;

            }

        });

    }

    public long getIdSelecionado() {
        return idFornecedorSelecionado;
    }

}
