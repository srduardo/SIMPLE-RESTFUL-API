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

## O que será explicado:

1. **Arquitetura em Camadas;**<br>
2. **Validação de dados;**<br>
   2.1. DTO's;<br>
   2.2. Validation;<br>
3. **Versionamento de banco de dados;**<br>
   3.1. Flyway;<br>
   3.2. Migrations;<br>
4. **Configuração de segurança;**<br>
   4.1. Spring Security;<br>
   4.2. Vulnerabilidades de API's;<br>
   4.3. SecurityFilterChain do Spring Security;<br>
5. **Autenticação de usuários;**<br>
   5.1. UserDetails e UserDetailsService;<br>
   5.2. Classe UsernamePasswordAuthenticationToken;<br>
   5.3. Provedores de autenticação do Spring Security;<br>
   5.4. Gerenciador de autenticação do Spring Security;<br>
6. **Geração e validação de tokens (JWT);**<br>
   6.1. JwtService gerenciar os tokens (JWT);<br>
   6.2. Geração de tokens (JWT);<br>
   6.3. Validação de tokens (JWT) com JwtFilter.<br>

## 1. Arquitetura em Camadas:

Arquiteturas são como estruturas que os sistemas utilizam para poderem
organizar os fluxos de comunicação e as interações entre as diferentes partes
e componentes do sistema. A arquitetura selecionada para este projeto é
a *"Arquitetura em Camadas"*, que define fortemente a responsabilidade de cada
camada presente na lógica organizacional do sistema, sendo elas:

- **Model:** Reponsável pela representação dos dados;
- **Controller:** Responsável pela comunicação entre cliente e servidor;
- **Service:** Responsável pela lógica de negócio do sistema;
- **Repository:** Responsável pela comunicação entre aplicação e banco de dados.

![Arquitetura em Camadas](images/ArquiteturaEmCamadas.png)

## 2. Validação de dados:

Este é um tópico extremamente importante para qualquer tipo de sistema, pois
é aqui onde é desenvolvido prevenções que impedem a entrada e saída de dados
inadequadamente. Por exemplo, imagine que um usuário preenche vários campos de
um formulário apenas com espaços em branco e envia, o seu sistema apenas aceitará
isso sem qualquer tratamento? Ou ele tratá uma resposta adequada ao usuário, pedindo
por dados válidos? Por isso é importante darmos muito atenção para essa parte, pois
os dados são os bens mais valiosos de um sistema, e sempre devemos manter a integridade
deles.

### 2.1. DTO's:

A criação de DTO (Data Transfer Object) é uma prática muito comum no desenvolvimento 
back-end, e assim como a camada *Model*, os DTO's também são uma representação dos dados em um objeto. A diferença
é que um DTO não tem o objetivo de se tornar um objeto que será usado para persistir os dados
finais no banco de dados, na verdade, um DTO é criado para que seja possível filtrar quais dados
queremos apresentar ao usuário ou extrair dele.

![DTO's](images/DTO's.png)

Claro, os DTO's não servem apenas para filtrar os dados durante as interações com o usuário,
eles também podem ser usados para filtrar os dados entre interações do próprio sistema, tornando
diversos processos mais simples, rápidos e eficientes.

### 2.2. Validation:

A dependência *Validation* é uma adição vital para os DTO's quando o objetivo é validar a entrada de dados, 
já que a proposta dessa dependência é justamente essa. Através de anotações, é possível declarar restrições para cada
atributo de um objeto que será usado para representar alguma entrada de dados. Isso permite que nós como desenvolvedores,
não precisemos gastarmos tempo criando sistemas de validação manualmente, tornando-se assim uma ferramenta extremamente útil.

**Restrições que o Valiadtion dispõe:**

- **@NotNull:** Não permite que o atributo seja nulo;
- **@NotEmpty:** Não permite que o atributo fique vazio;
- **@NotBlank:** Não permite que o atributo fique vazio e sem caracteres em branco;
- **@Size:** Define o tamanho mínimo e/ou máximo do atributo;
- **@Min:** Define o tamanho mínimo do atributo;
- **@Max:** Define o tamanho máximo do atributo;
- **@Positive:** Não permite que o número do atributo seja menor ou igual a zero;
- **@Negative:** Não permite que o número do atributo seja maior ou igual a zero;
- **@PositiveOrZero:** Não permite que o número do atributo seja menor que zero;
- **@NegativeOrZero:** Não permite que o número do atributo seja maior que zero;
- **@Digits:** Define o número máximo de números inteiros ou decimais de um atributo;
- **@DecimalMin:** Define um número mínimo para atributos de números decimais;
- **@DecimalMax:** Define um número máximo para atributos de números decimais;
- **@Email:** Define que o valor do atributo deve estar dentro dos padrões de um e-mail;
- **@Pattern:** Verifica se o valor do atributo é correspondente ao regex especificado;
- **@Past:** Verifica se a data está no passado;
- **@Future:** Verifica se a data está no futuro;
- **@PastOrPresent:** Verifica se a data está no passado ou no presente;
- **@FutureOrPresent:** Verifica se a data esta no futuro ou no presente.

![DTO's](images/Validation.png)

