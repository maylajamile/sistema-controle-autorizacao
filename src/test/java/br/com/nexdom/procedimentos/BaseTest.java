package br.com.nexdom.procedimentos;

import br.com.nexdom.procedimentos.util.JPAUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashMap;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    protected static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @BeforeAll
    void setUp() {
        postgres.start();

        Map<String, String> props = new HashMap<>();
        props.put("javax.persistence.jdbc.url", postgres.getJdbcUrl());
        props.put("javax.persistence.jdbc.user", postgres.getUsername());
        props.put("javax.persistence.jdbc.password", postgres.getPassword());
        props.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
        props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
        props.put("hibernate.hbm2ddl.auto", "create-drop");

        JPAUtil.initFactory(props);

        popularBanco();
    }

    @AfterAll
    void tearDown() {
        JPAUtil.close();
        postgres.stop();
    }

    protected abstract void popularBanco();
}
