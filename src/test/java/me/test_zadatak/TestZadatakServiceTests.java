package me.test_zadatak;

import me.test_zadatak.daos.Job2skillDAO;
import me.test_zadatak.daos.JobCandidateDAO;
import me.test_zadatak.daos.SkillDAO;
import me.test_zadatak.dtos.Job2skillDTO;
import me.test_zadatak.dtos.JobCandidateDTO;
import me.test_zadatak.dtos.SkillDTO;
import me.test_zadatak.entities.Job2skill;
import me.test_zadatak.entities.JobCandidate;
import me.test_zadatak.entities.Skill;
import me.test_zadatak.services.TestZadatakService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class TestZadatakServiceTests {

    @Mock
    private Job2skillDAO j2sDao;
    @Mock
    private JobCandidateDAO jcDAO;
    @Mock
    private SkillDAO skillDAO;

    @InjectMocks
    private TestZadatakService service;

    @Test
    void shouldAddJobCandidate_whenCandidateIsGiven() {

        JobCandidateDTO candidate = new JobCandidateDTO();
        candidate.setName("Marko");
        candidate.setEmail("marko@gmail.com");
        candidate.setContact("064-5237448");
        candidate.setDateOfBirth(LocalDate.of(2002, 6, 22));

        boolean result = service.addJobCandidate(candidate);

        assertTrue(result);
        verify(jcDAO, times(1)).save(any(JobCandidate.class));
    }

    @Test
    void shouldAddSkill_whenSkillIsGiven() {

        SkillDTO skill = new SkillDTO();
        skill.setName("Python");

        boolean result = service.addSkill(skill);

        assertTrue(result);
        verify(skillDAO, times(1)).save(any(Skill.class));
    }

    @Test
    void shouldAddJobCandidate2Skill_whenJobCandidateAndSkillAreGiven() {

        JobCandidate candidate = new JobCandidate();
        candidate.setJobCandidateName("Marko");
        candidate.setJobCandidateEMail("marko@gmail.com");
        candidate.setJobCandidateContactNumber("064-5237448");
        candidate.setJobCandidateDateOfBirth(LocalDate.of(2002, 6, 22));

        Skill skill = new Skill();
        skill.setSkillName("Python");

        Job2skillDTO j2s = new Job2skillDTO();
        j2s.setSkill(skill);
        j2s.setJobCandidate(candidate);

        boolean result = service.addJobCandidate2Skill(j2s);

        assertTrue(result);
        verify(j2sDao, times(1)).save(any(Job2skill.class));
    }

    @Test
    void shouldRemoveSkillFromJobCandidate_whenSkillAndJobCandidateAreGiven() {

        int candidate = 2;
        int skill = 1;

        boolean result = service.removeSkillFromCandidate(candidate, skill);

        assertTrue(result);

        verify(j2sDao, times(1)).remove(any(Integer.class), any(Integer.class));
    }

    @Test
    void shouldRemoveJobCandidate_whenGivenJobCandidateId() {

        boolean result = service.removeCandidate(2);

        assertTrue(result);

        verify(jcDAO, times(1)).deleteById(any(Integer.class));
    }

    @Test
    void shouldGetJobCandidates_whenGivenJobCandidateName() {

        String name = "Marko";

        List<JobCandidate> result = service.getCandidateByName(name);

        for (JobCandidate candidate : result) assertEquals(name, candidate.getJobCandidateName());
    }

    @Test
    void shouldGetJobCandidates_whenGivenSkills() {

        JobCandidate candidate = new JobCandidate();
        candidate.setJobCandidateName("Marko");
        candidate.setJobCandidateEMail("marko@gmail.com");
        candidate.setJobCandidateContactNumber("064-5237448");
        candidate.setJobCandidateDateOfBirth(LocalDate.of(2002, 6, 22));

        Skill skill = new Skill();
        skill.setSkillName("Python");

        Job2skillDTO j2s = new Job2skillDTO();
        j2s.setSkill(skill);
        j2s.setJobCandidate(candidate);

        service.addJobCandidate2Skill(j2s);

        List<Integer> list = new ArrayList<>();
        list.add(2);

        HashSet<JobCandidate> result = service.getCandidateBySkill(list);

        for (JobCandidate jc : result) assertEquals(candidate.getJobCandidateName(), jc.getJobCandidateName());
    }

}
