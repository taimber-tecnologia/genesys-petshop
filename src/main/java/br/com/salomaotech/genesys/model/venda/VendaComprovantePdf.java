package br.com.salomaotech.genesys.model.venda;

import br.com.salomaotech.genesys.model.empresa.EmpresaModelo;
import br.com.salomaotech.genesys.model.empresa.EmpresaPesquisa;
import br.com.salomaotech.genesys.model.produto.ProdutoModelo;
import br.com.salomaotech.genesys.model.servico.ServicoModelo;
import br.com.salomaotech.sistema.algoritmos.BigDecimais;
import br.com.salomaotech.sistema.algoritmos.ConverteNumeroParaMoedaBr;
import br.com.salomaotech.sistema.algoritmos.Datas;
import br.com.salomaotech.sistema.algoritmos.ExecutaProgramaExterno;
import br.com.salomaotech.sistema.algoritmos.GerarPdf;
import br.com.salomaotech.sistema.jpa.Repository;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.File;
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

    private PdfPCell createCell(String conteudo, int alinhamento, int fonte) {

        PdfPCell cell = new PdfPCell(new Phrase(conteudo, new Font(Font.FontFamily.COURIER, 8, fonte)));
        cell.setBorderWidth(0);
        cell.setColspan(1);
        cell.setHorizontalAlignment(alinhamento);
        return cell;

    }

    public boolean gerar() {

        /* modelos auxiliares */
        VendaModelo vendaModelo = (VendaModelo) new Repository(new VendaModelo()).findById(idVenda);
        EmpresaModelo empresaModelo = EmpresaPesquisa.getDadosEmpresa();

        /* conversão de dados */
        String dataBr = Datas.calendarParaStringBr(vendaModelo.getData());
        String valorTotalBr = ConverteNumeroParaMoedaBr.converter(vendaModelo.getValor().toString());

        /* adiciona conteúdo ao gerador de PDF */
        gerarPdf = new GerarPdf(pathDestino);
        gerarPdf.addConteudo("");
        gerarPdf.addConteudo("Empresa: " + empresaModelo.getNome());
        gerarPdf.addConteudo("CNPJ: " + empresaModelo.getCnpj());
        gerarPdf.addConteudo("==================================================");
        gerarPdf.addConteudo("Data: " + dataBr);
        gerarPdf.addConteudo("Hora: " + vendaModelo.getHora());
        gerarPdf.addConteudo("Codigo: " + vendaModelo.getId());
        gerarPdf.addConteudo("==================================================");
        gerarPdf.addConteudo("================ PRODUTOS/SERVIÇOS ===============");

        PdfPTable pdfPTable = new PdfPTable(4);

        try {

            /* produtos e serviços */
            pdfPTable.setWidths(new int[]{3, 2, 2, 2});
            pdfPTable.setTotalWidth(250);
            pdfPTable.setLockedWidth(true);
            pdfPTable.setHorizontalAlignment(Element.ALIGN_LEFT);
            pdfPTable.addCell(createCell("Nome", Element.ALIGN_LEFT, Font.BOLD));
            pdfPTable.addCell(createCell("Qtd.", Element.ALIGN_LEFT, Font.BOLD));
            pdfPTable.addCell(createCell("Vr. unt.", Element.ALIGN_LEFT, Font.BOLD));
            pdfPTable.addCell(createCell("Subtotal", Element.ALIGN_LEFT, Font.BOLD));

            for (VendaModeloItem vendaModeloItem : vendaModelo.getVendaModeloItemList()) {

                /* calculos */
                BigDecimal totalProduto = vendaModeloItem.getValor().multiply(vendaModeloItem.getQuantidade());

                /* conversão de dados */
                String nome = "";
                String quantidade = BigDecimais.formatarParaBigDecimalComCasaDecimal(vendaModeloItem.getQuantidade().toString(), 2).toString();
                String valorUnidade = BigDecimais.formatarParaBigDecimalComCasaDecimal(vendaModeloItem.getValor().toString(), 2).toString();
                String total = BigDecimais.formatarParaBigDecimalComCasaDecimal(totalProduto.toString(), 2).toString();

                /* valida se é um produto */
                if (vendaModeloItem.getIdProduto() != 0) {

                    ProdutoModelo produtoModelo = (ProdutoModelo) new Repository(new ProdutoModelo()).findById(vendaModeloItem.getIdProduto());
                    nome = produtoModelo.getNome();

                }

                /* valia se é um serviço */
                if (vendaModeloItem.getIdServico() != 0) {

                    ServicoModelo servicoModelo = (ServicoModelo) new Repository(new ServicoModelo()).findById(vendaModeloItem.getIdServico());
                    nome = servicoModelo.getNome();

                }

                pdfPTable.addCell(createCell(nome, Element.ALIGN_LEFT, Font.NORMAL));
                pdfPTable.addCell(createCell(quantidade, Element.ALIGN_LEFT, Font.NORMAL));
                pdfPTable.addCell(createCell(valorUnidade, Element.ALIGN_LEFT, Font.NORMAL));
                pdfPTable.addCell(createCell(total, Element.ALIGN_LEFT, Font.NORMAL));

            }

        } catch (DocumentException ex) {

        }

        gerarPdf.addConteudo(pdfPTable);
        gerarPdf.addConteudo("");
        gerarPdf.addConteudo("--------------------------------------------------");
        gerarPdf.addConteudo("Total em itens: " + valorTotalBr);
        gerarPdf.addConteudo("==================================================");
        gerarPdf.addConteudo("==================== PAGAMENTO ===================");

        /* forma de pagamento */
        pdfPTable = new PdfPTable(3);
        pdfPTable.setTotalWidth(250);
        pdfPTable.setLockedWidth(true);
        pdfPTable.setHorizontalAlignment(Element.ALIGN_LEFT);
        pdfPTable.addCell(createCell("Data", Element.ALIGN_LEFT, Font.BOLD));
        pdfPTable.addCell(createCell("Total", Element.ALIGN_LEFT, Font.BOLD));
        pdfPTable.addCell(createCell("Forma", Element.ALIGN_LEFT, Font.BOLD));
        pdfPTable.addCell(createCell(dataBr, Element.ALIGN_LEFT, Font.NORMAL));
        pdfPTable.addCell(createCell(valorTotalBr, Element.ALIGN_LEFT, Font.NORMAL));
        pdfPTable.addCell(createCell(vendaModelo.getFormaPagamento(), Element.ALIGN_LEFT, Font.NORMAL));
        gerarPdf.addConteudo(pdfPTable);

        /* rodapé */
        gerarPdf.addConteudo("");
        gerarPdf.addConteudo("--------------------------------------------------");
        gerarPdf.addConteudo("    *** Este ticket não é documento fiscal ***");

        if (gerarPdf.gerar()) {

            ExecutaProgramaExterno.executarModoDesktop(gerarPdf.getPathDeSaidaDoArquivo());
            return true;

        } else {

            JOptionPane.showMessageDialog(null, "Falha ao gerar comprovante de venda!");
            return false;

        }

    }

    public boolean excluir() {

        try {

            return new File(pathDestino + ".pdf").delete();

        } catch (Exception ex) {

            return false;

        }

    }

}
