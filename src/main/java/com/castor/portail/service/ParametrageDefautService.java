package com.castor.portail.service;

import com.castor.portail.domain.Alias;
import com.castor.portail.domain.ParametrageDefaut;
import com.castor.portail.repository.ParametrageDefautRepository;
import com.castor.portail.struct.ParametrageStruct;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParametrageDefautService {

    @Inject
    private ParametrageDefautRepository parametrageDefautRepository;

    @Inject
    private AliasService aliasService;

    /**
     * Récupération de l'ensemble des paramétrages par défaut
     * @return
     * @throws Exception
     */
    public List<ParametrageStruct> getAllParametrageDefaut() throws Exception {
        List<ParametrageDefaut> listeParametrageDefauts = parametrageDefautRepository.findAll();
        List<ParametrageStruct> listeParametrageStruct = new ArrayList<ParametrageStruct>();

        for (ParametrageDefaut parametrageDefaut : listeParametrageDefauts) {
            listeParametrageStruct.add(buildParametrageStruct(parametrageDefaut));
        }

        return listeParametrageStruct;
    }

    public void save(ParametrageStruct parametrageStruct) {
        ParametrageDefaut parametrageDefaut = null;
        if(parametrageStruct.getId() != null) {
            parametrageDefaut = parametrageDefautRepository.findOne(parametrageStruct.getId());
        }

        if(parametrageDefaut == null) {
            parametrageDefaut = new ParametrageDefaut();
        }

        parametrageDefaut.setTitre(parametrageStruct.getTitre());
        parametrageDefaut.setUrl(parametrageStruct.getUrl());
        if (!parametrageDefaut.getUrl().contains("://")) {
            parametrageDefaut.setUrl("http://" + parametrageDefaut.getUrl());
        }
        parametrageDefaut.setGlyphicon(parametrageStruct.getGlyphicon());
        if(parametrageStruct.getAliasStruct() != null && parametrageStruct.getAliasStruct().getId() != null) {
            Alias alias = new Alias();
            alias.setId(parametrageStruct.getAliasStruct().getId());
            parametrageDefaut.setAlias(alias);
            parametrageDefaut.setParametrageAlias(parametrageStruct.getParametrageAlias());
        } else {
            parametrageDefaut.setAlias(null);
        }

        parametrageDefautRepository.save(parametrageDefaut);
    }

    public void delete(Long id) {
        parametrageDefautRepository.delete(id);
    }

    public ParametrageStruct get(Long id) throws Exception {
        ParametrageDefaut parametrageDefaut = parametrageDefautRepository.findOne(id);
        return buildParametrageStruct(parametrageDefaut);
    }

    private ParametrageStruct buildParametrageStruct(ParametrageDefaut parametrageDefaut) throws Exception {
        ParametrageStruct parametrageStruct = new ParametrageStruct();

        parametrageStruct.setId(parametrageDefaut.getId());
        parametrageStruct.setGlyphicon(parametrageDefaut.getGlyphicon());
        parametrageStruct.setUrl(parametrageDefaut.getUrl());
        parametrageStruct.setTitre(parametrageDefaut.getTitre());
        if(parametrageDefaut.getAlias() != null) {
            String aliasToSearch = parametrageDefaut.getAlias().getAlias();
            if(parametrageDefaut.getParametrageAlias() != null && !"".equals(parametrageDefaut.getParametrageAlias())) {
                aliasToSearch += "/" + parametrageDefaut.getParametrageAlias();
            }
            parametrageStruct.setAliasStruct(aliasService.getAlias(aliasToSearch));
            parametrageStruct.setParametrageAlias(parametrageDefaut.getParametrageAlias());
            parametrageStruct.setUrl(parametrageStruct.getAliasStruct().getTarget());
        }
        if (!parametrageStruct.getUrl().contains("://")) {
            parametrageStruct.setUrl("http://" + parametrageStruct.getUrl());
        }
        return parametrageStruct;
    }

}
