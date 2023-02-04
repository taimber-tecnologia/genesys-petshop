package br.com.salomaotech.genesys.controller.vacina;

import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import br.com.salomaotech.genesys.model.vacina.ComboBoxVacinas;
import br.com.salomaotech.genesys.model.vacina.VacinaModelo;
import br.com.salomaotech.genesys.model.vacina.VacinaPesquisa;
import br.com.salomaotech.genesys.view.JFvacina;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class VacinaEventos {

    private final JFvacina view;
    private VacinaMetodos vacinaMetodos;
    private AnimalModelo animalModelo;
    private ComboBoxVacinas comboBoxVacinas;

    public VacinaEventos(JFvacina view) {
        this.view = view;
    }

    public void setVacinaMetodos(VacinaMetodos vacinaMetodos) {
        this.vacinaMetodos = vacinaMetodos;
    }

    public void setAnimalModelo(AnimalModelo animalModelo) {
        this.animalModelo = animalModelo;
    }

    public void setComboBoxVacinas(ComboBoxVacinas comboBoxVacinas) {
        this.comboBoxVacinas = comboBoxVacinas;
    }

    public void addEventos() {

        /* salvar */
        view.jBcadastroSalvar.addActionListener((ActionEvent e) -> {

            VacinaValidador vacinaValidador = new VacinaValidador(view);

            if (vacinaValidador.isValido()) {

                VacinaModelo vacinaModelo = vacinaMetodos.salvar();

                /* valida se salvou e atualiza os dados na view */
                if (vacinaModelo.getId() != 0) {

                    vacinaMetodos.popularFormulario(vacinaModelo);
                    vacinaMetodos.habilitarCampos();
                    new VacinaPesquisa(view.jTresultados, animalModelo, view.jCpaginador).pesquisar();
                    JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");
                    view.jCcadastroNome.requestFocus();

                } else {

                    JOptionPane.showMessageDialog(null, "Falha ao tentar salvar registro.");

                }

            } else {

                JOptionPane.showMessageDialog(null, vacinaValidador.getMensagensErro());

            }

        });

        /* excluir */
        view.jBcadastroExcluir.addActionListener((ActionEvent e) -> {

            if (JOptionPane.showConfirmDialog(null, "Excluir registro?") == 0) {

                /* valida se excluiu e atualiza os dados na view */
                if (vacinaMetodos.excluir()) {

                    vacinaMetodos.resetarView();
                    vacinaMetodos.habilitarCampos();
                    new VacinaPesquisa(view.jTresultados, animalModelo, view.jCpaginador).pesquisar();
                    JOptionPane.showMessageDialog(null, "Registro excluido!");

                } else {

                    JOptionPane.showMessageDialog(null, "Registro não excluido!");

                }

            }

        });

        /* abrir */
        view.jTresultados.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {

                    vacinaMetodos.abrirCadastro((long) view.jTresultados.getModel().getValueAt(view.jTresultados.getSelectedRow(), 0));

                }

            }

        });

        /* novo */
        view.jBatalhoCadastro.addActionListener((ActionEvent e) -> {

            view.jTabaPrincipal.setSelectedIndex(0);
            vacinaMetodos.resetarView();
            vacinaMetodos.habilitarCampos();

        });

        /* atalho para pesquisa */
        view.jBatalhoPesquisa.addActionListener((ActionEvent e) -> {

            view.jTabaPrincipal.setSelectedIndex(1);
            vacinaMetodos.pesquisar();

        });

        /* atualiza os nomes das vacinas na view */
        view.jBatualizarNomesVacinas.addActionListener((ActionEvent e) -> {

            comboBoxVacinas.preencher();

        });

        /* pesquisa */
        view.jBpaginador.addActionListener((ActionEvent e) -> {

            vacinaMetodos.pesquisar();

        });

    }

}
