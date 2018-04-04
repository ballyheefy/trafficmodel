package com.jonathan.helper;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author jonth
 * 
 * This class will read modes from XML and start creating the road network
 *
 */
public class Network_XML_Helper {
	
	private static final String documentName="network.xml.txt";
	private static final Network_XML_Helper _instance = new Network_XML_Helper();
	
	
	private Network_XML_Helper() {
		// TODO Auto-generated constructor stub
	}
	
	public static Network_XML_Helper getInstance() {
		return _instance;
	}
	
	public boolean buildTheModel() { 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(documentName);
			NodeList juncList = doc.getElementsByTagName("junction");
			System.out.println("nodes in junc list " + juncList.getLength());
			for(int i=0 ; i < juncList.getLength(); i++) {
				Node p = juncList.item(i);
				if(p.getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element) p;
					String s = e.getAttribute("id");
					System.out.println(s);
					NodeList nl = p.getChildNodes();
					for(int j = 0; j < nl.getLength(); j++ ) {
						Node node = nl.item(j);
						if(node.getNodeType()==Node.ELEMENT_NODE) {
							Element el = (Element) node;
							System.out.println(el.getNodeName() + " " + el.getNodeValue());
						}
					}
				}
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.xml.sax.SAXException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		return true;
		
	}
	
	};
	
	