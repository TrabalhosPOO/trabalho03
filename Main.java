import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Classes.Aluno;
import Classes.Disciplina;

public class Main {
    
    private static Scanner scanner;
    
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
      
        while (true) {
            exibirMenu();
            int menu = obterOpcaoMenu();

            switch (menu) {
                case 1:
					limparConsole();
                    adicionarRespostas();
                    break;

                case 2:
					limparConsole();
                    exibirResultado();
                    break;

                case 3:
					limparConsole();
                    encerrarPrograma();
                    return;

                default:
                    limparConsole();
                   System.out.println("\u001B[31mOpção inválida\u001B[0m, tente novamente");

            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n\nO que você deseja fazer?\n1- Adicionar respostas de uma disciplina\n2- Gerar o resultado de uma disciplina\n3- Sair");
        System.out.println("Digite sua opção: ");
    }

    private static int obterOpcaoMenu() {
        while (!scanner.hasNextInt()) {
            scanner.nextLine(); 
            System.out.println("\u001B[31mOpção inválida\u001B[0m, tente novamente");

        }

        int opcao = scanner.nextInt();
        scanner.nextLine(); 
        return opcao;
    }

    private static void adicionarRespostas() {
        boolean continuar = true;

        while (continuar) {
			limparConsole();
            System.out.println("\nO que você deseja fazer?");
            System.out.println("Digite 'sair' para voltar");
            System.out.println("1- Adicionar Disciplina");
            System.out.println("2- Adicionar resposta");
            System.out.print("Digite sua opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "sair":
                case "Sair":
				case "SAIR":
                    continuar = false;
                    break;

                case "1":
					limparConsole();
                    adicionarDisciplina();
                    break;

                case "2":
					limparConsole();
                    adicionarResposta();
                    break;

                default:
					limparConsole();
                  System.out.println("\u001B[31mOpção inválida\u001B[0m, tente novamente");

            }
        }
    }

    private static void adicionarDisciplina() {
        System.out.print("Digite o nome da disciplina: ");
        String nomeDisciplina = scanner.nextLine();
        Disciplina disciplina = new Disciplina(nomeDisciplina);

        System.out.print("Digite o gabarito da disciplina: ");
        String gabarito = scanner.nextLine();
        disciplina.adicionarDisciplina(gabarito);
    }

    private static void adicionarResposta() {
        try {
            System.out.print("Digite o nome da disciplina: ");
            String nomeDisciplina = scanner.nextLine();
            Disciplina disciplina = new Disciplina(nomeDisciplina);

            FileReader fr = new FileReader(nomeDisciplina + "Gabarito.txt");

            System.out.print("Digite as respostas do aluno: ");
            String resposta = scanner.nextLine();
            
            if (resposta.length() != 10) {
                System.out.println("Digite a quantidade correta de respostas (10)");
                fr.close();
                return;
            }
            
            System.out.print("Digite o nome e sobrenome do aluno: ");
            String nome = scanner.nextLine();
            
            Aluno aluno = new Aluno(nome, resposta, 0);
            disciplina.adicionarResposta(aluno);

            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("Disciplina não encontrada\n");
        } catch (IOException e) {
            System.out.println("Erro na leitura\n");
        }
    }

    private static void exibirResultado() {
        try {
            System.out.print("Digite a disciplina que deseja ver: ");
            String nomeDisciplina = scanner.nextLine();
            Disciplina disciplina = new Disciplina(nomeDisciplina);
            FileReader fr = new FileReader(nomeDisciplina + "Gabarito.txt");
            disciplina.ordenarNotas();
            disciplina.ordenarNomes();
            fr.close();
        } catch(FileNotFoundException e) {
            System.out.println("Disciplina não encontrada ou não cadastrada");
        } catch(IOException e) {
            System.out.println("Erro na leitura\n");
        }
    }

    private static void encerrarPrograma() {
        System.out.println("\nSaindo...");
        scanner.close();
        System.exit(0);
    }

    private static void limparConsole() {
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
