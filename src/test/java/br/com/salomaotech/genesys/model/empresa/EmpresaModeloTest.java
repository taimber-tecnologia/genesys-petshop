package br.com.salomaotech.genesys.model.empresa;

import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmpresaModeloTest {

    private final EmpresaModelo empresaModelo = new EmpresaModelo();

    @Test
    public void testGetId() {

        long resultado = empresaModelo.getId();
        System.out.println("Testando EmpresaModelo metodo: getId");
        assertEquals(true, resultado == 0);

    }

    @Test
    public void testSetId() {

        empresaModelo.setId(0);
        System.out.println("Testando EmpresaModelo metodo: setId");
        assertEquals(true, empresaModelo.getId() == 0);

    }

    @Test
    public void testGetNome() {

        String resultado = empresaModelo.getNome();
        System.out.println("Testando EmpresaModelo metodo: getNome");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetNome() {

        String nome = "";
        empresaModelo.setNome(nome);
        System.out.println("Testando EmpresaModelo metodo: setNome");
        assertEquals(true, empresaModelo.getNome().equals(nome));

    }

    @Test
    public void testGetCnpj() {

        String resultado = empresaModelo.getCnpj();
        System.out.println("Testando EmpresaModelo metodo: getCpf");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetCnpj() {

        String cnpj = "";
        empresaModelo.setCnpj(cnpj);
        System.out.println("Testando EmpresaModelo metodo: setCpf");
        assertEquals(true, empresaModelo.getCnpj().equals(cnpj));

    }

    @Test
    public void testGetCep() {

        String resultado = empresaModelo.getCep();
        System.out.println("Testando EmpresaModelo metodo: getCep");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetCep() {

        String cep = "75370-000";
        empresaModelo.setCep(cep);
        System.out.println("Testando EmpresaModelo metodo: setCep");
        assertEquals(true, empresaModelo.getCep().equals(cep));

    }

    @Test
    public void testGetRua() {

        String resultado = empresaModelo.getRua();
        System.out.println("Testando EmpresaModelo metodo: getRua");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetRua() {

        String rua = "01";
        empresaModelo.setRua(rua);
        System.out.println("Testando EmpresaModelo metodo: setRua");
        assertEquals(true, empresaModelo.getRua().equals(rua));

    }

    @Test
    public void testGetQuadra() {

        String resultado = empresaModelo.getQuadra();
        System.out.println("Testando EmpresaModelo metodo: getQuadra");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetQuadra() {

        String quadra = "02";
        empresaModelo.setQuadra(quadra);
        System.out.println("Testando EmpresaModelo metodo: setQuadra");
        assertEquals(true, empresaModelo.getQuadra().equals(quadra));

    }

    @Test
    public void testGetLote() {

        String resultado = empresaModelo.getLote();
        System.out.println("Testando EmpresaModelo metodo: getLote");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetLote() {

        String lote = "03";
        empresaModelo.setLote(lote);
        System.out.println("Testando EmpresaModelo metodo: setLote");
        assertEquals(true, empresaModelo.getLote().equals(lote));

    }

    @Test
    public void testGetNumero() {

        String resultado = empresaModelo.getNumero();
        System.out.println("Testando EmpresaModelo metodo: getNumero");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetNumero() {

        String numero = "04";
        empresaModelo.setNumero(numero);
        System.out.println("Testando EmpresaModelo metodo: setNumero");
        assertEquals(true, empresaModelo.getNumero().equals(numero));

    }

    @Test
    public void testGetBairro() {

        String resultado = empresaModelo.getBairro();
        System.out.println("Testando EmpresaModelo metodo: getBairro");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetBairro() {

        String bairro = "Centro";
        empresaModelo.setBairro(bairro);
        System.out.println("Testando EmpresaModelo metodo: setBairro");
        assertEquals(true, empresaModelo.getBairro().equals(bairro));

    }

    @Test
    public void testGetCidade() {

        String resultado = empresaModelo.getCidade();
        System.out.println("Testando EmpresaModelo metodo: getCidade");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetCidade() {

        String cidade = "Goiânia";
        empresaModelo.setCidade(cidade);
        System.out.println("Testando EmpresaModelo metodo: setCidade");
        assertEquals(true, empresaModelo.getCidade().equals(cidade));

    }

    @Test
    public void testGetUf() {

        String resultado = empresaModelo.getUf();
        System.out.println("Testando EmpresaModelo metodo: getUf");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetUf() {

        String uf = "GO";
        empresaModelo.setUf(uf);
        System.out.println("Testando EmpresaModelo metodo: setUf");
        assertEquals(true, empresaModelo.getUf().equals(uf));

    }

    @Test
    public void testGetComplemento() {

        String resultado = empresaModelo.getComplemento();
        System.out.println("Testando EmpresaModelo metodo: getComplemento");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetComplemento() {

        String complemento = "Em frente ao lago";
        empresaModelo.setComplemento(complemento);
        System.out.println("Testando EmpresaModelo metodo: setComplemento");
        assertEquals(true, empresaModelo.getComplemento().equals(complemento));

    }

    @Test
    public void testGetTelefone() {

        String resultado = empresaModelo.getTelefone();
        System.out.println("Testando EmpresaModelo metodo: getTelefone");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetTelefone() {

        String telefone = "62 0000-0000";
        empresaModelo.setTelefone(telefone);
        System.out.println("Testando EmpresaModelo metodo: setTelefone");
        assertEquals(true, empresaModelo.getTelefone().equals(telefone));

    }

    @Test
    public void testGetEmail() {

        String resultado = empresaModelo.getEmail();
        System.out.println("Testando EmpresaModelo metodo: getEmail");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetEmail() {

        String email = "00000001@teste.com";
        empresaModelo.setEmail(email);
        System.out.println("Testando EmpresaModelo metodo: setEmail");
        assertEquals(true, empresaModelo.getEmail().equals(email));

    }

}
