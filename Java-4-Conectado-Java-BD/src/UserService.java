import java.util.Scanner;

public class UserService {
    private final User usuario;
    private final Scanner scanner;
    private final Database database;

    public UserService(Scanner scanner, Database database) {
        this.scanner = scanner;
        this.database = database;
        this.usuario = new User();
    }

    public void inserirNomeSobrenome() {
        System.out.print("Digite o nome e sobrenome (separados por espaço): ");
        String[] partesNome = scanner.nextLine().split(" ");
        if (partesNome.length >= 2) {
            usuario.setNome(partesNome[0]);
            usuario.setSobrenome(partesNome[1]);
            database.inserirUser(usuario.getNome(), usuario.getSobrenome(), usuario.getSenha());
            System.out.println("Nome e sobrenome cadastrados com sucesso!");
        } else {
            System.out.println("Formato inválido. Tente novamente.");
        }
    }

    public void criarSenhaManual() {
        if (usuario.isCadastrado()) {
            System.out.print("Digite a senha desejada: ");
            String senha1 = scanner.nextLine();
            System.out.print("Digite novamente a senha: ");
            String senha2 = scanner.nextLine();

            if (senha1.equals(senha2) && senha1.length() >= 8 && senha1.charAt(3) == 'Z') {
                usuario.setSenha(senha1);
                database.atualizarUser(usuario.getNome(), usuario.getSobrenome(), usuario.getSenha());
                System.out.println("Senha criada e armazenada com sucesso!");
            } else {
                System.out.println("As senhas não coincidem ou não atendem aos requisitos.");
            }
        } else {
            System.out.println("Por favor, cadastre o nome e sobrenome primeiro.");
        }
    }

    public void criarSenhaAutomatica() {
        if (usuario.isCadastrado()) {
            String senhaAutomatica = (usuario.getNome().substring(0, Math.min(usuario.getNome().length(), 4)).toUpperCase() +
                    usuario.getSobrenome().substring(Math.max(usuario.getSobrenome().length() - 4, 0)).toUpperCase());
            usuario.setSenha(senhaAutomatica);
            database.atualizarUser(usuario.getNome(), usuario.getSobrenome(), usuario.getSenha());
            System.out.println("Senha automática gerada e armazenada: " + senhaAutomatica);
        } else {
            System.out.println("Por favor, cadastre o nome e sobrenome primeiro.");
        }
    }

    public void editarNomeSobrenome() {
        if (usuario.isCadastrado()) {
            System.out.print("Digite o novo nome e sobrenome (separados por espaço): ");
            String[] novasPartesNome = scanner.nextLine().split(" ");
            if (novasPartesNome.length >= 2) {
                usuario.setNome(novasPartesNome[0]);
                usuario.setSobrenome(novasPartesNome[1]);
                database.atualizarUser(usuario.getNome(), usuario.getSobrenome(), usuario.getSenha());
                System.out.println("Nome e sobrenome editados com sucesso!");
            } else {
                System.out.println("Formato inválido. Tente novamente.");
            }
        } else {
            System.out.println("Por favor, cadastre o nome e sobrenome primeiro.");
        }
    }

    public void mostrarDados() {
        if (usuario.isCadastrado()) {
            System.out.println("\n===== Dados Cadastrados =====");
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Sobrenome: " + usuario.getSobrenome());
            System.out.println("Senha: " + (usuario.getSenha() != null ? usuario.getSenha() : "Não cadastrada"));
        } else {
            System.out.println("Por favor, cadastre o nome e sobrenome primeiro.");
        }
    }
}