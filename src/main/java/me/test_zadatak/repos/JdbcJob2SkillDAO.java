package me.test_zadatak.repos;

import me.test_zadatak.daos.Job2skillDAO;
import me.test_zadatak.entities.Job2skill;
import me.test_zadatak.entities.Job2skillId;
import me.test_zadatak.entities.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcJob2SkillDAO implements Job2skillDAO {

    @Autowired
    JdbcJobCandidateDAO jcDAO;
    @Autowired
    JdbcSkillDAO sDAO;

    private final JdbcTemplate jdbcTemplate;

    public JdbcJob2SkillDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Job2skill j2s) {

        jdbcTemplate.update("INSERT INTO " +
                        "JOB2SKILL (skill_id, job_candidate_id) " +
                        "VALUES (?, ?)",
                j2s.getSkill().getId(),
                j2s.getJobCandidate().getId());

    }

    @Modifying
    @Override
    public void remove(int candidateId, int skillId) {

        jdbcTemplate.update("DELETE FROM " +
                "JOB2SKILL WHERE " +
                "JOB_CANDIDATE_ID = ? AND " +
                "SKILL_ID = ?", candidateId, skillId);

    }

}
