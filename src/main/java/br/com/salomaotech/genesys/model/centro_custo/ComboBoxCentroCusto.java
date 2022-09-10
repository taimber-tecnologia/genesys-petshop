package br.com.salomaotech.genesys.model.centro_custo;

import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JComboBox;

public class ComboBoxCentroCusto {

    private final JComboBox jComboBox;
    private List<CentroCustoModelo> centroCustoModeloList = new ArrayList();
    private final LinkedHashMap cadastroCentroCustosMap = new LinkedHashMap();
    private long idCentroCustoSelecionado;

    public ComboBoxCentroCusto(JComboBox jComboBox) {

        this.jComboBox = jComboBox;
        this.idCentroCustoSelecionado = 0;
        addEventos();

    }

    private JPQL popularJpql() {

        JPQL jpql = new JPQL(new CentroCustoModelo());
        jpql.addOrderBy("codigo", "ASC");
        jpql.addOrderBy("nome", "ASC");
        return jpql;

    }

    private void addEventos() {

        jComboBox.addActionListener((ActionEvent e) -> {

            try {

                idCentroCustoSelecionado = (long) cadastroCentroCustosMap.get(jComboBox.getSelectedItem());

            } catch (Exception ex) {

                idCentroCustoSelecionado = 0;

            }

        });

    }

    public void preencher() {

        jComboBox.removeAllItems();
        jComboBox.addItem("");

        centroCustoModeloList = new Repository(new CentroCustoModelo()).getResults(popularJpql().construirSelect());
        centroCustoModeloList.forEach(centroCusto -> {

            jComboBox.addItem(centroCusto.getNomeCompleto());
            cadastroCentroCustosMap.put(centroCusto.getNomeCompleto(), centroCusto.getId());

        });

    }

    public void selecionarItemPorId(long idCentroCusto) {

        jComboBox.setSelectedItem("");

        centroCustoModeloList = new Repository(new CentroCustoModelo()).getResults(popularJpql().construirSelect());
        centroCustoModeloList.forEach(centroCusto -> {

            if (centroCusto.getId() == idCentroCusto) {

                jComboBox.setSelectedItem(centroCusto.getNomeCompleto());
                idCentroCustoSelecionado = idCentroCusto;

            }

        });

    }

    public long getIdSelecionado() {
        return idCentroCustoSelecionado;
    }

}
