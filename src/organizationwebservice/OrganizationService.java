package organizationwebservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "organizationService", targetNamespace = "organizationWebService")
public interface OrganizationService {

	/**
	 * 
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "queryOrganizationChangeFromHRToOA", targetNamespace = "organizationWebService", className = "organizationwebservice.QueryOrganizationChangeFromHRToOA")
	@ResponseWrapper(localName = "queryOrganizationChangeFromHRToOAResponse", targetNamespace = "organizationWebService", className = "organizationwebservice.QueryOrganizationChangeFromHRToOAResponse")
	public String queryOrganizationChangeFromHRToOA(
			@WebParam(name = "arg0", targetNamespace = "") String arg0);

}