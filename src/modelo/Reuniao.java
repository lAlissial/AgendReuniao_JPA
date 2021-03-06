package modelo;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import javax.persistence.FetchType;
import javax.persistence.CascadeType;

//O Mapeamento Objeto-Relacional - MOR (ORM) ? feito de forma expl?cita com uso de anota??es (@) antes da classe ou dos atributos
//sinaliza que a classe ? persistente
@Entity 

public class Reuniao {
	@Id		 
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private int id;         

	private String assunto;
	// o generated value --> especifica uma coluna autoincrementada automaticamente pelo banco
	
	//Especifica-se o tipo sql da coluna da tabela
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime datahora;  


	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE}, fetch= FetchType.LAZY)

	private List <Participante> participantes = new ArrayList <Participante>();
	
	
	public Reuniao() {}
	
	public Reuniao(LocalDateTime datahora, String assunto) 	{
		this.datahora = datahora;
		//this.datahora = LocalDateTime.parse(datahora,DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		this.assunto = assunto;
	}

	public void adicionar(Participante p)	{
		participantes.add(p);
	}

	public void remover(Participante p)	{
		participantes.remove(p);
	}

	public Participante localizarParticipante(String nome)	{
		for(Participante p: participantes)	{
			if(p.getNome().equals(nome))
				return p;
		}
		return null;
	}

	public List<Participante> getParticipantes() 	{
		return participantes;
	}

	public void setParticipantes(List<Participante> participantes) 	{
		this.participantes = participantes;
	}

	public int getTotalParticipantes()	{
		return participantes.size();
	}

	public int getId() 	{
		return id;
	}

	public void setId(int id) 	{
		this.id = id;
	}

	public LocalDateTime getDatahora() {
		return this.datahora;
		//return LocalDateTime.parse(this.datahora.toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
	}
	
	public String getDatahoraStr() {
		return this.datahora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
	}

	public void setDatahora(LocalDateTime dth) 	{
		this.datahora = dth;
		//this.datahora = LocalDateTime.parse(dth.toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
	}


	public String getAssunto() 	{
		return assunto;
	}

	public void setAssunto(String assunto) 	{
		this.assunto = assunto;
	}

	@Override
	public String toString() 	{
		String texto = "id: " + id + ", Hor?rio: " + datahora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ", Assunto: " + assunto;

		texto +=  "\n Participantes:";
		for(Participante p: participantes) 
			texto += " " + p.getNome();

		return texto ;
	}
}
	




	


	
