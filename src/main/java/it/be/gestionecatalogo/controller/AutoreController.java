package it.be.gestionecatalogo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.be.gestionecatalogo.model.Autore;
import it.be.gestionecatalogo.service.AutoreService;

@RestController
@RequestMapping("/api/autore")
@SecurityRequirement(name = "bearerAuth")
public class AutoreController {
	
	@Autowired
	private AutoreService autoreservice;
	
	@GetMapping(path = "/findAll")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity<List<Autore>> findAll(){
		List<Autore> findAll = autoreservice.findAll();
		
		if(findAll != null) {
			return new ResponseEntity <> (findAll , HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity <> (null , HttpStatus.NOT_FOUND);
		}
		
	}
	@GetMapping(path = "/findById/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public ResponseEntity <Autore> findById	(@PathVariable(required = true) Long id) {
		Optional<Autore> find =  autoreservice.findById(id);
		
		if(find.isPresent()) {
			return new ResponseEntity <> (find.get() , HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity <> (null , HttpStatus.NOT_FOUND);
		}
		
	}
	@PostMapping(path = "/save")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity <Autore> save (@RequestBody Autore a ) {
		Autore salva = autoreservice.save(a);
		return new ResponseEntity <> (salva , HttpStatus.ACCEPTED);
		
	}
	@DeleteMapping(path = "/delete/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity <String> delete (@PathVariable Long id) {
		autoreservice.delete(id);
		return new ResponseEntity <> ("Autore cancellato" , HttpStatus.ACCEPTED);
		
	}
	@PutMapping(path = "/update/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity <Autore> update(@RequestBody Autore a ,@PathVariable Long id) {
		Autore aggiorna = autoreservice.update(a, id);
		return new ResponseEntity <> (aggiorna , HttpStatus.ACCEPTED);
				
		
	}
}
