package org.secureauth.sarestapi.data;

import java.util.Hashtable;

public class SAConfig {
    private Hashtable<String, Object> configs;
    private Boolean oldIdpSupport = false;
    private static volatile SAConfig instance = null;

    private SAConfig () {

    }

    public static synchronized SAConfig getInstance() {
        if (instance == null) {
            instance = new SAConfig();
        }
        return instance;
    }

    public void updateConfig( Hashtable<String, Object> newConfig ) {
        configs = newConfig;
        oldIdpSupport = (Boolean) configs.get("oldIdP");
    }

    public Boolean getOldIdPSupport() {
        return oldIdpSupport;
    }
}
