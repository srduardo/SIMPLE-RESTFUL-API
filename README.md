![Título](images/ReadmeTitle.png)

Este é um projeto criado com o intuito de aprimorar meus conhecimentos sobre
desenvolvimento de API's RESTful seguras e eficazes, focando a atenção 
em questões como validação de dados, autenticação e autorização de usuários com
Spring Security e JWT. Além disso, foram usados outras dependências e ferramentas 
para a otimização do desenvolvimento, como o Flyway para o versionamento e melhor
controle do banco de dados e o Apache Maven para o gerenciamento de dependências. 
Por fim, estou muito feliz com o resultado final, e espero que este repositório 
possa te ajudar a extrair um pouco de conhecimento também. E desde já, obrigado.

## O que foi utilizado para desenvolver o projeto:

- Java como linguagem de programação;
- Spring Boot e Spring Security como frameworks;
- Arquitetura MVC;
- Principais Dependências:<br>
*1. DevTools;*<br>
*2. JPA;*<br>
*3. JDBC;*<br>
*4. Flyway;*<br>
*5. Postgresql;*<br>
*6. Validation;*<br>
*7. Jjwt-Api;*<br>
*8. Jjwt-Impl;*<br>
*9. Jjwt-Jackson.*<br>
- IntelliJ como IDE;
- Postman para testes de requisição.

## Sumário do que será dito no README:

1. **Arquitetura em Camadas;**<br>
   1.1. Controllers;<br>
   1.2. Services;<br>
   1.3. Model;<br>
   1.4. Repositories.<br>
2. **Validação de dados;**<br>
   2.1. Validate;<br>
   2.2 DTO's;<br>
3. **Versionamento de banco de dados;**<br>
   3.1 Flyway;<br>
   3.2 Migrations;<br>
4. **Configuração de segurança;**<br>
   4.1 Spring Security;<br>
   4.2 Vulnerabilidades de API's;<br>
   4.3 SecurityFilterChain do Spring Security;<br>
5. **Autenticação de usuários;**<br>
   5.1 UserDetails e UserDetailsService;<br>
   5.2 Classe UsernamePasswordAuthenticationToken;<br>
   5.3 Provedores de autenticação do Spring Security;<br>
   5.4 Gerenciador de autenticação do Spring Security;<br>
6. **Geração e validação de tokens (JWT);**<br>
   6.1 JwtService gerenciar os tokens (JWT);<br>
   6.2 Geração de tokens (JWT);<br>
   6.3 Validação de tokens (JWT) com JwtFilter.<br>

## 1. Arquitetura em Camadas:

Arquiteturas são como estruturas que os sistemas utilizamn para poderem
organizar os fluxos de comunicação e as interações entre as diferentes partes
ou componentes do sistema. A arquitetura selecionada para este projeto é
a *"Arquitetura em Camadas"*, que define fortemente a responsabilidade de cada
camada presente na lógica organizacional do sistema, sendo elas:

- **Model:** Reponsável pela representação dos dados;
- **Controller:** Responsável pela comunicação entre cliente e servidor;
- **Service:** Responsável pela lógica de negócio do sistema;
- **Repository:** Responsável pela comunicação entre aplicação e banco de dados.

![Título](images/ArquiteturaEmCamadas.png)



