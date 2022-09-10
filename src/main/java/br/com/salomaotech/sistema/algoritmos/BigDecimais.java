package br.com.salomaotech.sistema.algoritmos;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimais {

    /**
     * Formata String para BigDecimal
     *
     * @param numero Número em String
     * @return BigDecimal
     */
    public static BigDecimal formatarParaBigDecimal(String numero) {

        try {

            return new BigDecimal(numero.replace(",", "."));

        } catch (Exception ex) {

            return new BigDecimal("0");

        }

    }

    /**
     * Divide um número exemplo 1000/3 = 333.33
     *
     * @param valor Valor a ser dividido
     * @param divisor Divisor
     * @return Retorna uma divisão com 02 casas decimais
     */
    public static BigDecimal dividir(BigDecimal valor, BigDecimal divisor) {

        try {

            return valor.divide(divisor, 2, RoundingMode.FLOOR);

        } catch (Exception ex) {

            return new BigDecimal("0");

        }

    }

    /**
     * Retorna se um BigDecimal é negativo
     *
     * @param valor Valor a ser analizado
     * @return true é negativo
     */
    public static boolean isNegativo(BigDecimal valor) {

        return valor.signum() == -1;

    }

}
