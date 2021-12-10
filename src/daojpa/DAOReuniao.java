package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;


import modelo.Convidado;
import modelo.Participante;
import modelo.Reuniao;

public class DAOReuniao extends DAO<Reuniao> {
	
	public Reuniao read (Object chave){
		try{
			int idz = (int) chave;
			TypedQuery<Reuniao> q = manager.createQuery("select p from Reuniao p where p.id=:x", Reuniao.class);
			q.setParameter("x", idz);
			return q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}

	//--------------------------------------------
	//  CONSULTA B
	//--------------------------------------------

	
	//B. Quais as reuniões que tem algum convidado?
	public List<Reuniao> consultaB() {
		TypedQuery<Reuniao> q = manager.createQuery("select distinct r from Convidado c join c.reunioes r", Reuniao.class);
		return q.getResultList();	
		//TypedQuery<Reuniao> q = manager.createQuery("select distinct r from Reuniao r join r.participantes w where TYPE(w) IN (Convidado)", Reuniao.class);
	}


}


