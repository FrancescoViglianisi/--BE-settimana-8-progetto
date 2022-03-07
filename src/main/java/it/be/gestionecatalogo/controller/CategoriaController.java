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
import it.be.gestionecatalogo.model.Categoria;
import it.be.gestionecatalogo.service.CategoriaService;



	@RestController
	@RequestMapping("/api/categoria")
	@SecurityRequirement(name = "bearerAuth")
	public class CategoriaController {

		@Autowired
		private CategoriaService categoriaservice;

		@GetMapping(path = "/findAll")
		@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")

		public ResponseEntity<List<Categoria>> findAll(){
			List<Categoria> findAll = categoriaservice.findAll();

			if(findAll != null) {
				return new ResponseEntity <> (findAll , HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity <> (null , HttpStatus.NOT_FOUND);
			}

		}

		@GetMapping(path = "/findById/{id}")
		@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")

		public ResponseEntity <Categoria> findById	(@PathVariable(required = true) Long id) {
			Optional<Categoria> find =  categoriaservice.findById(id);

			if(find.isPresent()) {
				return new ResponseEntity <> (find.get() , HttpStatus.ACCEPTED);
			}else {
				return new ResponseEntity <> (null , HttpStatus.NOT_FOUND);
			}

		}
		@PostMapping(path = "/save")
		@PreAuthorize("hasRole('ROLE_ADMIN')")
		public ResponseEntity <Categoria> save (@RequestBody Categoria c) {
			Categoria salva = categoriaservice.save(c);
			return new ResponseEntity <> (salva , HttpStatus.ACCEPTED);

		}
		@DeleteMapping(path = "/delete/{id}")
		@PreAuthorize("hasRole('ROLE_ADMIN')")
		public ResponseEntity <String> delete (@PathVariable Long id) {
			categoriaservice.delete(id);
			return new ResponseEntity <> ("Categoria cancellato" , HttpStatus.ACCEPTED);

		}
		@PutMapping(path = "/update/{id}")
		@PreAuthorize("hasRole('ROLE_ADMIN')")
		public ResponseEntity <Categoria> update(@RequestBody Categoria c ,@PathVariable Long id) {
			Categoria aggiorna = categoriaservice.update(c, id);
			return new ResponseEntity <> (aggiorna , HttpStatus.ACCEPTED);


		}
	}

