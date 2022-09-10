package br.com.salomaotech.sistema.jpa;

import static java.util.Objects.isNull;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexaoSingleton {

    private static EntityManagerFactory factory;
    private static EntityManager manager;
    private final String nomeDaConexao;

    /**
     * Construtor
     *
     * @param nomeDaConexao Nome da conexão no arquivo persistence.xml
     */
    public ConexaoSingleton(String nomeDaConexao) {

        this.nomeDaConexao = nomeDaConexao;

    }

    /**
     * Abre conexão
     */
    public void abrir() {

        try {

            /* valida se já existe uma instância da factory */
            if (isNull(factory)) {

                factory = Persistence.createEntityManagerFactory(nomeDaConexao);

            } else {

                /* valida se a factory está aberta */
                if (!factory.isOpen()) {

                    factory = Persistence.createEntityManagerFactory(nomeDaConexao);

                }

            }

            /* valida se já existe uma instância da manager */
            if (isNull(manager)) {

                manager = factory.createEntityManager();

            } else {

                /* valida se o manager está aberto */
                if (!manager.isOpen()) {

                    manager = factory.createEntityManager();

                }

            }

        } catch (Exception ex) {

        }

    }

    /**
     * Fecha a conexão
     */
    public void fechar() {

        try {

            manager.close();
            factory.close();

        } catch (Exception ex) {

        }

    }

    /**
     * Retorna o EntityManager
     *
     * @return EntityManager
     */
    public EntityManager getManager() {

        return manager;

    }

}
