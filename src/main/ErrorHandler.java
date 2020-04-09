package main;

import java.util.ArrayList;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class ErrorHandler extends DefaultHandler {
	
	public ArrayList<String> errors;
	
	public ErrorHandler(){
		this.errors = new ArrayList<String>();
	}
}
