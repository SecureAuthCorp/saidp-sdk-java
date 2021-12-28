package org.secureauth.sarestapi.main.api;

import org.secureauth.sarestapi.ISAAccess;

public interface DiagnosticApi {

    Object run(Parameters parameters, ISAAccess saAccess);

    String printHelp();
}
