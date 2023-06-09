package Classes;

import java.util.Scanner;
import java.io.*;

public class Main {
    private static Scanner scanner1;
	private static Scanner scanner2;
	private static Scanner scanner3;
    private static Scanner scanner;
    private static Scanner scanner4;
	public static void main(String[] args) {
		int menu;
        scanner = new Scanner(System.in);
        scanner1 = new Scanner(System.in);
        scanner2 = new Scanner(System.in);
        scanner3 = new Scanner(System.in);
        scanner4 = new Scanner(System.in);
        while (true) {
            System.out.println(
                    "\n\nO que voce deseja fazer?\n1- Adicionar respostas de uma disciplina\n2- Gerar o resultado de uma disciplina\n3- Sair");
            menu = scanner.nextInt();

            switch (menu) {
                case 1:
                	boolean n = true;
                	while(n == true) {
                		System.out.println(
                				"O que voce deseja fazer?\nDigite 'sair' "
                				+ "para voltar\n1- Adicionar Disciplina\n2- Adicionar resposta");
                		String disc = scanner1.nextLine();
                		if(disc.equals("sair") || disc.equals("Sair")) {
                			n = false;
                		} else if(disc.equals("1")) {
                			System.out.println("Digite o nome da disciplina");
                			String disc1 = scanner2.nextLine();
                			Disciplina d = new Disciplina(disc1);
                			System.out.println("Digite o gabarito da disciplina");
                			String disc2 = scanner3.nextLine();
                			d.adicionarDisciplina(disc2);
                		} else if(disc.equals("2")){
                			try {
                				System.out.println("Digite o nome da disciplina");
                				String disc1 = scanner4.nextLine();
                				FileReader fr = new FileReader(disc1 + "Gabarito.txt");
                				Disciplina d = new Disciplina(disc1);
                				System.out.println("Digite as respostas do aluno: ");
                    			String resposta = scanner2.nextLine();
                    			System.out.println("Digite o nome e sobrenome do aluno: ");
                    			String nome = scanner3.nextLine();
                    			Aluno a = new Aluno(nome, resposta, 0);
                    			d.adicionarResposta(a);
                    			fr.close();
                			} catch (FileNotFoundException e) {
                				System.out.println("Disciplina não encontrada\n");
                			} catch (IOException e) {
                				System.out.println("Erro na leitura\n");
                			}
                			
                		}
                	}
                    break;

                case 2:
                	try {
                		System.out.println("Digite a disciplina que deseja ver");
                		String disc1 = scanner1.nextLine();
                		Disciplina d = new Disciplina(disc1);
        				FileReader fr = new FileReader(disc1 + "Gabarito.txt");
        				d.ordenarNotas();
        				fr.close();
                	} catch(FileNotFoundException e) {
                		System.out.println("Disciplina não encontrada");
                	} catch(IOException e) {
                		System.out.println("Erro na leitura\n");
                	}
                    break;

                case 3:
                System.out.println("\nSaindo...");
                    System.exit(1);
                    scanner.close();
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
        
        }

    }
}
