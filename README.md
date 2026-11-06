# API de Tarefas

API REST para gerenciar uma lista de tarefas, desenvolvida com Java e Spring Boot para praticar arquitetura em camadas, persistência com JPA, validação e testes.

## Tecnologias

- Java 21
- Spring Boot 4
- Spring Web
- Spring Data JPA
- Bean Validation
- H2 Database (em memória)
- JUnit 5 e Mockito
- Maven

## Como rodar

Pré-requisitos: JDK 21 e Git.

```
git clone https://github.com/MikaSantos01/meu-projeto.git
cd meu-projeto
./mvnw spring-boot:run
```

A API sobe em `http://localhost:8080`.

## Endpoints

| Método | Rota           | Descrição                |
|--------|----------------|--------------------------|
| GET    | /tarefas       | Lista todas as tarefas   |
| GET    | /tarefas/{id}  | Busca uma tarefa por id  |
| POST   | /tarefas       | Cria uma tarefa          |
| PUT    | /tarefas/{id}  | Atualiza uma tarefa      |
| DELETE | /tarefas/{id}  | Remove uma tarefa        |

### Exemplo de requisição

POST /tarefas

```json
{
  "titulo": "Estudar Spring Boot",
  "descricao": "Terminar o projeto de tarefas"
}
```

## Tratamento de erros

- `404` quando a tarefa não existe
- `400` quando os dados são inválidos (ex: título vazio)

## Testes

```
./mvnw test
```

## Próximos passos

- Trocar o H2 por PostgreSQL
- Adicionar autenticação
- Containerizar com Docker