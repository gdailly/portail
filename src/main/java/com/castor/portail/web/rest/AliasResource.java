package com.castor.portail.web.rest;

import com.castor.portail.domain.Alias;
import com.castor.portail.service.AliasService;
import com.castor.portail.struct.AliasStruct;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing Alias.
 */
@RestController
@RequestMapping("/app")
public class AliasResource {

    private final Logger log = LoggerFactory.getLogger(AliasResource.class);

    @Inject
    private AliasService aliasService;

    private static final String URI_REDIRECT = "/app/redirect/";
    /**
     * GET  /rest/alias/:id -> get the "id" alias.
     * @throws Exception
     */
    @RequestMapping(value = "/redirect/**",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Alias> redirect(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String requestUri = request.getRequestURI();
        String aliasToSearch = requestUri.substring(requestUri.indexOf(URI_REDIRECT) + URI_REDIRECT.length());
        log.debug("REST request to get Alias : {}", aliasToSearch);
        AliasStruct aliasStruct = aliasService.getAlias(aliasToSearch);
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        if(aliasStruct == null) {
            response.setHeader("Location", request.getRequestURL().substring(0, request.getRequestURL().indexOf(URI_REDIRECT)));
        } else {
            response.setHeader("Location", aliasStruct.getTarget());
        }
        return null;
    }

    /**
     * GET  /rest/aliad -> get the alias.
     * @throws Exception
     */
    @RequestMapping(value = "/rest/alias",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AliasStruct> get(@RequestBody AliasStruct alias) throws Exception {
        log.debug("REST request to get Alias : {}", alias.getAlias());
        AliasStruct aliasStruct = aliasService.getAlias(alias.getAlias());
        if (aliasStruct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(aliasStruct, HttpStatus.OK);
    }


    /**
     * GET  /rest/alias/crud -> save alias.
     * @throws Exception
     */
    @RequestMapping(value = "/rest/alias/crud",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<AliasStruct> getAll() throws Exception {
        log.debug("REST request to get all Alias : {}");
        return aliasService.getAll();
    }

    /**
     * GET  /rest/alias/crud -> save alias.
     */
    @RequestMapping(value = "/rest/alias/crud",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void save(@RequestBody AliasStruct alias) {
        log.debug("REST request to save Alias : {}", alias.getAlias());
        aliasService.save(alias);
    }

    /**
     * GET  /rest/alias/crud -> save alias.
     * @throws Exception
     */
    @RequestMapping(value = "/rest/alias/crud/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AliasStruct> get(@PathVariable Long id) throws Exception {
        log.debug("REST request to get Alias : {}", id);
        AliasStruct aliasStruct = aliasService.getAlias(id);
        if (aliasStruct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(aliasStruct, HttpStatus.OK);
    }

    /**
     * GET  /rest/alias/crud -> save alias.
     */
    @RequestMapping(value = "/rest/alias/crud/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Alias : {}", id);
        aliasService.delete(id);
    }
}
