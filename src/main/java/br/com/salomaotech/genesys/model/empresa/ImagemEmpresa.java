package br.com.salomaotech.genesys.model.empresa;

import br.com.salomaotech.genesys.model.configuracoes.PastasEmpresa;
import br.com.salomaotech.sistema.algoritmos.ImagemExibe;
import br.com.salomaotech.sistema.algoritmos.ImagemRedimensiona;
import br.com.salomaotech.sistema.algoritmos.CopiaArquivosComDialogo;
import br.com.salomaotech.sistema.algoritmos.CopiarArquivosSemDialogo;
import br.com.salomaotech.sistema.algoritmos.RemovePastaDeArquivos;
import com.itextpdf.text.Image;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImagemEmpresa {

    public static boolean upload(String idEmpresa) {

        String pathDestino = new PastasEmpresa(idEmpresa).getSubPastaDeFotosDoPerfil();
        String pathTemporario = new PastasEmpresa(idEmpresa).getSubPastaDeTemporarios();

        /* faz o backup dos arquivos antigos e remove a pasta original */
        CopiarArquivosSemDialogo.copiar(pathDestino, pathTemporario);
        RemovePastaDeArquivos.remover(pathDestino);

        /* copiador de arquivos com diálogo */
        CopiaArquivosComDialogo copiaArquivosComDialogo = new CopiaArquivosComDialogo(pathDestino, false);
        copiaArquivosComDialogo.setFiltro(new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png"));

        /* copia os arquivos para a pasta de destino */
        if (copiaArquivosComDialogo.copiar()) {

            /* remove o backup antigo */
            RemovePastaDeArquivos.remover(pathTemporario);

            /* redimensiona a imagem copiada */
            File arquivo = new File(copiaArquivosComDialogo.getListaDeArquivosCopiados().get(0));
            ImagemRedimensiona imagemRedimensiona = new ImagemRedimensiona(arquivo.getAbsolutePath(), arquivo.getAbsolutePath(), 192);
            imagemRedimensiona.redimensionar();
            return arquivo.exists();

        } else {

            /* restaura os arquivos originais em caso de falha */
            CopiarArquivosSemDialogo.copiar(pathTemporario, pathDestino);
            RemovePastaDeArquivos.remover(pathTemporario);
            return false;

        }

    }

    public static boolean exibir(String idEmpresa, JPanel painel) {

        try {

            String pathDestino = new PastasEmpresa(idEmpresa).getSubPastaDeFotosDoPerfil();
            ImagemExibe imagemExibe = new ImagemExibe(painel, painel.getWidth() - 12, painel.getHeight() - 12);
            return imagemExibe.exibir(new File(pathDestino).listFiles()[0].getAbsolutePath());

        } catch (Exception ex) {

            painel.removeAll();
            painel.repaint();
            return false;

        }

    }

    public static boolean remover(String idEmpresa, JPanel jPanel) {

        jPanel.removeAll();
        jPanel.repaint();
        return RemovePastaDeArquivos.remover(new PastasEmpresa(idEmpresa).getSubPastaDeFotosDoPerfil());

    }

    public static Image getImagemLogotipo(String idEmpresa) {

        try {

            String pathDestino = new PastasEmpresa(idEmpresa).getSubPastaDeFotosDoPerfil();
            pathDestino = new File(pathDestino).listFiles()[0].getAbsolutePath();
            Image image = Image.getInstance(pathDestino);
            image.scaleToFit(125, 125);
            return image;

        } catch (Exception ex) {

            return null;

        }

    }

}
