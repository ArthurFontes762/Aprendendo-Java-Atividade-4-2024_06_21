import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database database = new Database();
        UserService usuarioService = new UserService(scanner, database);

        while (true) {
            System.out.println("\nEstá em fase de testes.");
            System.out.println("1. Inserir nome e sobrenome");
            System.out.println("2. Criar senha manualmente");
            System.out.println("3. Criar senha automaticamente");
            System.out.println("4. Editar nome ou sobrenome");
            System.out.println("5. Mostrar dados cadastrados");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a linha após o nextInt()

            switch (opcao) {
                case 1:
                    usuarioService.inserirNomeSobrenome();
                    break;
                case 2:
                    usuarioService.criarSenhaManual();
                    break;
                case 3:
                    usuarioService.criarSenhaAutomatica();
                    break;
                case 4:
                    usuarioService.editarNomeSobrenome();
                    break;
                case 5:
                    usuarioService.mostrarDados();
                    break;
                case 6:
                    System.out.println("Encerrando o programa, obrigado por testar...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}