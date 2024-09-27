# Projeto de Banco de Dados Integrado com JAVA

## Descrição
Este projeto consiste na criação de um sistema de gerenciamento de uma biblioteca, integrando um banco de dados MySQL com uma aplicação Java. O objetivo é permitir o cadastro, consulta, atualização e exclusão de informações relacionadas a livros, autores, editoras, usuários e empréstimos.

## Estrutura do Projeto
- **BibliotecaBanco**: Contém os arquivos Java responsáveis pela lógica de acesso aos dados (DAO).
- **ScriptBiblioteca.sql**: Script SQL para criação das tabelas e inserção de dados iniciais no banco de dados.
- **biblioteca_UNI.sql**: Script SQL gerado pelo MySQL Workbench para a engenharia reversa do banco de dados.

## Funcionalidades
- **Cadastro de Livros**: Permite adicionar novos livros ao sistema, incluindo informações como título, ano, categoria e editora.
- **Gerenciamento de Autores**: Cadastro e manutenção de informações sobre autores.
- **Gerenciamento de Editoras**: Cadastro e manutenção de informações sobre editoras.
- **Cadastro de Usuários**: Permite adicionar novos usuários ao sistema, incluindo informações pessoais e de contato.
- **Empréstimos de Livros**: Funcionalidade para registrar e gerenciar empréstimos de livros aos usuários.

## Tecnologias Utilizadas
- **Java**: Linguagem de programação utilizada para desenvolver a aplicação.
- **MySQL**: Sistema de gerenciamento de banco de dados utilizado para armazenar as informações.
- **MySQL Workbench**: Ferramenta utilizada para modelagem e engenharia reversa do banco de dados.

## Como Executar o Projeto
1. Clone o repositório:
   ```bash
   git clone https://github.com/kesiasilv/Projeto.git
   ```
2. Importe o projeto em sua IDE Java de preferência.
3. Configure a conexão com o banco de dados MySQL no arquivo de configuração.
4. Execute o script `ScriptBiblioteca.sql` para criar as tabelas e inserir os dados iniciais.
5. Compile e execute a aplicação Java.

## Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e enviar pull requests.

## Licença
Este projeto está licenciado sob a Licença MIT. Consulte o arquivo LICENSE para obter mais informações.
