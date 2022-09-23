package br.com.salomaotech.genesys.model.venda;

import br.com.salomaotech.genesys.model.empresa.EmpresaModelo;
import br.com.salomaotech.genesys.model.empresa.EmpresaPesquisa;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import br.com.salomaotech.sistema.algoritmos.Datas;
import br.com.salomaotech.sistema.algoritmos.ExecutaProgramaExterno;
import br.com.salomaotech.sistema.algoritmos.GerarPdf;
import br.com.salomaotech.sistema.jpa.Repository;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

public class VendaComprovantePdf {

    private GerarPdf gerarPdf;
    private final String pathDestino;
    private final long idVenda;

    public VendaComprovantePdf(String pathDestino, long idVenda) {

        this.pathDestino = pathDestino + "venda_" + idVenda;
        this.idVenda = idVenda;

    }

    public boolean gerar() {

        /* modelos auxiliares */
        VendaModelo vendaModelo = (VendaModelo) new Repository(new VendaModelo()).findById(this.idVenda);
        EmpresaModelo empresaModelo = EmpresaPesquisa.getDadosEmpresa();

        /* conversão de dados */
        String dataBr = Datas.calendarParaStringBr(vendaModelo.getData());
        String valorTotalBr = ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString());

        /* adiciona conteúdo ao gerador de PDF */
        gerarPdf = new GerarPdf(this.pathDestino);
        gerarPdf.addConteudo("");
        gerarPdf.addConteudo(empresaModelo.getNome());
        gerarPdf.addConteudo("");
        gerarPdf.addConteudo("==================================================");
        gerarPdf.addConteudo("Data: " + Datas.calendarParaStringBr(vendaModelo.getData()));
        gerarPdf.addConteudo("Hora: ");
        gerarPdf.addConteudo("Cliente: Teste");
        gerarPdf.addConteudo("Codigo: " + vendaModelo.getId());
        gerarPdf.addConteudo("==================================================");
        gerarPdf.addConteudo("=====================PRODUTOS=====================");
        gerarPdf.addConteudo("Nome          Qtd.   Vr. unt.   Desc.     Subtotal");
        gerarPdf.addConteudo("--------------------------------------------------");

        for (VendaModeloItem vendaModeloItem : vendaModelo.getVendaModeloItemList()) {

            /* modelos auxiliares */
            ProdutoModelo produtoModelo = (ProdutoModelo) new Repository(new ProdutoModelo()).findById(vendaModeloItem.getIdProduto());

            /* calculos */
            BigDecimal totalProduto = vendaModeloItem.getValor().multiply(vendaModeloItem.getQuantidade()).subtract(vendaModeloItem.getDesconto());

            /* conversão de dados */
            String totalProdutoBr = ConverteNumeroParaMoedaBr.converter(totalProduto.toString());

            gerarPdf.addConteudo(""
                    + produtoModelo.getNome().substring(0, 10) + "."
                    + "   "
                    + BigDecimais.formatarParaBigDecimalComCasaDecimal(vendaModeloItem.getQuantidade().toString(), 2)
                    + "   "
                    + BigDecimais.formatarParaBigDecimalComCasaDecimal(vendaModeloItem.getValor().toString(), 2)
                    + "      "
                    + BigDecimais.formatarParaBigDecimalComCasaDecimal(vendaModeloItem.getDesconto().toString(), 2)
                    + "      "
                    + totalProdutoBr
                    + "");

        }

        gerarPdf.addConteudo("");
        gerarPdf.addConteudo("--------------------------------------------------");
        gerarPdf.addConteudo("Total dos produtos: " + valorTotalBr);
        gerarPdf.addConteudo("==================================================");
        gerarPdf.addConteudo("=====================PAGAMENTO====================");
        gerarPdf.addConteudo("Vencimento    Valor             Forma de pagamento");
        gerarPdf.addConteudo(dataBr + "    " + valorTotalBr + "         " + vendaModelo.getFormaPagamento());
        gerarPdf.addConteudo("");
        gerarPdf.addConteudo("--------------------------------------------------");
        gerarPdf.addConteudo("    *** Este ticket não é documento fiscal ***");

        if (gerarPdf.gerar()) {

            JOptionPane.showMessageDialog(null, "Uma cópia do PDF também foi enviada para a área de impressão.");
            ExecutaProgramaExterno.executarModoDesktop(gerarPdf.getPathDeSaidaDoArquivo());
            return true;

        } else {

            JOptionPane.showMessageDialog(null, "Falha ao gerar comprovante de venda!");
            return false;

        }

    }

}
