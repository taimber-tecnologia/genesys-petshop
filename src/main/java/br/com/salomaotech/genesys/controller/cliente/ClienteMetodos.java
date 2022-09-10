package br.com.salomaotech.genesys.controller.cliente;

import br.com.salomaotech.genesys.model.cliente.ImagemCliente;
import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.genesys.model.cliente.ClientePesquisa;
import br.com.salomaotech.genesys.view.JFcliente;
import br.com.salomaotech.sistema.jpa.Repository;
import br.com.salomaotech.sistema.swing.PopUp;

public class ClienteMetodos {

    private final JFcliente view;

    public ClienteMetodos(JFcliente view) {
        this.view = view;
    }

    public void popularFormulario(ClienteModelo clienteModelo) {

        view.setId(clienteModelo.getId());
        view.jTbasicoNome.setText(clienteModelo.getNome());
        view.jFbasicoCpf.setText(clienteModelo.getCpf());
        view.jDbasicoDataNascimento.setCalendar(clienteModelo.getNascimento());
        view.jCbasicoSexo.setSelectedItem(clienteModelo.getSexo());
        view.jTbasicoNomePai.setText(clienteModelo.getNomePai());
        view.jTbasicoNomeMae.setText(clienteModelo.getNomeMae());
        view.jCbasicoNacionalidade.setSelectedItem(clienteModelo.getNacionalidade());
        view.jFenderecoCep.setText(clienteModelo.getCep());
        view.jTenderecoRua.setText(clienteModelo.getRua());
        view.jTenderecoQuadra.setText(clienteModelo.getQuadra());
        view.jTenderecoLote.setText(clienteModelo.getLote());
        view.jTenderecoNumero.setText(clienteModelo.getNumero());
        view.jCenderecoUf.setSelectedItem(clienteModelo.getUf());
        view.jTenderecoBairro.setText(clienteModelo.getBairro());
        view.jTenderecoCidade.setText(clienteModelo.getCidade());
        view.jTenderecoComplemento.setText(clienteModelo.getComplemento());
        view.jTcontatoTelefone.setText(clienteModelo.getTelefone());
        view.jTcontatoEmail.setText(clienteModelo.getEmail());

    }

    public void resetarView() {

        popularFormulario(new ClienteModelo());
        view.jPdadosPerfilFoto.removeAll();
        view.jPdadosPerfilFoto.repaint();
        view.jFbasicoCpf.setValue(null);
        view.jCbasicoSexo.setSelectedIndex(0);
        view.jCenderecoUf.setSelectedIndex(0);
        view.jCbasicoNacionalidade.setSelectedIndex(0);

    }

    public void habilitarCampos() {

        boolean isIdAberto = view.getId() != 0;
        view.jBcadastroExcluir.setEnabled(isIdAberto);
        view.jBadicionaFoto.setEnabled(isIdAberto);
        view.jBremoveFoto.setEnabled(isIdAberto);

    }

    public void addPopUpMenu() {

        PopUp popUp = new PopUp();
        popUp.adicionarMenu(view.jTbasicoNome);
        popUp.adicionarMenu(view.jFbasicoCpf);
        popUp.adicionarMenu(view.jTbasicoNomePai);
        popUp.adicionarMenu(view.jTbasicoNomeMae);
        popUp.adicionarMenu(view.jFenderecoCep);
        popUp.adicionarMenu(view.jTenderecoRua);
        popUp.adicionarMenu(view.jTenderecoQuadra);
        popUp.adicionarMenu(view.jTenderecoLote);
        popUp.adicionarMenu(view.jTenderecoNumero);
        popUp.adicionarMenu(view.jTenderecoBairro);
        popUp.adicionarMenu(view.jTenderecoCidade);
        popUp.adicionarMenu(view.jTenderecoComplemento);
        popUp.adicionarMenu(view.jTcontatoTelefone);
        popUp.adicionarMenu(view.jTcontatoEmail);
        popUp.adicionarMenu(view.jTpesquisaNome);
        popUp.adicionarMenu(view.jTpesquisaCpf);

    }

    public void abrirCadastro(long id) {

        Repository repository = new Repository(new ClienteModelo());
        ClienteModelo clienteModelo = (ClienteModelo) repository.findById(id);
        popularFormulario(clienteModelo);
        new ImagemCliente().exibir(String.valueOf(id), view.jPdadosPerfilFoto);
        habilitarCampos();

    }

    public ClienteModelo salvar() {

        ClienteModelo clienteModelo = new ClienteModelo();
        clienteModelo.setId(view.getId());
        clienteModelo.setNome(view.jTbasicoNome.getText());
        clienteModelo.setCpf(view.jFbasicoCpf.getText());
        clienteModelo.setNascimento(view.jDbasicoDataNascimento.getCalendar());
        clienteModelo.setSexo((String) view.jCbasicoSexo.getSelectedItem());
        clienteModelo.setNomePai(view.jTbasicoNomePai.getText());
        clienteModelo.setNomeMae(view.jTbasicoNomeMae.getText());
        clienteModelo.setNacionalidade((String) view.jCbasicoNacionalidade.getSelectedItem());
        clienteModelo.setCep(view.jFenderecoCep.getText());
        clienteModelo.setRua(view.jTenderecoRua.getText());
        clienteModelo.setQuadra(view.jTenderecoQuadra.getText());
        clienteModelo.setLote(view.jTenderecoLote.getText());
        clienteModelo.setNumero(view.jTenderecoNumero.getText());
        clienteModelo.setUf((String) view.jCenderecoUf.getSelectedItem());
        clienteModelo.setBairro(view.jTenderecoBairro.getText());
        clienteModelo.setCidade(view.jTenderecoCidade.getText());
        clienteModelo.setComplemento(view.jTenderecoComplemento.getText());
        clienteModelo.setTelefone(view.jTcontatoTelefone.getText());
        clienteModelo.setEmail(view.jTcontatoEmail.getText());
        new Repository(clienteModelo).save();
        return clienteModelo;

    }

    public boolean excluir() {

        return new Repository(new ClienteModelo()).delete(view.getId());

    }

    public void pesquisar() {

        ClientePesquisa clientePesquisa = new ClientePesquisa(view.jTresultados);
        clientePesquisa.setNome(view.jTpesquisaNome.getText());
        clientePesquisa.setCpf(view.jTpesquisaCpf.getText());
        clientePesquisa.pesquisar();

    }

}
