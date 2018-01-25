package com.eaton.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.xml.internal.ws.developer.Serialization;

import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yitgeng on 1/25/2018.
 */
public class JacksonUtil {

    public String objToJson(User user) {

        String jsonStr = null;

        ObjectMapper mapper = new ObjectMapper();
        // 为了使JSON视觉上的可读性，增加一行如下代码，注意，在生产中不需要这样，因为这样会增大Json的内容
        // mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        // 配置mapper忽略空属性
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // 默认情况，Jackson使用Java属性字段名称作为 Json的属性名称,也可以使用Jackson annotations(注解)改变Json属性名称
        try {
//            mapper.writeValue(new File("src/main/resources/tmp/user.json"), user);
            jsonStr = mapper.writeValueAsString(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonStr;

    }


    public User jsonToObj(String jsonLst) {

        User user = null;
        ObjectMapper mapper = new ObjectMapper();
        File json = new File("src/main/resources/tmp/user.json");

        //当反序列化json时，未知属性会引起的反序列化被打断，这里我们禁用未知属性打断反序列化功能，
        //因为，例如json里有10个属性，而我们的bean中只定义了2个属性，其它8个属性将被忽略
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        //从json映射到java对象，得到country对象后就可以遍历查找,下面遍历部分内容，能说明问题就可以了
        try {
            user = mapper.readValue(json, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }


    public static void main(String[] args) {
        User user = new User();
        user.setName("Jack");
        user.setGender(Gender.MALE);

        List<UserInfo> userInfos = new ArrayList<UserInfo>();

        UserInfo userInfo = new UserInfo();
        userInfo.setId(100);
        userInfo.setSchool("PKU");
        userInfo.setAddr("Bei Jing");
        userInfos.add(userInfo);

        userInfo = new UserInfo();
        userInfo.setId(200);
        userInfo.setSchool("NJU");
        userInfo.setAddr("Nan Jing");
        userInfos.add(userInfo);

        user.setAccounts(userInfos);

        JacksonUtil jacksonUtil = new JacksonUtil();

        String jsonStr = jacksonUtil.objToJson(user);
        System.out.println(jsonStr);


        User userRead = new User();
        System.out.println("User Name:" + user.getName());
    }


}
