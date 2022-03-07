package it.be.gestionecatalogo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.be.gestionecatalogo.exceptions.LibroException;
import it.be.gestionecatalogo.model.Libro;
import it.be.gestionecatalogo.repository.LibroRepository;

@Service
public class LibroService {
	
	@Autowired
	LibroRepository librorepository;
	 
	public Optional <Libro> findById (Long id) {
		return librorepository.findById(id);
	}
	
	public Optional <Libro> findLibroById (Long id) {
		return librorepository.findById(id);
	}
	public List<Libro> findAll() {
		return librorepository.findAll();
	}
	
	public Libro save(Libro l) {
		return librorepository.save(l);
		
	}
	
	public Libro update(Libro l, Long id) {
		Optional<Libro> trovato = librorepository.findById(id);
		if(trovato.isPresent()) {
			Libro libroUpdate = trovato.get();
			libroUpdate.setCategorie(libroUpdate.getCategorie());
			libroUpdate.setAutori(libroUpdate.getAutori());
			libroUpdate.setTitolo(libroUpdate.getTitolo());
			libroUpdate.setAnnoPubblicazione(libroUpdate.getAnnoPubblicazione());
			libroUpdate.setPrezzo(libroUpdate.getPrezzo());
			librorepository.save(libroUpdate);
			return libroUpdate;
		}
		else {
		throw new LibroException("Libro non aggiornato");
		}
	}
	
	
	public void delete(Long id) {
		librorepository.deleteById(id);
	}

}
