package br.com.salomaotech.sistema.jpa;

import br.com.salomaotech.sistema.patterns.Modelo;
import java.util.List;
import static java.util.Objects.isNull;

public class Repository {

    private final Modelo modelo;
    private final Dao dao;

    public Repository(Modelo modelo) {

        this.modelo = modelo;
        this.dao = new Dao(modelo.getClass(), "Conexao");

    }

    /**
     * Salva no banco de dados
     *
     * @return long
     */
    public long save() {

        if (modelo.getId() == 0) {

            dao.create(modelo);

        } else {

            dao.update(modelo);

        }

        return modelo.getId();

    }

    /**
     * Pesquisa usando SQL
     *
     * @param queryParam Instrução SQL
     * @return List
     */
    public List getResults(String queryParam) {

        return dao.findBySqlQuery(queryParam, 0, 0);

    }

    /**
     * Pesquisa usando SQL com paginação
     *
     * @param queryParam Instrução SQL
     * @param pageNumber
     * @param pageSize
     * @return List
     */
    public List getResultsComPaginador(String queryParam, int pageNumber, int pageSize) {

        return dao.findBySqlQuery(queryParam, pageNumber, pageSize);

    }

    /**
     * Busca usando um ID de registro
     *
     * @param id ID do registro
     * @return Modelo
     */
    public Modelo findById(long id) {

        Modelo modeloPesquisa = (Modelo) dao.findById(id);

        if (isNull(modeloPesquisa)) {

            try {

                modeloPesquisa = (Modelo) Class.forName(modelo.getClass().getName()).newInstance();

            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {

            }

        }

        return modeloPesquisa;

    }

    /**
     * Deleta usando um ID de registro
     *
     * @param id ID de registro
     * @return boolean
     */
    public boolean delete(long id) {

        if (isNull(dao.findById(id))) {

            return false;

        } else {

            dao.delete(id);
            return isNull(dao.findById(id));

        }

    }

    /**
     * Deleta todos os registros da tabela
     */
    public void deleteTodos() {

        dao.deleteTodos();

    }

    /**
     * Retorna o número de registros
     *
     * @param condicaoSql Condição WHERE se houver
     * @return Número de registros com base na pesquisa
     */
    public long countTodos(String condicaoSql) {

        return dao.countTodos(condicaoSql);

    }

    /**
     * Limpa o cache em memória
     *
     * @return
     */
    public boolean limparCache() {

        return dao.limparCache();

    }

}
