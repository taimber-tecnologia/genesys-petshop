package br.com.salomaotech.sistema.algoritmos;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerarPdf {

    private final String pathDeSaidaDoArquivo;
    private String pathLocalDaImagemDeLogoTipo;
    private final List conteudoDoCorpoDoDocumento = new ArrayList();
    private final Document documento = new Document();

    /**
     * Construtor
     *
     * @param pathDeSaidaDoArquivo Endereço path de saída do arquivo PDF
     * @param pathLocalDaImagemDeLogoTipo Path local da imagem de logotipo
     */
    public GerarPdf(String pathDeSaidaDoArquivo, String pathLocalDaImagemDeLogoTipo) {

        this.pathDeSaidaDoArquivo = pathDeSaidaDoArquivo + ".pdf";
        this.pathLocalDaImagemDeLogoTipo = pathLocalDaImagemDeLogoTipo;

        if (ValidaStringIsEmpty.isEmpty(pathLocalDaImagemDeLogoTipo)) {

            this.pathLocalDaImagemDeLogoTipo = getClass().getResource("/icones/animal128x.png").toString();

        }

    }

    /**
     * Retorna o path de destino de saida do arquivo PDF
     *
     * @return Path de destino do arquivo PDF
     */
    public String getPathDeSaidaDoArquivo() {

        return pathDeSaidaDoArquivo;

    }

    /**
     * Adiciona linha de conteúdo do corpo do documento
     *
     * @param conteudo Conteúdo do corpo do documento
     */
    public void addConteudo(String conteudo) {

        this.conteudoDoCorpoDoDocumento.add(conteudo);

    }

    /**
     * Gera o documento em PDF
     *
     * @param titulo Título do topo
     * @return true se conseguiu gerar o arquivo de destino
     */
    public boolean gerar(String titulo) {

        try {

            /* cria a pasta do arquivo caso não exista */
            CriaPastaLocal.criar(new File(this.pathDeSaidaDoArquivo).getParent());

            /* abre o documento */
            PdfWriter.getInstance(documento, new FileOutputStream(pathDeSaidaDoArquivo));
            documento.open();

            /* adiciona o logotipo do topo */
            Image image = Image.getInstance(pathLocalDaImagemDeLogoTipo);
            image.scaleToFit(75, 76);
            this.documento.add(image);

            /* adiciona o título do documento no topo */
            this.documento.add(new Paragraph(titulo, new Font(Font.FontFamily.COURIER, 14, Font.BOLD)));
            this.documento.add(new Paragraph("\n"));

            /* adiciona as linhas de conteúdo do corpo do documento */
            this.conteudoDoCorpoDoDocumento.forEach(linha -> {

                try {

                    this.documento.add(new Paragraph((String) linha, new Font(Font.FontFamily.COURIER, 8, Font.NORMAL)));

                } catch (DocumentException ex) {

                }

            });

            documento.close();
            return new File(pathDeSaidaDoArquivo).exists();

        } catch (DocumentException | IOException | java.lang.NullPointerException ex) {

            return false;

        }

    }

}
