# 🔀 BFF — Agendador de Tarefas

**Backend for Frontend** da plataforma de agendamento de tarefas. Atua como porta de entrada única para o frontend, orquestrando chamadas para os três microserviços internos: `usuario`, `agendador-tarefas` e `notificacao`.

Além de intermediar requisições, o BFF possui um **job agendado (CronService)** que executa automaticamente a cada intervalo configurado, busca tarefas com vencimento na próxima hora e dispara os e-mails de notificação.

---

## 🏗️ Arquitetura do sistema

```
Frontend
    └── BFF (bff-agendador) :8083  ← você está aqui
            ├── MS-Usuario      :8080  → PostgreSQL
            ├── MS-Agendador    :8081  → MongoDB
            └── MS-Notificacao  :8082  → SMTP (e-mail)
```

### Fluxo do job de notificação automática

```
[Cron - a cada intervalo]
    → Login automático no MS-Usuario
    → Consulta tarefas PENDENTES na próxima 1 hora (MS-Agendador)
    → Para cada tarefa encontrada:
        → Envia e-mail (MS-Notificacao)
        → Atualiza status para NOTIFICADO (MS-Agendador)
```

---

## 🚀 Tecnologias

| Tecnologia | Uso |
|---|---|
| Java 17 | Linguagem |
| Spring Boot (Maven) | Framework |
| Spring Cloud OpenFeign | Comunicação entre microserviços |
| Spring Scheduling (`@Scheduled`) | Job de notificação automática |
| Springdoc OpenAPI (Swagger) | Documentação |
| Lombok | Redução de boilerplate |
| Docker | Containerização |
| GitHub Actions | CI/CD |

---

## 📋 Endpoints

### Usuário (`/usuario`)

| Método | Rota | Descrição | Auth |
|---|---|---|---|
| `POST` | `/usuario` | Cadastra novo usuário | ❌ |
| `POST` | `/usuario/login` | Realiza login e retorna token JWT | ❌ |
| `GET` | `/usuario?email=` | Busca usuário por e-mail | ✅ |
| `PUT` | `/usuario` | Atualiza dados do usuário | ✅ |
| `DELETE` | `/usuario/{email}` | Remove usuário | ✅ |
| `POST` | `/usuario/endereco` | Cadastra endereço | ✅ |
| `PUT` | `/usuario/endereco?id=` | Atualiza endereço | ✅ |
| `POST` | `/usuario/telefone` | Cadastra telefone | ✅ |
| `PUT` | `/usuario/telefone?id=` | Atualiza telefone | ✅ |
| `GET` | `/usuario/endereco/{cep}` | Consulta CEP via ViaCEP | ❌ |

### Tarefas (`/tarefas`)

| Método | Rota | Descrição | Auth |
|---|---|---|---|
| `POST` | `/tarefas` | Cria nova tarefa | ✅ |
| `GET` | `/tarefas` | Lista tarefas do usuário | ✅ |
| `GET` | `/tarefas/eventos?dataInicial=&dataFinal=` | Busca tarefas por período | ✅ |
| `PUT` | `/tarefas?id=` | Atualiza tarefa | ✅ |
| `PATCH` | `/tarefas?id=&status=` | Altera status da tarefa | ✅ |
| `DELETE` | `/tarefas?id=` | Remove tarefa | ✅ |

> ✅ = Requer header `Authorization: Bearer {token}`

---

## ⚙️ Como executar (todos os serviços juntos)

### Pré-requisitos
- Docker e Docker Compose instalados
- Os outros 3 repositórios clonados na mesma estrutura de pastas

### Estrutura esperada de pastas

```
projetos/
├── usuario/usuario/
├── agendador-tarefas/agendador-tarefas/
├── notificacao/notificacao/
└── bff-agendador/bff-agendador/  ← docker-compose.yml aqui
```

### Subindo toda a stack

```bash
cd bff-agendador
docker-compose up --build
```

Isso sobe automaticamente: PostgreSQL, MongoDB, MS-Usuario, MS-Agendador, MS-Notificacao e o BFF.

### Portas dos serviços

| Serviço | Porta |
|---|---|
| BFF | `8083` |
| MS-Usuario | `8080` |
| MS-Agendador | `8081` |
| MS-Notificacao | `8082` |
| PostgreSQL | `5433` |
| MongoDB | `27017` |

### Variáveis de ambiente relevantes

| Variável | Descrição |
|---|---|
| `USUARIO_URL` | URL do MS-Usuario |
| `AGENDADOR_TAREFAS_URL` | URL do MS-Agendador |
| `NOTIFICACAO_URL` | URL do MS-Notificacao |
| `usuario.email` | E-mail usado pelo CronService para autenticar |
| `usuario.senha` | Senha usada pelo CronService |
| `cron.horario` | Expressão cron do job (ex: `0 */5 * * * *`) |

---

## 📖 Documentação (Swagger)

```
http://localhost:8083/swagger-ui.html
```

---

## 🗂️ Estrutura do projeto

```
src/main/java/com/lucasmanoel/bffagendador/
├── business/
│   ├── CronService.java           # Job agendado de notificação automática
│   ├── EmailService.java          # Chama MS-Notificacao via Feign
│   ├── TarefasService.java        # Chama MS-Agendador via Feign
│   ├── UsuarioService.java        # Chama MS-Usuario via Feign
│   ├── dto/
│   │   ├── in/                    # DTOs de entrada (Request)
│   │   └── out/                   # DTOs de saída (Response)
│   └── enums/
│       └── StatusNotificacaoEnum.java
├── controller/
│   ├── TarefasController.java     # Endpoints de tarefas
│   └── UsuarioController.java     # Endpoints de usuário
└── infrastructure/
    ├── client/
    │   ├── EmailClient.java       # Feign → MS-Notificacao
    │   ├── TarefasClient.java     # Feign → MS-Agendador
    │   ├── UsuarioClient.java     # Feign → MS-Usuario
    │   └── config/
    │       ├── FeignConfig.java   # Configuração do Feign
    │       └── FeignError.java    # Tratamento de erros do Feign
    ├── configs/
    │   └── CorsConfig.java        # Configuração de CORS
    ├── exceptions/                # Exceções customizadas
    └── security/
        └── SecurityConfig.java    # Configuração de segurança
```

---

## 🔗 Repositórios dos microserviços

- [usuario](https://github.com/Lucasmanoel1/usuario)
- [agendador-tarefas](https://github.com/Lucasmanoel1/agendador-tarefas)
- [notificacao](https://github.com/Lucasmanoel1/notificacao)
