package org.tmb.utilities;

import com.github.javafaker.Faker;
import org.json.JSONArray;
import org.json.JSONObject;

public class FakerGenerator {

    public static String FakerforPOSTRequestBasedonID(int id) {
        Faker faker=new Faker();
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOpt("id",id);
        jsonObject.putOpt("first_name",faker.name().firstName());
        jsonObject.putOpt("last_name",faker.name().lastName());
        jsonObject.putOpt("email",String.valueOf(faker.name().firstName())+"@gmail.com");
        jsonObject.accumulate("email",String.valueOf(faker.name().firstName())+"@gmail.com");
        JSONArray jsonArray = new JSONArray();
        String roleA=faker.job().title();
        String roleB=faker.job().title();
        jsonArray.put(roleA);
        jsonArray.put(roleB);
        jsonObject.putOpt("roles",jsonArray);
        return jsonObject.toString();
    }

    public static String FakerforPUTIDRequestBasedonID(int id) {
        Faker faker=new Faker();
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOpt("first_name",faker.name().firstName());
        jsonObject.putOpt("last_name",faker.name().lastName());
        return jsonObject.toString();
    }
}
