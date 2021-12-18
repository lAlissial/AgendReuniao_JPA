package modelo;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import javax.persistence.JoinColumn;

//per class --> Tabelas independentes
//--@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

@Entity 	
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)


public class Participante  {
	@Id		
	private String nome; 
	
	private String email;
	//o arroba id --> identifica a chave primária, o id será inicializado no banco e não deveconstar no construtor
	
	//mappedby -->Mapeia a referência inversa do relacionamentobidirecional
	@ManyToMany(mappedBy="participantes", cascade= {CascadeType.PERSIST, CascadeType.MERGE}, fetch= FetchType.LAZY)
	private List <Reuniao> reunioes = new ArrayList <Reuniao> ();
	public Participante() {
		
	}
	
	public Participante(String nome, String email) 	{
		super();
		this.nome = nome;
		this.email = email;
	}
	

	public String getNome() {
		return nome;
	}
	

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public List<Reuniao> getReunioes() 	{
		return reunioes;
	}

	public void adicionar(Reuniao r)	{
		reunioes.add(r);
	}

	public void remover(Reuniao r)	{
		reunioes.remove(r);
	}


	public int getTotalReunioes() 	{
		return reunioes.size();
	}

	@Override
	public String toString() 	{
		String texto = "Nome: " + nome + ", email: " + email + ", Reuniões: ";
		
		if (reunioes.isEmpty())
			texto += " sem reunião";
		else 	
			for(Reuniao r : reunioes) 	
				texto += " " + r.getId();

		return texto;
	}
}