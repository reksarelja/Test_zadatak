package me.test_zadatak.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Job2skillId implements Serializable {
    private static final long serialVersionUID = -4491444975738085119L;
    @NotNull
    @Column(name = "SKILL_ID", nullable = false)
    private Integer skillId;

    @NotNull
    @Column(name = "JOB_CANDIDATE_ID", nullable = false)
    private Integer jobCandidateId;

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public Integer getJobCandidateId() {
        return jobCandidateId;
    }

    public void setJobCandidateId(Integer jobCandidateId) {
        this.jobCandidateId = jobCandidateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job2skillId entity = (Job2skillId) o;
        return Objects.equals(this.skillId, entity.skillId) &&
                Objects.equals(this.jobCandidateId, entity.jobCandidateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, jobCandidateId);
    }
}