package me.test_zadatak.services;

import me.test_zadatak.daos.Job2skillDAO;
import me.test_zadatak.daos.JobCandidateDAO;
import me.test_zadatak.daos.SkillDAO;
import me.test_zadatak.dtos.Job2skillDTO;
import me.test_zadatak.dtos.JobCandidateDTO;
import me.test_zadatak.dtos.SkillDTO;
import me.test_zadatak.entities.Job2skill;
import me.test_zadatak.entities.JobCandidate;
import me.test_zadatak.entities.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class TestZadatakService {

    @Autowired
    private Job2skillDAO j2sDAO;

    @Autowired
    private JobCandidateDAO jcDAO;

    @Autowired
    private SkillDAO skillDAO;

    public boolean addJobCandidate(JobCandidateDTO dto) {
        JobCandidate candidate = new JobCandidate();
        try {

            candidate.setJobCandidateName(dto.getName());
            candidate.setJobCandidateEMail(dto.getEmail());
            candidate.setJobCandidateContactNumber(dto.getContact());
            candidate.setJobCandidateDateOfBirth(dto.getDateOfBirth());

            jcDAO.save(candidate);

            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public boolean addSkill(SkillDTO dto) {
        Skill skill = new Skill();
        try {

            skill.setSkillName(dto.getName());

            skillDAO.save(skill);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean addJobCandidate2Skill(Job2skillDTO dto) {
        Job2skill job2skill = new Job2skill();
        try {

            job2skill.setJobCandidate(dto.getJobCandidate());
            job2skill.setSkill(dto.getSkill());

            j2sDAO.save(job2skill);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeSkillFromCandidate(int skillId, int candidateSkill) {
        try {
            j2sDAO.remove(candidateSkill, skillId);

            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public boolean removeCandidate(int id) {
        try {

            jcDAO.deleteById(id);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public List<JobCandidate> getCandidateByName(String name) {
        try {
            return jcDAO.findByName(name);
        } catch (Exception e) {
            return null;
        }

    }

    public HashSet<JobCandidate> getCandidateBySkill(List<Integer> ids) {

        HashSet<JobCandidate> set = new HashSet<>();

        for (int id : ids) {
            set.addAll(jcDAO.findBySkill(id));
        }

        return set;
    }

}
