package br.com.nexdom.procedimentos.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Map;
import java.util.Objects;

public class JPAUtil {

    private static EntityManagerFactory factory;

    /**
     * Inicializa a factory com configurações customizadas (usado nos testes).
     */
    public static void initFactory(Map<String, String> props) {
        if (Objects.nonNull(factory) && factory.isOpen()) {
            factory.close();
        }
        factory = Persistence.createEntityManagerFactory("autorizacao", props);
    }

    public static EntityManager getEntityManager() {
        if (Objects.isNull(factory) || !factory.isOpen()) {
            factory = Persistence.createEntityManagerFactory("autorizacao");
        }
        return factory.createEntityManager();
    }

    public static void close() {
        if (Objects.nonNull(factory) && factory.isOpen()) {
            factory.close();
        }
    }
}
