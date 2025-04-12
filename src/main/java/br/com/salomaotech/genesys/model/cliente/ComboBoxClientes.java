package br.com.salomaotech.genesys.model.cliente;

import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.patterns.Command;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import static java.util.Objects.isNull;
import javax.swing.JComboBox;

public class ComboBoxClientes {

    private final JComboBox jComboBox;
    private List<ClienteModelo> clienteModeloList = new ArrayList();
    private final LinkedHashMap cadastroDeClientesMap = new LinkedHashMap();
    private final Command command;
    private long idClienteSelecionado;

    public ComboBoxClientes(JComboBox jComboBox) {

        this(jComboBox, null);

    }

    public ComboBoxClientes(JComboBox jComboBox, Command command) {

        this.jComboBox = jComboBox;
        this.command = command;
        this.idClienteSelecionado = 0;
        addEventos();

    }

    private JPQL popularJpql() {

        JPQL jpql = new JPQL(new ClienteModelo());
        jpql.addOrderBy("nome", "ASC");
        return jpql;

    }

    private void addEventos() {

        jComboBox.addActionListener((ActionEvent e) -> {

            try {

                idClienteSelecionado = (long) cadastroDeClientesMap.get(jComboBox.getSelectedItem());

            } catch (Exception ex) {

                idClienteSelecionado = 0;

            }

            if (!isNull(this.command)) {

                command.executar(idClienteSelecionado);

            }

        });

    }

    public void preencher() {

        jComboBox.removeAllItems();
        jComboBox.addItem("");

        clienteModeloList = new Repository(new ClienteModelo()).getResults(popularJpql().construirSelect());
        clienteModeloList.forEach(cliente -> {

            String nomeComTelefone = cliente.getNome() + " - " + cliente.getTelefone();
            jComboBox.addItem(nomeComTelefone);
            cadastroDeClientesMap.put(nomeComTelefone, cliente.getId());

        });

    }

    public void selecionarItemPorId(long idCliente) {

        jComboBox.setSelectedItem("");

        clienteModeloList = new Repository(new ClienteModelo()).getResults(popularJpql().construirSelect());
        clienteModeloList.forEach(cliente -> {

            if (cliente.getId() == idCliente) {

                String nomeComTelefone = cliente.getNome() + " - " + cliente.getTelefone();
                jComboBox.setSelectedItem(nomeComTelefone);
                idClienteSelecionado = idCliente;

                if (!isNull(this.command)) {

                    command.executar(idClienteSelecionado);

                }

            }

        });

    }

    public long getIdSelecionado() {
        return idClienteSelecionado;
    }

}
