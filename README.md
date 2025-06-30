# Sistema de Controle de Autorização de Procedimentos

Esta aplicação serve para controlar autorização de procedimentos médicos para um plano de saúde. Os critérios para permitir a execução de um procedimento são idade e sexo, de acordo
com a tabela a seguir:

<img src="https://github.com/maylajamile/github-images/blob/7e9347bf0fb58ab209065a70e2f3a0d3534b0c21/image16.png" alt="Imagem da tabela"/>


<img src="https://github.com/maylajamile/github-images/blob/a242c3dd60c6aec588ccf57c39771aa735b0bbce/image20.png" alt="Imagem da aplicação"/>

<hr>

## Como executar

<em>Você vai precisar do docker para iniciar os serviços</em>

<strong>1. Execute um dos comandos para gerar o arquivo war da aplicação:</strong>

  ```bash
  mvn clean package
  ```
  ```bash
  ./mvnw clean package
  ```

ou (para pular os testes de integração) 

  ```bash
  mvn clean package -DskipTests
  ```
  ```bash
  ./mvnw clean package -DskipTests
  ```


<strong>2. Depois execute o comando para subir os serviços com o docker:</strong>

  ```bash
  docker compose up
  ```
  ou (no modo detached)

  ```bash
  docker compose up -d
  ```


<strong>3. Você pode acessar a aplicação em:</strong>

  ```bash
  http://localhost:8080/sistema-controle-autorizacoes
  ```

<hr>

## O que foi utilizado

- Java 8
- JavaEE (Servlet, JSP, JPA Hibernate)
- Docker
- JBoss/Wildfly
- PostgreSQL
- Liquibase (Para carregar dados iniciais e versionamento de banco)
- Testcontainers (Para os testes de integração com JUnit e Mockito)
- Maven
- AJAX + jQuery






