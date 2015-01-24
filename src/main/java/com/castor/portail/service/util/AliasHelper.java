package com.castor.portail.service.util;

import com.castor.portail.struct.AliasStruct;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by GUDA on 24/01/2015.
 */
public class AliasHelper {

    /**
     * Return a populated Redirection instance from a target URL.
     *
     * @param url the target URL to parse
     * @return
     */
    public static AliasStruct fromTarget(String url) throws Exception {
        //
        // Make sure url is a valid URL
        //

        URL u = new URL(url);

        AliasStruct redirection = new AliasStruct();

        //
        // Parse path to extract positional parameters usage
        //
        // Positional parameters usage match: ${([0-9]+)(:[^}]*)}
        //

        Pattern pattern = Pattern.compile("\\$\\{([0-9]+(:[+!-][^}]*)?)\\}");
        Matcher m = pattern.matcher(url);

        //
        // Extract positional parameter usage if some were found
        //

        while(m.find()) {
            redirection.getParams().add(m.group(1));
        }

        redirection.setUrl(url);

        return redirection;

    }

    /**
     * Apply a redirection instance to a path
     *
     * @param redirection
     * @param path
     * @return
     */
    public static String apply(AliasStruct redirection, String path) {
        //
        // If there are no positional parameters to replace, return raw target
        //

        if (redirection.getParams().size() == 0) {
            return redirection.getUrl();
        }

        //
        // Extract positional parameters's values from path
        //

        Map<String,String> params = new HashMap<String,String>();

        String[] values = null;


        //
        // Handle extra infos (those behind the alias)
        //

        String extra;

        if (null == path) {
            extra = "";
        } else if (!path.startsWith("/")) {
            // If path does not start with '/', add a leading '/'
            extra = "/" + path;
        } else {
            extra = path;
        }

        values = extra.split("/");

        // Store whole infos in param '0'
        params.put("0", extra);

        // Store positional params values keyed by position
        for (int i = 1; i < values.length; i++) {
            params.put("" + i, values[i]);
        }

        //
        // Replace all occurences of positional parameters
        //

        String target = redirection.getUrl();

        for (String p: redirection.getParams()) {
            String key = null;
            String defvalue = null;
            boolean altvalue = false;
            boolean onlyifnotset = false;

            if (p.contains(":")) {
                key = p.substring(0,p.indexOf(":"));
                defvalue = p.substring(p.indexOf(":") + 1);

                if (defvalue.startsWith("+")) {
                    altvalue = true;
                    defvalue = defvalue.substring(1);
                } else if (defvalue.startsWith("-")) {
                    altvalue = false;
                    defvalue = defvalue.substring(1);
                } else if (defvalue.startsWith("!")) {
                    defvalue = defvalue.substring(1);
                    onlyifnotset = true;
                    altvalue = false;
                } else {
                    altvalue = false;
                }
            } else {
                key = p;
            }

            //
            // Support the following syntaxes
            //
            // ${X:-DEFAULT}  If parameter X is not set, use 'DEFAULT', otherwise substitute parameter X
            // ${X:!DEFAULT}  If parameter X is not set, use 'DEFAULT', otherwise use the empty string ''
            // ${X:+DEFAULT}  If parameter X is set, use 'DEFAULT' where $X was replaced by value of X
            // ${X} is equivalent to ${X:-}
            //
            if (params.containsKey(key)) {
                // Positional parameter was set, replace occurences of ${X[:...]} with its value.

                if (null != defvalue && altvalue) {
                    // First substitute occurences of $X in the default value
                    defvalue = defvalue.replace("$" + key, params.get(key));
                    target = target.replace("${" + p + "}", defvalue);
                } else {
                    if (!onlyifnotset) {
                        target = target.replace("${" + p + "}", params.get(key));
                    } else {
                        target = target.replace("${" + p + "}", "");
                    }
                }
            } else if (null != defvalue && !altvalue) {
                target = target.replace("${" + p + "}", defvalue);
            } else {
                target = target.replace("${" + p + "}", "");
            }
        }

        return target;
    }
}
