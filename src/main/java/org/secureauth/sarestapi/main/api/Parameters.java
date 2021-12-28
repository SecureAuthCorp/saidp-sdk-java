package org.secureauth.sarestapi.main.api;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.stream.Stream;

public class Parameters {
    private final Map<String, String> paramsMap;

    public Parameters(final String[] args, final String delimiter) {
        this.paramsMap = getParametersFrom( args, delimiter );
    }

    public String getOrDefault(final String key, final String defaultValue) {
        return this.paramsMap.getOrDefault( key, defaultValue );
    }

    public String getMandatory(final String key) throws IllegalArgumentException {
        final String value = this.paramsMap.get( key );
        if( Strings.isNullOrEmpty( value ) ) {
            throw new IllegalArgumentException( key + " is not provided and it is mandatory" );
        }
        return value;
    }

    private static Map<String, String> getParametersFrom(final String [] args, final String delimiter) {
        Map<String, String> params = Maps.newHashMap();
        for(int paramNumber=0; paramNumber < args.length; paramNumber++) {
            String[] paramAndValue = args[ paramNumber ].split( delimiter );
            // help is used for service information.
            if ( addParameterAsFlagIfApply( args[ paramNumber ], params, "help", "stacktrace" ) ) {
                continue;
            }
            if( paramAndValue.length != 2) {
                // ignore when the parameter format ( key-> value ) is not valid
                System.out.println( args[ paramNumber ] + " is invalid and will be ignored." );
                continue;
            }
            params.put( paramAndValue[0], paramAndValue[1]);
        }
        return params;
    }

    private static boolean addParameterAsFlagIfApply(final String currentParam, Map<String, String> params, String ...flagNames) {
        if ( Stream.of( flagNames ).anyMatch( name -> name.equalsIgnoreCase( currentParam ) ) ) {
            params.put( currentParam, "true" );
            return true;
        }
        return false;
    }
}
