package Classes;

import java.util.ArrayList;

import java.io.*;
import java.util.Scanner;

public class Disciplina {
    protected String nome;
    protected ArrayList<Aluno> alunos;
    protected Scanner pos;
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
			System.out.println(alunos.get(1).getRespostas());
			for(int i = 0; i < alunos.size(); i++) {
				for(int j = 0; j < 10; j ++) {
					if(l1.substring(j,j+1)
					.equals(alunos.get(i).getRespostas().substring(j,j+1))) {
						alunos.get(i).setNota(alunos.get(i).getNota() + 1);
					}
				}
			}
			br.close();
			br1.close();	
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
					else if(((alunos.get(i).getNota() 
							== alunos.get(j).getNota()) && (j >= i))) {
						Soma += 1;
					}
				}
				
				OrdemNota.add(alunos.size() - Soma, alunos.get(i));
				Soma = 0;
			}
			bw.write("Respostas -------- Nome -------- Nota");
			bw.newLine();
			for(int i = 0; i < OrdemNota.size() ; i++) {
				if(OrdemNota.get(i).getNome().equals(" ")) {}
				else {
				Integer.toString(OrdemNota.get(i).getNota());
				bw.write(OrdemNota.get(i).getRespostas() + " " 
			+ OrdemNota.get(i).getNome() + " " + OrdemNota.get(i).getNota());
				bw.newLine();
				}
			}
			
		}
    	bw.close();
    	fw.close();
    	fr.close();
    	fr1.close();	
		} catch(IOException e){
			System.out.println("Erro na escrita");
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
