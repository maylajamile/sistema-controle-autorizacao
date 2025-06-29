FROM liquibase/liquibase

COPY src/main/resources/db/changelog/ /liquibase/changelog/
