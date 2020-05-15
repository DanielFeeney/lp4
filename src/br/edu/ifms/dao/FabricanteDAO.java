package br.edu.ifms.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.ifms.modelo.Fabricante;
import br.edu.ifms.service.NegocioException;
import br.edu.ifms.util.jpa.Transactional;

public class FabricanteDAO implements Serializable {

	@Inject
	private EntityManager em;
	
	public void salvar(Fabricante fabricante) {
		em.merge(fabricante);
	}
	
	@SuppressWarnings("unchecked")
	public List<Fabricante> buscarTodos(){
		return em.createQuery("from Fabricante").getResultList();
	}
	
	@Transactional
	public void excluir(Fabricante fabricante) throws NegocioException{
		Fabricante fabricanteTemp = em.find(Fabricante.class, fabricante.getCodigo());
		
		em.remove(fabricanteTemp);
		em.flush();
	}
	
	public Fabricante buscarPeloCodigo(Long codigo) {
		return em.find(Fabricante.class, codigo);
	}
}
