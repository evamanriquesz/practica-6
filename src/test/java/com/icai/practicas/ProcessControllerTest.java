package com.icai.practicas;

import com.icai.practicas.controller.ProcessController;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.BDDAssertions.then;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcessControllerTest {

    @LocalServerPort
    private int port;


    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void process_entries_correct_then_ok(){

        //Given
        String address = "http://localhost:" + port + "/api/v1/process-step1";
        ProcessController.DataRequest dataEntry = new ProcessController.DataRequest("Porter Robinson", "02364298J", "987654321");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(dataEntry, headers);

        //When
        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

        //Then
        String expectedResult = "{\"result\":\"OK\"}";
        then(result.getBody()).isEqualTo(expectedResult);
    }


    @Test
    public void process_entries_bad_dni_then_ko()
    {
        //Given
        String address = "http://localhost:" + port + "/api/v1/process-step1";
        ProcessController.DataRequest dataEntry = new ProcessController.DataRequest("Eva Manrique", "06028177A", "630720672");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(dataEntry, headers);

        //When
        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

        //Then
        String expectedResult = "{\"result\":\"KO\"}";
        then(result.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void process_entries_bad_telephone_then_ko()
    {
        //Given
        String address = "http://localhost:" + port + "/api/v1/process-step1";
        ProcessController.DataRequest dataEntry = new ProcessController.DataRequest("Eva Manrique", "06028177S", "63072067");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProcessController.DataRequest> request = new HttpEntity<>(dataEntry, headers);

        //When
        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

        //Then
        String expectedResult = "{\"result\":\"KO\"}";
        then(result.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void process_entries_correct_then_ok_legacy(){

        //Given
        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("fullName", "Eva Manrique");
        data.add("dni", "06028177S");
        data.add("telefono", "630720672");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(data, headers);

        //When
        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

        //Then
        String expectedResult = "Muchas gracias por enviar los datos";
        then(result.getBody()).contains(expectedResult);
    }

    @Test
    public void process_entries_bad_dni_then_ko_legacy(){

        //Given
        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("fullName", "Eva Manrique");
        data.add("dni", "06028177A");
        data.add("telefono", "630720672");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(data, headers);

        //When
        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

        //Then
        String expectedResult = "Revise los datos introducidos";
        then(result.getBody()).contains(expectedResult);
    }

    @Test
    public void process_entries_bad_telephone_then_ko_legacy(){

        //Given
        String address = "http://localhost:" + port + "/api/v1/process-step1-legacy";
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("fullName", "Eva Manrique");
        data.add("dni", "06028177S");
        data.add("telefono", "6307272dsf");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(data, headers);

        //When
        ResponseEntity<String> result = this.restTemplate.postForEntity(address, request, String.class);

        //Then
        String expectedResult = "Revise los datos introducidos";
        then(result.getBody()).contains(expectedResult);
    }

}
