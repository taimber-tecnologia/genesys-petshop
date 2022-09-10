package br.com.salomaotech.sistema.jpa;

import org.junit.Test;
import static org.junit.Assert.*;

public class RepositoryTest {

    @Test
    public void testSave() {

        /* entidade */
        ModeloDeTeste modelo = new ModeloDeTeste();

        /* salva */
        Repository repository = new Repository(modelo);

        /* testa */
        System.out.println("Testando repository metodo: save");
        assertEquals(true, repository.save() != 0);

    }

    @Test
    public void testGetResults() {

        /* entidade */
        ModeloDeTeste modelo = new ModeloDeTeste();

        /* salva */
        Repository repository = new Repository(modelo);
        int resultadosAntesDeSalvar = repository.getResults(new JPQL(modelo).construirSelect()).size();
        repository.save();
        int resultadosDepoisDeSalvar = repository.getResults(new JPQL(modelo).construirSelect()).size();

        /* testa */
        System.out.println("Testando repository metodo: getResults");
        assertEquals(true, resultadosDepoisDeSalvar > resultadosAntesDeSalvar);

    }

    @Test
    public void testFindById() {

        /* entidade */
        ModeloDeTeste modelo = new ModeloDeTeste();

        /* salva */
        Repository repository = new Repository(modelo);
        repository.save();

        /* testa */
        System.out.println("Testando repository metodo: findById");
        assertEquals(true, repository.findById(modelo.getId()).getId() != 0);

    }

    @Test
    public void testDelete() {

        /* entidade */
        ModeloDeTeste modelo = new ModeloDeTeste();

        /* salva e depois deleta */
        Repository repository = new Repository(modelo);
        repository.save();
        repository.delete(modelo.getId());

        /* testa */
        System.out.println("Testando repository metodo: delete");
        assertEquals(true, repository.findById(modelo.getId()).getId() == 0);

    }

    @Test
    public void testDeleteTodos() {

        /* entidade */
        ModeloDeTeste modelo = new ModeloDeTeste();

        /* salva e depois deleta todos os registros */
        Repository repository = new Repository(modelo);
        repository.save();
        repository.deleteTodos();
        int resultados = repository.getResults(new JPQL(modelo).construirSelect()).size();

        /* testa */
        System.out.println("Testando repository metodo: deleteTodos");
        assertEquals(true, resultados == 0);

    }

    @Test
    public void testCountTodos() {

        /* deleta todos os registros */
        new Repository(new ModeloDeTeste()).deleteTodos();

        /* simula cadastro */
        int i = 0;

        for (i = 0; i <= 10; i++) {

            ModeloDeTeste modelo = new ModeloDeTeste();
            modelo.setNome("Teste " + i);
            new Repository(modelo).save();

        }

        /* esperado todos os registros */
        Repository repository = new Repository(new ModeloDeTeste());
        System.out.println("Testando classe Repository metodo: countTodos etapa 01");
        assertEquals(true, repository.countTodos(null) == i);

        /* esperado todos os registros */
        JPQL jpql = new JPQL(new ModeloDeTeste());
        repository = new Repository(new ModeloDeTeste());
        System.out.println("Testando classe Repository metodo: countTodos etapa 02");
        assertEquals(true, repository.countTodos(jpql.getCondicaoWhere()) == i);

        /* esperado 0 registro */
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroIgual("nome", "Teste " + (i + 1));
        repository = new Repository(new ModeloDeTeste());
        System.out.println("Testando classe Repository metodo: countTodos etapa 03");
        assertEquals(true, repository.countTodos(jpql.getCondicaoWhere()) == 0);

        /* esperado 1 registro */
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroIgual("nome", "Teste " + (i - 1));
        repository = new Repository(new ModeloDeTeste());
        System.out.println("Testando classe Repository metodo: countTodos etapa 04");
        assertEquals(true, repository.countTodos(jpql.getCondicaoWhere()) == 1);

        /* esperado todos os registros */
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroLike("nome", "Teste");
        repository = new Repository(new ModeloDeTeste());
        System.out.println("Testando classe Repository metodo: countTodos etapa 05");
        assertEquals(true, repository.countTodos(jpql.getCondicaoWhere()) == i);

    }

}
