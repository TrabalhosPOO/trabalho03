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
    		bw.write(m.getRespostas() + " " + m.getNome() + " ");
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
				String[] dados = l.split(" ");
				Aluno a = new Aluno(dados[1] + " " + dados[2], dados[0], 0);
				alunos.add(a);
				l = br.readLine();
			}
			
			for(int i = 0; i < alunos.size(); i++) {
				for(int j = 0; j < 10; j ++) {
					if(l1.substring(j,j+1)
					.equals(alunos.get(i).getRespostas().substring(j,j+1))) {
						alunos.get(i).setNota(alunos.get(i).getNota() + 1);
					}
				}
			}	
			int Soma = 0;
			Aluno ordem = new Aluno(" ", " " ,0);
			ArrayList<Aluno> OrdemNota = new ArrayList<>();
			for(int i = 0; i < alunos.size() ; i++) {
				OrdemNota.add(i,ordem);
			}

			for(int i = 0; i < alunos.size() ; i++) {
				for(int j = 0; j < alunos.size() ; j++) {
					if(alunos.get(i).getNota() >= alunos.get(j).getNota()) {
						Soma += 1;
					}
					if(((alunos.get(i).getNota() 
							== alunos.get(j).getNota()) && (j > i))) {
						Soma += 1;
					}
				}
				
				OrdemNota.add(alunos.size() - Soma, alunos.get(i));
				Soma = 0;
			}
			System.out.println("\n");
			for(int i = 0; i < OrdemNota.size() ; i++) {
				if(OrdemNota.get(i).getNome().equals(" ")) {}
				else {
					System.out.println("Respostas: " + OrdemNota.get(i).getRespostas() 
							+ "	Nome: " + OrdemNota.get(i).getNome() + 
							"	Nota: " + OrdemNota.get(i).getNota());
				Integer.toString(OrdemNota.get(i).getNota());
				bw.write(OrdemNota.get(i).getRespostas() + " " 
			+ OrdemNota.get(i).getNome() + " " + OrdemNota.get(i).getNota());
				bw.newLine();
				}
			}
			
			File diretorio = new File("/home/bruno/eclipse-workspace.p/" + 
			this.nome + "Gabarito.txt");
			System.out.println("Localizaçao do gabarito: " 
			+ diretorio.getAbsolutePath());
			br.close();
			br1.close();
		}
    	
    	bw.close();
    	fw.close();
    	fr.close();
    	fr1.close();	
		} catch(IOException e){
			System.out.println("Erro na escrita");
		}
    }
    
    public void ordenarNomes() {
    	ArrayList<Aluno> ordemAlfabetica = new ArrayList<Aluno>();
    	
    	for(int i = 0; i < alunos.size(); i++) {
			ordemAlfabetica.add(i,alunos.get(i));
    	}
    	
    	Collections.sort((List<Aluno>) ordemAlfabetica);
    	System.out.println("\n");
    	
    	for(Aluno aluno: ordemAlfabetica) {
    		System.out.println("Respostas: " + aluno.getRespostas() + 
    				"Nome: " + aluno.getNome() + "Nota: " + aluno.getNota());
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
