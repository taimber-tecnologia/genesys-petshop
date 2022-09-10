package br.com.salomaotech.genesys.model.venda.cliente;

import br.com.salomaotech.genesys.model.animal.AnimalModelo;
import br.com.salomaotech.genesys.model.empresa.EmpresaModelo;
import br.com.salomaotech.genesys.model.empresa.EmpresaPesquisa;
import br.com.salomaotech.genesys.model.empresa.ImagemEmpresa;
import br.com.salomaotech.genesys.model.cliente.ClienteModelo;
import br.com.salomaotech.sistema.algoritmos.Datas;
import br.com.salomaotech.sistema.algoritmos.ExecutaProgramaExterno;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import br.com.salomaotech.sistema.algoritmos.GerarPdf;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

public class VendaComprovantePdf {

    private GerarPdf gerarPdf;
    private final String pathDestino;
    private String titulo;
    private final long idVenda;

    public VendaComprovantePdf(String pathDestino, long idVenda) {

        this.pathDestino = pathDestino + "venda_" + idVenda;
        this.titulo = "Venda Nº " + idVenda;
        this.idVenda = idVenda;

    }

    public boolean gerar() {

        /* modelos auxiliares */
        VendaModelo vendaModelo = (VendaModelo) new Repository(new VendaModelo()).findById(this.idVenda);
        ClienteModelo clienteModelo = (ClienteModelo) new Repository(new ClienteModelo()).findById(vendaModelo.getIdCliente());
        EmpresaModelo empresaModelo = EmpresaPesquisa.getDadosEmpresa();
        AnimalModelo animalModelo = (AnimalModelo) new Repository(new AnimalModelo()).findById(vendaModelo.getIdAnimal());

        /* calculos */
        BigDecimal valorTotal = vendaModelo.getValorTotal();

        /* adiciona conteúdo ao gerador de PDF */
        gerarPdf = new GerarPdf(this.pathDestino, ImagemEmpresa.getPathLogotipo());
        gerarPdf.addConteudo("Dados da empresa ----------------------------------------");
        gerarPdf.addConteudo("Nome: " + empresaModelo.getNome());
        gerarPdf.addConteudo("Telefone: " + empresaModelo.getTelefone());
        gerarPdf.addConteudo("E-mail: " + empresaModelo.getEmail());
        gerarPdf.addConteudo("CNPJ: " + empresaModelo.getCnpj());
        gerarPdf.addConteudo(" ");
        gerarPdf.addConteudo("Dados do tutor ------------------------------------------");
        gerarPdf.addConteudo("Nome: " + clienteModelo.getNome());
        gerarPdf.addConteudo("Telefone: " + clienteModelo.getTelefone());
        gerarPdf.addConteudo(" ");
        gerarPdf.addConteudo("Dados do animal -----------------------------------------");
        gerarPdf.addConteudo("Nome: " + animalModelo.getNome());
        gerarPdf.addConteudo("Espécie: " + animalModelo.getEspecie());
        gerarPdf.addConteudo("Raça: " + animalModelo.getRaca());
        gerarPdf.addConteudo("Nascimento: " + Datas.calendarParaStringBr(animalModelo.getNascimento()));
        gerarPdf.addConteudo(" ");
        gerarPdf.addConteudo("Itens ---------------------------------------------------");

        vendaModelo.getVendaModeloItemList().stream().map(vendaModeloItem -> {

            String conteudo = ""
                    + vendaModeloItem.getQuantidade() + " " + vendaModeloItem.getMedida()
                    + " : "
                    + vendaModeloItem.getDescricao()
                    + " "
                    + ConverteNumeroParaMoedaBr.converter(vendaModeloItem.getQuantidade().multiply(vendaModeloItem.getValor()).toString())
                    + "";

            return conteudo;

        }).forEachOrdered(conteudo -> {
            gerarPdf.addConteudo(conteudo);
        });

        gerarPdf.addConteudo(" ");
        gerarPdf.addConteudo("Resumo --------------------------------------------------");
        gerarPdf.addConteudo("Total em itens R$: " + ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString()));
        gerarPdf.addConteudo("Desconto R$: " + ConverteNumeroParaMoedaBr.converter(vendaModelo.getDesconto().toString()));
        gerarPdf.addConteudo("Forma de pagamento: " + vendaModelo.getFormaPagamento());
        gerarPdf.addConteudo("Juros : " + vendaModelo.getJuros() + "%");
        gerarPdf.addConteudo("Parcelas: " + vendaModelo.getNumeroParcelas() + "x");
        gerarPdf.addConteudo("Total a ser pago R$: " + ConverteNumeroParaMoedaBr.converter(valorTotal.toString()));

        /* atualiza o título */
        titulo += "\n";
        titulo += "Valor " + ConverteNumeroParaMoedaBr.converter(valorTotal.toString());
        titulo += "\n";

        /* gera o pdf, se conseguir gerar então abrir o arquivo */
        if (gerarPdf.gerar(titulo)) {

            JOptionPane.showMessageDialog(null, "Uma cópia do PDF também foi enviada para a área de impressão.");
            ExecutaProgramaExterno.executarModoDesktop(gerarPdf.getPathDeSaidaDoArquivo());
            return true;

        } else {

            JOptionPane.showMessageDialog(null, "Falha ao gerar comprovante de venda!");
            return false;

        }

    }

}
