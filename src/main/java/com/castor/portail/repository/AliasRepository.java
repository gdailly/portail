package com.castor.portail.repository;

import com.castor.portail.domain.Alias;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by GUDA on 24/01/2015.
 */
public interface AliasRepository extends JpaRepository<Alias, Long> {

    Alias findByAlias(String alias);
}
