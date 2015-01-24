package com.castor.portail.repository;

import com.castor.portail.domain.Parametrage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by GUDA on 24/01/2015.
 */
public interface ParametrageRepository extends JpaRepository<Parametrage, Long> {

    List<Parametrage> findByCreatedBy(String owner);

    Parametrage findByIdAndCreatedBy(Long id, String owner);
}
