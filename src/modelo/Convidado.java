package modelo;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import modelo.Participante;


@Entity

//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//estratégia só no pai

public class Convidado extends Participante {
	
	private String empresa; 

	public Convidado() {}
	
	public Convidado(String nome, String email, String empresa) {
		super(nome, email);
		this.empresa=empresa;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	@Override
	public String toString() 	{
		String texto = super.toString() + "\n empresa="+empresa;
		return texto;
	}
}
