package jwd.magacin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.magacin.model.Artikal;
import jwd.magacin.model.StavkaPrijema;
import jwd.magacin.repository.ArtikalRepositiry;
import jwd.magacin.repository.StavkaPrijemaRepository;
import jwd.magacin.service.ArtikalService;

@Service
public class JpaArtikalService implements ArtikalService{
	
	@Autowired
	private ArtikalRepositiry artikalRepository;
	
	@Autowired
	private StavkaPrijemaRepository prijemRepository;

	@Override
	public Artikal findOne(Long id) {
		// TODO Auto-generated method stub
		return artikalRepository.findOne(id);
	}

	@Override
	public Page<Artikal> findAll(int pageNum) {
		// TODO Auto-generated method stub
		return artikalRepository.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Artikal save(Artikal artikal) {
		// TODO Auto-generated method stub
		return artikalRepository.save(artikal);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		artikalRepository.delete(id);
		
	}

	@Override
	public List<Artikal> findByMagacin(Long magacinId) {
		// TODO Auto-generated method stub
		return artikalRepository.findByMagacinId(magacinId);
	}

	@Override
	public Page<Artikal> search(String artikalNaziv, String magacinNaziv, int pageNum) {
		// TODO Auto-generated method stub
		
		if(artikalNaziv != null) {
			artikalNaziv = '%' + artikalNaziv + '%';
		}
		if(magacinNaziv != null) {
			magacinNaziv = '%' + magacinNaziv + '%';
		}
		return artikalRepository.search(artikalNaziv, magacinNaziv, new PageRequest(pageNum, 5));
	}

	@Override
	public StavkaPrijema primi(Long id, StavkaPrijema stNew) {
		Artikal artikal = artikalRepository.findOne(id);
		artikal.setKolicina(stNew.getKolicina() + artikal.getKolicina());
		artikal.setKalkulisanaCena((artikal.getKolicina()*artikal.getKalkulisanaCena()
				+ stNew.getKolicina() * stNew.getJedinicnaCena())/(artikal.getKolicina() +  stNew.getKolicina()));
		StavkaPrijema stavka1 = new StavkaPrijema();
		stavka1.setJedinicnaCena((int) stNew.getJedinicnaCena());
		stavka1.setKolicina(stNew.getKolicina());
		stavka1.setArtikal(artikal);
		artikalRepository.save(artikal);
		prijemRepository.save(stavka1);
		return stavka1;
	}

}
