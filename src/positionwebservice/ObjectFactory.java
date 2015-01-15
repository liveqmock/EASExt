package positionwebservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the positionwebservice package.
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

	private final static QName _QueryPositionInfoChangeFromHRToOA_QNAME = new QName(
			"positionWebService", "queryPositionInfoChangeFromHRToOA");
	private final static QName _QueryPositionInfoChangeFromHRToOAResponse_QNAME = new QName(
			"positionWebService", "queryPositionInfoChangeFromHRToOAResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: positionwebservice
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link QueryPositionInfoChangeFromHRToOAResponse }
	 * 
	 */
	public QueryPositionInfoChangeFromHRToOAResponse createQueryPositionInfoChangeFromHRToOAResponse() {
		return new QueryPositionInfoChangeFromHRToOAResponse();
	}

	/**
	 * Create an instance of {@link QueryPositionInfoChangeFromHRToOA }
	 * 
	 */
	public QueryPositionInfoChangeFromHRToOA createQueryPositionInfoChangeFromHRToOA() {
		return new QueryPositionInfoChangeFromHRToOA();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link QueryPositionInfoChangeFromHRToOA }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "positionWebService", name = "queryPositionInfoChangeFromHRToOA")
	public JAXBElement<QueryPositionInfoChangeFromHRToOA> createQueryPositionInfoChangeFromHRToOA(
			QueryPositionInfoChangeFromHRToOA value) {
		return new JAXBElement<QueryPositionInfoChangeFromHRToOA>(
				_QueryPositionInfoChangeFromHRToOA_QNAME,
				QueryPositionInfoChangeFromHRToOA.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link QueryPositionInfoChangeFromHRToOAResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "positionWebService", name = "queryPositionInfoChangeFromHRToOAResponse")
	public JAXBElement<QueryPositionInfoChangeFromHRToOAResponse> createQueryPositionInfoChangeFromHRToOAResponse(
			QueryPositionInfoChangeFromHRToOAResponse value) {
		return new JAXBElement<QueryPositionInfoChangeFromHRToOAResponse>(
				_QueryPositionInfoChangeFromHRToOAResponse_QNAME,
				QueryPositionInfoChangeFromHRToOAResponse.class, null, value);
	}

}
