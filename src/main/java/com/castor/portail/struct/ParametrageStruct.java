package com.castor.portail.struct;

/**
 * Created by GUDA on 24/01/2015.
 */
public class ParametrageStruct {

    private Long id;
    private AliasStruct aliasStruct;
    private String parametrageAlias;
    private String titre;
    private String url;
    private String glyphicon;
    /**
     * @return the aliasStruct
     */
    public AliasStruct getAliasStruct() {
        return aliasStruct;
    }
    /**
     * @param aliasStruct the aliasStruct to set
     */
    public void setAliasStruct(AliasStruct aliasStruct) {
        this.aliasStruct = aliasStruct;
    }
    /**
     * @return the glyphicon
     */
    public String getGlyphicon() {
        return glyphicon;
    }
    /**
     * @param glyphicon the glyphicon to set
     */
    public void setGlyphicon(String glyphicon) {
        this.glyphicon = glyphicon;
    }
    /**
     * @return the titre
     */
    public String getTitre() {
        return titre;
    }
    /**
     * @param titre the titre to set
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }
    /**
     * @return the parametrageAlias
     */
    public String getParametrageAlias() {
        return parametrageAlias;
    }
    /**
     * @param parametrageAlias the parametrageAlias to set
     */
    public void setParametrageAlias(String parametrageAlias) {
        this.parametrageAlias = parametrageAlias;
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

}
