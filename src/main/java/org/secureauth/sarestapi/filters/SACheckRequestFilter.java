package org.secureauth.sarestapi.filters;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by rrowcliffe on 5/6/16.
 */
public class SACheckRequestFilter implements ClientRequestFilter {

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        if (requestContext.getHeaders().get("Authorization") == null) {
            requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST).entity("Authorization header must be defined.").build());
        }

        //Moved all TimeStamp Header setting to Filter Controlled
        //if(!requestContext.getHeaders().containsKey("X-SA-Ext-Date")){
        //    requestContext.getHeaders().add("X-SA-Ext-Date", getServerTimeMs());
        //}
        //Technically we should never get this point
        if (!requestContext.getHeaders().containsKey("X-SA-Date") && !requestContext.getHeaders().containsKey("X-SA-Ext-Date")){
            requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST).entity("X-SA-Date or X-SA-Ext-Date header must be defined.").build());
        }

    }

    String getServerTimeMs() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss.SSS z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(calendar.getTime());
    }

    //Just in case we want to force the second version timestamp for older versions of IDP
    String getServerTimeSeconds() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(calendar.getTime());
    }
}
