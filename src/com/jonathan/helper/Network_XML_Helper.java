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

import com.jonathan.domainmodel.Junction;
import com.jonathan.domainmodel.JunctionFactory;
import com.jonathan.domainmodel.Road;

/**
 * 
 * @author jonth
 * 
 * This class will read modes from XML and start creating the road network
 *
 */
public class Network_XML_Helper {
	
	private static final String documentName="NewFile.xml";
	private static final Network_XML_Helper _instance = new Network_XML_Helper();
	private static final String _trafficRouteName = "trafficroute";
	private static final String _junctionName = "junction";
	private static final String _roadName = "road";
	

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
		
			NodeList trList = doc.getElementsByTagName(_trafficRouteName);
			NodeList jList = doc.getElementsByTagName(_junctionName);
			NodeList rList = doc.getElementsByTagName(_roadName);
			System.out.println("1.1 nodes in route list " + trList.getLength());
			for(int i=0 ; i < trList.getLength(); i++) {
				Node p = trList.item(i);
				if(p.getNodeType() == Node.ELEMENT_NODE) {
					System.out.println("1.5 i=" + i + "node type "+ p.getNodeType());
					Element e = (Element) p;
					String s = "1.6 attribue " + e.getAttribute("id") + "  node name " + e.getNodeName() + "  text content " + e.getTextContent().trim();
					System.out.println(s);
					NodeList nl = p.getChildNodes();
					System.out.println("1.7 nodes in child nodes list " + nl.getLength());
					for(int j = 0; j < nl.getLength(); j++ ) {
						Node node = nl.item(j);
						System.out.println("1.8 node type " + node.getNodeType() +  " node name " + node.getNodeName() + " node value "+  node.getNodeValue());
						if(node.getNodeType()==Node.ELEMENT_NODE) {
							Element el = (Element) node;
							System.out.println("1.9 node name "+el.getNodeName() + " node value " + el.getNodeValue());
						}
					}
				}
			}
				
			buildTheJunctions(jList);
			buildTheRoadList(rList);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SAXException e1) {
			
			e1.printStackTrace();
		}
		
		return true;
		
	}
	
	/**
	 * private boolean buildTheRoadList(final NodeList rList) 
	 * 
	 * @param rList - A list of roads (might be null)
	 * @return - true 
	 */
	private boolean buildTheRoadList(final NodeList rList) {
		
		final String sourceToken = "source";
		final String destToken = "dest";
		final String lengthToken = "length";
		final String laneCountToken = "lanecount";
		final String nameToken = "name";
		
			
		final int roadTupples = 4; // the number of data elements expected in a well formed road
	
		for(int i=0 ; i < rList.getLength(); i++) {
			Node p = rList.item(i);
			if(p.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println("3.1 i=" + i + "node type "+ p.getNodeType());
				Element e = (Element) p;
				String s = "3.2 attribue " + e.getAttribute("id") + "  node name " + e.getNodeName() + "  text content " + e.getTextContent().trim();
				System.out.println(s);
				NodeList nl = p.getChildNodes();
				// System.out.println("3.3 nodes in child nodes list " + nl.getLength());
				int foundCount=0;
				for(int j = 0; j < nl.getLength(); j++ ) {
					
					Node node = nl.item(j);
					String roadSource = null;
					String roadDest = null;
					int roadLaneCount =0;
					int roadLength = 0;
					// System.out.println("3.4 node type " + node.getNodeType() +  " node name " + node.getNodeName() + " node value ");
					if(node.getNodeType()==Node.ELEMENT_NODE) {
						Element el = (Element) node;
						String str = el.getNodeName().trim();
						foundCount++; 
						/*
						 *  This is a bit ugly but want to reduce the token to a switch statement rather than a series of if-then-else
						 */
						int token = str.equals(sourceToken)?1:
									(str.equals(destToken)?2:
									((str.equals(lengthToken)?3:
									(((str.equals(laneCountToken)?4:
									((((str.equals(nameToken)?5 
									:6))))))))));
						
						switch (token) {
							case 1: //source
								roadSource = e.getTextContent().trim().toUpperCase();
								break;
							case 2: // dest
								roadDest = e.getTextContent().trim().toUpperCase();
								break;
							case 3: // length
								roadLength = Integer.valueOf(e.getTextContent().trim());
								break;
							case 4: // lanecount
								// TODO need to use static map to convert int to type 
								// https://stackoverflow.com/questions/5292790/convert-integer-value-to-matching-java-enum/14829048 (very cool)
								roadLaneCount = Integer.valueOf(e.getTextContent().trim());
								break;
							case 5: // roadName
								break;
							default:
								EventLogger.getInstance().logEvent(EventLogger.TraceLevel.CRITIAL,"Illegal road found from XML - name["+el.getNodeName()+
										"] val ["+el.getTextContent().trim()+"]");
								foundCount--; 
								break;
						}
						if(foundCount == roadTupples) {
							// we can now construct this road 
							Junction sourceJunction = JunctionFactory.getInstance().makeJunction(roadSource);
							Junction destJunction = JunctionFactory.getInstance().makeJunction(roadDest);
							Road newRoad = new Road(sourceJunction, destJunction, Road.LaneCount.ONE_LANE, roadLength,"");
							
						}
						System.out.println("3.5 node name "+el.getNodeName() + " text= " + el.getTextContent().trim());
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param jList - a list of junctions extracted from the XML document as a NodeList this method only reads what is in the list hence final type specifier
	 * 
	 * @return - true if at least two junctions are created - a Road has to have a 1:2 cardinality of association with the attached junctions
	 */
	
	private boolean buildTheJunctions(final NodeList jList) {
				
		int jFoundCount=0;
				
		for(int i=0 ; i < jList.getLength(); i++) {
			Node p = jList.item(i);
			System.out.println("2.0 the node type @ 2 is " + p.getNodeType() + " list length = "+ jList.getLength());
			if(p.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) p;
				EventLogger.getInstance().logEvent(EventLogger.TraceLevel.LOW,"Building a Junction " + e.getTextContent().trim().toUpperCase() );
				String s = "2.2 attribue " + e.getAttribute("id") + "  node name " + e.getNodeName() + "  text content [" + e.getTextContent().trim().toUpperCase() +"]";
				System.out.println(s);
				JunctionFactory.getInstance().makeJunction(e.getTextContent().trim().toUpperCase());
				jFoundCount++;
			}
		}
		// leave an audit trail 
		EventLogger.getInstance().logEvent(EventLogger.TraceLevel.LOW,"buildTheJunctions exiting with [" + jFoundCount + "] created");
		
		// all good so long as at least two junctions found (trivial case)
		return jFoundCount >= 2;
			
		}
		
}; // end of class
	
	