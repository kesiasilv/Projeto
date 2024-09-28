# Biblioteca Universitária com Integração Java JDBC e MySQL

O objetivo deste trabalho foi desenvolver um sistema completo que integre a linguagem de programação Java com o banco de dados MySQL. O projeto comtempla a construção dos modelos conceitual, lógico e físico do banco de dados, bem como a implementação de uma aplicação Java que interaja diretamente com o banco para realizar operações de inserção e consulta de dados (CRUD).

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
    sexo ENUM ('M', 'F')
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

A integração com o banco de dados MySQL é feita utilizando a API JDBC. A seguir mostra como foi configurar a conexão JDBC:

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

O sistema implementa operações CRUD para cada tabela (create, read) . A seguir esta como foi implementado em todas as tabelas `usuarios`.

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

#### Create (Inserir Categoria)

```java
public boolean inserir(Categorias categoria) {
        String sql = "INSERT INTO categorias (cod_categoria, nome_categoria, descricao) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, categoria.getCod_categoria());
            ps.setString(2, categoria.getNome_categoria());
            ps.setString(3, categoria.getDescricao());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
```

#### Read (Ler Categoria)

```java
public List<Categorias> consultar() {
        List<Categorias> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categorias";

        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Categorias categoria = new Categorias();
                categoria.setCod_categoria(rs.getInt("cod_categoria"));
                categoria.setNome_categoria(rs.getString("nome_categoria"));
                categoria.setDescricao(rs.getString("descricao"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }
```

#### Create (Inserir Editora)

```java
public boolean inserir(Editora editora) {
        String sql = "INSERT INTO editora (nome_editora, endereco, contato) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, editora.getNome_editora());
            ps.setString(2, editora.getEndereco());
            ps.setString(3, editora.getContato());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
```

#### Read (Ler Editora)

```java
 public List<Editora> consultar() {
        List<Editora> editoras = new ArrayList<>();
        String sql = "SELECT * FROM editora";

        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Editora editora = new Editora();
                editora.setId_editora(rs.getInt("id_editora"));
                editora.setNome_editora(rs.getString("nome_editora"));
                editora.setEndereco(rs.getString("endereco"));
                editora.setContato(rs.getString("contato"));
                editoras.add(editora);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return editoras;
    }
```

#### Create (Inserir Emprestimos)

```java
public boolean inserir(Emprestimos emprestimo) {
        String sql = "INSERT INTO emprestimos (cod_emprestimo, data_emprestimo, data_devolucao, ISBN, CPF) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, emprestimo.getCod_emprestimo());
            ps.setDate(2, emprestimo.getData_emprestimo());
            ps.setDate(3, emprestimo.getData_devolucao());
            ps.setInt(4, emprestimo.getISBN());
            ps.setString(5, emprestimo.getCPF());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
```

#### Read (Ler Emprestimos)
```java
public List<Emprestimos> consultar() {
        List<Emprestimos> emprestimos = new ArrayList<>();
        String sql = "SELECT * FROM emprestimos";

        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Emprestimos emprestimo = new Emprestimos();
                emprestimo.setCod_emprestimo(rs.getInt("cod_emprestimo"));
                emprestimo.setData_emprestimo(rs.getDate("data_emprestimo"));
                emprestimo.setData_devolucao(rs.getDate("data_devolucao"));
                emprestimo.setISBN(rs.getInt("ISBN"));
                emprestimo.setCPF(rs.getString("CPF"));
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprestimos;
    }

```

#### Create (Inserir Livros)

```java
public boolean inserir(Livros livro) {
        String sql = "INSERT INTO livros (ISBN, titulo, ano, cod_categoria, id_editora) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, livro.getISBN());
            ps.setString(2, livro.getTitulo());
            ps.setString(3, livro.getAno());
            ps.setInt(4, livro.getCod_categoria());
            ps.setInt(5, livro.getId_editora());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

```

#### Read (Ler Livros)

```java
public List<Livros> consultar() {
        List<Livros> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";

        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Livros livro = new Livros();
                livro.setISBN(rs.getInt("ISBN"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAno(rs.getString("ano"));
                livro.setCod_categoria(rs.getInt("cod_categoria"));
                livro.setId_editora(rs.getInt("id_editora"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }
```

#### Create (Inserir Usuarios)

```java
public boolean inserir(Usuarios usuario) {
        String sql = "INSERT INTO usuarios (CPF, nome, email, data_nascimento, endereco) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, usuario.getCpf());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getEmail());
            ps.setDate(4, usuario.getData_nascimento()); 
            ps.setString(5, usuario.getEndereco());
            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
```

#### Read (Ler Usuarios)

```java
public List<Usuarios> consultar() {
        List<Usuarios> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setCpf(rs.getString("CPF"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setData_nascimento(rs.getDate("data_nascimento")); 
                usuario.setEndereco(rs.getString("endereco"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }
```

/*
 * A classe `BibliotecaBanco` é responsável por estabelecer a conexão com o banco de dados de uma biblioteca
 * e executar as operações CRUD (Criar, Ler) para as diferentes entidades do sistema, 
 * como `Usuarios`, `Autores`, `Editoras`, `Categorias`, `Livros`, e `Emprestimos`.
 *
 * A main() é o ponto de entrada da aplicação e, dentro dela, as seguintes ações ocorrem:
 *
 * 1. **Estabelecimento da conexão com o banco de dados**: 
 *    Através do método `getConnection()`, uma conexão com o banco MySQL é estabelecida usando a URL, 
 *    o nome de usuário e a senha definidos. Caso a conexão seja bem-sucedida, ela é usada em todas 
 *    as operações subsequentes com o banco de dados.
 *
 * 2. **Operações CRUD com Usuários**:
 *    - Inserção de um novo usuário no banco.
 *    - Consulta e listagem de todos os usuários cadastrados no banco.
 *
 * 3. **Operações CRUD com Autores**:
 *    - Inserção de um novo autor no banco.
 *    - Consulta e listagem de todos os autores cadastrados.
 *
 * 4. **Operações CRUD com Editoras**:
 *    - Inserção de uma nova editora.
 *    - Consulta e listagem de editoras no sistema.
 *
 * 5. **Operações CRUD com Categorias**:
 *    - Inserção de uma nova categoria.
 *    - Consulta e listagem de categorias disponíveis.
 *
 * 6. **Operações CRUD com Livros**:
 *    - Inserção de um novo livro.
 *    - Consulta e listagem de todos os livros cadastrados.
 *
 * 7. **Operações CRUD com Empréstimos**:
 *    - Registro de um novo empréstimo.
 *    - Consulta e listagem de todos os empréstimos realizados.
 *
 * 8. **Encerramento da conexão**:
 *    Após todas as operações, a conexão com o banco de dados é fechada.
 *
 * O programa usa Data Access Objects (DAOs) para abstrair a lógica de acesso ao banco de dados para cada entidade.
 */


## Conclusão

Este projeto de Biblioteca Universitária demonstra a integração entre Java e MySQL, utilizando a API JDBC para realizar operações CRUD em um banco de dados relacional. Ao longo do desenvolvimento, foram aplicados conceitos fundamentais de modelagem de banco de dados, com a criação dos modelos conceitual, lógico e físico, além da implementação de uma aplicação Java capaz de manipular os dados das tabelas de maneira eficiente e segura.

A solução atende aos requisitos propostos, permitindo a inserção e consulta de dados de usuários, autores, editoras, categorias, livros e empréstimos, garantindo integridade referencial e consistência no banco de dados. A utilização de boas práticas de programação em Java, junto com o uso de prepared statements para evitar injeção de SQL e a correta gestão de conexões, torna o sistema robusto e confiável.

O projeto também oferece uma base sólida para futuras expansões, como a adição de funcionalidades mais avançadas, relatórios, e melhorias no gerenciamento da biblioteca.
