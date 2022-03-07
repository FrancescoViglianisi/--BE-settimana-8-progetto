package it.be.gestionecatalogo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.be.gestionecatalogo.exceptions.CategoriaException;
import it.be.gestionecatalogo.model.Categoria;
import it.be.gestionecatalogo.repository.CategoriaRepository;


@Service
public class CategoriaService {
	@Autowired
	CategoriaRepository categoriarepository;

	public Optional <Categoria> findById (Long id) {
		return categoriarepository.findById(id);
	}
	public List<Categoria> findAll() {
		return categoriarepository.findAll();
	}

	public Categoria save(Categoria c) {
		return categoriarepository.save(c);

	}

	public Categoria update(Categoria c, Long id) {
		Optional<Categoria> trovato = categoriarepository.findById(id);
		if(trovato.isPresent()) {
			Categoria categoriaUpdate = trovato.get();
			categoriaUpdate.setNome(categoriaUpdate.getNome());
			categoriarepository.save(categoriaUpdate);
			return categoriaUpdate;
		}
		else {
			throw new CategoriaException("Categoria non aggiornata");
		}
	}


	public void delete(Long id) {
		categoriarepository.deleteById(id);
	}


}
