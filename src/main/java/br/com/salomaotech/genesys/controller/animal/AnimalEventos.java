package br.com.salomaotech.genesys.controller.animal;

import br.com.salomaotech.genesys.controller.cliente.ClienteController;
import br.com.salomaotech.genesys.controller.vacina.VacinaController;
import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import br.com.salomaotech.genesys.model.animal.AnimalPesquisa;
import br.com.salomaotech.genesys.model.animal.ComboBoxAnimalEspecie;
import br.com.salomaotech.genesys.model.animal.ComboBoxAnimalRaca;
import br.com.salomaotech.genesys.model.cliente.ComboBoxClientes;
import br.com.salomaotech.genesys.view.JFanimal;
import br.com.salomaotech.sistema.jpa.Repository;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class AnimalEventos {

    private final JFanimal view;
    private AnimalMetodos animalMetodos;
    private ComboBoxClientes comboBoxClientesCadastro;
    private ComboBoxClientes comboBoxClientesPesquisa;
    private ComboBoxAnimalRaca comboBoxAnimalRaca;
    private ComboBoxAnimalEspecie comboBoxAnimalEspecie;

    public AnimalEventos(JFanimal view) {
        this.view = view;
    }

    public void setAnimalMetodos(AnimalMetodos animalMetodos) {
        this.animalMetodos = animalMetodos;
    }

    public void setComboBoxClientesCadastro(ComboBoxClientes comboBoxClientesCadastro) {
        this.comboBoxClientesCadastro = comboBoxClientesCadastro;
    }

    public void setComboBoxClientesPesquisa(ComboBoxClientes comboBoxClientesPesquisa) {
        this.comboBoxClientesPesquisa = comboBoxClientesPesquisa;
    }

    public void setComboBoxAnimalRaca(ComboBoxAnimalRaca comboBoxAnimalRaca) {
        this.comboBoxAnimalRaca = comboBoxAnimalRaca;
    }

    public void setComboBoxAnimalEspecie(ComboBoxAnimalEspecie comboBoxAnimalEspecie) {
        this.comboBoxAnimalEspecie = comboBoxAnimalEspecie;
    }

    public void addEventos() {

        /* salvar */
        view.jBcadastroSalvar.addActionListener((ActionEvent e) -> {

            AnimalValidador animalValidador = new AnimalValidador(view);

            if (animalValidador.isValido()) {

                AnimalModelo animalModelo = animalMetodos.salvar();

                /* valida se salvou e atualiza os dados na view */
                if (animalModelo.getId() != 0) {

                    animalMetodos.popularFormulario(animalModelo);
                    animalMetodos.habilitarCampos();
                    new AnimalPesquisa(view.jTresultados, view.jCpaginador).pesquisar();
                    JOptionPane.showMessageDialog(null, "Registro salvo com sucesso!");
                    view.jTnomeCadastro.requestFocus();

                } else {

                    JOptionPane.showMessageDialog(null, "Falha ao tentar salvar registro.");

                }

            } else {

                JOptionPane.showMessageDialog(null, animalValidador.getMensagensErro());

            }

        });

        /* excluir */
        view.jBcadastroExcluir.addActionListener((ActionEvent e) -> {

            if (JOptionPane.showConfirmDialog(null, "Excluir registro?") == 0) {

                /* valida se excluiu e atualiza os dados na view */
                if (animalMetodos.excluir()) {

                    animalMetodos.resetarView();
                    animalMetodos.habilitarCampos();
                    new AnimalPesquisa(view.jTresultados, view.jCpaginador).pesquisar();
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

                    animalMetodos.abrirCadastro((long) view.jTresultados.getModel().getValueAt(view.jTresultados.getSelectedRow(), 0));

                }

            }

        });

        /* novo */
        view.jBatalhoCadastro.addActionListener((ActionEvent e) -> {

            animalMetodos.resetarView();
            animalMetodos.habilitarCampos();
            view.jTabaPrincipal.setSelectedIndex(0);

        });

        /* atalho para pesquisa */
        view.jBatalhoPesquisa.addActionListener((ActionEvent e) -> {

            view.jTabaPrincipal.setSelectedIndex(1);

        });

        /* pesquisa */
        view.jBpesquisa.addActionListener((ActionEvent e) -> {

            animalMetodos.pesquisar();

        });

        /* paginador e pesquisa */
        view.jBpaginador.addActionListener((ActionEvent e) -> {

            animalMetodos.pesquisar();

        });

        /* atalho para novo cliente */
        view.jBpesquisaClienteCadastro.addActionListener((ActionEvent e) -> {

            new ClienteController().construir();

        });

        /* atualiza lista de clientes */
        view.jBrefreshClienteCadastro.addActionListener((ActionEvent e) -> {

            comboBoxClientesCadastro.preencher();
            comboBoxClientesPesquisa.preencher();

        });

        /* atualiza lista de clientes no campo de pesquisa */
        view.jBpesquisaNomeClienteRefresh.addActionListener((ActionEvent e) -> {

            comboBoxClientesCadastro.preencher();
            comboBoxClientesPesquisa.preencher();

        });

        /* pesquisa */
        view.jTpesquisaNome.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == 10) {

                    animalMetodos.pesquisar();

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

        /* atalho para vacinas */
        view.jBatalhoVacinas.addActionListener((ActionEvent e) -> {

            AnimalModelo animalModelo = (AnimalModelo) new Repository(new AnimalModelo()).findById(view.getId());

            if (animalModelo.getId() != 0) {

                new VacinaController(animalModelo).construir();

            } else {

                animalMetodos.resetarView();
                animalMetodos.habilitarCampos();
                JOptionPane.showMessageDialog(null, "Registro excluído anteriormente!");

            }

        });

        /* campo para atualizar lista de raças de animais */
        view.jBrefreshRacaCadastro.addActionListener((ActionEvent e) -> {

            comboBoxAnimalRaca.preencher();

        });

        /* campo para atualizar lista de especies de animais */
        view.jBrefreshEspecieCadastro.addActionListener((ActionEvent e) -> {

            comboBoxAnimalEspecie.preencher();

        });

    }

}
