package com.optum.ndb.apiutils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPMessage;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Soap_Webservice 
{
	 private String username;
	    private String password;
	    private String requestTemplate;
	    private Document xmlRequestDocument;
	    public Document xmlResponseDocument;
	    
	    private String templateURI;

	    public Soap_Helper soapHelper = new Soap_Helper();
	    public Xml_Helper xmlHelper = new Xml_Helper();
	    
	    private SOAPMessage res;

	    /**
	     * @return the username
	     */
	    public String getUsername() {
	        return username;
	    }

	    /**
	     * @param username the username to set
	     */
	    public void setUsername(String username) {
	        this.username = username;
	    }

	    /**
	     * @return the password
	     */
	    public String getPassword() {
	        return password;
	    }

	    /**
	     * @param password the password to set
	     */
	    public void setPassword(String password) {
	    	
	    	//this.password = passworddecrypt.passworddecrypt(password);
	    	
	    	this.password =  password;
	    	    	
	         
	    }

	    /**
	     * @return the requestTemplate
	     */
	    public String getRequestTemplate() {
	        return requestTemplate;
	    }

	    /**
	     * @param requestTemplate the requestTemplate to set
	     */
	    public void setRequestTemplate(String requestTemplate) {
	        this.requestTemplate = requestTemplate;
	    }

	    /**
	     * @return the xmlDocument
	     */
	    public Document getXmlDocument() {
	        return xmlRequestDocument;
	    }

	    
	   
	    
	    
	    /**
		 * @return the templateURI
		 */
		public String getTemplateURI() {
			return templateURI;
		}

		/**
		 * @param templateURI the templateURI to set
		 */
		public void setTemplateURI(String templateURI) {
			this.templateURI = templateURI;
		}

		/**
	     * @param xmlDocument the xmlDocument to set
	     * @throws IOException
	     * @throws SAXException
	     * @throws ParserConfigurationException
	     * @throws FileNotFoundException
	     */
	    public void load_XML_Request_Template(String xmlTemplateRelativePath)
	            throws FileNotFoundException, ParserConfigurationException,
	            SAXException, IOException {
	    	 this.templateURI ="src\\test\\resources\\LAWW\\api\\" + xmlTemplateRelativePath;
	    	// System.out.println("Path for Request file:"+ this.templateURI);
	        this.xmlRequestDocument = xmlHelper.getXMLDoc(this.templateURI);
	       
	    }
	    
		/* public void load_XML_Request_Template(String xmlTemplateRelativePath)
		            throws FileNotFoundException, ParserConfigurationException,
		            SAXException, IOException {
			 String filepath = System.getProperty("user.dir") + "\\src\\test\\resources\\ICUE\\api\\testdata\\";
			 System.out.println(filepath);
		        this.xmlRequestDocument =
		                xmlHelper.getXMLDoc(filepath + xmlTemplateRelativePath);
		        this.templateURI = filepath + xmlTemplateRelativePath;
		    }*/
	    
	    
	    public void load_XML_Request_Template1(String xmlTemplateAbsPath)
	            throws FileNotFoundException, ParserConfigurationException,
	            SAXException, IOException {
	    	this.templateURI = xmlTemplateAbsPath;
	    	System.out.println("Absolute Path for Request file:"+ this.templateURI);
	        this.xmlRequestDocument = xmlHelper.getXMLDoc(this.templateURI);
	        
	    }
	public void  node_value_response_store(String arg1, String arg2){
		
	}
	    public void setNodeValue(String nodeName, String nodeValue)
	            throws Exception {
	        this.xmlRequestDocument =
	                xmlHelper.setNodeValue(xmlRequestDocument, nodeName, nodeValue);

	        NodeList nList = xmlHelper.getNodeList(xmlRequestDocument, nodeName);
	        //System.out.println(xmlHelper.getNodeVal(nList, nodeName));
	    }

	    @SuppressWarnings("static-access")
		public void post(String endPoint , String ResponseFilename , String XMLRequestfor) throws Exception {
			//System.out.println("!!!!!!!!!!!       Post Method   !!!!!!!!!!!!!!!");
			System.setProperty("javax.xml.soap.MessageFactory","com.sun.xml.internal.messaging.saaj.soap.ver1_2.SOAPMessageFactory1_2Impl");
			System.setProperty("javax.xml.bind.JAXBContext", "com.sun.xml.internal.bind.v2.ContextFactory");

	    	//Parsing the input xml file
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(this.templateURI);
	          /*      
			//iterating through the input xml template and insert the layer7 credentials       
	        NodeList nodeList = doc.getElementsByTagName("*");
	        for (int i = 0; i < nodeList.getLength(); i++) {
	            Node node = nodeList.item(i);
	            if (node.getNodeType() == Node.ELEMENT_NODE) {
	                               
	                DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	                DateTime DT= new DateTime();
	               
	     		   if ("wsu:Created".equals(node.getNodeName())) {
	     			node.setTextContent(CentralTimeStampUtil.getCentralJoda().toString());
	     		   }
	     		   
	     		  if ("wsu:Expires".equals(node.getNodeName())) {
	       			node.setTextContent(CentralTimeStampUtil.getCentralJodaPlusFive().toString());
	       		   }
	       		   
	            }
	        }
	           */
	            
	     // update header with layer7 in request template xml file
				////TransformerFactory transformerFactory = TransformerFactory.newInstance();
				//Transformer transformer = transformerFactory.newTransformer();
	            //DOMSource source = new DOMSource(doc);
	        //StreamResult result = new StreamResult(new File("src/main/resources/api/testdata/input/supplier/v2.0.2/supplier_request_template.xml"));
//	        StreamResult result = new StreamResult(new File("src\\test\\resources\\ICUE\\api\\Response\\MemberEligibility.xml"));
//	        
//	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//	        Transformer transformer = transformerFactory.newTransformer();
//	        System.out.println("Source:"+source);
//	        transformer.transform(source, result);
	        
			        
			// Create SOAP req from Document
	        SOAPMessage req = soapHelper.readSoapMessage(this.xmlRequestDocument , XMLRequestfor);
	        
	        
	        // get URL
	       // String url = "http://icuealpha.uhc.com/icue/" + endPoint;
	        String polarisurl = endPoint;

	        // submit req
	        
	       // res = soapHelper.sendSoapMessage(req, url);
	        res = soapHelper.sendSoapMessage(req, polarisurl);
	        new FileOutputStream("src/test/resources/LAWW/api/Response/"+ResponseFilename+".xml", true).close();
	        Soap_Helper.soapToXML(res ,ResponseFilename);
	     
	        xmlResponseDocument = xmlHelper.getXMLDoc("src/test/resources/LAWW/api/Response/"+ResponseFilename+".xml");//
	    }


	    public Boolean getNodeValue(String nodeName, String nodeValue)
	            throws Exception {

	        String parentNode;
	        String parentNode1;
	        String childNode;
	        String childNode1;
	        
	        NodeList parentNodeList;
	        NodeList childNodeList;
	        String actVal = null;

	        String[] parts = nodeName.split("/");

	        if (parts.length > 1) {
	            parentNode = parts[0];
	            childNode = parts[1];
//	            childNode1 = parts[2];
	        //    parentNodeList = xmlHelper.getNodeList(xmlResponseDocument, parentNode);
	       //        Element elem = (Element)parentNodeList.item(0);
	        //    childNodeList = elem.getChildNodes();
	            
	            actVal =  xmlHelper.getNodeValue(xmlResponseDocument, parentNode, childNode);
	            
	        } else {
	            parentNode = childNode = nodeName;
	            childNodeList = xmlHelper.getNodeList(xmlResponseDocument, parentNode);
	            actVal = xmlHelper.getNodeVal(childNodeList, childNode);
	        }

	       
	        
	        System.out.println(childNode);
	        
	        System.out.println("Actual Value in Response :" + actVal);

	        return nodeValue.equals(actVal);
	    }
	    

		/*@Override
		public void close(MessageContext arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean handleFault(SOAPMessageContext arg0) {
			// TODO Auto-generated method stub
			return false;
		}
	*/
		public boolean handleMessage(String endPointUrl,String ResponseFilename , String XMLRequestfor) {
			// TODO Auto-generated method stub
			try {
				post(endPointUrl,ResponseFilename , XMLRequestfor);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}

		public Document getXmlDocument1() {
			
			// TODO Auto-generated method stub
			return xmlResponseDocument;
		}

		/*@Override
		public Set<QName> getHeaders() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean handleMessage(SOAPMessageContext context) {
			// TODO Auto-generated method stub
			return false;
		}
	*/
}
