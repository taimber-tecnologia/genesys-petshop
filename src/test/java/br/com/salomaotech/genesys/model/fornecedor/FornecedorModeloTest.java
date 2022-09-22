package br.com.salomaotech.genesys.model.fornecedor;

import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class FornecedorModeloTest {

    private final FornecedorModelo modelo = new FornecedorModelo();

    @Test
    public void testGetId() {

        long resultado = modelo.getId();
        System.out.println("Testando FornecedorModelo metodo: getId");
        assertEquals(true, resultado == 0);

    }

    @Test
    public void testSetId() {

        modelo.setId(0);
        System.out.println("Testando FornecedorModelo metodo: setId");
        assertEquals(true, modelo.getId() == 0);

    }

    @Test
    public void testGetNome() {

        String resultado = modelo.getNome();
        System.out.println("Testando FornecedorModelo metodo: getNome");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetNome() {

        String nome = "Teste";
        modelo.setNome(nome);
        System.out.println("Testando FornecedorModelo metodo: setNome");
        assertEquals(true, modelo.getNome().equals(nome));

    }

    @Test
    public void testGetCnpj() {

        String resultado = modelo.getCnpj();
        System.out.println("Testando FornecedorModelo metodo: getCnpj");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetCnpj() {

        String cnpj = "Teste";
        modelo.setCnpj(cnpj);
        System.out.println("Testando FornecedorModelo metodo: setCnpj");
        assertEquals(true, modelo.getCnpj().equals(cnpj));

    }

    @Test
    public void testGetCep() {

        String resultado = modelo.getCep();
        System.out.println("Testando FornecedorModelo metodo: getCep");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetCep() {

        String cep = "75370-000";
        modelo.setCep(cep);
        System.out.println("Testando FornecedorModelo metodo: setCep");
        assertEquals(true, modelo.getCep().equals(cep));

    }

    @Test
    public void testGetRua() {

        String resultado = modelo.getRua();
        System.out.println("Testando FornecedorModelo metodo: getRua");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetRua() {

        String rua = "01";
        modelo.setRua(rua);
        System.out.println("Testando FornecedorModelo metodo: setRua");
        assertEquals(true, modelo.getRua().equals(rua));

    }

    @Test
    public void testGetQuadra() {

        String resultado = modelo.getQuadra();
        System.out.println("Testando FornecedorModelo metodo: getQuadra");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetQuadra() {

        String quadra = "02";
        modelo.setQuadra(quadra);
        System.out.println("Testando FornecedorModelo metodo: setQuadra");
        assertEquals(true, modelo.getQuadra().equals(quadra));

    }

    @Test
    public void testGetLote() {

        String resultado = modelo.getLote();
        System.out.println("Testando FornecedorModelo metodo: getLote");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetLote() {

        String lote = "03";
        modelo.setLote(lote);
        System.out.println("Testando FornecedorModelo metodo: setLote");
        assertEquals(true, modelo.getLote().equals(lote));

    }

    @Test
    public void testGetNumero() {

        String resultado = modelo.getNumero();
        System.out.println("Testando FornecedorModelo metodo: getNumero");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetNumero() {

        String numero = "04";
        modelo.setNumero(numero);
        System.out.println("Testando FornecedorModelo metodo: setNumero");
        assertEquals(true, modelo.getNumero().equals(numero));

    }

    @Test
    public void testGetBairro() {

        String resultado = modelo.getBairro();
        System.out.println("Testando FornecedorModelo metodo: getBairro");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetBairro() {

        String bairro = "Centro";
        modelo.setBairro(bairro);
        System.out.println("Testando FornecedorModelo metodo: setBairro");
        assertEquals(true, modelo.getBairro().equals(bairro));

    }

    @Test
    public void testGetCidade() {

        String resultado = modelo.getCidade();
        System.out.println("Testando FornecedorModelo metodo: getCidade");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetCidade() {

        String cidade = "Goiânia";
        modelo.setCidade(cidade);
        System.out.println("Testando FornecedorModelo metodo: setCidade");
        assertEquals(true, modelo.getCidade().equals(cidade));

    }

    @Test
    public void testGetUf() {

        String resultado = modelo.getUf();
        System.out.println("Testando FornecedorModelo metodo: getUf");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetUf() {

        String uf = "GO";
        modelo.setUf(uf);
        System.out.println("Testando FornecedorModelo metodo: setUf");
        assertEquals(true, modelo.getUf().equals(uf));

    }

    @Test
    public void testGetComplemento() {

        String resultado = modelo.getComplemento();
        System.out.println("Testando FornecedorModelo metodo: getComplemento");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetComplemento() {

        String complemento = "Em frente ao lago";
        modelo.setComplemento(complemento);
        System.out.println("Testando FornecedorModelo metodo: setComplemento");
        assertEquals(true, modelo.getComplemento().equals(complemento));

    }

    @Test
    public void testGetTelefone() {

        String resultado = modelo.getTelefone();
        System.out.println("Testando FornecedorModelo metodo: getTelefone");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetTelefone() {

        String telefone = "62 0000-0000";
        modelo.setTelefone(telefone);
        System.out.println("Testando FornecedorModelo metodo: setTelefone");
        assertEquals(true, modelo.getTelefone().equals(telefone));

    }

    @Test
    public void testGetEmail() {

        String resultado = modelo.getEmail();
        System.out.println("Testando FornecedorModelo metodo: getEmail");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetEmail() {

        String email = "00000001@teste.com";
        modelo.setEmail(email);
        System.out.println("Testando FornecedorModelo metodo: setEmail");
        assertEquals(true, modelo.getEmail().equals(email));

    }

    @Test
    public void testGetContato() {

        String resultado = modelo.getContato();
        System.out.println("Testando FornecedorModelo metodo: getContato");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetContato() {

        String contato = "Teste";
        modelo.setContato(contato);
        System.out.println("Testando FornecedorModelo metodo: setContato");
        assertEquals(true, modelo.getContato().equals(contato));

    }

}
