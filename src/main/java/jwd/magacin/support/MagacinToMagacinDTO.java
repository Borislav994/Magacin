package jwd.magacin.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.magacin.model.Magacin;
import jwd.magacin.web.dto.MagacinDTO;

@Component
public class MagacinToMagacinDTO  implements Converter<Magacin, MagacinDTO>{

	@Override
	public MagacinDTO convert(Magacin magacin) {
		MagacinDTO retValue = new MagacinDTO();
		retValue.setId(magacin.getId());
		retValue.setNaziv(magacin.getNaziv());
		retValue.setAdresa(magacin.getAdresa());
		
		return retValue;
	}
	
	public List<MagacinDTO> convert(List<Magacin> magacini) {
		List<MagacinDTO> ret = new ArrayList<>();

		for (Magacin magacin : magacini) {
			ret.add(convert(magacin));
		}

		return ret;
	}
}
