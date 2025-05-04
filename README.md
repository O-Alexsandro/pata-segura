# 🐾 Pata Segura

Sistema para cadastro e busca de animais desaparecidos, com foco em facilitar a conexão entre quem perdeu e quem encontrou um animal.

---

## 🚀 Funcionalidades

- Cadastro de animais desaparecidos com dados detalhados
- Filtro público para busca por nome, tipo, raça, cor, porte, localização, etc.
- Status do animal (PERDIDO, ENCONTRADO) com filtro padrão para exibir apenas os desaparecidos
- Endereço do desaparecimento vinculado ao animal
- Integração futura com frontend para exibição das imagens

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **H2 Database (desenvolvimento)**
- **Flyway (migrações de banco)**
- **Lombok**
- **Maven**
- **OpenAPI / Swagger (documentação futura)**

---

## 📦 Estrutura dos Diretórios

```
src/
├── main/
│   ├── java/
│   │   └── br/com/pata/segura/
│   │       ├── controller/
│   │       ├── domain/
│   │       │   ├── animal/
│   │       │   └── endereco/
│   │       ├── repository/
│   │       ├── service/
```

---

## 📄 Endpoints principais

| Método | Endpoint                     | Descrição                               |
|--------|------------------------------|------------------------------------------|
| POST   | `/usuários`                  | Cadastra um novo usuário                |
| POST   | `/animais`                   | Cadastra um novo animal                 |
| PUT    | `/animais/atualizar`         | Atualiza os dados de um animal cadastrado |
| GET    | `/animais?page=0&size=3`     | Lista os animais com base no filtro |

---

## 🔍 Exemplo de Filtro

```json
{
  "nome": "Rex",
  "tipo": "CÃO",
  "raca": "Vira-lata",
  "cor": "Preto",
  "porte": "MÉDIO",
  "status": "PERDIDO",
  "descricao": "Muito dócil",
  "dataDesaparecimento": "20/03/2025",
  "endereco": {
    "cidade": "São Paulo",
    "bairro": "Centro",
    "cep": "01001-000"
  }
}
```

---

## 🧪 Como rodar o projeto

1. Clone o repositório
2. Configure o banco H2 e `application.properties`
3. Rode com sua IDE ou `./mvnw spring-boot:run`
4. Acesse `http://localhost:8080`

---

## 🧱 Regras de negócio

- Animais com status `ENCONTRADO` não aparecem na busca pública
- Somente o status `PERDIDO` é considerado no filtro, mesmo que não seja enviado explicitamente
- Cada animal pode conter 0 ou mais imagens

---

## 👨‍💻 Autor

**Alexsandro Ribas**  

---

## 📌 Roadmap Futuro

- Validação de login para donos dos animais
- Sistema de notificação para avistamentos
- Área administrativa para gestão de cadastros
- Envio de imagens via link público
