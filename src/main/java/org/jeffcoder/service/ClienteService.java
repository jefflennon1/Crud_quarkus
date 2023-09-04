package org.jeffcoder.service;

import java.util.List;

import org.jeffcoder.models.ClienteModel;
import org.jeffcoder.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ClienteService {

	@Inject
	private ClienteRepository repository;
	
	@PersistenceContext
    EntityManager entityManager;
	

	public List<ClienteModel> buscarTodos() { 
		return repository.findAll();
	}

	public ClienteModel buscarPorId(Long id) { 
		return repository.getOne(id);
	}
	
	public void atualizar(ClienteModel cliente) {  
		repository.save(cliente);
	}

	public void deletar(Long id) {
		 repository.deleteById(id);
	}

	public void salvar(ClienteModel cliente) {
		repository.save(cliente);
	}
}
