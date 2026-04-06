package me.test_zadatak.controllers;

import jakarta.validation.Valid;
import me.test_zadatak.dtos.Job2skillDTO;
import me.test_zadatak.dtos.JobCandidateDTO;
import me.test_zadatak.dtos.SkillDTO;
import me.test_zadatak.entities.JobCandidate;
import me.test_zadatak.services.TestZadatakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;


@RestController
@RequestMapping("/rest_controller/")
public class TestZadatakController {

    @Autowired
    TestZadatakService service;

    @PostMapping("save_candidate")
    public ResponseEntity<?> newCandidate(@RequestBody @Valid JobCandidateDTO candidate) {

        boolean result = service.addJobCandidate(candidate);

        if (result) return ResponseEntity.ok("Uspesno sacuvan kandidat");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Greska pri cuvanju kandidata");

    }

    @PostMapping("save_skill")
    public ResponseEntity<?> newSkill(@RequestBody @Valid SkillDTO skill) {

        boolean result = service.addSkill(skill);

        if (result) return ResponseEntity.ok("Uspesno sacuvana vestina");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Greska pri cuvanju vestine");

    }

    @PostMapping("give_candidate_skill")
    public ResponseEntity<?> giveCandidateSkill(@RequestBody @Valid Job2skillDTO j2s) {

        boolean result = service.addJobCandidate2Skill(j2s);

        if (result) return ResponseEntity.ok("Uspesno sacuvana vestina kandidata");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Greska pri cuvanju vestine kandidatu");
    }

    @DeleteMapping("remove_skill_from_candidate/{candidateId}/{skillId}")
    public ResponseEntity<?> removeSkillCandidate(@PathVariable int candidateId, @PathVariable int skillId) {

        service.removeSkillFromCandidate(candidateId, skillId);

        return ResponseEntity.ok("Uspesno uklonjena vestina kandidata");

    }

    @DeleteMapping("remove_candidate/{id}")
    public ResponseEntity<?> removeCandidate(@PathVariable("id") int candidateId) {

        service.removeCandidate(candidateId);

        return ResponseEntity.ok("Uspesno uklonjen kandidat");

    }

    @GetMapping("search_candidate_name/{name}")
    public ResponseEntity<?> searchCandidateViaName(@PathVariable("name") String candidateName) {

        List<JobCandidate> list = service.getCandidateByName(candidateName);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("search_candidate_skill/{skills}")
    public ResponseEntity<?> searchCandidateViaSkill(@PathVariable("skills") List<Integer> ids) {

        HashSet<JobCandidate> set = service.getCandidateBySkill(ids);

        return ResponseEntity.ok().body(set);
    }
}
