public class User {
    public static String nome;
    public static String sobrenome;
    public static String senha;

    // Método para cadastrar dados do usuário
    public void cadastrarDados(String nome, String sobrenome, String senha) {
        if (nome != null && sobrenome != null) {
            this.nome = nome;
            this.sobrenome = sobrenome;
            this.senha = senha;
            Database.salvarUsuario(nome, sobrenome, senha); // Salva o usuário no banco de dados
            System.out.println("Dados cadastrados com sucesso!");
        } else {
            System.out.println("Nome e sobrenome não podem ser nulos.");
        }
    }

    public String gerarSenhaAutomatica() {
        if (nome != null && sobrenome != null) {
            return (nome.substring(0, Math.min(nome.length(), 4)).toUpperCase() +
                    sobrenome.substring(Math.max(sobrenome.length() - 4, 0)).toUpperCase());
        }
        return null;
    }

    public static void mostrarDados() {
        if (nome != null && sobrenome != null) {
            System.out.println("\n===== Dados Cadastrados =====");
            System.out.println("Nome: " + nome);
            System.out.println("Sobrenome: " + sobrenome);
            System.out.println("Senha: " + senha); // Não exibir diretamente a senha
        } else {
            System.out.println("Por favor, cadastre o nome e sobrenome primeiro.");
        }
    }

    // Método para atualizar nome e sobrenome
    public void atualizarNomeSobrenome(String novoNome, String novoSobrenome) {
        if (this.nome != null && this.sobrenome != null) {
            this.nome = novoNome;
            this.sobrenome = novoSobrenome;
            System.out.println("Nome e sobrenome atualizados com sucesso!");
        } else {
            System.out.println("Por favor, cadastre o nome e sobrenome primeiro.");
        }
    }
}