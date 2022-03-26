# Dados relativos a bolsas de estudo do governo
## O que a aplicação faz?
A aplicação processa um arquivo **.csv** e aplica as funcionalidades solicitadas.
## Tecnologias
- Java;<br>
- Lombok;<br>
- Maven;<br>
- Junit;<br>
## Requisitos para build
- Ter **maven** instalado e configurado;
- Java JDK 11
## Rodando o projeto
Na raiz do projeto abra o terminal e rode os seguintes comandos:

Para compilar rode:
```
mvn compile
```

Após rode o seguinte comando para que o **jar** da aplicação seja gerado:
```
mvn package
```

Feito isso, agora rode o seguinte comando para a execução do projeto:
```
java -jar target/etapa-2-programa-1.0-SNAPSHOT.jar
```
## Funcionalidades:

1 - Recebe um input **ano** e retorna o primeiro bolsista daquele ano;<br>
2 - Recebe um input **nome** e retorna o primeiro objeto que contém a String do input;<br>
3 - Recebe um input **ano** e retorna a média do valor da mensalidade daquele ano;<br>
4 - Retorna os três alunos com o valor da bolsa mais altos e mais baixos;<br>
5 - Termina o programa;
