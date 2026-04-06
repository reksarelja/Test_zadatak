package me.test_zadatak;


import me.test_zadatak.controllers.TestZadatakController;
import me.test_zadatak.dtos.Job2skillDTO;
import me.test_zadatak.dtos.JobCandidateDTO;
import me.test_zadatak.dtos.SkillDTO;
import me.test_zadatak.entities.JobCandidate;
import me.test_zadatak.entities.Skill;
import me.test_zadatak.services.TestZadatakService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(TestZadatakController.class)
@AutoConfigureRestTestClient
public class TestZadatakControllerTests {

    @Autowired
    private RestTestClient restTestClient;


    @MockitoBean
    private TestZadatakService service;

    @Test
    void saveCandidateShouldReturnOk() {

        JobCandidateDTO candidate = new JobCandidateDTO();

        candidate.setName("Nikola");
        candidate.setContact("064-4778531");
        candidate.setEmail("marko@gmail.com");
        candidate.setDateOfBirth(LocalDate.of(2002, 11, 11));

        when(service.addJobCandidate(any(JobCandidateDTO.class))).thenReturn(true);

        restTestClient.post()
                .uri("/rest_controller/save_candidate")
                .contentType(MediaType.APPLICATION_JSON)
                .body(candidate)
                .exchange()
                .expectStatus().isOk()
                .expectBody();

    }

    @Test
    void saveSkillShouldReturnOk() {

        SkillDTO skill = new SkillDTO();

        skill.setName("Python");

        when(service.addSkill(any(SkillDTO.class))).thenReturn(true);

        restTestClient.post()
                .uri("/rest_controller/save_skill")
                .contentType(MediaType.APPLICATION_JSON)
                .body(skill)
                .exchange()
                .expectStatus().isOk()
                .expectBody();

    }

    @Test
    void giveCandidateSkillShouldReturnOk() {

        Skill skill = new Skill();

        skill.setSkillName("Java Programming");

        JobCandidate candidate = new JobCandidate();

        candidate.setJobCandidateName("Nikola");
        candidate.setJobCandidateContactNumber("064-4778531");
        candidate.setJobCandidateEMail("marko@gmail.com");
        candidate.setJobCandidateDateOfBirth(LocalDate.of(2002, 11, 11));


        Job2skillDTO j2s = new Job2skillDTO();
        j2s.setSkill(skill);
        j2s.setJobCandidate(candidate);


        when(service.addJobCandidate2Skill(any(Job2skillDTO.class))).thenReturn(true);

        restTestClient.post()
                .uri("/rest_controller/give_candidate_skill")
                .contentType(MediaType.APPLICATION_JSON)
                .body(j2s)
                .exchange()
                .expectStatus().isOk()
                .expectBody();

    }

    @Test
    void removeSkillFromCandidateShouldReturnOk() {
        int candidate = 2;
        int skill = 1;
        restTestClient.delete()
                .uri("/rest_controller/remove_skill_from_candidate/{candidate}/{skill}", candidate, skill)
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    void removeCandidateShouldReturnOk() {

        int id = 2;

        restTestClient.delete()
                .uri("/rest_controller/remove_candidate/{id}", id)
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    void searchCandidateViaNameShouldReturnOk() {

        restTestClient.method(HttpMethod.GET)
                .uri("/rest_controller/search_candidate_name/{name}", "Nemanja")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class);

    }

    @Test
    void searchCandidateViaSkillShouldReturnOk() {

        List<String> list = new ArrayList<>();

        list.add("Python");

        restTestClient.method(HttpMethod.GET)
                .uri("/rest_controller/search_candidate_skill/{skills}", list)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class);

    }

}
