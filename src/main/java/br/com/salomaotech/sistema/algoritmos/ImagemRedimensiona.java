package br.com.salomaotech.sistema.algoritmos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;

public class ImagemRedimensiona {

    private final String pathOrigem;
    private final String pathDestino;
    private final int largura;

    /**
     * Construtor
     *
     * @param pathOrigem Path de origem da imagem
     * @param pathDestino Path de destino da imagem
     * @param largura Nova largura da imagem
     */
    public ImagemRedimensiona(String pathOrigem, String pathDestino, int largura) {

        this.pathOrigem = pathOrigem;
        this.pathDestino = pathDestino;
        this.largura = largura;

    }

    /**
     * Redimensiona
     *
     * @return true a imagem conseguiu ser redimensionada
     */
    public boolean redimensionar() {

        try {

            /* arquivo de origem */
            File arquivo = new File(pathOrigem);

            /* valida o nome da imagem */
            if (arquivo.getName().lastIndexOf('.') > 0) {

                /* extensão da imagem */
                String extensao = arquivo.getName().substring(arquivo.getName().lastIndexOf('.') + 1);

                /* grava */
                BufferedImage imagemOriginal = ImageIO.read(arquivo);
                BufferedImage miniatura = Scalr.resize(imagemOriginal, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC, largura, Scalr.OP_ANTIALIAS);
                ImageIO.write(miniatura, extensao, new File(this.pathDestino));

            }

            /* retorna se o arquivo gerado tem a dimensão desejada */
            return ImageIO.read(new File(pathDestino)).getHeight() == largura;

        } catch (IOException ex) {

            return false;

        }

    }

}
