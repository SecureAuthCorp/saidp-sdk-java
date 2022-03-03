package org.secureauth.sarestapi.main.api;

public class HelpBuilder {
    private StringBuffer message = new StringBuffer();

    public HelpBuilder mandatory(String parameterName) {
        this.message.append( parameterName );
        this.message.append( " is mandatory.\n" );
        return this;
    }

    public HelpBuilder optional(String parameterName, String defaultValue) {
        this.message.append( parameterName );
        this.message.append( " is optional : the default value " );
        this.message.append( defaultValue );
        this.message.append( " is set if omitted.\n" );
        return this;
    }

    public HelpBuilder optional(String parameterName) {
        this.message.append( parameterName );
        this.message.append( " is optional.\n" );
        return this;
    }

    public String print() {
        return this.message.toString();
    }
}
