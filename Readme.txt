Trabalho 03 - POO
Nome: Deyvisson
Matricula: 385156
Professor: Atilio

Professor, implementei as questões em um projeto de um sistema de bloco de notas.
Basicamente é a mesma coisa, mas com chamada remota das funções, passando antes pelo servidor e sendo utilizadas pelo cliente.

A execução deve seguir esses passos:

make // Compilar todas as classes;

// Abrir dois novos terminais na mesma pasta do projeto (CTRL+SHIFT+T)

make rmi // No primeiro terminal. As chamadas remotas são ativadas.

make server // No segundo terminal. Note que o servidor irá iniciar, dar a resposta de atividade e permanecer online;

make client // No terceiro terminal, onde acontece a interação com o usuário.

Após a execução dos testes, feche a janela com os terminais, inicie um novo terminal e execute o comando "make clean" para deletar os arquivos .class.
