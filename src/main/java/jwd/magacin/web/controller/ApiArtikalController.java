package jwd.magacin.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jwd.magacin.model.Artikal;
import jwd.magacin.model.StavkaPrijema;
import jwd.magacin.service.ArtikalService;
import jwd.magacin.support.ArtikalDTOtoArtikal;
import jwd.magacin.support.ArtikalToArtikalDTO;
import jwd.magacin.web.dto.ArtikalDTO;

@Controller
@RequestMapping("/api/artikli")
public class ApiArtikalController {

	@Autowired
	private ArtikalService artikalService;
	

	@Autowired
	private ArtikalDTOtoArtikal toArtikal;

	@Autowired
	private ArtikalToArtikalDTO toDTO;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ArtikalDTO>> get(
			@RequestParam(required = false) String artikalNaziv,
			@RequestParam(required = false) String magacinNaziv,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum) {

		Page<Artikal> recordsPage = null;

		if (artikalNaziv != null || magacinNaziv != null) {
			recordsPage = artikalService.search(artikalNaziv, magacinNaziv, pageNum);
		} else {
			recordsPage = artikalService.findAll(pageNum);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(recordsPage.getTotalPages()));

		return new ResponseEntity<>(toDTO.convert(recordsPage.getContent()), headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ArtikalDTO> get(@PathVariable Long id) {

		Artikal artikal = artikalService.findOne(id);

		return new ResponseEntity<>(toDTO.convert(artikal), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ArtikalDTO> add(@Validated @RequestBody ArtikalDTO newArtikal) {

		Artikal persisted = artikalService.save(toArtikal.convert(newArtikal));

		artikalService.save(persisted);

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<ArtikalDTO> edit(@PathVariable Long id, @RequestBody ArtikalDTO editedArtikal) {

		if (id == null || !id.equals(editedArtikal.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Artikal persisted = artikalService.save(toArtikal.convert(editedArtikal));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ArtikalDTO> delete(@PathVariable Long id) {
		Artikal ar = artikalService.findOne(id);
		if (ar.getKolicina() == 0.0) {
			artikalService.delete(id);

			return new ResponseEntity<>(toDTO.convert(ar), HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<StavkaPrijema> primi(@PathVariable Long id, @RequestBody StavkaPrijema stNew
			) {
		StavkaPrijema st = artikalService.primi(id, stNew);
		
		if(st == null) {
			return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		}

		return new ResponseEntity<>(st, HttpStatus.OK);
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
