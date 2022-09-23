package br.com.salomaotech.sistema.algoritmos;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerarPdf {

    private final String pathDeSaidaDoArquivo;
    private final List conteudoDoCorpoDoDocumento = new ArrayList();
    private final Document documento = new Document();

    public GerarPdf(String pathDeSaidaDoArquivo) {

        this.pathDeSaidaDoArquivo = pathDeSaidaDoArquivo + ".pdf";

    }

    public String getPathDeSaidaDoArquivo() {

        return pathDeSaidaDoArquivo;

    }

    public void addConteudo(String conteudo) {

        this.conteudoDoCorpoDoDocumento.add(conteudo);

    }

    public boolean gerar() {

        try {

            /* cria a pasta do arquivo caso não exista */
            CriaPastaLocal.criar(new File(this.pathDeSaidaDoArquivo).getParent());

            /* abre o documento */
            PdfWriter.getInstance(documento, new FileOutputStream(pathDeSaidaDoArquivo));
            documento.open();

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
