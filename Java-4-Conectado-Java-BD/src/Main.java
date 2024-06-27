import java.util.Scanner;

// A regra é clara: Se tá funcionando, não mexe em mais nada.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService(scanner);

        while (true) {
            exibirMenu();

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a linha após o nextInt()

            switch (opcao) {
                case 1:
                    userService.inserirNomeSobrenome();
                    break;
                case 2:
                    userService.criarSenhaManual();
                    break;
                case 3:
                    userService.criarSenhaAutomatica();
                    break;
                case 4:
                    userService.editarNomeSobrenome();
                    break;
                case 5:
                    User.mostrarDados();
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

    private static void exibirMenu() {
        System.out.println("\nEstá em fase de testes.");
        System.out.println("1. Inserir nome e sobrenome");
        System.out.println("2. Criar senha manualmente");
        System.out.println("3. Criar senha automaticamente");
        System.out.println("4. Editar nome ou sobrenome");
        System.out.println("5. Mostrar dados cadastrados");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
    }
}