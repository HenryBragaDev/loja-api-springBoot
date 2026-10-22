# Loja API

API REST de gestão de loja, feita com **Spring Boot**, **JPA/Hibernate** e **MySQL**. Permite cadastrar e consultar clientes e produtos (com categoria) seguindo uma arquitetura em camadas.

**API no ar:** https://loja-api-springboot.onrender.com/api/teste

> A API roda no plano gratuito do Render. Se ficar um tempo sem uso, o servidor "dorme", e a primeira requisição pode levar cerca de 1 minuto para responder. Depois disso, as respostas voltam ao normal.

## Tecnologias

- Java 25
- Spring Boot (Web e Data JPA)
- JPA / Hibernate
- MySQL
- Maven
- Docker
- Deploy: Render (aplicação) e Aiven (banco MySQL)

## Estrutura

O projeto é dividido em camadas:

```
src/main/java/.../
├── model        # entidades JPA (Cliente, Produto, Categoria)
├── repository   # acesso ao banco (Spring Data JPA)
├── service      # regras de negócio
└── controller   # endpoints REST
```

## Endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/api/teste` | Verifica se a API está no ar |
| GET | `/api/clientes` | Lista os clientes |
| GET | `/api/clientes/{id}` | Busca um cliente pelo id |
| POST | `/api/clientes` | Cadastra um cliente |
| PUT | `/api/clientes/{id}` | Atualiza um cliente |
| DELETE | `/api/clientes/{id}` | Remove um cliente |
| GET | `/api/produtos` | Lista os produtos |
| GET | `/api/produtos/{id}` | Busca um produto pelo id |
| POST | `/api/produtos` | Cadastra um produto |
| PUT | `/api/produtos/{id}` | Atualiza um produto |
| DELETE | `/api/produtos/{id}` | Remove um produto |

Cada produto pertence a uma categoria (relacionamento `@ManyToOne`).

### Exemplo de requisição

```http
POST /api/produtos
Content-Type: application/json

{
  "nome": "Camiseta",
  "preco": 59.90,
  "estoque": 20,
  "categoria": { "id": 1 }
}
```

## Como rodar localmente

**Pré-requisitos:** Java 25 e MySQL instalados.

1. Crie o banco:
   ```sql
   CREATE DATABASE loja;
   ```
2. Defina a senha do MySQL local como variável de ambiente:
   ```bash
   # Linux/macOS
   export DB_LOCAL_PASSWORD=sua_senha

   # Windows (PowerShell)
   $env:DB_LOCAL_PASSWORD="sua_senha"
   ```
3. Rode a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Acesse `http://localhost:8080/api/teste`.

## Rodando com Docker

```bash
docker build -t loja-api .
docker run -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e DB_URL="jdbc:mysql://HOST:PORTA/loja?sslMode=REQUIRED" \
  -e DB_USERNAME=usuario \
  -e DB_PASSWORD=senha \
  loja-api
```

## Deploy

- **Render:** o serviço é criado a partir do repositório no GitHub e usa o `Dockerfile` da raiz.
- **Aiven:** hospeda o banco MySQL de produção.
- As credenciais ficam em variáveis de ambiente no Render (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD` e `SPRING_PROFILES_ACTIVE=prod`) e não aparecem no código.

## Autor

**Henry Braga**
[LinkedIn](https://www.linkedin.com/in/henrybraga/) · [GitHub](https://github.com/HenryBragaDev)
