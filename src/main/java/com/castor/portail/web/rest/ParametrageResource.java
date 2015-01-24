package com.castor.portail.web.rest;

import com.castor.portail.service.ParametrageDefautService;
import com.castor.portail.service.ParametrageService;
import com.castor.portail.struct.ParametrageStruct;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * REST controller for managing Alias.
 */
@RestController
@RequestMapping("/app")
public class ParametrageResource {

    private final Logger log = LoggerFactory.getLogger(ParametrageResource.class);

    @Inject
    private ParametrageDefautService parametrageDefautService;

    @Inject
    private ParametrageService parametrageService;

    /**
     * GET  /rest/parametrage/defaut -> get the parametrage.
     * @throws Exception
     */
    @RequestMapping(value = "/rest/parametrage/defaut",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ParametrageStruct> getParametrageDefaut() throws Exception {
        log.debug("REST request to getParametrageDefaut : {}");
        List<ParametrageStruct> listeParametrageStruct = parametrageDefautService.getAllParametrageDefaut();
        return listeParametrageStruct;
    }

    /**
     * GET  /rest/parametrage/defaut -> get the parametrage.
     * @throws Exception
     */
    @RequestMapping(value = "/rest/parametrage/defaut/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ParametrageStruct> getParametrageDefaut(@PathVariable Long id) throws Exception {
        log.debug("REST request to getParametrageDefaut : {}",id);
        ParametrageStruct parametrageStruct = parametrageDefautService.get(id);;
        if (parametrageStruct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(parametrageStruct, HttpStatus.OK);
    }

    /**
     * POST  /rest/parametrage/defaut -> save the parametrage.
     * @throws Exception
     */
    @RequestMapping(value = "/rest/parametrage/defaut",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void saveParametrageDefaut(@RequestBody ParametrageStruct parametrageDefaut) throws Exception {
        log.debug("REST request to saveParametrageDefaut : {}");
        parametrageDefautService.save(parametrageDefaut);;
    }

    /**
     * POST  /rest/parametrage/defaut -> save the parametrage.
     * @throws Exception
     */
    @RequestMapping(value = "/rest/parametrage/defaut/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void deleteParametrageDefaut(@PathVariable Long id) throws Exception {
        log.debug("REST request to deleteParametrageDefaut : {}",id);
        parametrageDefautService.delete(id);;
    }

    /**
     * GET  /rest/parametrage -> get the parametrage.
     * @throws Exception
     */
    @RequestMapping(value = "/rest/parametrage",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ParametrageStruct> getParametrage() throws Exception {
        log.debug("REST request to getParametrageDefaut : {}");
        List<ParametrageStruct> listeParametrageStruct = parametrageService.getAllParametrage();
        return listeParametrageStruct;
    }

    /**
     * GET  /rest/parametrage -> get the parametrage.
     * @throws Exception
     */
    @RequestMapping(value = "/rest/parametrage/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ParametrageStruct> getParametrage(@PathVariable Long id) throws Exception {
        log.debug("REST request to getParametrage : {}",id);
        ParametrageStruct parametrageStruct = parametrageService.get(id);;
        if (parametrageStruct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(parametrageStruct, HttpStatus.OK);
    }

    /**
     * POST  /rest/parametrage/defaut -> save the parametrage.
     * @throws Exception
     */
    @RequestMapping(value = "/rest/parametrage",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void saveParametrage(@RequestBody ParametrageStruct parametrage) throws Exception {
        log.debug("REST request to saveParametrage : {}");
        parametrageService.save(parametrage);;
    }

    /**
     * POST  /rest/parametrage -> save the parametrage.
     * @throws Exception
     */
    @RequestMapping(value = "/rest/parametrage/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void deleteParametrage(@PathVariable Long id) throws Exception {
        log.debug("REST request to deleteParametrage : {}",id);
        parametrageService.delete(id);;
    }
}
