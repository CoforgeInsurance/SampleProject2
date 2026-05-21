package com.ca.aviva.esb.api.notification.sendphonenotification.processor;


import com.ca.aviva.esb.api.notification.util.NotificationConstants;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.jasypt.JasyptPropertiesParser;

public class AuthorizationHeaderEncoderProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        JasyptPropertiesParser jasp = new JasyptPropertiesParser();
        jasp.setAlgorithm("PBEWithMD5AndDES");
        jasp.setPassword("MasterPassword");
        String authUsername = (String)exchange.getProperty("authUsername");
        String authPasswordEncrypted = (String)exchange.getProperty("authPasswordEncrypted");
        String plainText = jasp.parseProperty(null,
                exchange.getContext().resolvePropertyPlaceholders(authPasswordEncrypted), null);
        String authorizationHeader = "Basic "
                + org.apache.cxf.common.util.Base64Utility.encode((authUsername + ":" + plainText).getBytes());
        exchange.getIn().setHeader("Authorization",authorizationHeader);
    }
}
