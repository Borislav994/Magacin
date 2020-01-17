package jwd.magacin.service;

import java.util.List;

import jwd.magacin.model.Magacin;


public interface MagacinService {

	List<Magacin> findAll();

	Magacin findOne(Long id);

	Magacin save(Magacin magacin);

	Magacin delete(Long id);

	List<Magacin> saveList(List<Magacin> magacin);

	void removeList(List<Long> magacin);
}
