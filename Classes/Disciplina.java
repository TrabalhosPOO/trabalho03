package Classes;

import java.util.ArrayList;

import java.io.*;
import java.util.Scanner;

public class Disciplina {
    protected String nome;
    protected ArrayList<Aluno> alunos;
    protected Scanner pos;

    public Disciplina(String nome) {
    	this.alunos = new ArrayList<>();
        this.nome = nome;
    }
    
    public void adicionarDisciplina(String m) {
    	FileWriter fw1;
    	FileWriter fw2;
    	try {
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
    		bw.write(m.getRespostas() + " " + m.getNome());
    		bw.newLine();
    		bw.close();
    		fw1.close();
    	} catch (IOException e){
    		e.printStackTrace();
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
