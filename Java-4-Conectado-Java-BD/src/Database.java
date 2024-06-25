import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:sqlite:BancoUser.db";

    public Database() {
        // Cria a tabela se ela não existir
        try (Connection conn = connect()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS usuarios ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nome TEXT NOT NULL,"
                    + "sobrenome TEXT NOT NULL,"
                    + "senha TEXT"
                    + ");";
            try (PreparedStatement pstmt = conn.prepareStatement(createTableSQL)) {
                pstmt.execute();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    private Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return null;
        }
    }

    public void inserirUser(String nome, String sobrenome, String senha) {
        String sql = "INSERT INTO usuarios(nome, sobrenome, senha) VALUES(?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, sobrenome);
            pstmt.setString(3, senha);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    public void atualizarUser(String nome, String sobrenome, String senha) {
        String sql = "UPDATE usuarios SET senha = ? WHERE nome = ? AND sobrenome = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, senha);
            pstmt.setString(2, nome);
            pstmt.setString(3, sobrenome);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }
}