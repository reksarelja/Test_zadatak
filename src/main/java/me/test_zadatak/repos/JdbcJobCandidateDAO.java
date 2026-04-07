package me.test_zadatak.repos;

import me.test_zadatak.daos.JobCandidateDAO;
import me.test_zadatak.entities.JobCandidate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class JdbcJobCandidateDAO implements JobCandidateDAO {

    private final JdbcTemplate jdbcTemplate;

    public JdbcJobCandidateDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void save(JobCandidate jobCandidate) {

        jdbcTemplate.update("INSERT INTO " +
                        "JOB_CANDIDATE (job_candidate_name, " +
                        "job_candidate_date_of_birth, " +
                        "job_candidate_contact_number, " +
                        "job_candidate_e_mail) " +
                        "VALUES (?, ?, ?, ?)",
                jobCandidate.getJobCandidateName(),
                jobCandidate.getJobCandidateDateOfBirth(),
                jobCandidate.getJobCandidateContactNumber(),
                jobCandidate.getJobCandidateEMail());

    }

    @Override
    public List<JobCandidate> findByName(String name) {

        return jdbcTemplate.query(
                "SELECT * FROM " +
                        "job_candidate WHERE " +
                        "JOB_CANDIDATE_NAME = ?",
                (rs, rowNum) -> {

                    JobCandidate jc = new JobCandidate();
                    jc.setJobCandidateId(rs.getInt("job_candidate_id"));
                    jc.setJobCandidateName(rs.getString("job_candidate_name"));
                    jc.setJobCandidateDateOfBirth(rs.getObject("job_candidate_date_of_birth", LocalDate.class));
                    jc.setJobCandidateContactNumber(rs.getString("job_candidate_contact_number"));
                    jc.setJobCandidateEMail(rs.getString("job_candidate_e_mail"));
                    return jc;

                },
                name);

    }

    @Override
    public List<JobCandidate> findBySkill(int skillId) {

        return jdbcTemplate.query(
                "SELECT * FROM " +
                        "JOB_CANDIDATE jc LEFT JOIN " +
                        "JOB2SKILL j2s ON " +
                        "jc.JOB_CANDIDATE_ID = j2s.JOB_CANDIDATE_ID WHERE " +
                        "j2s.SKILL_ID = ? ",
                (rs, rowCount) -> {

                    JobCandidate candidate = new JobCandidate();
                    candidate.setJobCandidateId(rs.getInt("job_candidate_id"));
                    candidate.setJobCandidateDateOfBirth(rs.getObject("job_candidate_date_of_birth", LocalDate.class));
                    candidate.setJobCandidateEMail(rs.getString("job_candidate_e_mail"));
                    candidate.setJobCandidateContactNumber(rs.getString("job_candidate_contact_number"));
                    candidate.setJobCandidateName(rs.getString("job_candidate_name"));
                    return candidate;

                },
                skillId);
    }

    @Override
    public int deleteById(int id) {

       return jdbcTemplate.update("DELETE FROM " +
                "JOB_CANDIDATE WHERE " +
                "JOB_CANDIDATE_ID = ?", id);
    }

}
