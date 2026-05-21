/**
 * Created by Apache CXF WadlToJava code generator
**/
package com.avivacanada.avivaapi.claims.v1.twilio.sms;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/send")
public class SMS {

    @POST
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public Response Send(NotificationRequestType request) {
        //TODO: implement
        return null;
    }

}