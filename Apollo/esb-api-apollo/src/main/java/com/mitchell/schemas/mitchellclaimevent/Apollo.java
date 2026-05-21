package com.mitchell.schemas.mitchellclaimevent;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.aviva.ca.esb.core.conf.exception.SOAPServiceException;
import com.mitchell.schemas.filedelivery.FileReceiverServiceRs;

@WebService
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface Apollo {
	@WebMethod
	public @WebResult(name = "FileReceiverServiceRs", targetNamespace="http://www.mitchell.com/schemas/filedelivery") FileReceiverServiceRs receiveEstimateDetails(
			@WebParam(name = "MitchellClaimEvent", targetNamespace="http://www.mitchell.com/schemas/mitchellclaimevent") MitchellClaimEvent parameters)
			throws SOAPServiceException;
}
