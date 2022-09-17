package br.com.salomaotech.sistema.jpa;

import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.isNull;
import javax.persistence.TypedQuery;

public class Dao<E> {

    private final ConexaoSingleton conexao;
    private final Class nomeTabela;
    private final String objetoDados;

    /**
     * Construtor
     *
     * @param classeEntity Nome da classe @Entity
     * @param nomeDaConexao Nome da conexão no arquivo persistence.xml
     */
    public Dao(Class classeEntity, String nomeDaConexao) {

        /* nome da tabela */
        this.nomeTabela = classeEntity;

        /* objeto que irá representar os dados */
        objetoDados = "objeto";

        /* abre uma conexão */
        this.conexao = new ConexaoSingleton(nomeDaConexao);
        this.conexao.abrir();

    }

    /**
     * Criar um registro no banco de dados
     *
     * @param entidade @Entity
     */
    public void create(E entidade) {

        try {

            conexao.getManager().getTransaction().begin();
            conexao.getManager().persist(entidade);
            conexao.getManager().getTransaction().commit();

        } catch (Exception ex) {

        }

    }

    /**
     * Atualizar um registro no banco de dados
     *
     * @param entidade @Entity
     */
    public void update(E entidade) {

        try {

            conexao.getManager().getTransaction().begin();
            conexao.getManager().merge(entidade);
            conexao.getManager().getTransaction().commit();

        } catch (Exception ex) {

        }

    }

    /**
     * Deletar um registro do banco de dados por ID
     *
     * @param id ID do registro
     */
    public void delete(Long id) {

        try {

            conexao.getManager().getTransaction().begin();
            conexao.getManager().remove(conexao.getManager().find(nomeTabela, id));
            conexao.getManager().getTransaction().commit();

        } catch (Exception ex) {

        }

    }

    /**
     * Buscar um registro do banco de dados por ID
     *
     * @param id ID do registro
     * @return @Entity
     */
    public E findById(Long id) {

        E entidadeRetorno = null;

        try {

            entidadeRetorno = (E) conexao.getManager().find(nomeTabela, id);

        } catch (Exception ex) {

        }

        return entidadeRetorno;

    }

    /**
     * Busca por registros no banco de dados utilizando SQL
     *
     * @param sqlParametros Parâmetros SQL
     * @param pageNumber
     * @param pageSize
     *
     * @return List com @Entity
     */
    public List findBySqlQuery(String sqlParametros, int pageNumber, int pageSize) {

        List EntidadesRetorno = new ArrayList();

        try {

            TypedQuery query = conexao.getManager().createQuery(sqlParametros, nomeTabela);

            /* avança no contador dos registros */
            if (pageNumber > 0) {

                query.setFirstResult((pageNumber - 1) * pageSize);

            }

            /* define número máximo de registros */
            if (pageSize > 0) {

                query.setMaxResults(pageSize);

            }

            EntidadesRetorno = query.getResultList();

        } catch (Exception ex) {

        }

        return EntidadesRetorno;

    }

    /**
     * Lista todos os registros do banco de dados
     *
     * @return @Entity
     */
    public List findTodos() {

        return findBySqlQuery("SELECT " + objetoDados + " FROM " + nomeTabela.getSimpleName() + " " + objetoDados, 0, 0);

    }

    /**
     * Deleta todos os registros da tabela
     */
    public void deleteTodos() {

        /* 
        OBS: Com este algoritmo é garantido que relacionamentos do tipo @OneToMany, @ManyToMany, @ManyToOne, @OneToOne serão excluídos
        Poderia ser utilizado DELETE FROM, más isto deixaria (cascade = CascadeType.ALL, orphanRemoval = true) inútil causando bug
         */
        List registrosList = findTodos();

        try {

            conexao.getManager().getTransaction().begin();

            registrosList.forEach(objeto -> {

                conexao.getManager().remove(objeto);

            });

            conexao.getManager().getTransaction().commit();

        } catch (Exception ex) {

        }

    }

    /**
     * Retorna o número de registros
     *
     * @param condicaoSql Condição WHERE se houver
     * @return Número de registros com base na pesquisa
     */
    public long countTodos(String condicaoSql) {

        try {

            String sqlParametros = "SELECT COUNT(" + objetoDados + ".id) FROM " + nomeTabela.getSimpleName() + " " + objetoDados;

            if (!isNull(condicaoSql)) {

                sqlParametros += " " + condicaoSql;

            }

            return (long) conexao.getManager().createQuery(sqlParametros, Long.class).getSingleResult();

        } catch (Exception ex) {

            return 0;

        }

    }

    /**
     * Limpa o cache em memória
     */
    public void limparCache() {

        try {

            conexao.getManager().clear();

        } catch (Exception ex) {

        }

    }

}
