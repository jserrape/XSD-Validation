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
import org.xml.sax.SAXParseException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.StringReader;

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
		System.out.println("");
		
		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(XSDpath));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(XMLpath)));
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Exception: " + e.getMessage());
		} catch (SAXParseException e) {
            int line = e.getLineNumber();
            int col = e.getColumnNumber();
            String message = e.getMessage();
            System.out.println("Error when validate XML against XSD Schema\n" +
                    "line: " + line + "\n" +
                    "column: " + col + "\n" +
                    "message: " + message.substring(message.indexOf(":") + 2));
        } catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("");
		LOGGER.log(Level.INFO, "Finish ok");
	}

}
