package org.secureauth.sarestapi.main.api.users;

import org.secureauth.sarestapi.ISAAccess;
import org.secureauth.sarestapi.main.api.DiagnosticApi;
import org.secureauth.sarestapi.main.api.HelpBuilder;
import org.secureauth.sarestapi.main.api.Parameters;

public class UsersProfileApi implements DiagnosticApi {

    private final static String USER_ID = "user_id";

    @Override
    public Object run(Parameters parameters, ISAAccess saAccess) {
        return saAccess.getUserProfile( parameters.getMandatory( USER_ID ) );
    }

    @Override
    public String printHelp() {
        return new HelpBuilder()
                .mandatory( USER_ID )
                .print();
    }
}
