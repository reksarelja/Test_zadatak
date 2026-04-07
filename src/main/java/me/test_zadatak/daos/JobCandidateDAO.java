package me.test_zadatak.daos;

import me.test_zadatak.entities.JobCandidate;

import java.util.List;

public interface JobCandidateDAO {

    void save(JobCandidate jobCandidate);

    List<JobCandidate> findByName(String name);

    List<JobCandidate> findBySkill(int skillId);

    int deleteById(int id);

}
