# Desafio programação - Descrição

Você recebeu um arquivo CNAB com os dados das movimentações finanaceira de várias lojas.
Objetivo: criar uma maneira para que estes dados sejam importados para um banco de dados.

Tarefa: criar uma interface web que aceite upload do [arquivo CNAB](https://github.com/ByCodersTec/desafio-ruby-on-rails/blob/master/CNAB.txt), normalize os dados e armazene-os em um banco de dados relacional e exiba essas informações em tela.

Requisitos:

- A aplicação deve interpretar ("parsear") o arquivo recebido, normalizar os dados, e salvar corretamente a informação em um banco de dados relacional, **de acordo com as documentações** que estão logo abaixo;
- Exibir uma lista das operações importadas por lojas, e nesta lista deve conter um totalizador do saldo em conta;

Informações importantes:
## Documentação do CNAB

| Descrição do campo  | Inicio | Fim | Tamanho | Comentário
| ------------- | ------------- | -----| ---- | ------
| Tipo  | 1  | 1 | 1 | Tipo da transação
| Data  | 2  | 9 | 8 | Data da ocorrência
| Valor | 10 | 19 | 10 | Valor da movimentação. *Obs.* O valor encontrado no arquivo precisa ser divido por cem(valor / 100.00) para normalizá-lo.
| CPF | 20 | 30 | 11 | CPF do beneficiário
| Cartão | 31 | 42 | 12 | Cartão utilizado na transação 
| Hora  | 43 | 48 | 6 | Hora da ocorrência atendendo ao fuso de UTC-3
| Dono da loja | 49 | 62 | 14 | Nome do representante da loja
| Nome loja | 63 | 81 | 19 | Nome da loja

## Documentação sobre os tipos das transações

| Tipo | Descrição | Natureza | Sinal |
| ---- | -------- | --------- | ----- |
| 1 | Débito | Entrada | + |
| 2 | Boleto | Saída | - |
| 3 | Financiamento | Saída | - |
| 4 | Crédito | Entrada | + |
| 5 | Recebimento Empréstimo | Entrada | + |
| 6 | Vendas | Entrada | + |
| 7 | Recebimento TED | Entrada | + |
| 8 | Recebimento DOC | Entrada | + |
| 9 | Aluguel | Saída | - |

### Referência

Este desafio foi baseado neste outro desafio: https://github.com/lschallenges/data-engineering

# Setup

## Requisitos Técnicos

Para executar esta aplicação, é necessário instalar em seu computador:

- Java (preferencialmente 11 ou superior - https://www.oracle.com/java/technologies/downloads/);

- Maven (https://maven.apache.org/install.html)

- Node (https://nodejs.org/en/download/)

- MySQL (https://www.mysql.com/downloads/)

## Configuração

A primeira coisa a se fazer é criar o esquema que irá conter o banco de dados no MySQL.

Para isso, abra sua conexão no MySQL (por padrão a instância ficará no endereço http://localhost:3306) e execute o script contido na pasta '..\desafio-dev-fullstack\backend\src\main\resources\db\scripts' (se seu MySQL está configurado em outra porta ou com outro usuário/senha que não o padrão de instalção, é necessário alterar os parâmetros no arquivo 'config.properties' do backend);

Uma vez que a conexão com o MySQL está aberta e o esquema foi criado, existem 2 maneiras de executar o projeto:

### Executar os projetos 'backend' e 'frontend' separadamente:

Para tanto, basta executar os projetos como se fossem dois projetos distintos através de sua IDE de preferência

#### Backend:

Antes de executar o projeto 'backend', certifique-se de executar o comando:

'mvn clean install'

Por padrão, o projeto 'backend' irá ser executado no endereço 'http://localhost:8080'

Ao executar o backend, é possível executar os testes para avaliar a cobertura, testar as chamadas de API via Postamn ou semelhantes;

#### Frontend:

Antes de executar o projeto 'frontend', certifique-se de executar o comando

'npm install' 

Por padrão, o projeto 'frontend' irá ser executado no endereço 'http://localhost:3000'

Note que para funcionar corretamente, é necessário que o projeto 'backend' esteja sendo executado juntamente com o 'frontend';

### Executar os projetos em conjunto:

Para executar os projetos em conjunto, vá até seu terminal, navegue até a pasta 'backend' do projeto e execute o comando:

'mvn clean package'

Esse comando vai se encarregar de construir o build de forma correta (Caso ocorra algum problema durante este processo de build, certifique-se que as versões '<nodeVersion>v14.15.5</nodeVersion>' e '<npmVersion>6.14.11</npmVersion>' do plugin no arquivo 'pom.xml' do backend são as mesmas instaladas em seu computador).

Ao final desse processo, será criada uma pasta 'target' dentro de 'backend', e, dentro desta pasta, um arquivo .jar contendo o nome do projeto.

Para executar, basta rodar o segunte comando na pasta 'target':

'java -jar {nomeDoArquivo}.jar'

Após executar este comando, acesse 'http://localhost:8080/' e você conseguirá interagir com a aplicação sem problemas

## Interagindo com a aplicação

Ao executar a aplicação e acessá-la através da URL, você verá uma tela simples com um formulário através do qual é possível fazer o upload de um arquivo de texto;

Uma vez que seja feito o upload, o arquivo será enviado para o backend, que será processado de acordo com os requisitos e os dados serão salvos no banco de dados correspondente.

Após esse processo, os dados já processados são carregados do backend e mostrados no formato de uma tabela para o usuário

Ao final da tabela, é mostrado um campo com o saldo em conta após todas as movimentações listadas

## Documentação API

Para documentar a API, foi utilizado o Swagger. Sendo assim, para acessar a documentação, basta executar o 'backend' do projeto (instruções para executar foram descritas anteriormente) e, uma vez que a aplicação estiver rodando acesse:

'http://localhost:8080/swagger-ui.html'

A documentação dos endpoints implementados para a aplicação estarão em 'cnab-controller'

Para se ter uma noção básica, foram implementados 2 endpoints:

(GET) '/desafio/server/cnab' -> Retorna uma lista de transações salvas do arquivo CNAB

(POST) '/desafio/server/cnab' -> Salva os dados do arquivo de texto CNAB

