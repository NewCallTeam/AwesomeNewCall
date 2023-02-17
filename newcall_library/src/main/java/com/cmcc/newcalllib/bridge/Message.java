/*
 * Copyright (c) 2023 China Mobile Communications Group Co.,Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cmcc.newcalllib.bridge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Message {

	private String callbackId; //callbackId
	private String responseId; //responseId
	private String responseData; //responseData
	private String data; //data of message
	private String handlerName; //name of handler

    private final static String CALLBACK_ID_STR = "callbackId";
    private final static String RESPONSE_ID_STR = "responseId";
    private final static String RESPONSE_DATA_STR = "responseData";
    private final static String DATA_STR = "data";
    private final static String HANDLER_NAME_STR = "handlerName";
	
	public String getResponseId() {
		return responseId;
	}
	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}
	public String getResponseData() {
		return responseData;
	}
	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}
	public String getCallbackId() {
		return callbackId;
	}
	public void setCallbackId(String callbackId) {
		this.callbackId = callbackId;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHandlerName() {
		return handlerName;
	}
	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}
	
	public String toJson() {
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put(CALLBACK_ID_STR, getCallbackId());
            jsonObject.put(DATA_STR, getData());
            jsonObject.put(HANDLER_NAME_STR, getHandlerName());
            jsonObject.put(RESPONSE_DATA_STR, getResponseData());
            jsonObject.put(RESPONSE_ID_STR, getResponseId());
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public static Message toObject(String jsonStr) {
        Message m =  new Message();
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            m.setHandlerName(jsonObject.has(HANDLER_NAME_STR) ? jsonObject.getString(HANDLER_NAME_STR):null);
            m.setCallbackId(jsonObject.has(CALLBACK_ID_STR) ? jsonObject.getString(CALLBACK_ID_STR):null);
            m.setResponseData(jsonObject.has(RESPONSE_DATA_STR) ? jsonObject.getString(RESPONSE_DATA_STR):null);
            m.setResponseId(jsonObject.has(RESPONSE_ID_STR) ? jsonObject.getString(RESPONSE_ID_STR):null);
            m.setData(jsonObject.has(DATA_STR) ? jsonObject.getString(DATA_STR):null);
            return m;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return m;
	}
	
	public static List<Message> toArrayList(String jsonStr){
        List<Message> list = new ArrayList<Message>();
        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
            for(int i = 0; i < jsonArray.length(); i++){
                Message m = new Message();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                m.setHandlerName(jsonObject.has(HANDLER_NAME_STR) ? jsonObject.getString(HANDLER_NAME_STR):null);
                m.setCallbackId(jsonObject.has(CALLBACK_ID_STR) ? jsonObject.getString(CALLBACK_ID_STR):null);
                m.setResponseData(jsonObject.has(RESPONSE_DATA_STR) ? jsonObject.getString(RESPONSE_DATA_STR):null);
                m.setResponseId(jsonObject.has(RESPONSE_ID_STR) ? jsonObject.getString(RESPONSE_ID_STR):null);
                m.setData(jsonObject.has(DATA_STR) ? jsonObject.getString(DATA_STR):null);
                list.add(m);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
	}
}
