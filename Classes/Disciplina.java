package Classes;

import java.util.*;
import java.io.*;

public class Disciplina {
    protected String nome;
    protected ArrayList<Aluno> alunos;
    protected String gabarito;

    public Disciplina(String nome) {
    	this.alunos = new ArrayList<>();
        this.nome = nome;
    }
    
    public void adicionarDisciplina(String m) {
    	FileWriter fw1;
    	FileWriter fw2;
    	try {
    		this.gabarito = m;
    		fw1 = new FileWriter(this.nome + "Gabarito.txt", true);
    		fw2 = new FileWriter(this.nome + ".txt",true);
    		BufferedWriter bw = new BufferedWriter(fw1);
    		bw.write(m);
    		bw.newLine();
    		bw.close();
    		fw1.close();
    		fw2.close();
    	} catch (IOException e){
    		e.printStackTrace();
    	}
    }
    
    public void adicionarResposta(Aluno m){
    	FileWriter fw1;
    	try {
    		this.alunos.add(m);
    		fw1 = new FileWriter(this.nome + ".txt", true);
    		BufferedWriter bw = new BufferedWriter(fw1);
    		bw.write(m.getRespostas() + "\t" + m.getNome() + "\t");
    		bw.newLine();
    		bw.close();
    		fw1.close();
    	} catch (IOException e){
    		e.printStackTrace();
    	}
    }
    
    public void ordenarNotas() {
		try {
		FileReader fr = new FileReader(this.nome + ".txt");
		FileReader fr1 = new FileReader(this.nome + "Gabarito.txt");
    	FileWriter fw = new FileWriter("OrdemNota" + this.nome + ".txt", true);
    	BufferedWriter bw = new BufferedWriter(fw);
    	try (BufferedReader br = new BufferedReader(fr);
				BufferedReader br1 = new BufferedReader(fr1)) {
			String l = br.readLine();
			String l1 = br1.readLine();
			while(l != null) {
				String[] dados = l.split("\t");
				Aluno a = new Aluno(dados[1], dados[0], 0);
				alunos.add(a);
				l = br.readLine();
			}
			
			for(int i = 0; i < alunos.size(); i++) {
				if(alunos.get(i).getRespostas().equals("VVVVVVVVVV") ||
						alunos.get(i).getRespostas().equals("FFFFFFFFFF")) {
					alunos.get(i).setNota(0);
				} else {
				for(int j = 0; j < 10; j ++) {
					if(l1.substring(j,j+1)
					.equals(alunos.get(i).getRespostas().substring(j,j+1))) {
						alunos.get(i).setNota(alunos.get(i).getNota() + 1);
					}
				}
				}
			}
			
			
			int Soma = 0;
			Aluno ordem = new Aluno(" ", " ",0);
			ArrayList<Aluno> OrdemNota = new ArrayList<>();
			for(int i = 0; i < alunos.size() ; i++) {
				OrdemNota.add(i,ordem);
			}

			for(int i = 0; i < alunos.size() ; i++) {
				for(int j = 0; j < alunos.size() ; j++) {
					if(alunos.get(i).getNota() > alunos.get(j).getNota()) {
						Soma += 1;
					}
					if(((alunos.get(i).getNota() 
							== alunos.get(j).getNota()) && (j > i))) {
						Soma += 1;
					}
				}
				
				OrdemNota.add(alunos.size() - Soma - 1, alunos.get(i));
				Soma = 0;
			}
			double SomaMedia = 0;
			System.out.println("\n");
			System.out.println("-----------ORDEM NOTA-----------");	
			for(int i = 0; i < OrdemNota.size(); i++) {
				if(OrdemNota.get(i).getNome().equals(" ")) {}
				else {
					System.out.println("Respostas: " + OrdemNota.get(i).getRespostas() 
							+ "\tNome: " + OrdemNota.get(i).getNome() + 
							"\tNota: " + OrdemNota.get(i).getNota());
					SomaMedia += OrdemNota.get(i).getNota();
				Integer.toString(OrdemNota.get(i).getNota());
				bw.write(OrdemNota.get(i).getRespostas() + "	" 
			+ OrdemNota.get(i).getNome() + "	" + OrdemNota.get(i).getNota());
				bw.newLine();
				}
			}
			bw.write("Média da Turma: \t" + SomaMedia/alunos.size());
			System.out.printf("Média da Turma: %.2f\t", (SomaMedia/alunos.size()));
			br.close();
			br1.close();
		}
    	
    	bw.close();
    	fw.close();
    	fr.close();
    	fr1.close();	
		} catch(IOException e){
			System.out.println("Erro na leitura para ordenar as notas");
		} catch(ArrayIndexOutOfBoundsException e) {
			File diretorio = new File("/home/bruno/eclipse-workspace.p/" + 
	    			this.nome + ".txt");
	    	System.out.println("Erro na formatação do arquivo");
	    	System.out.println("Excluindo arquivo...");
	    	File arquivo = new File(this.nome + ".txt");
	    	boolean excluir = arquivo.delete();
	    	if(excluir) {
	    		System.out.println("Arquivo com as notas excluído!" + 
	    	"\nColoque as respostas dos alunos novamente");
	    	} else {
	    		System.out.println("Exclusão do arquivo não foi possível, vá em " + 
	    				diretorio.getAbsolutePath() + "\n, exclua o arquivo e"
	    						+ " cadastre as respostas novamente.");
	    	}
		}
    }
    
    public void ordenarNomes() {
    	FileReader fr;
		try {
			fr = new FileReader(this.nome + ".txt");
    	try(BufferedReader br = new BufferedReader(fr);) {
    	ArrayList<Aluno> ordemAlfabetica = new ArrayList<Aluno>();
    	FileWriter fw = new FileWriter("OrdemNome" + this.nome + ".txt", true);
    	BufferedWriter bw = new BufferedWriter(fw);
    	for(int i = 0; i < alunos.size(); i++) {
			ordemAlfabetica.add(i,alunos.get(i));
    	}
    	
    	Collections.sort((List<Aluno>) ordemAlfabetica);
    	int SomaMedia = 0;
    	System.out.println("\n");
    	System.out.println("-----------ORDEM ALFABÉTICA-----------");
    	for(Aluno aluno: ordemAlfabetica) {
    		System.out.println("Respostas: " + aluno.getRespostas() + 
    				"\tNome: " + aluno.getNome() + "\tNota: " + aluno.getNota());
    		bw.write(aluno.getRespostas() + "\t" + aluno.getNome() + 
    				"\t" + aluno.getNota());
    		SomaMedia += aluno.getNota();
    		bw.newLine();
    	}
    	bw.write("Média da Turma: \t" + SomaMedia/ordemAlfabetica.size());
    	System.out.println("Média da Turma: \t" + SomaMedia/ordemAlfabetica.size());
    	File diretorio = new File("/home/bruno/eclipse-workspace.p/" + 
    			this.nome + "Gabarito.txt");
    			System.out.println("\nLocalizaçao do gabarito: " 
    			+ diretorio.getAbsolutePath());
    	bw.close();
    	fw.close();
    	} catch(ArithmeticException e) {
    		System.out.println("Nenhum aluno cadastrado nessa disciplina");
    	} 
    	} catch(IOException e) {
    		System.out.println("Erro na leitura para ordenar os nomes");
    	}
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
   
    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }
    
}
