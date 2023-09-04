package org.jeffcoder.resources;

import java.net.URI;
import java.util.List;

import org.jeffcoder.models.ClienteModel;
import org.jeffcoder.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/clientes")
public class ClienteResource {
	
	@Inject
	private ClienteService service;
	 
	@GET
	@Path("/todos") 
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> buscarTodos(){ 
		try {
			List<ClienteModel> lista = service.buscarTodos();
			if(lista.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lista);
		} catch (Exception e) { 
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GET
	@Path("/buscar") 
	@Produces(MediaType.APPLICATION_JSON) 
	@Consumes(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> buscarPorId(@QueryParam("id") Long id){ 
		try {
			ClienteModel cliente =  service.buscarPorId(id);
			if(cliente.getId() == null) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(cliente);
		} catch (Exception e) {  
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@POST
	@Path("/salvar") 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> salvar(@RequestBody ClienteModel cliente){ 
		try {
			service.salvar(cliente); 
			URI location = URI.create(String.format("/clientes/buscar?id=%s", cliente.getId()));
			return ResponseEntity.created(location).build();
		} catch (Exception e) { 
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PUT
	@Path("/atualizar") 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<?> atualizar(@RequestBody ClienteModel cliente){ 
		try {
			service.atualizar(cliente); 
			URI location = URI.create(String.format("/clientes/buscar?id=%s", cliente.getId()));
			return ResponseEntity.created(location).build();
		} catch (Exception e) { 
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DELETE
	@Path("/deletar") 
	@Produces(MediaType.APPLICATION_JSON) 
	public ResponseEntity<?> deletar(@QueryParam("id") Long id){ 
		try {
			service.deletar(id); 
			return ResponseEntity.ok().build();
		} catch (Exception e) { 
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
