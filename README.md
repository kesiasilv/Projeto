# Biblioteca Universitária com Integração Java JDBC e MySQL

O objetivo deste trabalho é desenvolver um sistema completo que integre a linguagem de programação Java com o banco de dados MySQL. O projeto deverá contemplar a construção dos modelos conceitual, lógico e físico do banco de dados, bem como a implementação de uma aplicação Java que interaja diretamente com o banco para realizar operações de inserção, consulta, atualização e exclusão de dados (CRUD).

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
<dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.33</version>
        </dependency>
    </dependencies>
```

### Conexão com o Banco de Dados

O código Java abaixo estabelece uma conexão com o banco de dados MySQL. Além de lidar com a possibilidade de erro na tentativa de conexão, o programa também exibe uma mensagem informando se a conexão foi ou não bem-sucedida. Caso ocorra um erro durante a conexão, uma exceção será lançada com detalhes específicos. Além disso, há uma verificação e tratamento de possíveis erros ao fechar a conexão, exibindo mensagens adequadas em ambos os casos.

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BibliotecaBanco {

    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca"; // URL correta do banco de dados
    private static final String USUARIO = "root"; // Nome de usuário do MySQL
    private static final String SENHA = "c@tolic@"; // Senha do MySQL

    public static void main(String[] args) {
        Connection conexao = null;

        // Estabelecendo a conexão
        conexao = getConnection();
        System.out.println("Conexão estabelecida com sucesso!");

............................

// Fechando a conexão
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar a conexão: " + ex.getMessage());
            }
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao estabelecer conexão com o banco de dados", e);
        }
    }
}
```

### Operações CRUD

O sistema implementa operações CRUD para cada tabela (create, read, update e delete) . A seguir esta como foi implementado para a tabela `usuarios`.

#### Create (Inserir Usuário)

```java
// Inserção de dados (CREATE)
    public boolean inserir(Autores autor) {
        String sql = "INSERT INTO autores (nome, nacionalidade, sexo) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, autor.getNome());
            ps.setString(2, autor.getNacionalidade());
            ps.setString(3, autor.getSexo());
            ps.execute();
            
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

```

#### Read (Ler Usuário)

```java
// Consulta de dados (READ)
    public List<Autores> consultar() {
        List<Autores> autores = new ArrayList<>();
        String sql = "SELECT * FROM autores";

        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Autores autor = new Autores();
                autor.setId_autor(rs.getInt("id_autor"));
                autor.setNome(rs.getString("nome"));
                autor.setNacionalidade(rs.getString("nacionalidade"));
                autor.setSexo(rs.getString("sexo"));
                autores.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autores;
    }

```

#### Update (Atualizar Usuário)

```java
 // Atualização de dados (UPDATE)
    public boolean atualizar(Autores autor) {
        String sql = "UPDATE autores SET nome = ?, nacionalidade = ?, sexo = ? WHERE id_autor = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, autor.getNome());
            ps.setString(2, autor.getNacionalidade());
            ps.setString(3, autor.getSexo());
            ps.setInt(4, autor.getId_autor());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

```

#### Delete (Deletar Usuário)

```java
// Exclusão de dados (DELETE)
    public void excluir(int id) {
        String sql = "DELETE FROM autores WHERE id_autor = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
```

## Conclusão

Este projeto de Biblioteca Universitária demonstra a integração entre Java e MySQL, utilizando a API JDBC para realizar operações CRUD em um banco de dados relacional. Ao longo do desenvolvimento, foram aplicados conceitos fundamentais de modelagem de banco de dados, com a criação dos modelos conceitual, lógico e físico, além da implementação de uma aplicação Java capaz de manipular os dados das tabelas de maneira eficiente e segura.

A solução atende aos requisitos propostos, permitindo a inserção, consulta, atualização e exclusão de dados de usuários, autores, editoras, categorias, livros e empréstimos, garantindo integridade referencial e consistência no banco de dados. A utilização de boas práticas de programação em Java, junto com o uso de prepared statements para evitar injeção de SQL e a correta gestão de conexões, torna o sistema robusto e confiável.

O projeto também oferece uma base sólida para futuras expansões, como a adição de funcionalidades mais avançadas, relatórios, e melhorias no gerenciamento da biblioteca.
