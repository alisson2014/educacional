CREATE TABLE alunos (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL
);