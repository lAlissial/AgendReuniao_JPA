/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */

package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


import modelo.Participante;
import modelo.Reuniao;

public class DAOParticipante extends DAO<Participante>{


	public Participante read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Participante> q = manager.createQuery("select p from Participante p where p.nome=:n", Participante.class);
			q.setParameter("n", nome);
			return q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}


	//--------------------------------------------
	//  CONSULTA A
	//--------------------------------------------

	//A. Quais os participantes que tem reunião com participante de nome N no mês M?
	
	
	public List<Participante> consultaA(String nomez, int mes) {
		
		//TypedQuery<Participante> q = manager.createQuery("select distinct p from Participante p join p.reunioes r where (substring(cast(r.datahora as text),6,2)=:x) and (((r.id in (select distinct re.id from Participante pa join pa.reunioes re where pa.nome='"+nomez+"')) or (r.id in (select distinct reu.id from Convidado co join co.reunioes reu where co.nome='"+nomez+"'))))", Participante.class);
		TypedQuery<Participante> q = manager.createQuery("select distinct p from Participante p join p.reunioes r join r.participantes par where (substring(cast(r.datahora as text),6,2)=:x) and par.nome='"+nomez+"'", Participante.class);
		q.setParameter("x", Integer.toString(mes));
		//q.setParameter("y", nomez);
		return q.getResultList();
	}
	
}


