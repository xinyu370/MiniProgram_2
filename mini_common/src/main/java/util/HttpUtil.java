package util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

public class HttpUtil {

    public static String httpGet(String url,Map<String,String> params){
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        params.forEach((k,v)->{
            valueMap.add(k, v);
        });
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        URI uri = builder.queryParams(valueMap).build().encode().toUri();
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
        return response.getBody();
    }
    public static<T> String sendPost(String url, Map<String,String> head, Map<String,T> body){
        HttpHeaders requestHeaders = new HttpHeaders();
//        head.forEach((k,v)->{
//            requestHeaders.add(k,v);
//        });
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);
        body.forEach((k,v)->{
            uriComponentsBuilder.queryParam(k,v);
        });
        URI uri = uriComponentsBuilder.build().toUri();
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(params, requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.postForObject(uri, httpEntity, String.class);
        return res;
    }
}
