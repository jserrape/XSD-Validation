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

	// Input files 1
	// private final static String XSDpath = "./src/files/students.xsd";
	// private final static String XMLpath = "./src/files/students.xml";

	// Input files 2
	private final static String XSDpath = "./src/files/client.xsd";
	private final static String XMLpath = "./src/files/client.xml";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LOGGER.log(Level.INFO, "Start of execution");

		System.out.println("XML path:".concat(XMLpath));
		System.out.println("XSD path:".concat(XSDpath));
		System.out.println("");

		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(XSDpath));
			Validator validator = schema.newValidator();
			ErrorHandler handler = new ErrorHandler() {
				@Override
				public void warning(SAXParseException exception) throws SAXException {
					System.out.println(exception);
				}

				@Override
				public void fatalError(SAXParseException exception) throws SAXException {
					System.out.println(exception);
				}

				@Override
				public void error(SAXParseException exception) throws SAXException {
					if (!exception.getMessage().contains("cvc-type")) {
						String reasonAux = "Error in the line " + exception.getLineNumber() + " and the column "
								+ exception.getColumnNumber() + "Reason: " + exception.getMessage();
						// System.out.println(reasonAux);
						this.errors.add(reasonAux);
					}
				}
			};
			validator.setErrorHandler(handler);
			validator.validate(new StreamSource(new File(XMLpath)));

			if (!handler.errors.isEmpty()) {
				LOGGER.log(Level.SEVERE, "There are " + handler.errors.size() + " errors:");
				for (String err : handler.errors) {
					LOGGER.log(Level.SEVERE, err);
				}
			} else {
				LOGGER.log(Level.SEVERE, "The execution finished without errors");
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Exception: " + e.getMessage());
		} catch (SAXException e) {
			LOGGER.log(Level.SEVERE, "SAX Exception: " + e.getMessage());
		}
	}
}
