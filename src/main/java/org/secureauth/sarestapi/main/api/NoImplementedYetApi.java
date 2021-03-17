package org.secureauth.sarestapi.main.api;

import org.secureauth.sarestapi.ISAAccess;

public class NoImplementedYetApi implements DiagnosticApi {
    private String service;

    public NoImplementedYetApi(String service) {
        this.service = service;
    }

    @Override
    public Object run(Parameters parameters, ISAAccess saAccess) {
        return this.printHelp();
    }

    @Override
    public String printHelp() {
        return this.service + " is not implemented yet.";
    }
}
