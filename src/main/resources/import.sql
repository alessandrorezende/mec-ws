insert into usuario (id, username, password) values (1, 'josesilva@mec.com', '0');
insert into instituicao (id, nome,hash_id) values (1, 'Puc Minas','$2a$10$0vHpXKEPDEBYNA5ZXhwSA.54k1XmNj1MjA7Dfh/2rsC0kB8/9azZq');
insert into instituicao (id, nome,hash_id) values (2, 'Pitagoras','$2a$10$0vHpXKEPDEBYNA5ZXhwSA.54k1XmNj1MjA7Dfh/2rsC0kB8fdbbfg');
insert into curso (id, nome,turno,instituicao,vagas) values (1, 'Matematica' ,'Integral',1,0)
insert into curso (id, nome,turno,instituicao,vagas) values (2, 'Engenharia' ,'Noite',1,0)
insert into curso (id, nome,turno,instituicao,vagas) values (3, 'Educação Física' ,'Noite',1,0)
insert into curso (id, nome,turno,instituicao,vagas) values (4, 'Letras' ,'Integral',1,0)
insert into curso (id, nome,turno,instituicao,vagas) values (5, 'Computação', 'Noite',1,0)
insert into nota (id, nota,curso) values (1, 5, 1);
insert into nota (id, nota,curso) values (2, 4, 2);
insert into nota (id, nota,curso) values (3, 4, 3);
insert into aluno (id, nome,idade, curso) values (1,'José da Silva',25,1);
insert into aluno (id, nome,idade, curso) values (2,'Maria da Souza',20,2);
insert into aluno (id, nome,idade, curso) values (3,'Pedro Oliveira',19,4);
insert into aluno (id, nome,idade, curso) values (4,'Alessandro Rezende',21,5);