package com.optum.ndb.apiutils;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Rest_Service_Test 
{
	public static void main (String args[]) {
		 
		// This is request which we are sending to the server 
		String restURL_XML = "http://parabank.parasoft.com/parabank/services/bank/customers/12212/";
		 
		// sending request and we are passing parameter in url itself
		String restURL_JSON = "http://api.openweathermap.org/data/2.5/weather?q=Amsterdam";
		 
		try {
		 
		testStatusCode(restURL_XML); // - HttpStatus.SC_OK
		//testStatusCode(restURL_JSON); //- - HttpStatus.SC_UNAUTHORIZED
		//testMimeType(restURL_XML,"application/xml"); //application/xml
		//testMimeType(restURL_JSON,"application/json");
		testContent(restURL_XML,"lastName","Smith");
		//testContentJSON(restURL_JSON,"name","Amsterdam");
		 
		} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}


		// Responses:
		//testStatusCode(restURL_XML); // - HttpStatus.SC_OK
		//testStatusCode(restURL_JSON); //- - HttpStatus.SC_UNAUTHORIZED
		////testMimeType(restURL_XML,"application/xml");HttpResponseProxy{HTTP/1.1 200 OK [Server: Apache-Coyote/1.1, Date: Fri, 07 Oct 2016 12:58:14 GMT, Content-Length: 319, Content-Type: application/xml, Via: 1.1 proxy-ii702-ip2.uhc.com:80 (Cisco-WSA/9.0.1-162), Connection: keep-alive] ResponseEntityProxy{[Content-Type: application/xml,Content-Length: 319,Chunked: false]}}
		//testMimeType(restURL_JSON,"application/json");HttpResponseProxy{HTTP/1.1 401 Unauthorized [Server: openresty, Date: Fri, 07 Oct 2016 13:00:27 GMT, X-Cache-Key: /data/2.5/weather?q=amsterdam, Access-Control-Allow-Origin: *, Access-Control-Allow-Credentials: true, Access-Control-Allow-Methods: GET, POST, Content-Length: 107, Content-Type: application/json; charset=utf-8, Via: 1.1 proxy-ii702-ip2.uhc.com:80 (Cisco-WSA/9.0.1-162), Connection: keep-alive] ResponseEntityProxy{[Content-Type: application/json; charset=utf-8,Content-Length: 107,Chunked: false]}}

		public static void testStatusCode(String restURL) throws ClientProtocolException, IOException {
		 
		HttpUriRequest request = new HttpGet(restURL);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		System.out.println("<<<<<<<<<<<<<< httpResponse  >>>>>>>>>>>>>>>"+  httpResponse);
		 
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),HttpStatus.SC_OK);
		System.out.println("<<<<<<<<<     Done  >>>>>>>>>>>>>>>");
		}
		 
		public static void testMimeType(String restURL, String expectedMimeType) throws ClientProtocolException, IOException {
		 
		HttpUriRequest request = new HttpGet(restURL);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		System.out.println("<<<<<<<<<<<<<< httpResponse  >>>>>>>>>>>>>>>"+  httpResponse);
		Assert.assertEquals(expectedMimeType,ContentType.getOrDefault(httpResponse.getEntity()).getMimeType());
		System.out.println("<<<<<<<<<     Done  >>>>>>>>>>>>>>>");
		}
		 
		public static void testContent(String restURL, String element, String expectedValue) throws ClientProtocolException, IOException, SAXException, ParserConfigurationException {
		 
		 
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(restURL);
		NodeList nodelist = doc.getElementsByTagName(element);
		System.out.println("<<<<<<    Node List  Attributes: "+nodelist.item(0).getNodeValue()+"   >>>>>>>>>");
		 
		//Assert.assertEquals(expectedValue,nodelist.item(0).getAttributes()); //.getTextContent()
		}
		 
		public static void testContentJSON(String restURL, String element, String expectedValue) throws ClientProtocolException, IOException, SAXException, ParserConfigurationException, JSONException {
		 
		HttpUriRequest request = new HttpGet(restURL);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		 
		// Convert the response to a String format
		String result = EntityUtils.toString(httpResponse.getEntity());
		 
		// Convert the result as a String to a JSON object
		JSONObject jo = new JSONObject(result);
		 
		Assert.assertEquals(expectedValue, jo.getString(element));
		 
		}
}
