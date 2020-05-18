package io.namoosori.travelclub.phase7.spec.facade.aggregate;

import com.google.gson.*;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NameValueList {
    private List<NameValue> nameValueList;

    public NameValueList() {
        this.nameValueList = new ArrayList<>();
    }

    public void addNameValue(NameValue nameValue ){
        this.nameValueList.add(nameValue);
    }

    public void addNameValue(String name, String value ){
        this.nameValueList.add(new NameValue(name, value));
    }

    //    public static NameValueList fromTravelClubJson(String json) {
//        //
//        // {"usid":"","name":"travelClub-test3","intro":"test U (nameValueList)","foundationTime":0,"boardId":"","membershipList":[],"presidentEmail":""}
//
//        JsonDeserializer<NameValueList> jsonDeserializer = new JsonDeserializer() {
//            @Override
//            public NameValueList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//                JsonObject jsonObject = json.getAsJsonObject();
//
//                NameValueList nameValueList = new NameValueList();
//
//                nameValueList.addNameValue("usid", jsonObject.get("usid").getAsString());
//                nameValueList.addNameValue("name", jsonObject.get("name").getAsString());
//                nameValueList.addNameValue("intro", jsonObject.get("intro").getAsString());
//                nameValueList.addNameValue("foundationTime", jsonObject.get("foundationTime").getAsString());
//                nameValueList.addNameValue("boardId", jsonObject.get("boardId").getAsString());
//                nameValueList.addNameValue("membershipList", jsonObject.get("membershipList").toString()); //TODO
//                nameValueList.addNameValue("presidentEmail", jsonObject.get("presidentEmail").getAsString());
//
//                return nameValueList;
//            }
//        };
//
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(NameValueList.class, jsonDeserializer);
//
//        Gson travelClubGson = gsonBuilder.create();
//        NameValueList nameValueList = travelClubGson.fromJson(json, NameValueList.class);
//
//        return nameValueList;
//
//    }
//
//    public static NameValueList fromMemberJson(String json) {
//        //
//        JsonDeserializer<NameValueList> jsonDeserializer = new JsonDeserializer() {
//            @Override
//            public NameValueList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//                JsonObject jsonObject = json.getAsJsonObject();
//
//                NameValueList nameValueList = new NameValueList();
//
//                nameValueList.addNameValue("email", jsonObject.get("email").getAsString());
//                nameValueList.addNameValue("name", jsonObject.get("name").getAsString());
//                nameValueList.addNameValue("nickName", jsonObject.get("nickName").getAsString());
//                nameValueList.addNameValue("phoneNumber", jsonObject.get("phoneNumber").getAsString());
//                nameValueList.addNameValue("birthDay", jsonObject.get("birthDay").getAsString());
//                nameValueList.addNameValue("addresses", jsonObject.get("addresses").toString()); //TODO
//                nameValueList.addNameValue("membershipList", jsonObject.get("membershipList").toString()); //TODO
//
//                return nameValueList;
//            }
//        };
//
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(NameValueList.class, jsonDeserializer);
//
//        Gson memberGson = gsonBuilder.create();
//        NameValueList nameValueList = memberGson.fromJson(json, NameValueList.class);
//
//        return nameValueList;
//
//    }
//
//
//    public static NameValueList fromLoginJson(String json) {
//        //
//        JsonDeserializer<NameValueList> jsonDeserializer = new JsonDeserializer() {
//            @Override
//            public NameValueList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//                JsonObject jsonObject = json.getAsJsonObject();
//
//                NameValueList nameValueList = new NameValueList();
//
//                nameValueList.addNameValue("id", jsonObject.get("id").getAsString());
//                nameValueList.addNameValue("password", jsonObject.get("password").getAsString());
//
//                return nameValueList;
//            }
//        };
//
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(NameValueList.class, jsonDeserializer);
//
//        Gson loginGson = gsonBuilder.create();
//        NameValueList nameValueList = loginGson.fromJson(json, NameValueList.class);
//
//        return nameValueList;
//
//    }
}
