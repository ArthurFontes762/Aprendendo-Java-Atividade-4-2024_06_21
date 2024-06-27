import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:sqlite:BancoUsers.db"; // URL de conexão com o banco de dados SQLite

    // Método para conectar ao banco de dados SQLite
    private static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Método para criar o banco de dados e a tabela 'usuarios', se ainda não existirem
    public static void criarBanco() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR NOT NULL, sobrenome VARCHAR NOT NULL, senha VARCHAR)";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.executeUpdate();
            System.out.println("Tabela 'usuarios' criada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    // Método para salvar um usuário no banco de dados
    public static void salvarUsuario(String nome, String sobrenome, String senha) {
        String sql = "INSERT INTO usuarios (nome, sobrenome, senha) VALUES (?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, sobrenome);
            pstmt.setString(3, senha);
            pstmt.executeUpdate();

            System.out.println("Usuário cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    // Método para buscar usuário pelo nome e sobrenome
    public static User buscarUsuario(String nome, String sobrenome) {
        User usuario = null;
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios WHERE nome = ? AND sobrenome = ?")) {
            stmt.setString(1, nome);
            stmt.setString(2, sobrenome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new User();
                usuario.nome = rs.getString("nome");
                usuario.sobrenome = rs.getString("sobrenome");
                usuario.senha = rs.getString("senha");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    // Método para atualizar nome e sobrenome do usuário
    public static void atualizarNomeSobrenomeUsuario(String novoNome, String novoSobrenome, User usuario) {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement stmt = conn.prepareStatement("UPDATE usuarios SET nome = ?, sobrenome = ? WHERE nome = ? AND sobrenome = ?")) {
            stmt.setString(1, novoNome);
            stmt.setString(2, novoSobrenome);
            stmt.setString(3, usuario.nome);
            stmt.setString(4, usuario.sobrenome);
            stmt.executeUpdate();

            // Atualiza os dados do usuário localmente também
            usuario.nome = novoNome;
            usuario.sobrenome = novoSobrenome;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}