# first-crud-quarkus

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

<br>

> ## How to run

### Create a copy of the `.env.example` file named `.env`

### Run database container

~~~shell
docker compose -f docker-compose.dev.yml up
~~~

### Packaging and running the application

```shell script
./mvnw package
```

```shell script
java -jar target/quarkus-app/quarkus-run.jar
```

<br>

> ## Accessing documentation with swagger

### `localhost:8080/swagger-ui`
