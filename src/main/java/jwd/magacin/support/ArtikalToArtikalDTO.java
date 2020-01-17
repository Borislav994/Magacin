package jwd.magacin.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.magacin.model.Artikal;
import jwd.magacin.web.dto.ArtikalDTO;

@Component
public class ArtikalToArtikalDTO implements Converter<Artikal, ArtikalDTO> {

	@Override
	public ArtikalDTO convert(Artikal artikal) {
		ArtikalDTO retValue = new ArtikalDTO();
		retValue.setId(artikal.getId());
		retValue.setNaziv(artikal.getNaziv());
		retValue.setPakovanje(artikal.getPakovanje());
		retValue.setJedinicaMere(artikal.getJedinicaMere());
		retValue.setKolicina(artikal.getKolicina());
		retValue.setKalkulisanaCena(artikal.getKalkulisanaCena());
		retValue.setMagacinId(artikal.getMagacin().getId());
		retValue.setMagacinName(artikal.getMagacin().getNaziv());

		return retValue;
	}
	
	public List<ArtikalDTO> convert(List<Artikal> artikals) {
		List<ArtikalDTO> ret = new ArrayList<>();

		for (Artikal artikal : artikals) {
			ret.add(convert(artikal));
		}

		return ret;
	}

}
