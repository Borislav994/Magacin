package jwd.magacin.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.magacin.model.Artikal;
import jwd.magacin.model.Magacin;
import jwd.magacin.service.ArtikalService;
import jwd.magacin.service.MagacinService;
import jwd.magacin.web.dto.ArtikalDTO;


@Component
public class ArtikalDTOtoArtikal implements Converter<ArtikalDTO, Artikal> {
	
	@Autowired
	private ArtikalService artikalSevice;
	
	@Autowired
	private MagacinService magacinService;

	@Override
	public Artikal convert(ArtikalDTO artikalDTO) {
		Magacin magacin = magacinService.findOne(artikalDTO.getMagacinId());

		if (magacin != null) {

			Artikal artikal = null;

			if (artikalDTO.getId() != null) {
				artikal = artikalSevice.findOne(artikalDTO.getId());
			} else {
				artikal = new Artikal();
			}

			artikal.setId(artikalDTO.getId());
			artikal.setNaziv(artikalDTO.getNaziv());
			artikal.setPakovanje(artikalDTO.getPakovanje());
			artikal.setJedinicaMere(artikalDTO.getJedinicaMere());
			artikal.setKalkulisanaCena(artikalDTO.getKalkulisanaCena());
			artikal.setKolicina(artikalDTO.getKolicina());
			artikal.setMagacin(magacin);

			return artikal;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}		
	}
	
	public List<Artikal> convert(List<ArtikalDTO> artikalsDTOs) {
		List<Artikal> ret = new ArrayList<>();

		for (ArtikalDTO artikalDTO : artikalsDTOs) {
			ret.add(convert(artikalDTO));
		}

		return ret;
	}

}
