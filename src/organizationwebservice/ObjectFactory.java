package organizationwebservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the organizationwebservice package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _QueryOrganizationChangeFromHRToOAResponse_QNAME = new QName(
			"organizationWebService",
			"queryOrganizationChangeFromHRToOAResponse");
	private final static QName _QueryOrganizationChangeFromHRToOA_QNAME = new QName(
			"organizationWebService", "queryOrganizationChangeFromHRToOA");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: organizationwebservice
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link QueryOrganizationChangeFromHRToOA }
	 * 
	 */
	public QueryOrganizationChangeFromHRToOA createQueryOrganizationChangeFromHRToOA() {
		return new QueryOrganizationChangeFromHRToOA();
	}

	/**
	 * Create an instance of {@link QueryOrganizationChangeFromHRToOAResponse }
	 * 
	 */
	public QueryOrganizationChangeFromHRToOAResponse createQueryOrganizationChangeFromHRToOAResponse() {
		return new QueryOrganizationChangeFromHRToOAResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link QueryOrganizationChangeFromHRToOAResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "organizationWebService", name = "queryOrganizationChangeFromHRToOAResponse")
	public JAXBElement<QueryOrganizationChangeFromHRToOAResponse> createQueryOrganizationChangeFromHRToOAResponse(
			QueryOrganizationChangeFromHRToOAResponse value) {
		return new JAXBElement<QueryOrganizationChangeFromHRToOAResponse>(
				_QueryOrganizationChangeFromHRToOAResponse_QNAME,
				QueryOrganizationChangeFromHRToOAResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link QueryOrganizationChangeFromHRToOA }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "organizationWebService", name = "queryOrganizationChangeFromHRToOA")
	public JAXBElement<QueryOrganizationChangeFromHRToOA> createQueryOrganizationChangeFromHRToOA(
			QueryOrganizationChangeFromHRToOA value) {
		return new JAXBElement<QueryOrganizationChangeFromHRToOA>(
				_QueryOrganizationChangeFromHRToOA_QNAME,
				QueryOrganizationChangeFromHRToOA.class, null, value);
	}

}
