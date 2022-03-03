package org.secureauth.sarestapi.main.api.fingerprint;

import org.secureauth.sarestapi.ISAAccess;
import org.secureauth.sarestapi.data.Response.DFPValidateResponse;
import org.secureauth.sarestapi.main.api.DiagnosticApi;
import org.secureauth.sarestapi.main.api.HelpBuilder;
import org.secureauth.sarestapi.main.api.Parameters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DFPApi implements DiagnosticApi {

    private static final String FINGERPRINT_JSON_PATH = "fingerprint_json_path";
    private static final String USER_ID = "user_id";
    private static final String HOST = "host";
    private static final String FINGERPRINT_ID = "fingerprint_id";
    private static final String DEFAULT_FINGERPRINT_JSON_PATH = "configuration/dfp/fingerprint.json";

    @Override
    public Object run(Parameters parameters, ISAAccess saAccess) {
        final String jsonPath =  parameters.getOrDefault( FINGERPRINT_JSON_PATH, DEFAULT_FINGERPRINT_JSON_PATH );
        try {
            final String fingerprint_json = readFromFile( jsonPath );
            DFPValidateResponse response = saAccess.DFPSaveFingerprint(
                    parameters.getMandatory( USER_ID ),
                    parameters.getMandatory( HOST ),
                    parameters.getOrDefault( FINGERPRINT_ID, ""),
                    fingerprint_json);
            return response.toString();
        } catch (IOException exc) {
            throw new RuntimeException( exc );
        }
    }

    @Override
    public String printHelp() {
        return new HelpBuilder()
                .mandatory( USER_ID )
                .mandatory( HOST )
                .optional( FINGERPRINT_JSON_PATH, DEFAULT_FINGERPRINT_JSON_PATH )
                .optional( FINGERPRINT_ID )
                .print();
    }

    private String readFromFile(final String path) throws IOException {
        return Files.lines( Paths.get( path ) ).reduce( "", (line1, line2) -> line1 + line2 );
    }
}
