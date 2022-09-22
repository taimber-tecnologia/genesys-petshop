package br.com.salomaotech.genesys.model.cliente;

import java.util.Calendar;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteModeloTest {

    private final Calendar calendar = Calendar.getInstance();
    private final ClienteModelo modelo = new ClienteModelo();

    @Test
    public void testGetId() {

        long resultado = modelo.getId();
        System.out.println("Testando ClienteModelo metodo: getId");
        assertEquals(true, resultado == 0);

    }

    @Test
    public void testSetId() {

        modelo.setId(0);
        System.out.println("Testando ClienteModelo metodo: setId");
        assertEquals(true, modelo.getId() == 0);

    }

    @Test
    public void testGetNome() {

        String resultado = modelo.getNome();
        System.out.println("Testando ClienteModelo metodo: getNome");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetNome() {

        String nome = "Teste";
        modelo.setNome(nome);
        System.out.println("Testando ClienteModelo metodo: setNome");
        assertEquals(true, modelo.getNome().equals(nome));

    }

    @Test
    public void testGetCpf() {

        String resultado = modelo.getCpf();
        System.out.println("Testando ClienteModelo metodo: getCpf");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetCpf() {

        String cpf = "Teste";
        modelo.setCpf(cpf);
        System.out.println("Testando ClienteModelo metodo: setCpf");
        assertEquals(true, modelo.getCpf().equals(cpf));

    }

    @Test
    public void testGetSexo() {

        String resultado = modelo.getSexo();
        System.out.println("Testando ClienteModelo metodo: getSexo");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetSexo() {

        String sexo = "Teste";
        modelo.setSexo(sexo);
        System.out.println("Testando ClienteModelo metodo: setSexo");
        assertEquals(true, modelo.getSexo().equals(sexo));

    }

    @Test
    public void testGetNascimento() {

        Calendar resultado = modelo.getNascimento();
        System.out.println("Testando ClienteModelo metodo: getNascimento");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetNascimento() {

        modelo.setNascimento(calendar);
        System.out.println("Testando ClienteModelo metodo: setNascimento");
        assertEquals(true, modelo.getNascimento().equals(calendar));

    }

    @Test
    public void testGetNacionalidade() {

        String resultado = modelo.getNacionalidade();
        System.out.println("Testando ClienteModelo metodo: getNacionalidade");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetNacionalidade() {

        String nacionalidade = "Teste";
        modelo.setNacionalidade(nacionalidade);
        System.out.println("Testando ClienteModelo metodo: setNacionalidade");
        assertEquals(true, modelo.getNacionalidade().equals(nacionalidade));

    }

    @Test
    public void testGetNomePai() {

        String resultado = modelo.getNomePai();
        System.out.println("Testando ClienteModelo metodo: getNomePai");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetNomePai() {

        String nomePai = "Teste";
        modelo.setNomePai(nomePai);
        System.out.println("Testando ClienteModelo metodo: getNomePai");
        assertEquals(true, modelo.getNomePai().equals(nomePai));

    }

    @Test
    public void testGetNomeMae() {

        String resultado = modelo.getNomeMae();
        System.out.println("Testando ClienteModelo metodo: getNomeMae");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetNomeMae() {

        String nomeMae = "Teste";
        modelo.setNomeMae(nomeMae);
        System.out.println("Testando ClienteModelo metodo: getNomeMae");
        assertEquals(true, modelo.getNomeMae().equals(nomeMae));

    }

    @Test
    public void testGetCep() {

        String resultado = modelo.getCep();
        System.out.println("Testando ClienteModelo metodo: getCep");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetCep() {

        String cep = "75370-000";
        modelo.setCep(cep);
        System.out.println("Testando ClienteModelo metodo: setCep");
        assertEquals(true, modelo.getCep().equals(cep));

    }

    @Test
    public void testGetRua() {

        String resultado = modelo.getRua();
        System.out.println("Testando ClienteModelo metodo: getRua");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetRua() {

        String rua = "01";
        modelo.setRua(rua);
        System.out.println("Testando ClienteModelo metodo: setRua");
        assertEquals(true, modelo.getRua().equals(rua));

    }

    @Test
    public void testGetQuadra() {

        String resultado = modelo.getQuadra();
        System.out.println("Testando ClienteModelo metodo: getQuadra");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetQuadra() {

        String quadra = "02";
        modelo.setQuadra(quadra);
        System.out.println("Testando ClienteModelo metodo: setQuadra");
        assertEquals(true, modelo.getQuadra().equals(quadra));

    }

    @Test
    public void testGetLote() {

        String resultado = modelo.getLote();
        System.out.println("Testando ClienteModelo metodo: getLote");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetLote() {

        String lote = "03";
        modelo.setLote(lote);
        System.out.println("Testando ClienteModelo metodo: setLote");
        assertEquals(true, modelo.getLote().equals(lote));

    }

    @Test
    public void testGetNumero() {

        String resultado = modelo.getNumero();
        System.out.println("Testando ClienteModelo metodo: getNumero");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetNumero() {

        String numero = "04";
        modelo.setNumero(numero);
        System.out.println("Testando ClienteModelo metodo: setNumero");
        assertEquals(true, modelo.getNumero().equals(numero));

    }

    @Test
    public void testGetBairro() {

        String resultado = modelo.getBairro();
        System.out.println("Testando ClienteModelo metodo: getBairro");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetBairro() {

        String bairro = "Centro";
        modelo.setBairro(bairro);
        System.out.println("Testando ClienteModelo metodo: setBairro");
        assertEquals(true, modelo.getBairro().equals(bairro));

    }

    @Test
    public void testGetCidade() {

        String resultado = modelo.getCidade();
        System.out.println("Testando ClienteModelo metodo: getCidade");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetCidade() {

        String cidade = "Goiânia";
        modelo.setCidade(cidade);
        System.out.println("Testando ClienteModelo metodo: setCidade");
        assertEquals(true, modelo.getCidade().equals(cidade));

    }

    @Test
    public void testGetUf() {

        String resultado = modelo.getUf();
        System.out.println("Testando ClienteModelo metodo: getUf");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetUf() {

        String uf = "GO";
        modelo.setUf(uf);
        System.out.println("Testando ClienteModelo metodo: setUf");
        assertEquals(true, modelo.getUf().equals(uf));

    }

    @Test
    public void testGetComplemento() {

        String resultado = modelo.getComplemento();
        System.out.println("Testando ClienteModelo metodo: getComplemento");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetComplemento() {

        String complemento = "Em frente ao lago";
        modelo.setComplemento(complemento);
        System.out.println("Testando ClienteModelo metodo: setComplemento");
        assertEquals(true, modelo.getComplemento().equals(complemento));

    }

    @Test
    public void testGetTelefone() {

        String resultado = modelo.getTelefone();
        System.out.println("Testando ClienteModelo metodo: getTelefone");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetTelefone() {

        String telefone = "62 0000-0000";
        modelo.setTelefone(telefone);
        System.out.println("Testando ClienteModelo metodo: setTelefone");
        assertEquals(true, modelo.getTelefone().equals(telefone));

    }

    @Test
    public void testGetEmail() {

        String resultado = modelo.getEmail();
        System.out.println("Testando ClienteModelo metodo: getEmail");
        assertEquals(true, isNull(resultado));

    }

    @Test
    public void testSetEmail() {

        String email = "00000001@teste.com";
        modelo.setEmail(email);
        System.out.println("Testando ClienteModelo metodo: setEmail");
        assertEquals(true, modelo.getEmail().equals(email));

    }

}
