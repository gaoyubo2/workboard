package cn.cestc.util;

import cn.cestc.constant.TemplateUrl;
import cn.cestc.domain.vo.CharsHistory;
import cn.cestc.domain.vo.DocUrl;
import cn.hutool.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class TemplateUtil {
    @Value("${template.createDoc}")
    private String CREATEDOC;
    @Value("${template.saveDoc}")
    private String SAVEDOC;
    @Value("${template.apikey}")
    private String APIKEY;
    @Value("${template.docUrl}")
    private String DOCURL;
    @Value("${template.getChar}")
    private String GETCHARS;
    @Value("${template.deleteDoc}")
    private String DELETEPAD;
    @Value("${template.getDoc}")
    private String GETDOC;

    private final RestTemplate restTemplate;
    private final RestTemplateUtil restTemplateUtil;
    private final UrlBuilder urlUtil;

    @Autowired
    public TemplateUtil(RestTemplate restTemplate, RestTemplateUtil restTemplateUtil, @Qualifier("templateUrlBuilderUtil") UrlBuilder urlUtil) {
        this.restTemplate = restTemplate;
        this.restTemplateUtil = restTemplateUtil;
        this.urlUtil = urlUtil;
    }

    public DocUrl createPad(String padID, String content) {
        DocUrl docUrl = new DocUrl();
        Map<String,String> map = new HashMap<>();
        map.put("padID", padID);
        map.put("text", content);
        String createPad = urlUtil.buildApiUrl("template", TemplateUrl.CREATE_DOC.getMethodName());
        String forObject = restTemplateUtil.get(createPad,String.class, map);
        if (forObject != null && forObject.contains("ok")) {
            docUrl.setResUrl(DOCURL+padID);
            return docUrl;
        }
        return null;
    }

    public boolean savePad(String padID, String content) {
        String url = SAVEDOC + APIKEY + "&padID=" + padID + "&text=" + content;
        String forObject = restTemplate.getForObject(url, String.class);
        String test = "ok";
        return forObject != null && forObject.contains(test);
    }

    public List<CharsHistory> getChar(String padID) {
        String url = GETCHARS + APIKEY + "&padID=" + padID;
        String forObject = restTemplate.getForObject(url, String.class);
        System.out.println(forObject);

        // 使用 Gson 解析 JSON 字符串
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(forObject, JsonObject.class);
        JsonArray messagesArray = jsonObject.getAsJsonObject("data").getAsJsonArray("messages");

        // 创建一个 List 来存储 CharsHistory 对象
        List<CharsHistory> charsHistoryList = new ArrayList<>();
        // 遍历消息数组并将每个消息转换为 CharsHistory 对象
        for (int i = 0; i < messagesArray.size(); i++) {
            JsonObject messageObject = messagesArray.get(i).getAsJsonObject();
            Date date = new Date(Long.parseLong(String.valueOf(messageObject.get("time").getAsLong())));
            CharsHistory chars = CharsHistory
                    .builder()
                    .text(messageObject.get("text").getAsString())
                    .time(String.valueOf(messageObject.get("time").getAsLong()))
                    .userId(messageObject.get("userId").getAsString())
                    .userName(messageObject.get("userName").getAsString())
                    .date(date)
                    .build();
            charsHistoryList.add(chars);
        }
        return charsHistoryList;
    }

    public boolean deletePad(String padID) {
        String url = DELETEPAD + APIKEY + "&padID=" + padID;
        String forObject = restTemplate.getForObject(url, String.class);
        String test = "ok";
        return forObject != null && forObject.contains(test);
    }

    public String getPad(String padID) {
        String url = GETDOC + APIKEY + "&padID=" + padID;
        String forObject = restTemplate.getForObject(url, String.class);
        String test = "ok";
        if (forObject != null && forObject.contains(test)) {
            // 解析 JSON 字符串
            JSONObject jsonObject = new JSONObject(forObject);
            // 提取 data 字段的值到一个 String 变量
            JSONObject data = jsonObject.getJSONObject("data");
            return data.getStr("text");
        }
        return null;
    }
}
