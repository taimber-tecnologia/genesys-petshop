package br.com.salomaotech.sistema.jpa;

import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class DaoTest {

    @Test
    public void testCreate() {

        /* entidade */
        ModeloDeTeste agenda = new ModeloDeTeste();
        agenda.setNome("Teste");

        /* cria um novo registro no banco de dados */
        Dao dao = new Dao(ModeloDeTeste.class, "Conexao");
        dao.create(agenda);

        /* testa */
        System.out.println("Testando classe Dao metodo: create");
        assertEquals(true, agenda.getId() != 0);

    }

    @Test
    public void testUpdate() {

        /* entidade */
        ModeloDeTeste agenda = new ModeloDeTeste();
        agenda.setNome("Teste");

        /* cria um novo registro no banco de dados */
        Dao dao = new Dao(ModeloDeTeste.class, "Conexao");
        dao.create(agenda);

        /* atualiza o nome */
        agenda.setNome("Teste 2");

        /* faz a alteração dos dados do registro */
        dao.update(agenda);

        /* pesquisa no banco de dados para ver se encontra o registro já atualizado */
        ModeloDeTeste agendaFind = (ModeloDeTeste) dao.findById(agenda.getId());

        System.out.println("Testando classe Dao metodo: update");
        assertEquals(true, agenda.getNome().equals(agendaFind.getNome()));

    }

    @Test
    public void testDelete() {

        /* entidade */
        ModeloDeTeste agenda = new ModeloDeTeste();
        agenda.setNome("Teste");

        /* cria um novo registro no banco de dados */
        Dao dao = new Dao(ModeloDeTeste.class, "Conexao");
        dao.create(agenda);

        /* deleta o registro */
        dao.delete(agenda.getId());

        System.out.println("Testando classe Dao metodo: delete");
        assertEquals(true, isNull(dao.findById(agenda.getId())));

    }

    @Test
    public void testFindById() {

        /* entidade */
        ModeloDeTeste agenda = new ModeloDeTeste();
        agenda.setNome("Teste");

        /* cria um novo registro no banco de dados */
        Dao dao = new Dao(ModeloDeTeste.class, "Conexao");
        dao.create(agenda);

        System.out.println("Testando classe Dao metodo: findById");
        assertEquals(false, isNull(dao.findById(agenda.getId())));

    }

    @Test
    public void testFindBySqlQuery() {

        /* conexão DAO */
        Dao dao = new Dao(ModeloDeTeste.class, "Conexao");

        /* deleta todos os registros */
        dao.deleteTodos();

        /* itera e simula cadastro na DAO */
        int i = 0;

        for (i = 0; i <= 10; i++) {

            /* entidade */
            ModeloDeTeste agenda = new ModeloDeTeste();
            agenda.setNome("Teste");

            /* cria um novo registro no banco de dados */
            dao.create(agenda);

        }

        /* parâmetro SQL */
        String sqlParametros = "SELECT objeto FROM " + ModeloDeTeste.class.getSimpleName() + " objeto WHERE objeto.nome='Teste'";

        System.out.println("Testando classe Dao metodo: findBySqlQuery etapa 01");
        assertEquals(true, dao.findBySqlQuery(sqlParametros, 0, 0).size() == i);

        System.out.println("Testando classe Dao metodo: findBySqlQuery etapa 02");
        assertEquals(true, dao.findBySqlQuery(sqlParametros, 0, 1).size() == 1);

        System.out.println("Testando classe Dao metodo: findBySqlQuery etapa 03");
        assertEquals(true, dao.findBySqlQuery(sqlParametros, 0, 2).size() == 2);

        System.out.println("Testando classe Dao metodo: findBySqlQuery etapa 04");
        assertEquals(true, dao.findBySqlQuery(sqlParametros, 0, 5).size() == 5);

        System.out.println("Testando classe Dao metodo: findBySqlQuery etapa 05");
        assertEquals(true, dao.findBySqlQuery(sqlParametros, 1, 5).size() == 5);

        System.out.println("Testando classe Dao metodo: findBySqlQuery etapa 06");
        assertEquals(true, dao.findBySqlQuery(sqlParametros, 2, 5).size() == 5);

        System.out.println("Testando classe Dao metodo: findBySqlQuery etapa 07");
        assertEquals(true, dao.findBySqlQuery(sqlParametros, 3, 5).size() == 1);

        System.out.println("Testando classe Dao metodo: findBySqlQuery etapa 08");
        assertEquals(true, dao.findBySqlQuery(sqlParametros, 4, 5).isEmpty());

        System.out.println("Testando classe Dao metodo: findBySqlQuery etapa 09");
        assertEquals(true, dao.findBySqlQuery(sqlParametros, 5, 5).isEmpty());

        System.out.println("Testando classe Dao metodo: findBySqlQuery etapa 10");
        assertEquals(true, dao.findBySqlQuery(sqlParametros, 6, 5).isEmpty());

    }

    @Test
    public void testFindTodos() {

        /* dao */
        Dao dao = new Dao(ModeloDeTeste.class, "Conexao");

        /* quantidade de registros antigos */
        long quantidadeDeRegistrosAntigos = dao.findTodos().size();

        /* entidade */
        ModeloDeTeste agenda = new ModeloDeTeste();
        agenda.setNome("Teste");

        /* cria um novo registro no banco de dados */
        dao.create(agenda);

        System.out.println("Testando classe Dao metodo: findTodos");
        assertEquals(true, dao.findTodos().size() > quantidadeDeRegistrosAntigos);

    }

    @Test
    public void testDeleteTodos() {

        /* dao */
        Dao dao = new Dao(ModeloDeTeste.class, "Conexao");

        /* entidade */
        ModeloDeTeste agenda = new ModeloDeTeste();
        agenda.setNome("Teste");

        /* cria um novo registro no banco de dados */
        dao.create(agenda);
        dao.deleteTodos();

        System.out.println("Testando classe Dao metodo: deleteTodos");
        assertEquals(true, dao.findTodos().isEmpty());

    }

    @Test
    public void testCountTodos() {

        /* modelo */
        ModeloDeTeste agenda = new ModeloDeTeste();

        /* dao */
        Dao dao = new Dao(ModeloDeTeste.class, "Conexao");

        /* deleta todos os registros */
        dao.deleteTodos();

        /* itera e simula cadastro na DAO */
        int i = 0;

        for (i = 0; i <= 10; i++) {

            agenda = new ModeloDeTeste();
            agenda.setNome("Teste " + i);
            dao.create(agenda);

        }

        System.out.println("Testando classe dao metodo: countTodos etapa 01");
        assertEquals(true, dao.countTodos(null) == i);

        System.out.println("Testando classe dao metodo: countTodos etapa 02");
        assertEquals(true, dao.countTodos("WHERE objeto.nome='" + agenda.getNome() + "'") == 1);

        System.out.println("Testando classe dao metodo: countTodos etapa 03");
        assertEquals(true, dao.countTodos("WHERE objeto.nome LIKE '%Teste%'") == i);

    }

    @Test
    public void testLimparCache() {

    }

}
