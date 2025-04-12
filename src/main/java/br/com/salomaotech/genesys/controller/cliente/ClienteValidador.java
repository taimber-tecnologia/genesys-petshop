package br.com.salomaotech.genesys.controller.cliente;

import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.view.JFcliente;
import br.com.salomaotech.sistema.algoritmos.Datas;
import br.com.salomaotech.sistema.algoritmos.ValidaCpf;
import br.com.salomaotech.sistema.algoritmos.ValidaStringIsEmpty;
import br.com.salomaotech.sistema.jpa.JPQL;
import br.com.salomaotech.sistema.jpa.Repository;
import java.util.List;
import static java.util.Objects.isNull;

public class ClienteValidador {

    private final JFcliente view;
    private final long id;
    private String mensagensErro = "";

    public ClienteValidador(JFcliente view, long id) {
        this.view = view;
        this.id = id;
    }

    public boolean isValido() {

        Repository repository = new Repository(new ClienteModelo());

        /* valida nome */
        if (ValidaStringIsEmpty.isEmpty(view.jTbasicoNome.getText())) {

            mensagensErro = "Informe o nome do cliente.";
            view.jTbasicoNome.requestFocus();
            return false;

        }

        /* só validar o CPF se ele for informado */
        if (!view.jFbasicoCpf.getText().equals("   .   .   -  ")) {

            /* valida CPF */
            if (!ValidaCpf.isValido(view.jFbasicoCpf.getText())) {

                mensagensErro = "CPF inválido.";
                view.jFbasicoCpf.requestFocus();
                return false;

            } else {

                JPQL jpql = new JPQL(new ClienteModelo());
                jpql.addParametroIgual("cpf", view.jFbasicoCpf.getText());
                jpql.addParametroDiferente("id", id);
                List resultados = repository.getResults(jpql.construirSelect());

                if (!resultados.isEmpty()) {

                    mensagensErro = "CPF já em uso.";
                    view.jFbasicoCpf.requestFocus();
                    return false;

                }

            }

        }

        /* só validar a data de nascimento se ela for informada */
        if (!isNull(view.jDbasicoDataNascimento.getCalendar())) {

            /* valida data de nascimento */
            if (!Datas.isCalendarioValido(view.jDbasicoDataNascimento.getCalendar())) {

                mensagensErro = "Data de nascimento inválida.";
                return false;

            }

        }

        /* valida telefone */
        if (ValidaStringIsEmpty.isEmpty(view.jTcontatoTelefone.getText())) {

            mensagensErro = "Telefone inválido.";
            view.jTabaCadastro.setSelectedIndex(2);
            view.jTcontatoTelefone.requestFocus();
            return false;

        } else {

            JPQL jpql = new JPQL(new ClienteModelo());
            jpql.addParametroIgual("nome", view.jTbasicoNome.getText());
            jpql.addParametroIgual("telefone", view.jTcontatoTelefone.getText());
            jpql.addParametroDiferente("id", id);
            List resultados = repository.getResults(jpql.construirSelect());

            if (!resultados.isEmpty()) {

                mensagensErro = "Já existe um cadastro com este nome e telefone.";
                view.jTabaCadastro.setSelectedIndex(2);
                view.jTcontatoTelefone.requestFocus();
                return false;

            }

        }

        return true;

    }

    public String getMensagensErro() {
        return mensagensErro;
    }

}
