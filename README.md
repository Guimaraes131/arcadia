# **Jogo ARCADIA**
Projeto mercado em formato de jogo com CRUD e filtros de busca

# Arcadia API - CRUD de Personagem e Itens

Bem-vindo(a) ao projeto **Arcadia**!  
Aqui você irá encontrar todas as instruções para realizar o **CRUD** de **Personagem** e **Item**, além de exemplos de **filtros** de busca no Postman ou navegador.

---

## Como usar a API

A API está rodando em:  
```
http://localhost:8080
```

---

## CRUD de Personagem

- **Criar Personagem (POST)**
  ```http
  POST /personagens
  ```
  Exemplo de corpo (JSON):
  ```json
  {
    "nome": "Leonidas",
    "classe": "GUERREIRO",
    "nivel": 75,
    "moedas": 4500
  }
  ```

- **Listar todos (GET)**
  ```http
  GET /personagens
  ```

- **Buscar Personagem por Nome (GET)**
  ```http
  GET /personagens/nome/{nome}
  ```

- **Buscar Personagem por Classe (GET)**
  ```http
  GET /personagens/classe/{classe}
  ```

- **Atualizar Personagem (PUT)**
  ```http
  PUT /personagens/{id}
  ```

- **Deletar Personagem (DELETE)**
  ```http
  DELETE /personagens/{id}
  ```

---

## CRUD de Item

- **Criar Item (POST)**
  ```http
  POST /itens
  ```
  Exemplo de corpo (JSON):
  ```json
  {
    "nome": "Espada Flamejante",
    "tipo": "ARMA",
    "raridade": "EPICO",
    "preco": 1500,
    "dono": {
      "id": 1
    }
  }
  ```

- **Listar todos (GET)**
  ```http
  GET /itens
  ```

- **Buscar Item por Nome Parcial (GET)**
  ```http
  GET /itens/nome?nome={parteDoNome}
  ```

- **Buscar Item por Tipo (GET)**
  ```http
  GET /itens/tipo/{tipo}
  ```

- **Buscar Item pelo Menor Preço (GET)**
  ```http
  GET /itens/minPreco
  ```

- **Buscar Item pelo Maior Preço (GET)**
  ```http
  GET /itens/maxPreco
  ```

- **Buscar Item por Raridade (GET)**
  ```http
  GET /itens/raridade/{raridade}
  ```

- **Atualizar Item (PUT)**
  ```http
  PUT /itens/{id}
  ```

- **Deletar Item (DELETE)**
  ```http
  DELETE /itens/{id}
  ```

---

## Observações importantes

- Enum de **classe**: `GUERREIRO`, `MAGO`, `ARQUEIRO`
- Enum de **tipo** de item: `ARMA`, `ARMADURA`, `POCAO`, `ACESSORIO`
- Enum de **raridade**: `COMUM`, `RARO`, `EPICO`, `LENDARIO`
- Sempre envie **o campo `dono.id`** no POST/PUT de item para relacionar com um personagem já existente!

---

## Dicas

- Use o **Postman ou Insomnia** para testar todos os endpoints rapidamente.
- Para ver o banco H2:  
  Acesse [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
  
  JDBC URL:
  ```
  jdbc:h2:mem:arcadia
  ```

---

## Tecnologias usadas

- Spring Boot 3.4.x
- H2 Database
- Jakarta Validation
- Lombok
- JPA/Hibernate
