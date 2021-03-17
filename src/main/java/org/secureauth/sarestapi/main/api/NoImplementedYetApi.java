package org.secureauth.sarestapi.main.api;

import org.secureauth.sarestapi.ISAAccess;

import java.util.Set;

public class NoImplementedYetApi implements DiagnosticApi {
    private String service;
    private Set<String> availableServices;

    public NoImplementedYetApi(String service, Set<String> availableServices) {
        this.service = service;
        this.availableServices = availableServices;
    }

    @Override
    public Object run(Parameters parameters, ISAAccess saAccess) {
        return this.printHelp();
    }

    @Override
    public String printHelp() {
        return this.service + " is not implemented yet. Implemented services are : " + this.availableServices;
    }
}
