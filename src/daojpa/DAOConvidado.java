/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */

package daojpa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.Convidado;
import modelo.Reuniao;

public class DAOConvidado extends DAO<Convidado>{

	public Convidado read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Convidado> q = manager.createQuery("select c from Convidado c where c.nome=:n", Convidado.class);
			q.setParameter("n", nome);
			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}
	


}

