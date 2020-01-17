package jwd.magacin.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.magacin.model.Artikal;
import jwd.magacin.model.Magacin;
import jwd.magacin.service.ArtikalService;
import jwd.magacin.service.MagacinService;
import jwd.magacin.support.ArtikalToArtikalDTO;
import jwd.magacin.support.MagacinDTOtoMagacin;
import jwd.magacin.support.MagacinToMagacinDTO;
import jwd.magacin.web.dto.ArtikalDTO;
import jwd.magacin.web.dto.MagacinDTO;


@RestController
@RequestMapping("/api/magacini")
public class ApiMagacinController {
	
	@Autowired
	private ArtikalService artikalService;
	
	@Autowired
	private MagacinService magacinService;
	
	@Autowired
	private MagacinDTOtoMagacin toMagacin;
	
	@Autowired
	private MagacinToMagacinDTO toDTO;
	
	@Autowired
	private ArtikalToArtikalDTO toDTOa;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<MagacinDTO>> getActivities(){
		
		List<Magacin> magacini = magacinService.findAll();
		
		return new ResponseEntity<>(toDTO.convert(magacini), HttpStatus.OK);
	} 
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<MagacinDTO> getOne(@PathVariable Long id) {
		Magacin ac = magacinService.findOne(id);
		if (ac == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(ac), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<MagacinDTO> delete(@PathVariable Long id) {
		Magacin ac = magacinService.delete(id);
		if (ac.getId() == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(ac), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<MagacinDTO> add(@Validated @RequestBody MagacinDTO mgToAdd) {

		Magacin saved = magacinService.save(toMagacin.convert(mgToAdd));

		return new ResponseEntity<>(toDTO.convert(saved), HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}",
			consumes="application/json")
	public ResponseEntity<MagacinDTO> edit(@PathVariable Long id, @RequestBody MagacinDTO mgEdit) {

		if (!id.equals(mgEdit.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Magacin persisted = magacinService.save(toMagacin.convert(mgEdit));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	@RequestMapping(value = "{magacinId}/artikli", method = RequestMethod.GET)
	public ResponseEntity<List<ArtikalDTO>> getAllId(@PathVariable Long magacinId){
		List<Artikal> artikli = artikalService.findByMagacin(magacinId);

		return new ResponseEntity<>(toDTOa.convert(artikli), HttpStatus.OK);
	}

	
	

}
