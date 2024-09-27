# Biblioteca Universitária com Integração Java JDBC e MySQL

Este projeto consiste em um sistema de gerenciamento de uma biblioteca universitária utilizando **Java** e **MySQL**, integrados através da API JDBC (Java Database Connectivity). O sistema permite gerenciar usuários, autores, editoras, categorias de livros, livros e empréstimos, utilizando as operações CRUD (Create, Read, Update e Delete) para manipulação dos dados.

## Estrutura do Banco de Dados

O banco de dados **Biblioteca** foi projetado com as seguintes tabelas:

### 1. **Usuários**
Esta tabela armazena as informações dos usuários da biblioteca, como CPF, nome, email, data de nascimento e endereço.
```sql
CREATE TABLE usuarios (
    CPF VARCHAR(13) PRIMARY KEY NOT NULL,
    nome VARCHAR(40),
    email VARCHAR(30),
    data_nascimento DATE NOT NULL,
    endereco VARCHAR(60)
);
```

### 2. **Autores**
Tabela para armazenar os autores dos livros, com identificador único (ID), nome, nacionalidade e sexo.
```sql
CREATE TABLE autores (
    id_autor INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome VARCHAR(20),
    nacionalidade VARCHAR(20),
    sexo VARCHAR(1)
);
```

### 3. **Editoras**
Esta tabela armazena as editoras dos livros, contendo nome da editora, endereço e informações de contato.
```sql
CREATE TABLE editora (
    id_editora INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nome_editora VARCHAR(40) NOT NULL UNIQUE,
    endereco VARCHAR(60),
    contato VARCHAR(10)
);
```

### 4. **Categorias**
Tabela responsável pela categorização dos livros, com código único, nome e descrição da categoria.
```sql
CREATE TABLE categorias (
    cod_categoria INT PRIMARY KEY NOT NULL,
    nome_categoria VARCHAR(40) NOT NULL UNIQUE,
    descricao TEXT
);
```

### 5. **Livros**
Tabela que contém os livros disponíveis na biblioteca, associando-os a uma categoria e editora.
```sql
CREATE TABLE livros (
    ISBN INT PRIMARY KEY NOT NULL,
    titulo VARCHAR(40),
    ano VARCHAR(4),
    cod_categoria INT,
    id_editora INT,
    FOREIGN KEY (cod_categoria) REFERENCES categorias(cod_categoria),
    FOREIGN KEY (id_editora) REFERENCES editora(id_editora)
);
```

### 6. **Empréstimos**
Tabela que controla os empréstimos realizados pelos usuários, associando um livro e um usuário a uma data de empréstimo e devolução.
```sql
CREATE TABLE emprestimos (
    cod_emprestimo INT PRIMARY KEY NOT NULL,
    data_emprestimo DATE NOT NULL,
    data_devolucao DATE NOT NULL,
    ISBN INT,
    CPF VARCHAR(13),
    FOREIGN KEY (ISBN) REFERENCES livros(ISBN),
    FOREIGN KEY (CPF) REFERENCES usuarios(CPF)
);
```

## Configuração do Banco de Dados

1. **Criando o Banco de Dados**
   ```sql
   CREATE DATABASE Biblioteca;
   ```

2. **Definindo Permissões**
   ```sql
   GRANT ALL PRIVILEGES ON biblioteca.* TO 'root'@'localhost';
   FLUSH PRIVILEGES;
   ```

3. **Usando o Banco de Dados**
   ```sql
   USE Biblioteca;
   ```

## Integração JDBC

A integração com o banco de dados MySQL é feita utilizando a API JDBC. A seguir está um exemplo básico de como configurar a conexão JDBC:

### Dependências
Adicione o driver JDBC MySQL ao seu projeto. Se você estiver usando Maven, adicione a seguinte dependência no seu `pom.xml`:

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.31</version>
</dependency>
```

### Conexão com o Banco de Dados

O código Java para estabelecer a conexão com o banco de dados seria semelhante a este:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDados {
    private static final String URL = "jdbc:mysql://localhost:3306/Biblioteca";
    private static final String USUARIO = "root";
    private static final String SENHA = "sua_senha";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
```

### Operações CRUD

O sistema implementa operações CRUD para cada tabela. A seguir está um exemplo de como fazer o CRUD para a tabela `usuarios`.

#### Create (Inserir Usuário)

```java
public void inserirUsuario(String cpf, String nome, String email, String dataNascimento, String endereco) {
    String sql = "INSERT INTO usuarios (CPF, nome, email, data_nascimento, endereco) VALUES (?, ?, ?, ?, ?)";
    
    try (Connection conn = ConexaoBancoDados.conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
         
        pstmt.setString(1, cpf);
        pstmt.setString(2, nome);
        pstmt.setString(3, email);
        pstmt.setDate(4, java.sql.Date.valueOf(dataNascimento));
        pstmt.setString(5, endereco);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
```

#### Read (Ler Usuário)

```java
public Usuario buscarUsuario(String cpf) {
    String sql = "SELECT * FROM usuarios WHERE CPF = ?";
    Usuario usuario = null;
    
    try (Connection conn = ConexaoBancoDados.conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
         
        pstmt.setString(1, cpf);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            usuario = new Usuario(rs.getString("CPF"), rs.getString("nome"), rs.getString("email"), 
                                  rs.getDate("data_nascimento"), rs.getString("endereco"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
    return usuario;
}
```

#### Update (Atualizar Usuário)

```java
public void atualizarUsuario(String cpf, String nome, String email, String endereco) {
    String sql = "UPDATE usuarios SET nome = ?, email = ?, endereco = ? WHERE CPF = ?";
    
    try (Connection conn = ConexaoBancoDados.conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
         
        pstmt.setString(1, nome);
        pstmt.setString(2, email);
        pstmt.setString(3, endereco);
        pstmt.setString(4, cpf);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
```

#### Delete (Deletar Usuário)

```java
public void deletarUsuario(String cpf) {
    String sql = "DELETE FROM usuarios WHERE CPF = ?";
    
    try (Connection conn = ConexaoBancoDados.conectar();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
         
        pstmt.setString(1, cpf);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
```

## Conclusão

Este projeto implementa um sistema de biblioteca universitária completo, utilizando JDBC para a integração entre Java e MySQL. Ele permite realizar as operações CRUD em todas as tabelas, garantindo o gerenciamento de usuários, autores, editoras, categorias, livros e empréstimos de forma eficiente e escalável.
