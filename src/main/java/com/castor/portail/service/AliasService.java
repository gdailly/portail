package com.castor.portail.service;

import com.castor.portail.domain.Alias;
import com.castor.portail.repository.AliasRepository;
import com.castor.portail.security.SecurityUtils;
import com.castor.portail.service.util.AliasHelper;
import com.castor.portail.struct.AliasStruct;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GUDA on 24/01/2015.
 */
@Service
public class AliasService {

    @Inject
    private AliasRepository aliasRepository;

    public Alias getAliasFromBdd(String alias) {
        return aliasRepository.findByAlias(alias);
    }

    public AliasStruct getAlias(Long id) throws Exception {
        AliasStruct response = null;

        // Etape 1 : Récupération de l'alias en base de données
        Alias aliasBdd = null;
        aliasBdd = aliasRepository.findOne(id);

        // Etape 2 : Construction de l'url
        if(aliasBdd != null) {
            response = buildAliasStruct(aliasBdd,"");
        }

        return response;
    }

    /**
     * Récupération de l'alias en base de données
     * @throws Exception
     */
    public AliasStruct getAlias(String url) throws Exception {
        AliasStruct response = null;

        // Etape 1 : Parse de l'url
        String parameters = "";
        String alias = url;
        if (url.contains("/")) {
            parameters = url.substring(url.indexOf("/"));
            alias = url.substring(0, url.indexOf("/"));
        }

        // Etape 2 : Récupération de l'alias en base de données
        Alias aliasBdd = null;
        if(!"".equals(alias)) {
            aliasBdd = getAliasFromBdd(alias);
        }

        // Etape 3 : Construction de l'url
        if(aliasBdd != null) {
            response = buildAliasStruct(aliasBdd,parameters);
        }

        return response;
    }

    public List<AliasStruct> getAll() throws Exception {
        List<AliasStruct> response = new ArrayList<AliasStruct>();
        List<Alias> listeAlias = aliasRepository.findAll();

        for (Alias alias : listeAlias) {
            response.add(buildAliasStruct(alias,""));
        }

        return response;
    }

    public void save(AliasStruct aliasStruct) {
        Alias alias = null;
        if(aliasStruct.getId() != null) {
            alias = aliasRepository.findOne(aliasStruct.getId());
        }

        if(alias == null) {
            alias = new Alias();
        }
        alias.setAlias(aliasStruct.getAlias());

        if (!aliasStruct.getUrl().contains("://")) {
            aliasStruct.setUrl("http://" + aliasStruct.getUrl());
        }
        alias.setUrl(aliasStruct.getUrl());

        if(aliasStruct.getOwner() != null) {
            alias.setOwner(aliasStruct.getOwner());
        } else {
            alias.setOwner(SecurityUtils.getCurrentLogin());
        }

        aliasRepository.save(alias);
    }

    public void delete(Long id) {
        aliasRepository.delete(id);
    }

    private AliasStruct buildAliasStruct(Alias alias, String parameters) throws Exception {
        AliasStruct response = AliasHelper.fromTarget(alias.getUrl());
        response.setTarget(AliasHelper.apply(response, parameters));
        response.setAlias(alias.getAlias());
        response.setId(alias.getId());
        response.setOwner(alias.getOwner());

        return response;
    }
}
