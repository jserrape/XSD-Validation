package main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

/**
 * @author jserrape
 *
 */
public class Validate {

	private final static Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LOGGER.log(Level.INFO, "Start of execution");

		String XSDpath = "C:/Users/jserrape/Documents/GitHub/XSD-Validation/src/files/students.xsd";
		String XMLpath = "C:/Users/jserrape/Documents/GitHub/XSD-Validation/src/files/students.xml";


		System.out.println("XML path:".concat(XMLpath));
		System.out.println("XSD path:".concat(XSDpath));

		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(XSDpath));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(XMLpath)));
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Exception: " + e.getMessage());
		} catch (SAXException e1) {
			LOGGER.log(Level.SEVERE, "SAX Exception: " + e1.getMessage());
		}
		
		LOGGER.log(Level.INFO, "Finish ok");
	}

}
