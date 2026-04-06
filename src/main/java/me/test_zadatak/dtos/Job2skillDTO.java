package me.test_zadatak.dtos;

import me.test_zadatak.entities.JobCandidate;
import me.test_zadatak.entities.Skill;

public class Job2skillDTO {

    JobCandidate jobCandidate;

    Skill skill;

    public JobCandidate getJobCandidate() {
        return jobCandidate;
    }

    public void setJobCandidate(JobCandidate jobCandidate) {
        this.jobCandidate = jobCandidate;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Job2skillDTO{" +
                "jobCandidate=" + jobCandidate +
                ", skill=" + skill +
                '}';
    }
}
