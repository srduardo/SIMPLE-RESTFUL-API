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
6. **JSON Web Token (JWT);**<br>
   6.1. Partes de um token;<br>
   6.2. Assinatura com chave secreta;<br>
   6.3. Gerenciamento de tokens com JwtService;<br>
   6.4. Geração de tokens;<br>
   6.5. Validação de tokens com JwtFilter.<br>

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

**CSRF (Cross-site Request Forgery):**

- **O que é:** Um tipo de ataque que tem o objetivo de induzir
um usuário com uma sessão ativa em algum site à realizar
uma requisição sem o seu consentimento.
- **Método de ataque:** O site malicioso porta diversos métodos
de sequestrar a requisição do usuário, sendo através de botões,
links, imagens invisíveis, iframes e muito mais. Portanto, 
essas formas de interação carregam um script, configurado para
enviar uma requisição para o site em que o usuário possui uma
sessão ativa. Por exemplo, o usuário pode ser induzido a 
clicar em um botão, e ao fazer isso uma requisição é enviada
para o seu banco e uma transferência é realizada sem o seu
consentimento.
- **Como funciona:** A requisição feita pelo usuário carrega
o seu token de validação, fazendo com que ela passe
pelo sistema de segurança como se fosse uma mais uma requisição
normal do usuário legítimo. Dessa forma, o usuário mal-intencionado
por trás do ataque consegue realizar (indiretamente) ações
no site, como por exemplo, alterar configurações ou realizar
uma transferência de dinheiro.
- **Medida de proteção:** Hoje existe o token CSRF, que é um
token único gerado pelo servidor e entregue ao usuário. Este
token foi criado para ser enviado junto das requisições, onde
ele é validado para garantir que elas (requisições) estão vindo de
fato do usuário legítimo. Se o token não for válido, a requisição
é encerrada e o ataque é evitado.

**XSS (Cross-site Scripting):**

- **O que é:** É um tipo de ataque que injeta scripts maliciosos
(normalmente feito em Javascript) em sites confiáveis, com o objetivo
de prejudicar os usuários que acessarem esses sites. A diferença entre
o XSS e o CSRF é que o segundo explora a sessão ativa do usuário, enquanto 
o primeiro explora a confiança do usuário no site.
- **Método de ataque:** Normalmente os métodos de ataque dessa
vulnerabiliade procura explorar as entradas de dados, como
formulários, URL, comentários, campos de pesquisa e outros. Veja a seguir
como esses métodos podem ser executados:
1. **XSS refletido:** Basicamente reflete as alterações do ataque para o usuário assim que a injeção 
é feita, normalmente pelos parâmetros da URL ou por formulários.
2. **XSS armazenado:** Quando o script malicioso é armazenado direto
no banco de dados, sendo executado assim que um usuário acessa
o recurso correspondente ao script.
3. **XSS por DOM:** São scripts maliciosos que afetam diretamente
as propriedades DOM (Document Object Model) do navegador, para que
códigos maliciosos sejam injetados sem irem diretamente para o
servidor.
- **Como funciona:** Normalmente os scripts são desenvolvidos
em Javascript, já que é uma linguagem totalmente compatível
com o navegador, que é um dos principais focos do ataque. E com isso,
como consequência, o script pode executar diversas ações quando
executados, como redirecionar o usuário para outra página 
maliciosa, alterar suas configurações, roubar cookies de sessão, 
ou até mesmo instalar malwares.
- **Medida de proteção:** Inicialmente é essencial a 
implementação de validação e condificação das entradas de dados,
já que os métodos de ataque sempre focam principalmente esse
meio para ser eficaz. Outras medidas necessárias são a adição
de algum framework de segurança com proteção contra ataques
XSS nos sites, e a implementação de políticas de segurança
eficientes, como treinamento de desenvolvedores, testes de
software frequentes e atualizações relevantes.

**SQL Injection:**

- **O que é:** É um tipo de ataque que assim como o XSS, também
se aproveita da vulnerabilidade de entradas de dados, injetando
consultas SQL e induzindo o sistema a fazer com que o banco de dados
execute essas consultas. Fazendo isso, o atacante pode coletar
dados sensíveis ou manipular os dados presentes no banco.
- **Método de ataque:** Imagine um cenário onde um usuário
insere uma string da condição `` ' ' OR 'a' = 'a'``, em um formulário 
de login, mais especificamente no campo de senha. 
Caso o sistema não tenha a devida proteção contra esse tipo 
de ataque, essa string será concatenada com a consulta SQL
padrão do sistema:

> ``SELECT * FROM users WHERE username = 'eduardo' AND password = ' ' OR 'a' = 'a';``

- Esta condição sempre será verdadeira, portanto seria como se o sistema
estivesse pedindo para o banco de dados retornar todos os usuários da tabela:

> ``SELECT * FROM users;``

- **Como funciona:** Esse tipo de ataque permite que o atacante
tenha acesso a diversos dados sensíveis, tenha capacidade de
alterar ou remover os dados, acessar a conta de outros usuários e
muito mais. No entanto, hoje talvez seja difícil encontrar algum
sistema sem esse tipo de defesa, mas ainda é válido listarmos
algumas formas de proteção contra esse tipo de ataque.
- **Medida de proteção:** Existem três principais medidas de
proteção, sendo elas:
1. **Consultas parametrizadas:** Esse tipo de consulta exige
que o sistema primeiro defina a consulta SQL, para que depois
seja passado os parâmetros de consulta. Isso faz com que caso 
seja enviado uma condição para alterar a consulta, essa condição
não será concatenada com a consulta, e sim tratada como um dado
para ser passado como parâmetro.
2. **Procedimentos armazenados:** Esse método faz com que
a consulta seja definida em um procedimento armazenado, que é
literalmente persistido no banco de dados. Com isso, o sistema
pode chamar esse procedimento para efetuar a consulta, impedindo
que a consulta possa ser alterada. No entanto, se a consulta implementada no procedimento for dinâmica, a
vulnerabilidade pode persistir.
3. **Validação de entradas:** Por último, novamente a validação
de entrada de dados se mostra extremamente importante, pois com
ela é possível identificar logo no inicio se um dado não 
corresponde com os padrões definidos do sistema. Normalmente
essas validações são feitas com padrões regex, mas existem
diversas formas de implementar essa medida.

### 4.3. SecurityFilterChain do Spring Security:

**O que é:**

No SecurityFilterChain é onde nós como desenvolvedores podemos
definir regras de segurança em diversos filtros, que serão utilizados 
para validar as requisições enviadas pelo usuário. Desta forma,
o sistema terá um centro de controle para a proteção dos endpoints da aplicação,
liberando acesso apenas para as requisições autenticadas e autorizadas a ter
acesso aquele recurso.

**Filtros padrões:**

Existem diversos tipos de filtros padrões que o
Spring Security fornece para facilitar o desenvolvimento, como
o ``UsernamePasswordAuthenticationFilter``, por exemplo, que é
responsável por gerir autenticações através de formulários, onde
o usuário insere suas credenciais, sendo normalmente o nome de
usuário e a senha. E outro filtro padrão que
pode ser citado é o ``BasicAuthenticationFilter``, responsável
por realizar a autenticação de requisições que carreguem
as credenciais do usuário, normalmente em seus cabeçalhos. Esses filtros
são usados e chamados por padrão pelo Spring Security.

**Filtros personalizados:**

O Spring Security também nos permite criar
filtros personalizados para que possamos ter mais controle e
flexibilidade, e este tipo de filtro personalizado foi aplicado
neste projeto para que fosse possível definir algumas regras
de segurança para as requisições. 

**Filtro personalizado do projeto:**

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(customizer -> customizer.disable()) 
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("api/users/register", "api/users/login")
                        .permitAll()
                        .anyRequest().authenticated()) 
                .httpBasic(Customizer.withDefaults()) 
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

 ``HttpSecurity``: É basicamente o contrutor que receberá todas
 as configurações de segurança para as requisições. Ele será
 o objeto retornado instanciado pelo Bean quando este filtro
 for chamado, e todas as regras que foram definidas 
 serão usadas para validar a requisição.

 ``.csrf(customizer -> customizer.disable())``: Aqui está sendo 
 desativado a protenção contra ataques CSRF, isso porque APIs
 Restful normalmente não mantém sessões. Cada requisição de uma
 API Restful é enviada de forma independente, carregando um token de validação
 em seu cabeçalho para a realização da autenticação.

 ``.authorizeHttpRequests()``: Aqui é onde são declaradas 
 algumas regras de autorização para as requisições.
 ``requests -> requests``: Valirável de referência que representa
 a requisição lançada pelo usuário.
 ``.requestMatchers("api/users/register", "api/users/login")``:
 Aponta para endpoints especificos, para que eles sejam colocados
 nos parâmetros de uma nova regra de autorização.
 ``.permitAll()``: Declara que todas as requisições devem ter
 acesso concedido nos endpoints específiciados pelo item anterior.
 ``.anyRequest().authenticated())``: Declara que qualquer outra
 requisição deverá ser autenticada para poder acessar qualquer
 outro endpoint.

 ``.httpBasic(Customizer.withDefaults()) ``: Declara que em cada
 requisição não autenticada deve ser requisitado as credenciais do usuário em um
 prompt de comando no navegador.

 ``.sessionManagement(session ->
session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))``:
 Declara que a política de sessão dessa aplicação é de uma sessão
 sem estado. Ou seja, o servidor não vai guardar qualquer dado do
 usuário, e todas as requisições serão independentes, cada uma
 carregando as credenciais do usuário ou tokens de autenticação
 em seus cabeçalhos.

 ``.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)``:
 Declara que o filtro jwtFilter deve ser executado antes do UsernamePasswordAuthenticationFilter.

 ``.build();``: Por último, este é responsável por contruir o objeto do Bean
 para que ele seja instanciado assim que for chamado.


Mas, lembre-se sempre que a segurança das APIs não se baseia apenas em filtros, mas também
em outras medidas de segurança, como validação de dados, sistema de tokens,
e muitas outras.

## 5. Autenticação de usuários:

A autenticação de usuários é um processo que busca garantir que
o usuário tentando acessar determinado recurso seja, de fato, o
dono das credenciais cadastradas no sistema. No entanto, para a
realização deste processo é necessário a conclusão de várias
etapas essenciais para o sucesso do procedimento. Vamos começar
explicando sobre o ``UserDetails`` e o ``UserDetailsService``, que são
responsáveis por gerenciar as credenciais do usuário para a
autenticação. Posteriormente será abordado o ``UsernamePasswordAuthenticationToken``,
que é nada mais do que um token de autenticação que é fornecido
ao provedor de autenticação, para que o processo seja realizado
com sucesso. Por sinal, logo em seguida será explicado sobre os
provedores de autenticação, que são responsáveis por validar
as credenciais do usuário e até de descriptografar a senha para a
validação. Por último, falaremos sobre o gerenciador de autenticação,
que em suma é responsável por selecionar o provedor de autenticação 
mais adequado para o processo.

### 5.1. UserDetails e UserDetailsService: 

O **UserDetails** é uma interface que ao ser implementada em uma classe, 
a torna uma representação das credenciais do usuário e de outros detalhes
relevantes, como, por exemplo:

- Se a conta do usuário está ou não expirada;
- Se a conta do usuário está ou não bloqueada;
- Se as credenciais do usuário estão ou não bloqueadas;
- Se o UserDetails está ou não ativo;
- Quais as permissões que o usuário possui.

Já o **UserDetailsService** é basicamente outra interface que ao ser
implementada em uma classe, faz com que ela sobreescreva um método
chamado ``loadUserByUsername()``. Este método é responsável por:

- **Requisitar os dados** do usuário ao banco de dados (através de ser username ou email);
- **Validar os dados** coletados para saber se o usuário existe no banco de dados;
- **Instanciar** o UserDetails do usuário;
- **Retornar** o UserDetails.

**UserDetails:**

    public class MainUserDetails implements UserDetails {

       private User user;
   
       public MainUserDetails(User user) {
           this.user = user;
       }
   
       @Override
       public Collection<? extends GrantedAuthority> getAuthorities() {
           return Collections.singleton(new SimpleGrantedAuthority("USER"));
       }
   
       @Override
       public String getPassword() {
           return user.getPassword();
       }
   
       @Override
       public String getUsername() {
           return user.getEmail();
       }
   
       @Override
       public boolean isAccountNonExpired() {
           return true;
       }
   
       @Override
       public boolean isAccountNonLocked() {
           return true;
       }
   
       @Override
       public boolean isCredentialsNonExpired() {
           return true;
       }
   
       @Override
       public boolean isEnabled() {
           return true;
       }
    }

**UserDetailsService:**

    @Service
    public class MainUserDetailsService implements UserDetailsService {

       @Autowired
       private UserRepository userRepository;
   
       @Override
       public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
   
           User user = userRepository.findByEmail(email);
   
           if (user == null) {
               System.out.println("Usuário não encontrado");
               throw new UsernameNotFoundException("Usuário não encontrado");
           }
   
           return new MainUserDetails(user);
       }

    }

**Exemplo:**

![UserDetails e UserDetailsService](images/UserDetailsFlow.png)

> No caso deste projeto, ao invés da autenticação ser feita
> com username, eu optei por usar o email como uma das credenciais
> de autenticação, o que também é possível.

### 5.2. UsernamePasswordAuthenticationToken:

O ``UsernamePasswordAuthenticationToken`` é uma classe utilizada
para "envelopar" as credenciais relevantes do usuário durante o 
processo de autenticação:

- O ``UsernamePasswordAuthenticationToken`` recebe as credenciais
do usuário (username e senha), normalmente de um ``UserDetails``;
- O ``UsernamePasswordAuthenticationToken`` retorna um token não
autenticado para o ``AuthenticationManager``;
- O ``AuthenticationManager`` seleciona o melhor provedor para
autenticar aquele objeto;
- Se o processo de autenticação for bem-sucedido, é retornado um 
token autenticado e as permissões armazenadas no ``UserDetails``
são adicionadas neste token.

Os principais pontos a serem destacados são:

- Enquanto o ``UserDetails`` se responsabiliza por representar
as credenciais do usuário e seus detalhes relevantes, o 
``UsernamePasswordAuthenticationToken`` é responsável por 
"envelopar" apenas as credenciais necessárias para o processo
de autenticação.
- O ``UsernamePasswordAuthenticationToken`` pode retornar
tanto tokens "autenticados" quanto "não autenticados".
- Os tokens "autenticados" são aqueles que passaram de forma
bem-sucedida no processo de autenticação. Já os "não autenticados"
são aqueles que ainda não passaram pelo processo de autenticação.
- Por fim, o token autenticado é adicionado ao 
``SecurityContextHolder ``, que é responsável por gerenciar todo
o contexto de segurança da aplicação. 

![UsernamePasswordAuthenticationToken](images/UsernamePasswordAuthenticationToken.png)

> Outro ponto extremamente importante para se destacar, é que
> durante o processo de autenticação, o provedor requisita o 
> ``UserDetails`` do usuário para comparar e validar as suas 
> credenciais com as credenciais presentes no token do 
> ``UsernamePasswordAuthenticationToken`` e atribuir as suas 
> devidas permissões.

### 5.3. Provedores de autenticação do Spring Security:

Os provedores de autenticação (``AuthenticationProvider``) são
responsáveis pelo processo de autenticação de usuário, caso haja
alguma tentativa de login. Cada provedor segue um tipo específico
de lógica, portanto existem alguns tipos de provedores:

- ``DaoAuthenticationProvider``: O tipo mais comum de provedor
de autenticação, que usa o UserDetails como representação
das credenciais do usuário, coletadas do banco de dados ou
qualquer outra fonte de armazenamento.
- ``LdapAuthenticationProvider``: Um provedor que delega a 
autenticação a um servidor LDAP, usando o ``LdapAuthenticator`` para
realizar o processo de autenticação. Além disso, ele também utiliza
o ``LdapAuthoritiesPopulator`` para coletar as permissões do usuário.

Esses são os provedores mais comuns e usados na área de desenvolvimento
back-end, já outros provedores existentes são para casos mais 
específicos. O provedor utilizado neste projeto é o 
``DaoAuthenticationProvider``.

### 5.4. Gerenciador de autenticação do Spring Security:

O gerenciador de autenticação (``AuthentiactionManager``) 
é basicamente um intermediador entre a requisição de login do
usuário e os provedores de autenticação. Assim que um usuário 
requisita a autenticação de suas credenciais, o gerenciador:

- Recebe um objeto ``Authentication`` (normalmente um
``UsernamePasswordAuthenticationToken``);
- Delega essa autentcação para um provedor adequado;
- O provedor autentica aquele objeto;
- Caso a autenticação for bem-sucedida, um objeto 
``Authentication`` preenchido e autenticado é retornado;
- Caso a autenticação falhar, é retornado uma 
``AuthenticationException``.

Pontos importantes a serem destacados:

- Não é o ``AuthenticationManager`` que faz a autenticação
diretamente, e sim o provedor que ele seleciona;
- O método utilizado para selecionar o provedor e encaminhar
a autenticação é o ``.authenticate()``;
- O token retornado pelo ``UsernamePasswordAuthenticationToken``
também é um objeto ``Authentication``.

## 6. JSON Web Token (JWT):

Um **JWT (Json Web Token)** é um token de validação que é entregue
ao usuário após a sua primeira autenticação bem-sucedida. O 
objetivo deste token é tornar o processo de autenticação mais 
simples e eficiente, de modo que o token se torne um tipo de 
passe-livre com uma assinatura de "autenticado" marcada em si, 
por exemplo, fazendo com que o sistema possa validar a identidade
do usuário de forma segura sem a necessidade de acessar o banco
de dados. Com isso, o uso de JWT nas autenticações é útil e 
eficaz, pois:

- A autenticação não precisa acessar o banco de dados;
- A autenticação é segura por conta da assinatura criptografada, que mantém a integridade do token;
- A autenticação não precisa interagir diretamente com as credenciais do usuário para validá-lo.

Mas como é feito esse processo de autenticação? Bom, basicamente, 
assim que um usuário se autentica no sistema pela primeira vez, 
o mesmo recebe um token (JWT) que contém:

- Informações e permissões do usuário;
- Data de criação e expiração do token;
- Assinatura de segurança criptografada;

Desta forma, sempre que o usuário fizer uma nova requisição, o 
sistema receberá o seu token (JWT) junto da requisição e assim 
o sistema saberá que aquele é o usuário verídico, pois o token é
como se fosse um passe-livre marcado com "autenticado" (key 
criptografada e única), fazendo com que assim seja liberado o 
acesso ao recurso. 

![Login Authentication](images/LoginAuthentication.png)
![Following Authentications](images/FollowingAuthentications.png)

### 6.1. Partes de um token:

Antes de entendermos como o token é gerado, precisamos
aprender sobre as partes que compõem um token. Todas essas partes
são codificadas em base64url, e elas são:

**Header:**

O cabeçalho ou header é a primeira parte do token, contendo 
informações que instruem o sistema a como lidar com aquele 
token. Sendo essas informações:

- Tipo de token (JWT);
- Algoritmo de assinatura (``HMAC``, ``RSA``, ``ECDSA``).

Exemplo:

      {
          "alg": "HS256", //Tipo de algoritmo de assinatura
          "typ": "JWT"    //Tipo de token
      }

> eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9

**Payload:**

O corpo e a segunda parte do token, contendo informações de 
identificação do usuário e outros detalhes sobre o token. Sendo
essas informações:

- ID do token
- Nome do usuário;
- Tempo de criação e expiração do token;
- Permissões do usuário.

Essas informações são comumente chamadas de "claims". Existem
3 tipos de claims, sendo eles:

- **Claims registrados:** claims já pré-estabelecidos, mas não
obrigatórios.
1. iat (data de emissão);
2. exp (data de expiração);
3. sub (assunto do token);
4. Dentre outros...
- **Claims públicos:** São claims reconhecidos publicamente e 
comumente usados em aplicações.
1. name;
2. email;
3. role (função);
4. Dentre outros...
- **Claims privados:** São claims privados e criados apenas 
para casos específicos de projetos.
1. departament;
2. userId.

Exemplo de Payload:

    {
        "sub": "1234567890", //Assunto
        "name": "John Doe",  //Nome
        "iat": 1516239022    //Data de emissão
    }

> eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ

**Signature:**

A assinatura e terceira parte do token, contendo a própria 
assinatura de identificação que é gerada através de um algoritmo
de assinatura (``HMAC``, no caso deste projeto). Esse algoritmo
de assinatura usa uma chave secreta que não deve ser revelada
ou compartilhada de nenhum modo, pois é ela que mantém a 
integridade do token. Além disso, todas essas três partes são
concatenadas e separadas por um ponto (.) no token.

Exemplo:

    HMACSHA256(                         //Algortimo de assinatura
        base64UrlEncode(header) + "." + //Header
        base64UrlEncode(payload),       //Payload
        secret-key                      //Chave secreta para realizar a assinatura
    )

> eWeSlpStc9jRBPdKItcSZ12Kcc890ktcrOzJd_zl2-M

**Token completo:**

> eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.eWeSlpStc9jRBPdKItcSZ12Kcc890ktcrOzJd_zl2-M

### 6.2. Gerenciamento de tokens com JwtService:

Com a introdução desta seção foi possível notar que existem
diversas etapas que precisam ser realizadas para lidar com
um token, portanto, existe uma service criada especificamente
para realizar os principais processos relacionados aos
tokens, como, por exemplo:

- Geração de token;
- Assinatura do token;
- Geração de chave secreta;
- Extração de informações do token;
- Validação de assinatura.

O objetivo do ``JwtService`` é o mesmo de qualquer outra service,
fornecer as regras de negócio dos tokens para o sistema. Normalmente
quem usa essa service é a ``UserService``, já que os tokens dizem
respeito ao próprio usuário.

### 6.2. Assinatura com chave secreta:

A assinatura de tokens nada mais é do que um código base64url
gerado com base em uma chave secreta e um algoritmo de assinatura.
Esse algortimo basicamente usa a chave secreta para codificá-la
juntamante com o header e o payload do token. Desta forma é
possível evitar ataques de falsificação de tokens, pois apenas
o servidor que tiver a mesma chave secreta poderá validá-los.

    public class JwtService {
    
        private SecretKey key = getKey();

        ...
    }

Inicialmente no projeto nós começamos declarando um atributo do
tipo ``SecretKey`` (Uma interface que estende de outra 
interface chamada ``Key``) chamado "key". Esse atributo por fim
recebe a chave secreta de um método chamado ``getKey()``, que é
responsável por codificar e retornar a chave.

    private SecretKey getKey() {
        SecretKey sk;

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            sk = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return sk;
    }

- ``private SecretKey getKey()``: Declaração do método que
retornará uma ``SecretKey``;
- ``SecretKey sk;``: Declaração da variável que representará a 
chave secreta;
- ``try-catch``: Implementação de um try-catch para o controle
de possíveis exceções;
- ``KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");``:
Instanciação da classe responsável por gerar a chave secreta,
com a seleção do algoritmo de assinatura que será
utilizado, através do método ``.getInstance("HmacSHA256")``;
- ``sk = keyGenerator.generateKey();``: Atribuindo a chave gerada
para a variável ``sk`` com o método ``.generateKey()``;
- ``catch (NoSuchAlgorithmException e)``: catch para caso o
algoritmo de criptografia da assinatura não esteja disponível;
- ``throw new RuntimeException(e);``: Lançamento da exceção em
tempo de execução;
- ``return sk;``: Retorno da chave secreta;

### 6.3. Geração de tokens:

Para gerar um token nós utilizamos uma classe muito importante
chamada ``Jwts``, que tem o objetivo de gerar e analisar tokens.
Mas, vamos por partes, começando pelo fato que a classe Jwts 
configura o header do token sem que nós precisemos lidar com isso
manualmente, portanto, vamos focar na definição das claims do 
payload e na signature. A seguir veremos o código de geração
de token do projeto:

    public String generateToken(String email) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
                .and()
                .signWith(key)
                .compact();
    }

Isso pode parecer muito confuso no momento, mas vamos explicar 
linha por linha para que você possa entender.

- ``public String generateToken(String email)``: A definição de 
um método da classe JwtService, citada anteriormente.
Este método recebe o email do usuário (poderia ser o
username também) como uma String, e retorna o token que também
será uma String;
- ``Map<String, Object> claims = new HashMap<>();``: A instanciação de 
um HashMap, com o tipo da sua chave sendo String e o seu
valor do tipo Object. Esse Map será a representação do conjunto de
claims/payload do nosso token, fazendo sentido já que o
payload nada mais é do que um Json que possui "chaves" e "valores" (claims);
- ``return Jwts.builder()``: O retorno do construtor da classe 
Jwts, que antecede e dá início à estrutura de métodos sequenciais que 
configurará o token;
- ``.claims().add(claims)``: Definição/adição do Map que 
representará os claims/playload do token;
- ``.subject(email)``: Definição do assunto do token;
- ``.issuedAt(new Date(System.currentTimeMillis()))``: Definição
da data de emissão do token;
- ``.expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))``:
Definição do tempo de expiração do token;
- ``.and()``: Separação lógica do encadeamento de métodos(opcional);
- ``.signWith(key)``: Define a chave secreta que será usado para
realizar a assinatura;
- ``.compact();``: Constrói e retorna o token como uma String.

###  6.4. Validação de tokens com JwtFilter:

Em algumas sessões anteriores foi comentado sobre filtros de
segurança padrões e personalizados, que o Spring Security nos
oferece para a configuração de segurança das requisições que
são enviadas ao servidor. Nesta seção nós falaremos de um filtro
personalizado, desenvolvido com o propósito de validar os tokens
que chegam com as requisições do usuário.

    @Component
    public class JwtFilter extends OncePerRequestFilter {
    
        @Autowired
        private JwtService jwtService;
    
        @Autowired
        ApplicationContext applicationContext;
    
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    
            String authHeader = request.getHeader("Authorization");
    
            String token = null;
            String email = null;
    
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7); 
                email = jwtService.extractEmail(token);
            }
    
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
    
                UserDetails userDetails = applicationContext.getBean(MainUserDetailsService.class).loadUserByUsername(email);
    
                if (jwtService.validateToken(token, userDetails)) {
    
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
    
            filterChain.doFilter(request, response);
        }
    }

- ``@Component``: Uma anotação que marca uma classe 
para ser gerenciada pelo container de injeção de dependência do
Spring;
- ``public class JwtFilter extends OncePerRequestFilter``:
Define a classe que representará o filtro de validação dos
tokens. Essa classe estende de uma classe abstrata chamada
``OncePerRequestFilter ``, que faz com que o filtro seja
usado apenas uma vez para cada requisição;
- ``@Autowired``: Uma anotação que marca um atributo que será
alvo de injeção de dependência;
- ``private JwtService jwtService;``: Definição do ``JwtService``,
para que o filro possa utiliza das regras de negócio relacionadas
aos tokens;
- ``ApplicationContext applicationContext;``: Instanciação do
``applicationContext`` para podermos lidar com os beans do 
container do Spring;
- ``@Override``: Uma anotação que marca um método de outra 
classe (herdada) sobreescrito na classe atual (herdeira);
- ``protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException``:
Definição do principal método que será utilizado pelo filtro
para realizar as validações. Os parâmetros ``request``, ``response`` e ``filterChain``
representam a requisição recebida, a resposta para aquela requisição e
a corrente de filtros do Spring Security, respectivamente;
- ``String authHeader = request.getHeader("Authorization");``:
Definição do cabeçalho de autorização (Header Authorization) da
requisição (request) com o método ``.getHeader()``, selecionando
como argumento a String "Authorization" para indicar o cabeçalho
que deve ser coletado;
- ``String token = null;``: Definição da variável (String) que
representará o token;
- ``String email = null;``: Definição da variável (String) que 
representará o email do usuário;
- ``if (authHeader != null && authHeader.startsWith("Bearer ")) {``
Condicional que verifica se o cabeçalho de autorização está ou
não vazio, e se caso ele existir, se ele começa com "Bearer " 
(marcador que antecede o token);
- ``token = authHeader.substring(7);``: Atribuição do token 
para a variável "token", usando o método ``.substring`` da 
do Wrapper String;
- ``email = jwtService.extractEmail(token);``: Atribuição do
email do usuário usando um método do ``jwtService``;
- ``if (email != null && SecurityContextHolder.getContext().getAuthentication() == null)``:
Condicional que verifica se existe algum email de usuário, e se
o SecurityContextHolder já possui alguma autenticação armazenada;
- ``UserDetails userDetails = applicationContext.getBean(MainUserDetailsService.class).loadUserByUsername(email);``
1.  ``UserDetails userDetails``: Definição da variável que 
representará os detalhes do usuário;
2. ``applicationContext.getBean(MainUserDetailsService.class)``:
O ApplicationContext obtém a classe ``MainUserDetailsService``
com o método ``.getBean()``;
3. ``.loadUserByUsername(email)``: Um método utilizado para obter os 
detalhes do usuário (a partir do email) usado pelo ``MainUserDetailsService``;
- ``if (jwtService.validateToken(token, userDetails))``: Condicional
que valida o token pelo seus detalhes de usuário, e caso for
válido, o méotodo retorna "true";
- ``UsernamePasswordAuthenticationToken authToken =
  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());``:
Um token de autenticação baseado em Username e Password é criado e
instanciado em uma variável do tipo ``UsernamePasswordAuthenticationToken``;
- ``authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));``:
O token de autenticação recebe mais alguns detalhes sobre o token
JWT, como o IP do cliente, por exemplo;
- ``SecurityContextHolder.getContext().setAuthentication(authToken);``:
O token de autenticação é atribuido ao ``SecurityContextHolder``, para
que o processo todo do último ``if`` (atual) não seja necessário;
- ``filterChain.doFilter(request, response);``:
A requisição e resposta é enviada para o próximo filtro
do ``filterChain``;