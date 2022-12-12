package org.secureauth.sarestapi.data;

import java.util.Hashtable;

public class SAConfig {
    private Hashtable<String, Object> config;
    private Boolean oldIdpSupport = false;
    private static volatile SAConfig instance = null;

    private SAConfig () {

    }

    public static synchronized SAConfig getInstance() {
        if (instance == null)
            instance = new SAConfig();
        return instance;
    }

    public void updateConfig( Hashtable<String, Object> newConfig ) {
        getInstance().config = newConfig;
        oldIdpSupport = (Boolean) config.get("support.deprecated.idp");
    }

    public Boolean getOldIdPSupport() {
        return oldIdpSupport;
    }
}
