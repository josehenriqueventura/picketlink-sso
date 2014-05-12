package br.com.josehenriqueventura.repository;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.josehenriqueventura.entity.User;

@Stateless
@Local
public class UserRepository {

	@PersistenceContext
	EntityManager em;
	
	public User getUserAndModulePermission(String name){
		
		Query query = em.createNamedQuery("BUSCA");
		query.setParameter("name",name);
		
		return (User) query.getSingleResult();
	}
}
