package com.castor.portail.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by GUDA on 24/01/2015.
 */
@Entity
@Table(name = "T_PARAMETRAGE_DEFAUT")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ParametrageDefaut extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aliasid")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Alias alias;

    @Column(name = "parametre_alias")
    private String parametrageAlias;

    @Column(name = "titre")
    private String titre;

    @Column(name = "url")
    private String url;

    @Column(name = "glyphicon")
    private String glyphicon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ParametrageDefaut alias = (ParametrageDefaut) o;

        if (id != null ? !id.equals(alias.id) : alias.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "ParametrageDefaut{" +
            "id=" + id +
            ", alias='" + alias + "'" +
            '}';
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
     * @return the alias
     */
    public Alias getAlias() {
        return alias;
    }

    /**
     * @param alias the alias to set
     */
    public void setAlias(Alias alias) {
        this.alias = alias;
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
}
