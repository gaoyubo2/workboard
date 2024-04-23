package cn.cestc.util;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class TemplateUtil {
    @Value("${template.createDoc}")
    private String CREATEDOC;
    @Value("${template.saveDoc}")
    private String SAVEDOC;
    @Value("${template.apikey}")
    private String APIKEY;
    @Value("${template.docUrl}")
    private String DOCURL;
    private final RestTemplate restTemplate;

    public String createPad(String padID,String content) {
        String url = CREATEDOC + APIKEY + "&padID=" + padID + "&text=" + content ;
        String forObject = restTemplate.getForObject(url, String.class);
        String test = "ok";
        if (forObject != null && forObject.contains(test)) {
            return DOCURL+padID;
        }
        return null;
    }

    public boolean savePad(String padID, String content) {
        String url = SAVEDOC + APIKEY + "&padID=" + padID + "&text=" + content;
        String forObject = restTemplate.getForObject(url, String.class);
        String test = "ok";
        return forObject != null && forObject.contains(test);
    }
}
