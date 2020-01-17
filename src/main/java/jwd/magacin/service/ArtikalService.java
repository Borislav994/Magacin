package jwd.magacin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import jwd.magacin.model.Artikal;
import jwd.magacin.model.StavkaPrijema;


public interface ArtikalService {

	Artikal findOne(Long id);

	Page<Artikal> findAll(int pageNum);

	Artikal save(Artikal artikal);

	void delete(Long id);

	List<Artikal> findByMagacin(Long magacinId);
	
	Page<Artikal> search(
			@Param("artikalNaziv") String artikalNaziv, 
			@Param("magacinNaziv") String magacinNaziv, 
			 int pageNum);
	
	StavkaPrijema primi(Long id, StavkaPrijema stNew);
}
