package jwd.magacin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.magacin.model.Artikal;
import jwd.magacin.model.Magacin;
import jwd.magacin.service.ArtikalService;
import jwd.magacin.service.MagacinService;

@Component
public class TestData {

	@Autowired
	private MagacinService magacinService;
	
	@Autowired
	private ArtikalService artikalService;
	
	@PostConstruct
	public void init() {
		

		Magacin magacin1 = new Magacin();
				magacin1.setNaziv("Magacin1");
				magacin1.setAdresa("Lenjinova 45 Sajkas");
		magacin1 = magacinService.save(magacin1);
		
		Magacin magacin2 = new Magacin();
			magacin2.setNaziv("Magacin2");
			magacin2.setAdresa("Lenjinova 45 Mosorin");
			
		magacin2 = magacinService.save(magacin2);
		
		Artikal artikal1 = new Artikal();
		artikal1.setNaziv("Voda");
		artikal1.setPakovanje(100);
		artikal1.setMagacin(magacin1);
		artikal1.setJedinicaMere("Litar");
		artikal1 = artikalService.save(artikal1);
		
		Artikal artikal2 = new Artikal();
		artikal2.setNaziv("Sok");
		artikal2.setPakovanje(120);
		artikal2.setJedinicaMere("Litar");
		artikal2.setMagacin(magacin2);
		artikal2 = artikalService.save(artikal2);
		
		Artikal artikal3 = new Artikal();
		artikal3.setNaziv("Kafa");
		artikal3.setPakovanje(130);
		artikal3.setJedinicaMere("Kilogram");
		artikal3.setMagacin(magacin1);
		artikal3 = artikalService.save(artikal3);
		
		
				
		
	}
}
