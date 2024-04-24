package cn.cestc.util;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RestTemplateUtil {

    private final RestTemplate restTemplate;

    public <T> T get(String url, Class<T> responseType, Map<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }
        URI uri = builder.build().toUri();
        System.out.println(uri);
        return restTemplate.getForObject(uri, responseType);
    }

    public <T, R> T post(String url, R requestBody, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<R> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<T> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType);
        return responseEntity.getBody();
    }
}
