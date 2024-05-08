package cn.cestc.util;

import cn.cestc.constant.TemplateUrl;
import cn.cestc.domain.Document;
import cn.cestc.domain.vo.CharsHistory;
import cn.cestc.domain.vo.DocUrl;
import cn.cestc.mapper.DocumentMapper;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TemplateUtil {
    private final RestTemplateUtil restTemplateUtil;
    private final UrlBuilder urlUtil;
    private final DocumentMapper documentMapper;

    @Autowired
    public TemplateUtil(RestTemplateUtil restTemplateUtil, @Qualifier("templateUrlBuilderUtil") UrlBuilder urlUtil, DocumentMapper documentMapper) {
        this.restTemplateUtil = restTemplateUtil;
        this.urlUtil = urlUtil;
        this.documentMapper = documentMapper;
    }

    public DocUrl createPad(String padID, String content) {
        DocUrl docUrl = new DocUrl();
        Map<String,String> map = new HashMap<>();
        map.put("padID", padID);
        map.put("text", content);
        String createPad = urlUtil.buildApiUrl("template", TemplateUrl.CREATE_DOC.getMethodName());
        String forObject = restTemplateUtil.get(createPad,String.class, map);
        if (forObject != null && forObject.contains("ok")) {
            docUrl.setResUrl(urlUtil.buildApiUrl("template",TemplateUrl.DOC_URL.getMethodName())+padID);
            return docUrl;
        }
        return null;
    }

    public boolean savePad(String padID, String content) {
        Map<String,String> map = new HashMap<>();
        map.put("padID", padID);
        map.put("text", content);
        String saveDoc = urlUtil.buildApiUrl("template", TemplateUrl.SAVE_DOC.getMethodName());
        String forObject = restTemplateUtil.get(saveDoc,String.class, map);
        return forObject != null && forObject.contains("ok");
    }

    public List<CharsHistory> getChar(String padID) {
        Map<String,String> map = new HashMap<>();
        map.put("padID", padID);
        String getChar = urlUtil.buildApiUrl("template", TemplateUrl.GET_CHAR.getMethodName());
        System.out.println("请求的url："+getChar);
        String forObject = restTemplateUtil.get(getChar,String.class, map);
        // 使用 Gson 解析 JSON 字符串
        Gson gson = new Gson();
        System.out.println(forObject);
        // 创建一个 List 来存储 CharsHistory 对象
        List<CharsHistory> charsHistoryList = new ArrayList<>();
        if (forObject != null && forObject.contains("ok")) {
            JsonObject jsonObject = gson.fromJson(forObject, JsonObject.class);
            JsonArray messagesArray = jsonObject.getAsJsonObject("data").getAsJsonArray("messages");

            //根据padid 找到source
            Document document = documentMapper.selectOne(new QueryWrapper<Document>().eq("padId", padID));
            String source = document.getSource();
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
                        .source(source)
                        .build();
                charsHistoryList.add(chars);
            }
        }
        return charsHistoryList;
    }

    public boolean deletePad(String padID) {
        Map<String,String> map = new HashMap<>();
        map.put("padID", padID);
        String deletePad = urlUtil.buildApiUrl("template", TemplateUrl.DELETE_DOC.getMethodName());
        String forObject = restTemplateUtil.get(deletePad,String.class, map);

        return forObject != null && forObject.contains("ok");
    }

    public String getPad(String padID) {
        Map<String,String> map = new HashMap<>();
        map.put("padID", padID);
        String getPad = urlUtil.buildApiUrl("template", TemplateUrl.GET_DOC.getMethodName());
        String forObject = restTemplateUtil.get(getPad,String.class, map);
        if (forObject != null && forObject.contains("ok")) {
            // 解析 JSON 字符串
            JSONObject jsonObject = new JSONObject(forObject);
            // 提取 data 字段的值到一个 String 变量
            JSONObject data = jsonObject.getJSONObject("data");
            return data.getStr("text");
        }
        return null;
    }


}
