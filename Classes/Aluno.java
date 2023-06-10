package Classes;

import java.util.Collections;

public class Aluno implements Comparable<Aluno>{
    protected String nome;
    protected String respostas;
    protected int nota;

    public Aluno(String nome, String respostas, int nota) {
        this.nome = nome;
        this.respostas = respostas;
        this.nota = nota;
    }
    
    @Override
    public int compareTo(Aluno outroAluno) {
    	return this.nome.compareTo(outroAluno.getNome());
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRespostas() {
        return respostas;
    }

    public void setRespostas(String respostas) {
        this.respostas = respostas;
    }

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

}
