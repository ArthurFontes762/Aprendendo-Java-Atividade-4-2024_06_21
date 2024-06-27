import java.util.Scanner;

public class UserService {
    private final Scanner scanner;

    public UserService(Scanner scanner) {
        this.scanner = scanner;
        Database.criarBanco(); // Garante que o banco de dados está criado ao instanciar o serviço
    }

    public void inserirNomeSobrenome() {
        System.out.print("Digite o nome e sobrenome (separados por espaço): ");
        String[] partesNome = scanner.nextLine().split(" ");
        if (partesNome.length >= 2) {
            User usuario = new User();
            usuario.cadastrarDados(partesNome[0], partesNome[1], null); // Senha inicialmente nula
        } else {
            System.out.println("Formato inválido. Tente novamente.");
        }
    }

    public void criarSenhaManual() {
        System.out.print("Digite o nome e sobrenome para validar a senha: ");
        String[] partesNome = scanner.nextLine().split(" ");
        if (partesNome.length >= 2) {
            User usuario = Database.buscarUsuario(partesNome[0], partesNome[1]);
            if (usuario != null) {
                System.out.print("Digite a senha desejada: ");
                String senha1 = scanner.nextLine();
                System.out.print("Digite novamente a senha: ");
                String senha2 = scanner.nextLine();

                if (senha1.equals(senha2) && senha1.length() >= 8 && senha1.charAt(3) == 'Z') {
                    usuario.cadastrarDados(partesNome[0], partesNome[1], senha1);
                    System.out.println("Senha criada e armazenada com sucesso!");
                } else {
                    System.out.println("As senhas não coincidem ou não atendem aos requisitos.");
                }
            } else {
                System.out.println("Usuário não encontrado.");
            }
        } else {
            System.out.println("Formato inválido. Tente novamente.");
        }
    }

    public void criarSenhaAutomatica() {
        System.out.print("Digite o nome e sobrenome para criar senha automática: ");
        String[] partesNome = scanner.nextLine().split(" ");
        if (partesNome.length >= 2) {
            String nome = partesNome[0];
            String sobrenome = partesNome[1];

            // Verifica se o usuário já está cadastrado no banco de dados
            User usuario = Database.buscarUsuario(nome, sobrenome);
            if (usuario != null) {
                String senhaAutomatica = usuario.gerarSenhaAutomatica();
                usuario.cadastrarDados(nome, sobrenome, senhaAutomatica); // Atualiza a senha no banco de dados
                System.out.println("Senha automática gerada e armazenada: " + senhaAutomatica);
                User.mostrarDados();
            } else {
                System.out.println("Usuário não encontrado no banco de dados.");
            }
        } else {
            System.out.println("Formato inválido. Tente novamente.");
        }
    }

    public void editarNomeSobrenome() {
        System.out.print("Digite o nome e sobrenome a serem editados (separados por espaço): ");
        String[] novasPartesNome = scanner.nextLine().split(" ");
        if (novasPartesNome.length >= 2) {
            String novoNome = novasPartesNome[0];
            String novoSobrenome = novasPartesNome[1];

            // Verifica se o usuário já está cadastrado no banco de dados
            User usuario = Database.buscarUsuario(novoNome, novoSobrenome);
            if (usuario != null) {
                usuario.atualizarNomeSobrenome(novoNome, novoSobrenome); // Atualiza nome e sobrenome do usuário

                // Mostra os dados atualizados
                User.mostrarDados();

                // Atualiza no banco de dados
                Database.atualizarNomeSobrenomeUsuario(novoNome, novoSobrenome, usuario);
            } else {
                System.out.println("Usuário não encontrado no banco de dados.");
            }
        } else {
            System.out.println("Formato inválido. Tente novamente.");
        }
    }
}