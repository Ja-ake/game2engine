package edu.catlin.springerj.g2e.tiled;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLParser {
	private static DocumentBuilderFactory dbf;
	private static DocumentBuilder db;
	static { 
		try {	
			dbf = DocumentBuilderFactory.newInstance(); 
			db = dbf.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Document document;
	
	public XMLParser(File file) {
		try {
			document = db.parse(file);
		} catch (SAXException | IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Element get(String location) {
		String[] parsed = location.split("[.]");
		System.out.println(document.getDocumentElement());
		Element e = getRecursive(document.getDocumentElement(), parsed);
		return e;
	}
	
	private Element getRecursive(Element element, String[] parsed) {
		if (parsed.length == 1) return (Element) element.getElementsByTagName(parsed[0]).item(0);
		String[] newParsed = new String[parsed.length-1];
		for (int i=1; i<parsed.length; i++) {
			newParsed[i-1] = parsed[i];
		}
		Element newElement = (Element) element.getElementsByTagName(parsed[0]).item(0);
		return getRecursive(newElement, newParsed);
	}
}
