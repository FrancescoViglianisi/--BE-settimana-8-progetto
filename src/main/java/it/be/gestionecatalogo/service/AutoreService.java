package it.be.gestionecatalogo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.be.gestionecatalogo.exceptions.AutoreException;
import it.be.gestionecatalogo.model.Autore;
import it.be.gestionecatalogo.repository.AutoreRepository;

@Service
public class AutoreService {

	@Autowired
	AutoreRepository autorerepository;

	public Optional <Autore> findById(Long id) {
		return autorerepository.findById(id);
	}

	public List<Autore> findAll() {
		return autorerepository.findAll();
	}

	public Autore save(Autore a) {
		return autorerepository.save(a);

	}

	public Autore update(Autore a, Long id) {
		Optional<Autore> trovato = autorerepository.findById(id);
		if(trovato.isPresent()) {
			Autore autoreUpdate = trovato.get();
			autoreUpdate.setNome(autoreUpdate.getNome());
			autoreUpdate.setCognome(autoreUpdate.getCognome());
			autorerepository.save(autoreUpdate);
			return autoreUpdate;
		}
		else {
			throw new AutoreException("Autore non aggiornato");
		}
	}


	public void delete(Long id) {
		autorerepository.deleteById(id);
	}

}
