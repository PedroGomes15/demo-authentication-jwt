
# Demo Authentication JWT

Projeto de exemplo para realizar registro e login de um usuario.

A autenticação e o login retornam um *JWToken* para fazer futuras autenticações.

Quando uma nova conta é criada a senha é criptografada em SHA256.

Antes da senha ser criptografada é gerado um [salty](https://www.techtarget.com/searchsecurity/definition/salt) para deixar a criptografia unica


## Requisitos

- Java 21
- Maven 3.8.1 ou superior

## Instalação

### Passos para configurar o ambiente de desenvolvimento:

1. **Clone o repositório:**

    ```bash
    git clone https://github.com/PedroGomes15/demo-authentication-jwt.git
    cd demo-authentication-jwt
    ```

2. **Instale as dependências:**

    Navegue até o diretório raiz do projeto e execute o comando Maven para baixar as dependências:

    ```bash
    mvn clean install
    ```

3. **Configuração do Banco de Dados:**

    Certifique-se de que você possui um banco de dados PostgreSQL configurado. Atualize as configurações de conexão no arquivo `application.properties` que está localizado no diretório `src/main/resources`.

    ```properties
    spring.application.name=Demo Authentication Token
    spring.datasource.url=jdbc:postgresql://localhost:5432/...
    spring.datasource.username=...
    spring.datasource.password=...
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    api.security.token.secret=${JWT_SECRET:demo-token}
    ```

4. **Executando a aplicação:**

    Para iniciar a aplicação, execute o comando abaixo na raiz do projeto:

    ```bash
    mvn spring-boot:run
    ```

    A aplicação estará disponível em `http://localhost:8080`.

## Testes

Para executar os testes unitários, utilize o seguinte comando Maven:

```bash
mvn test
```
## Fluxograma

![Auth Token - Spring drawio](https://github.com/PedroGomes15/demo-authentication-jwt/assets/19183303/d4c9f277-3f1c-473a-b588-8a9834fe4543)

## Autores

- [@PedroGomes15](https://github.com/PedroGomes15)

