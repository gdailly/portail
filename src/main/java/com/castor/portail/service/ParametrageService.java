package com.castor.portail.service;


import com.castor.portail.domain.Alias;
import com.castor.portail.domain.Parametrage;
import com.castor.portail.repository.ParametrageRepository;
import com.castor.portail.security.SecurityUtils;
import com.castor.portail.struct.ParametrageStruct;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParametrageService {

    @Inject
    private ParametrageRepository parametrageRepository;

    @Inject
    private AliasService aliasService;

    /**
     * Récupération de l'ensemble des paramétrages par défaut
     * @return
     * @throws Exception
     */
    public List<ParametrageStruct> getAllParametrage() throws Exception {
        List<Parametrage> listeParametrage = parametrageRepository.findByCreatedBy(SecurityUtils.getCurrentLogin());
        List<ParametrageStruct> listeParametrageStruct = new ArrayList<ParametrageStruct>();

        for (Parametrage parametrage : listeParametrage) {
            listeParametrageStruct.add(buildParametrageStruct(parametrage));
        }

        return listeParametrageStruct;
    }

    public void save(ParametrageStruct parametrageStruct) {
        Parametrage parametrage = null;
        if(parametrageStruct.getId() != null) {
            parametrage = parametrageRepository.findByIdAndCreatedBy(parametrageStruct.getId(), SecurityUtils.getCurrentLogin());
        }

        if(parametrage == null) {
            parametrage = new Parametrage();
        }

        parametrage.setTitre(parametrageStruct.getTitre());
        parametrage.setUrl(parametrageStruct.getUrl());
        if (!parametrage.getUrl().contains("://")) {
            parametrage.setUrl("http://" + parametrage.getUrl());
        }
        parametrage.setGlyphicon(parametrageStruct.getGlyphicon());
        if(parametrageStruct.getAliasStruct() != null && parametrageStruct.getAliasStruct().getId() != null) {
            Alias alias = new Alias();
            alias.setId(parametrageStruct.getAliasStruct().getId());
            parametrage.setAlias(alias);
            parametrage.setParametrageAlias(parametrageStruct.getParametrageAlias());
        } else {
            parametrage.setAlias(null);
        }

        parametrageRepository.save(parametrage);
    }

    public void delete(Long id) {
        parametrageRepository.delete(id);
    }

    public ParametrageStruct get(Long id) throws Exception {
        Parametrage parametrage = parametrageRepository.findByIdAndCreatedBy(id, SecurityUtils.getCurrentLogin());
        return buildParametrageStruct(parametrage);
    }

    private ParametrageStruct buildParametrageStruct(Parametrage Parametrage) throws Exception {
        ParametrageStruct parametrageStruct = new ParametrageStruct();

        parametrageStruct.setId(Parametrage.getId());
        parametrageStruct.setGlyphicon(Parametrage.getGlyphicon());
        parametrageStruct.setUrl(Parametrage.getUrl());
        parametrageStruct.setTitre(Parametrage.getTitre());
        if(Parametrage.getAlias() != null) {
            String aliasToSearch = Parametrage.getAlias().getAlias();
            if(Parametrage.getParametrageAlias() != null && !"".equals(Parametrage.getParametrageAlias())) {
                aliasToSearch += "/" + Parametrage.getParametrageAlias();
            }
            parametrageStruct.setAliasStruct(aliasService.getAlias(aliasToSearch));
            parametrageStruct.setParametrageAlias(Parametrage.getParametrageAlias());
            parametrageStruct.setUrl(parametrageStruct.getAliasStruct().getTarget());
        }
        if (!parametrageStruct.getUrl().contains("://")) {
            parametrageStruct.setUrl("http://" + parametrageStruct.getUrl());
        }
        return parametrageStruct;
    }

}
