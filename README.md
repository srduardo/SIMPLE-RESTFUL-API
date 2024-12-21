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

## 3. Versionamento de banco de dados:

O versionamento de banco de dados é uma prática comum no 
desenvolvimento back-end, que busca guardar o histórico de
mudanças na estrutura do banco de dados ao longo do tempo, 
o que permite uma melhor gestão e manutenibilidade para com
o projeto. Este histórico assume forma em arquivos de extensão
".sql" ou ".java", e são separados por versões únicas 
(representadas como: V1, V2, V3...) que servem para 
identificar cada mudança feita no banco de dados em ordem 
cronológica, como a criação de uma nova tabela ou a alteração 
de algum dado, por exemplo. Os benefícios desta prática são 
diversos, contando com mais segurança no projeto, devido a 
permanencia das informações sobre a estrutura do banco de 
dados, a integridade da estrutura, facilidade de manutenção, 
organização eficaz e maior praticidade.


### 3.1. Migrations:

As migrations são basicamente os scripts que registram as
alterações feitas no banco de dados, sendo separados por
versões únicas (V1, V2, V3...) que sinalizam a ordem em que
essas alterações foram realizadas. Além disso, a nomeclatura
das migrations possui um padrão próprio, sendo ele da 
seguinte forma: 

- "V1__nome_da_migration.sql";
- "V1__nome-da-migration.sql".

Com isso, o objetivo das migrations é de adicionar ao 
schema do banco de dados as alterações feitas em ordem,
mantendo assim a integridade da estrutura e separando-a
adequadamente de forma organizada. E sim, cada migration 
representa apenas uma alteração do banco de dados, e não 
um processo de estruturação completo. Exemplo:

- V1__create_table_user.sql;
- V2__add_columns_user.sql;
- V3__alter_column_user.sql;
- V4__insert_new_user.sql.

### 3.2. Flyway:

O Flyway é uma ferramenta extremamente útil que simplifica
o processo de manter um banco de dados atualizado com as
migrations que foram criadas durante o desenvolvimento. Por
conta disso, o Flyway cria uma tabela chamada 
``flyway_schema_history`` no banco de dados, que armazena as
alterações feitas com as migrations, seguindo obrigatoriamente 
a ordem de suas versões. Esse registro é feito com o objetivo 
de evitar a necessidade de executar uma migration mais de uma
vez, pois uma vez que sua alteração foi registrada no schema,
ela não precisa ser executada novamente, fazendo com que o
schema analise apenas com as novas mudanças em 
migrations futuras, ou em migrations que ainda não foram 
aplicadas pelo Flyway. A vantagem desta funcionalidade é a 
maior consistência que é atribuida ao banco de dados.

![Flyway e migrations](images/MigrationsEFlyway.png)

## 4. Configuração de segurança:

A configuração de segurança é essencial para estabelecer os
todas as regras e personalizações de segurança necessárias
para proteger o usuário e o sistema, sendo um recurso fornecido
pelo Spring Security. Essa configuração é desenvolvida em uma
classe Java, com auxílio do Spring Boot para a integração com
outras ferramentas e recursos, e existem diversos tipos de
configurações que podem ou devem ser feitas. As que foram feitas
neste projeto são:

- Desativação da segurança contra CSRF;
- Personalização do ``SecurityFilterChain`` para tratar as 
requisições de acordo com a segurança do sistema;
- Instanciação e personalização de um provedor de autenticação;
- Instanciação do gerenciador de autenticação, para que o 
desenvolvedor tenha acesso ao mesmo.

### 4.1. Spring Security:

O Spring Security é um Framework extremamente útil e importante
para manter APIs REST seguras. Com ele é possível implementar
sistemas de autenticação e autorização para os usuários, e proteger
endpoints específicos através de permissões obrigatórias. Ele pode personalizar
filtros de segurança do ``SecurityFilterChain`` e gerenciar políticas
de sessões, como tornar uma sessão stateless ou stateful, por exemplo.
Além disso, é com o Spring Security que é feito a configuração de segurança 
citada no tópico anterior.

### 4.2. Vulnerabilidades de APIs:

Existem diversas vulnerabilidades que podem compromenter
a integridade de um sistema e prejudicar o usuário de diversas
formas, sendo roubando suas credenciais, usar seus recursos
para prejudicar a aplicação, ou até mesmo realizar ações
maliciosas nos sistemas para benefício próprio. Algumas 
dessas vulnerabilidades e técnicas usadas para prejudicar
aplicações e usuários são:

- **CSRF (Cross-site Request Forgery):** É um tipo de ataque onde um usuário mal-intencionado
pode cria um site malicioso com algum botão ou link que possua
algum script pré-configurado para lançar uma requisição maliciosa
para outros sites. Tendo este site malicioso, o usuário mal-intencionado pode atrair e
induzir um usuário que possui uma sessão ativa no site alvo,
fazendo-o clicar em um dos botões e disparar uma requisição
para o site alvo. Essa requisição passa pelo sistema de
segurança do site alvo (pois para o site, quem está acessando é
o usuário legítimo) e realiza ações mal-intencionadas, como
uma transferência monetária, por exemplo.
- **XSS (Cross-site Scripting):** É uma vulnerabilidade onde
o usuário mal-intencionado pode injetar scripts maliciosos em sites diversos, 
novamente com o objetivo de roubar informações
sensíveis, como credenciais ou ID de sessão. Um usuário usuário mal-intencionado pode por
exemplo adicionar um script à um comentário em um site, e assim
que algum usuário viualizar aquele comentário, o script é
executado e acaba por roubar as credencais do usuário.
Isso permite que o usuário mal-intencionado pessoa acessar sua conta e se passar 
pelo usuário legítimo, por exemplo.
- **SQL Injection:** Também um tipo de vulnerabilidade, que 
consiste em enviar uma consulta SQL em um campo de entrada de dados e
contornar o sistema de segurança, por falta de validação na
entrada dos dados. Isso permite que a consulta SQL seja
interpretada pelo banco de dados, fazendo com que as informações
sejam expostas no console do navegador. 

