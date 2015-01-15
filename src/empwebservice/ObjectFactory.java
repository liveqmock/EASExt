package empwebservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the empwebservice package.
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

	private final static QName _QueryEmpInfoChangeFromHRToSMPAndOAResponse_QNAME = new QName(
			"empWebService", "queryEmpInfoChangeFromHRToSMPAndOAResponse");
	private final static QName _QueryEmpInfoChangeFromHRToSMPAndOA_QNAME = new QName(
			"empWebService", "queryEmpInfoChangeFromHRToSMPAndOA");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: empwebservice
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link QueryEmpInfoChangeFromHRToSMPAndOAResponse }
	 * 
	 */
	public QueryEmpInfoChangeFromHRToSMPAndOAResponse createQueryEmpInfoChangeFromHRToSMPAndOAResponse() {
		return new QueryEmpInfoChangeFromHRToSMPAndOAResponse();
	}

	/**
	 * Create an instance of {@link QueryEmpInfoChangeFromHRToSMPAndOA }
	 * 
	 */
	public QueryEmpInfoChangeFromHRToSMPAndOA createQueryEmpInfoChangeFromHRToSMPAndOA() {
		return new QueryEmpInfoChangeFromHRToSMPAndOA();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link QueryEmpInfoChangeFromHRToSMPAndOAResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "empWebService", name = "queryEmpInfoChangeFromHRToSMPAndOAResponse")
	public JAXBElement<QueryEmpInfoChangeFromHRToSMPAndOAResponse> createQueryEmpInfoChangeFromHRToSMPAndOAResponse(
			QueryEmpInfoChangeFromHRToSMPAndOAResponse value) {
		return new JAXBElement<QueryEmpInfoChangeFromHRToSMPAndOAResponse>(
				_QueryEmpInfoChangeFromHRToSMPAndOAResponse_QNAME,
				QueryEmpInfoChangeFromHRToSMPAndOAResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link QueryEmpInfoChangeFromHRToSMPAndOA }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "empWebService", name = "queryEmpInfoChangeFromHRToSMPAndOA")
	public JAXBElement<QueryEmpInfoChangeFromHRToSMPAndOA> createQueryEmpInfoChangeFromHRToSMPAndOA(
			QueryEmpInfoChangeFromHRToSMPAndOA value) {
		return new JAXBElement<QueryEmpInfoChangeFromHRToSMPAndOA>(
				_QueryEmpInfoChangeFromHRToSMPAndOA_QNAME,
				QueryEmpInfoChangeFromHRToSMPAndOA.class, null, value);
	}

}
