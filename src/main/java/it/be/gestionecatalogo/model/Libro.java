package it.be.gestionecatalogo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String titolo;
	@Column(nullable = false)
	private Integer annoPubblicazione;
	@Column(nullable = false)
	private Double prezzo;
	
	@ManyToMany()
	@JoinTable(name = "libri_autori",
    joinColumns = @JoinColumn(name = "libri_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "autore_id", referencedColumnName = "id"))
	private List<Autore> autori = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "libri_categoria",
    joinColumns = @JoinColumn(name = "libri_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "categoria_id", referencedColumnName = "id"))
	private List<Categoria> categorie = new ArrayList<>();

}
