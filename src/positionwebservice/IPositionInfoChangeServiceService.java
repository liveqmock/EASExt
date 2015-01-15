package positionwebservice;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import com.creditease.eas.util.consts.AddressConfig;
/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * <p>
 * An example of how this class may be used:
 * 
 * <pre>
 * IPositionInfoChangeServiceService service = new IPositionInfoChangeServiceService();
 * PositionService portType = service.getPositionServicePort();
 * portType.queryPositionInfoChangeFromHRToOA(...);
 * </pre>
 * 
 * </p>
 * 
 */
@WebServiceClient(name = "IPositionInfoChangeServiceService", targetNamespace = "positionWebService", wsdlLocation = AddressConfig.POSITIONWSDLLOCATION)
public class IPositionInfoChangeServiceService extends Service {

	private final static URL IPOSITIONINFOCHANGESERVICESERVICE_WSDL_LOCATION;
	private final static Logger logger = Logger
			.getLogger(positionwebservice.IPositionInfoChangeServiceService.class
					.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = positionwebservice.IPositionInfoChangeServiceService.class
					.getResource(".");
			url = new URL(
					baseUrl,
					AddressConfig.POSITIONWSDLLOCATION);
		} catch (MalformedURLException e) {
			logger
					.warning("Failed to create URL for the wsdl Location: 'http://10.105.18.140:9061/PositionInfoChange/services/PositionInfoChangeService?wsdl', retrying as a local file");
			logger.warning(e.getMessage());
		}
		IPOSITIONINFOCHANGESERVICESERVICE_WSDL_LOCATION = url;
	}

	public IPositionInfoChangeServiceService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public IPositionInfoChangeServiceService() {
		super(IPOSITIONINFOCHANGESERVICESERVICE_WSDL_LOCATION, new QName(
				"positionWebService", "IPositionInfoChangeServiceService"));
	}

	/**
	 * 
	 * @return returns PositionService
	 */
	@WebEndpoint(name = "positionServicePort")
	public PositionService getPositionServicePort() {
		return super.getPort(new QName("positionWebService",
				"positionServicePort"), PositionService.class);
	}

}