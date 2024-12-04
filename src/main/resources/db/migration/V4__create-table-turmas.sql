CREATE TABLE turmas(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    curso_id INT NOT NULL,
    ano INT NOT NULL,
    semestre INT NOT NULL,
    FOREIGN KEY (curso_id) REFERENCES cursos(id)
);