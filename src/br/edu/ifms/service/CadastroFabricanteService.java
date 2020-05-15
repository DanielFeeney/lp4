package br.edu.ifms.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.edu.ifms.dao.FabricanteDAO;
import br.edu.ifms.modelo.Fabricante;
import br.edu.ifms.util.jpa.Transactional;

public class CadastroFabricanteService implements Serializable{
	@Inject
	private FabricanteDAO fabricanteDAO;
	
	@Transactional
	public void salvar(Fabricante fabricante) throws NegocioException{
		if(fabricante.getNome() == null || fabricante.getNome().trim().equals("")) {
			throw new NegocioException("� nome do fabricante � obrigat�rio");
		}
		this.fabricanteDAO.salvar(fabricante);
	}
}
