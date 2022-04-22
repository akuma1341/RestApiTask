package com.example.restapitask.controllers;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExamMarksRestControllerTests {

    @LocalServerPort
    int port;
    private String uri;

    @BeforeEach
    void init() {
        String path = "http://localhost:%d/api/v1/examMarks";
        uri = String.format(path, port);
    }

    @Test
    void whenGetExamMarks_thenStatus405() {
        when()
                .get(uri)
                .then()
                .statusCode(405);
    }

    @Test
    void whenGetExamMarksById_thenStatus200() {
        when()
                .get(uri + "/{examMarkId}", 1)
                .then()
                .statusCode(200);
    }

    @Test
    @Transactional
    void givenExamMark_whenPost_thenStatus201() {
        int mark = 5;
        int studentId = 1;
        int subjectId = 5;
        String request = String.format("{\"mark\":%d, " +
                        "\"student\":\"/%d\", " +
                        "\"subject\":\"/%d\"}",
                mark, studentId, subjectId);

        given().contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(uri)
                .then()
                .statusCode(201);
    }

    @Test
    @Transactional
    void givenExamMark_whenPut_thenStatus200() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"mark\": 10}")
                .when()
                .put(uri + "/{examMarkId}", 3)
                .then()
                .statusCode(200)
                .assertThat().body("mark", equalTo(10))
                .log().all();


    }

    @Test
    @Transactional
    void givenExamMark_whenPatch_thenStatus200() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"mark\": 10}")
                .when()
                .put(uri + "/{examMarkId}", 3)
                .then()
                .statusCode(200)
                .assertThat().body("mark", equalTo(10))
                .log().all();
    }

    @Test
    @Transactional
    void givenExamMarkId_whenDelete_thenStatus204() {
        when()
                .delete(uri + "/{examMarkId}", 1)
                .then()
                .statusCode(204);
    }

    @Test
    void givenMark_whenGetExamMarksByMark_thenStatus200AndResultSizeIs20() {
        given()
                .param("mark", 8)
                .when()
                .get(uri + "/search/find")
                .then()
                .statusCode(200)
                .assertThat().body("_embedded.examMarks.size()", equalTo(20));
    }

    @Test
    @Transactional
    void givenMark_WhenDeleteByMark_thenStatus200() {
        given()
                .param("mark", 8)
                .when()
                .get(uri + "/search/deleteByMark")
                .then()
                .statusCode(200);
    }

    @Test
    @Transactional
    void givenMark_WhenUpdateByMark_thenStatus200() {
        given()
                .param("mark", 8)
                .when()
                .get(uri + "/search/updateMarks")
                .then()
                .statusCode(200);
    }
}
