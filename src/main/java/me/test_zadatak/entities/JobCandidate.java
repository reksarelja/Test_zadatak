package me.test_zadatak.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "JOB_CANDIDATE")
public class JobCandidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOB_CANDIDATE_ID", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "JOB_CANDIDATE_NAME")
    private String jobCandidateName;

    @Column(name = "JOB_CANDIDATE_DATE_OF_BIRTH")
    private LocalDate jobCandidateDateOfBirth;

    @Lob
    @Column(name = "JOB_CANDIDATE_CONTACT_NUMBER")
    private String jobCandidateContactNumber;

    @Lob
    @Column(name = "JOB_CANDIDATE_E_MAIL")
    private String jobCandidateEMail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobCandidateName() {
        return jobCandidateName;
    }

    public void setJobCandidateName(String jobCandidateName) {
        this.jobCandidateName = jobCandidateName;
    }

    public LocalDate getJobCandidateDateOfBirth() {
        return jobCandidateDateOfBirth;
    }

    public void setJobCandidateDateOfBirth(LocalDate jobCandidateDateOfBirth) {
        this.jobCandidateDateOfBirth = jobCandidateDateOfBirth;
    }

    public String getJobCandidateContactNumber() {
        return jobCandidateContactNumber;
    }

    public void setJobCandidateContactNumber(String jobCandidateContactNumber) {
        this.jobCandidateContactNumber = jobCandidateContactNumber;
    }

    public String getJobCandidateEMail() {
        return jobCandidateEMail;
    }

    public void setJobCandidateEMail(String jobCandidateEMail) {
        this.jobCandidateEMail = jobCandidateEMail;
    }

    @Override
    public String toString() {
        return "JobCandidate{" +
                "id=" + id +
                ", jobCandidateName='" + jobCandidateName + '\'' +
                ", jobCandidateDateOfBirth=" + jobCandidateDateOfBirth +
                ", jobCandidateContactNumber='" + jobCandidateContactNumber + '\'' +
                ", jobCandidateEMail='" + jobCandidateEMail + '\'' +
                '}';
    }
}