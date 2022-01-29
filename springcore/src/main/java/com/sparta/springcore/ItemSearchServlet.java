//package com.sparta.springcore;
//
//import lombok.Getter;
//
////추가됨 ItemSearchServlet 클래스
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//@WebServlet(urlPatterns = "/api/search")
//public class ItemSearchServlet extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//// 1. API Request 의 파라미터 값에서 검색어 추출 -> query 변수
//        String query = request.getParameter("query");
//            //추가됨 요청된 값에서 'query'라는 이름의 값을 추출, 대입
//
//// 2. 네이버 쇼핑 API 호출에 필요한 Header, Body 정리
//        RestTemplate rest = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("X-Naver-Client-Id", "zdqMoIkFaK8uKvC2oNY2");
//        headers.add("X-Naver-Client-Secret", "LiZfsgtuD5");
//        String body = "";
//        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
//
//// 3. 네이버 쇼핑 API 호출 결과 -> naverApiResponseJson (JSON 형태)
//        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query=" + query, HttpMethod.GET, requestEntity, String.class);
//            //추가됨 getBody() method로 빼내서 String타입의 JSON으로 받아줌.(응답된 JSON형태의 값)
//            //추가됨 JSON형태로 요청을 했기 때문에 JSON형태로 응답을 받게 됨.
//        String naverApiResponseJson = responseEntity.getBody();
//
//// 4. naverApiResponseJson (JSON 형태) -> itemDtoList (자바 객체 형태)
//// - naverApiResponseJson 에서 우리가 사용할 데이터만 추출 -> List<ItemDto> 객체로 변환
//        ObjectMapper objectMapper = new ObjectMapper()
//                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            //추가됨 ObjectMapper : 네이버에서 응답 받은 JSON형태를 자바 객체로 만들어줌.(JSON -> List<ItemDto>)
//        JsonNode itemsNode = objectMapper.readTree(naverApiResponseJson).get("items");
//        List<ItemDto> itemD toList = objectMapper
//                .readerFor(new TypeReference<List<ItemDto>>() {})
//                .readValue(itemsNode);
//
//// 5. API Response 보내기  //추가됨 response로 응답할때는 header, body로 나뉘어져 있음(?)
//// 5.1) response 의 header 설정
//        response.setContentType("application/json");    //추가됨 JSON형태라는 것을 알려주어야함
//        response.setCharacterEncoding("UTF-8");
//// 5.2) response 의 body 설정
//        PrintWriter out = response.getWriter();
//// - itemDtoList (자바 객체 형태) -> itemDtoListJson (JSON 형태)    //추가됨 자바 객체를 JSON형태로 변환해줌
//        String itemDtoListJson = objectMapper.writeValueAsString(itemDtoList);
//        out.print(itemDtoListJson); //추가됨 out에는 JSON이 들어감 ( JSON형태로 변환해준 이유)
//                    //추가됨 out : response에서 가져온 객체 (응답을 담당), 응답에 해당 내용을 넣어줌
//        out.flush();
//    }
//}
