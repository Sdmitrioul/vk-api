package org.skroba.vk.parser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.skroba.vk.util.VkResponse;

public class VkResponseParser implements Parser<VkResponse> {
    @Override
    public VkResponse parse(String data) throws ParseException {
        VkResponse response = new VkResponse();
    
        JSONObject jsonObject = (JSONObject) JSONValue.parse(data);
    
        JSONObject responseObject = (JSONObject) jsonObject.get("response");
        
        if (responseObject == null) {
            throw new ParseException("Response don't contain posts!");
        }
    
        JSONArray items = (JSONArray) responseObject.get("items");
    
        for (Object o : items) {
            JSONObject item = (JSONObject) o;
        
            Object time = item.get("date");
        
            if (!(time instanceof Long)) {
                throw new ParseException("Post don't contain time!");
            }
    
            response.addTime((Long) time);
        }
        
        return response;
    }
}
