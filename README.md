🚀 Landing Page de Cadastro de Eventos
📖 Visão Geral

Aplicação web para cadastro de eventos, onde usuários enviam suas informações por meio de uma landing page.

Os dados são processados por uma API REST construída com Java + Spring Boot e armazenados em banco de dados, garantindo validações e integridade das informações.

🎯 Objetivo

Aplicar na prática conceitos essenciais de desenvolvimento backend e integração com frontend:

Arquitetura em camadas
Criação de API REST
Validação de dados
Tratamento de exceções
Comunicação entre frontend e backend
🏗️ Arquitetura

O projeto segue o padrão de arquitetura em camadas, organizado da seguinte forma:

Controller → Service → Repository → Banco de Dados
🔹 Camadas
Controller
Recebe requisições HTTP
Retorna respostas para o cliente
Service
Implementa regras de negócio
Valida os dados recebidos
Repository
Faz a comunicação com o banco de dados
Model (Entity)
Representa os dados da aplicação<img width="1024" height="1536" alt="5f8b92c8-02ea-41e9-a03f-c0221f908c90" src="https://github.com/user-attachments/assets/0ec9606a-2b5e-4829-82a0-36bb3e247501" />
