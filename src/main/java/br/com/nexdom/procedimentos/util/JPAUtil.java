package br.com.nexdom.procedimentos.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("autorizacoes");

        public static EntityManager getEntityManager() {
            return FACTORY.createEntityManager();
        }

        public static void close() {
            FACTORY.close();
        }
}
