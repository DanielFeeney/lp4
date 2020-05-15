package br.edu.ifms.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifms.modelo.Fabricante;
import br.edu.ifms.service.CadastroFabricanteService;
import br.edu.ifms.service.NegocioException;
import br.edu.ifms.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroFabricanteBean {
	@Inject
	private CadastroFabricanteService cadastroFabricanteService;
	
	private Fabricante fabricante;
	
	public void salvar() {
		try {
			this.cadastroFabricanteService.salvar(fabricante);
			FacesUtil.addSuccessMessage("Registro salvo com sucesso");
			this.limpar();
		}
		catch(NegocioException ex) {
			FacesUtil.addErrorMessage(ex.getMessage());
		}
	}
	
	@PostConstruct
	public void init() {
		this.limpar();
	}

	private void limpar() {
		this.fabricante = new Fabricante();
		
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}
	
	
}
