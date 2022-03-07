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
import it.be.gestionecatalogo.model.Libro;
import it.be.gestionecatalogo.service.LibroService;

@RestController
@RequestMapping("/api/libro")
@SecurityRequirement(name = "bearerAuth")
public class LibroController {

	@Autowired
	private LibroService libroservice;

	@GetMapping(path = "/findAll")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")

	public ResponseEntity<List<Libro>> findAll(){
		List<Libro> findAll = libroservice.findAll();

		if(findAll != null) {
			return new ResponseEntity <> (findAll , HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity <> (null , HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping(path = "/findById/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")

	public ResponseEntity <Libro> findById	(@PathVariable(required = true) Long id) {
		Optional<Libro> find =  libroservice.findById(id);

		if(find.isPresent()) {
			return new ResponseEntity <> (find.get() , HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity <> (null , HttpStatus.NOT_FOUND);
		}

	}
	@PostMapping(path = "/save")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")

	public ResponseEntity <Libro> save (@RequestBody Libro l) {
		Libro salva = libroservice.save(l);
		return new ResponseEntity <> (salva , HttpStatus.ACCEPTED);

	}
	@DeleteMapping(path = "/delete/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")

	public ResponseEntity <String> delete (@PathVariable Long id) {
		libroservice.delete(id);
		return new ResponseEntity <> ("Libro cancellato" , HttpStatus.ACCEPTED);

	}
	@PutMapping(path = "/update/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")

	public ResponseEntity <Libro> update(@RequestBody Libro l ,@PathVariable Long id) {
		Libro aggiorna = libroservice.update(l, id);
		return new ResponseEntity <> (aggiorna , HttpStatus.ACCEPTED);


	}
}
