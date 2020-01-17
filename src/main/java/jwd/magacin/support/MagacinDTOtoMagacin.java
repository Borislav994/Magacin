package jwd.magacin.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.magacin.model.Magacin;
import jwd.magacin.service.MagacinService;
import jwd.magacin.web.dto.MagacinDTO;

@Component
public class MagacinDTOtoMagacin implements Converter<MagacinDTO, Magacin>  {
	
	@Autowired
	private MagacinService magacinService;

	@Override
	public Magacin convert(MagacinDTO magacinDTO) {
		Magacin magacin = magacinService.findOne(magacinDTO.getId());

		if ( magacin != null) {
			magacin = new Magacin();
			magacin.setId(magacinDTO.getId());
			magacin.setNaziv(magacinDTO.getNaziv());
			magacin.setAdresa(magacinDTO.getAdresa());
			return magacin;
		} else {
			magacin = new Magacin();
			magacin.setId(magacinDTO.getId());
			magacin.setNaziv(magacinDTO.getNaziv());
			magacin.setAdresa(magacinDTO.getAdresa());
			return magacin;
		}
}
	
	
	public List<Magacin> convert(List<MagacinDTO> magacinDTOs) {
		List<Magacin> ret = new ArrayList<>();

		for (MagacinDTO magacinDTO: magacinDTOs) {
			ret.add(convert(magacinDTO));
		}

		return ret;
	}
	

}
