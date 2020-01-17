package jwd.magacin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.magacin.model.StavkaPrijema;

@Repository
public interface StavkaPrijemaRepository extends JpaRepository<StavkaPrijema, Long >{

}
