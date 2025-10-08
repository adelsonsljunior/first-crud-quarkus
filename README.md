# first-crud-quarkus

## Como rodar

### Crie uma copia de `.env.example` com o nome `.env` e mude a variável `DB_HOST` para database (nome do serviço do docker compose)

~~~diff
- DB_HOST=localhost
+ DB_HOST=database
~~~

### Suba a aplicação

~~~shell
docker compose up
~~~

> ## Documentação com Swagger

<a href="http://localhost:8080/api/q/docs/" target="_blank">http://localhost:8080/api/q/docs/</a>
 