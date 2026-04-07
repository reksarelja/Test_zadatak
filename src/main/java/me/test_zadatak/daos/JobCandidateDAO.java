package me.test_zadatak.daos;

import me.test_zadatak.entities.Job2skill;
import me.test_zadatak.entities.JobCandidate;
import me.test_zadatak.entities.Skill;

import java.util.List;

public interface JobCandidateDAO {

    //TODO add job
    void save(JobCandidate jobCandidate);

    //TODO search job
    List<JobCandidate> findByName(String name);

    //TODO search all job with skill(s)
    List<JobCandidate> findBySkill(int skillId);

    //TODO remove job
    int deleteById(int id);


}
