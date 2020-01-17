package jwd.magacin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.magacin.model.Artikal;
import jwd.magacin.model.Magacin;

@Repository
public interface ArtikalRepositiry extends JpaRepository<Artikal, Long >{

	List<Artikal> findByMagacinId(Long magacinId);

	List<Artikal> findByMagacin(Magacin magacin);
	
	@Query("SELECT a FROM Artikal a WHERE "
			+ "(:artikalNaziv IS NULL or a.naziv like :artikalNaziv ) AND "
			+ "(:magacinNaziv IS NULL OR a.magacin.naziv like :magacinNaziv)"
			)
	Page<Artikal> search(
			@Param("artikalNaziv") String artikalNaziv, 
			@Param("magacinNaziv") String magacinNaziv, 
			Pageable pageRequest);
}
