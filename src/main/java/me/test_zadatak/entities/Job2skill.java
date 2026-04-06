package me.test_zadatak.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "JOB2SKILL")
public class Job2skill {
    @EmbeddedId
    private Job2skillId id;

    @MapsId("skillId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "SKILL_ID", nullable = false)
    private Skill skill;

    @MapsId("jobCandidateId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "JOB_CANDIDATE_ID", nullable = false)
    private JobCandidate jobCandidate;

    public Job2skillId getId() {
        return id;
    }

    public void setId(Job2skillId id) {
        this.id = id;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public JobCandidate getJobCandidate() {
        return jobCandidate;
    }

    public void setJobCandidate(JobCandidate jobCandidate) {
        this.jobCandidate = jobCandidate;
    }

    @Override
    public String toString() {
        return "Job2skill{" +
                "id=" + id +
                ", skill=" + skill +
                ", jobCandidate=" + jobCandidate +
                '}';
    }
}