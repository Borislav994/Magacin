package jwd.magacin.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.magacin.model.Magacin;
import jwd.magacin.repository.MagacinRepository;
import jwd.magacin.service.MagacinService;

@Service
@Transactional
public class JpaMagacinService implements MagacinService {
	
	@Autowired
	private MagacinRepository magacinRepository;

	@Override
	public List<Magacin> findAll() {
		// TODO Auto-generated method stub
		return magacinRepository.findAll();
	}

	@Override
	public Magacin findOne(Long id) {
		// TODO Auto-generated method stub
		return magacinRepository.findOne(id);
	}

	@Override
	public Magacin save(Magacin magacin) {
		// TODO Auto-generated method stub
		return magacinRepository.save(magacin);
	}

	@Override
	public Magacin delete(Long id) {
		// TODO Auto-generated method stub
		Magacin toDelete = magacinRepository.findOne(id);
		if(toDelete != null) {
			magacinRepository.delete(toDelete);
		}
		return toDelete;
	}

	@Override
	public List<Magacin> saveList(List<Magacin> magacini) {
		// TODO Auto-generated method stub
		return magacinRepository.save(magacini);
	}

	@Override
	public void removeList(List<Long> magacini) {
		// TODO Auto-generated method stub
		for (Long ids : magacini) {
			magacinRepository.delete(ids);
		}
	}

}
