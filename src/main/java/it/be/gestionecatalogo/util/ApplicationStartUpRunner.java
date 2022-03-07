package it.be.gestionecatalogo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.be.gestionecatalogo.model.Autore;
import it.be.gestionecatalogo.model.Categoria;
import it.be.gestionecatalogo.model.Libro;
import it.be.gestionecatalogo.repository.AutoreRepository;
import it.be.gestionecatalogo.repository.CategoriaRepository;
import it.be.gestionecatalogo.repository.LibroRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ApplicationStartUpRunner implements CommandLineRunner {
	
	@Autowired
	private LibroRepository librorepository;

	@Autowired
	private AutoreRepository autorerepository;
	
	@Autowired
	private CategoriaRepository categoriarepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		
		Autore autore = new Autore();
        autore.setNome("Francesco");
        autore.setCognome("Viglianisi");
        autorerepository.save(autore);


        Categoria categoria = new Categoria();
        categoria.setNome("fantascienza");
        categoriarepository.save(categoria);


        Libro libro = new Libro();
        libro.setTitolo("Viaggio nello spazio");
        libro.setAnnoPubblicazione(2005);
        libro.setPrezzo(17.99);
        libro.getAutori().add(autore);
        libro.getCategorie().add(categoria);
        librorepository.save(libro);

        
	}

}
