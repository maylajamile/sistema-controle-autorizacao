--CREATE TABLE autorizacao (
--    id SERIAL PRIMARY KEY,
--    procedimento INTEGER,
--    idade INTEGER,
--    sexo VARCHAR(1),
--    permitido BOOLEAN
--);

INSERT INTO autorizacao (procedimento, idade, sexo, permitido) VALUES (1234, 10, 'M', false);
INSERT INTO autorizacao (procedimento, idade, sexo, permitido) VALUES (4567, 20, 'M', true);
INSERT INTO autorizacao (procedimento, idade, sexo, permitido) VALUES (6789, 10, 'F', false);
INSERT INTO autorizacao (procedimento, idade, sexo, permitido) VALUES (6789, 10, 'M', true);
INSERT INTO autorizacao (procedimento, idade, sexo, permitido) VALUES (1234, 20, 'M', true);
INSERT INTO autorizacao (procedimento, idade, sexo, permitido) VALUES (4567, 30, 'F', true);