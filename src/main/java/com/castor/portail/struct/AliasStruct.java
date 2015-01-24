package com.castor.portail.struct;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GUDA on 24/01/2015.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AliasStruct {

    private Long id;

    /**
     * The alias of the redirection (the first path component after 'go')
     */
    private String alias;

    /**
     * The target URL.
     */
    private String url;

    /**
     * The owner
     */
    private String owner;

    /**
     * The target URL.
     */
    private String target;

    /**
     * Liste des paramétres
     */
    private List<String> params = new ArrayList<String>();

    /**
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the params
     */
    public List<String> getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(List<String> params) {
        this.params = params;
    }

    /**
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    /**
     * @param target the target to set
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }
}

