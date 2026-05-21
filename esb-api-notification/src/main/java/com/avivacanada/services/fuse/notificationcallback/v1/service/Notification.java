package com.avivacanada.services.fuse.notificationcallback.v1.service;

import com.avivacanada.services.fuse.notificationcallback.v1.StatusupdateRequestType;
import com.avivacanada.services.fuse.notificationcallback.v1.UnsubscribeRequestType;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

public class Notification {

    @POST
    @Path("/unsubscribe")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public Response unsubscribe(UnsubscribeRequestType request) {
        //TODO: implement
        return null;
    }

    @POST
    @Path("/statusupdate")
    @Consumes("application/json; charset=utf-8")
    @Produces("application/json; charset=utf-8")
    public Response statusupdate(StatusupdateRequestType request) {
        //TODO: implement
        return null;
    }

}
