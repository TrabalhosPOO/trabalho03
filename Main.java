import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(
                    "\n\nO que voce deseja fazer?\n1- Adicionar respostas de uma disciplina\n2- Gerar o resultado de uma disciplina\n3- Sair");
            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1:

                    break;

                case 2:

                    break;

                case 3:
                System.out.println("\nSaindo...");
                    System.exit(1);
                    break;

                default:
                    
                    try {
                        new ProcessBuilder("clear").inheritIO().start().waitFor();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Opcao invalida, tente novamente");
                    main(args);
            }
            scanner.close();
        }

    }
}