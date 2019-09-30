package com.optum.ndb.apiutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Multimap;

public class Json_Helper 
{
	/**
	 * Prevent class from being instantiated.
	 */
	private Json_Helper() {
		// Empty constructor
	}

	/**
	 * Read a json file and populate pojo.
	 * 
	 * @param <T> type of configuration class
	 * @param pathToJsonFile Path to json file
	 * @param type the type of top level (parent) class
	 * @return reference to class of type
	 * @throws IOException invalid config file
	 */
	public static <T> T readJsonFile(final String pathToJsonFile,
			final Class<T> type) throws IOException {

		ObjectMapper mapper = new ObjectMapper();

		// JSON from file to Object
		return mapper.readValue(new File(pathToJsonFile), type);
	}

	/**
	 * Create a json object from a file.
	 * 
	 * @param pathToJsonFile path to json file
	 * @return new json object created from file
	 * @throws IOException invalid config file
	 */
	/*
	public static JSONObject createJSONObjectFromFile(
			final String pathToJsonFile) throws IOException {

		if (pathToJsonFile == null || pathToJsonFile.isEmpty()) {
			throw new IllegalArgumentException("File Path Not Specified");
		}

		File f = new File(pathToJsonFile);
		if (!f.exists() || f.isDirectory()) {
			throw new IllegalArgumentException(String.format(
					"File %s Not Found", pathToJsonFile));
		}

		InputStream ioStream = new FileInputStream(f);
		String jsonString = org.apache.commons.io.IOUtils.toString(ioStream);

		return new JSONObject(jsonString);
	}
*/
	/**
	 * Check if a json object has an element.
	 * 
	 * @param jsonObject json object to search
	 * @param jsonKey key to search
	 * @return true if key found in json object
	 */
	public static Boolean hasElement(final JSONObject jsonObject,
			final String jsonKey) {

		if (jsonObject == null) {
			throw new IllegalArgumentException("JSON Object is null");
		}

		if (jsonKey == null || jsonKey.isEmpty()) {
			throw new IllegalArgumentException("JSON Key Not Provided");
		}

		return jsonObject.has(jsonKey);
	}

	/**
	 * Get a child json object based on a parent jsonobject and key.
	 * 
	 * @param jsonObject parent json object
	 * @param jsonKey key in parent json object
	 * @return json object starting from child node in parent
	 */
	public static JSONObject getJsonObject(final JSONObject jsonObject,
			final String jsonKey) {

		if (jsonObject == null) {
			throw new IllegalArgumentException("JSON Object is null");
		}

		if (jsonKey == null || jsonKey.isEmpty()) {
			throw new IllegalArgumentException("JSON Key Not Provided");
		}

		JSONObject retObj = null;

		if (hasElement(jsonObject, jsonKey)) {
			retObj = jsonObject.getJSONObject(jsonKey);
		}

		return retObj;
	}

	/**
	 * @param json
	 * @param out
	 * @return
	 * @throws JSONException
	 */
	public static HashMap<String, Object> parseJsonObject(JSONObject json, HashMap<String, Object> out)
			throws JSONException {
		Iterator<String> keys = json.keys();

		while (keys.hasNext()) {
			String key = keys.next();
			Object val = json.get(key);
			if (val != null) {
				out.put(key, val);
			}

			Object objJson = json.get(key);
			if (objJson instanceof JSONObject) {
				JSONObject value = json.getJSONObject(key);
				parseJsonObject(value, out);
			}
			else if (objJson instanceof JSONArray)
			{
				getArray(objJson, out);
			}
		}
		return out;
	} 

	/**
	 * @param json
	 * @param out
	 * @return
	 * @throws JSONException
	 */
	public static Multimap<String, Object> parseJsonObject(JSONObject json, Multimap<String, Object> out)
            throws JSONException {
     Iterator<String> keys = json.keys();
     
     while (keys.hasNext()) {
            String key = keys.next();
            //String val = json.getString(key);
            Object val = json.get(key);
            if (val != null) {
                  out.put(key, val);
            }
            
            Object objJson = json.get(key);
            if (objJson instanceof JSONObject) {
                  JSONObject value = json.getJSONObject(key);
                  parseJsonObject(value, out);
            }
            else if (objJson instanceof JSONArray)
            {
                  /*JSONArray arrJSON = new JSONArray(val);
                  JSONObject innerJson = (JSONObject) arrJSON.get(0);
                  parseJsonObject(innerJson, out);*/
                  getArray(objJson, out);
            }
     }
     return out;
}


	/**
	 * @param object2
	 * @param out
	 * @throws JSONException
	 */
	public static void getArray(Object object2, HashMap<String, Object> out) throws JSONException {
		JSONArray jsonArr = (JSONArray) object2;
		for (int k = 0; k < jsonArr.length(); k++) {
			if (jsonArr.get(k) instanceof JSONObject) {
				parseJsonObject((JSONObject) jsonArr.get(k), out);
			} else {
				System.out.println(jsonArr.get(k));
			}
		} 
	}
	
	/**
	 * @param object2
	 * @param out
	 * @throws JSONException
	 */
	public static void getArray(Object object2, Multimap<String, Object> out) throws JSONException {

        JSONArray jsonArr = (JSONArray) object2;

        for (int k = 0; k < jsonArr.length(); k++) {

            if (jsonArr.get(k) instanceof JSONObject) {
                //parseJson((JSONObject) jsonArr.get(k));
                  parseJsonObject((JSONObject) jsonArr.get(k), out);
            } else {
                System.out.println(jsonArr.get(k));
            }

        }
    }

}
