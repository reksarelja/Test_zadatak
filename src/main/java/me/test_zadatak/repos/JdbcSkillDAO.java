package me.test_zadatak.repos;

import me.test_zadatak.daos.SkillDAO;
import me.test_zadatak.entities.Skill;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcSkillDAO implements SkillDAO {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSkillDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Skill skill) {

        jdbcTemplate.update("INSERT INTO " +
                        "SKILL (SKILL_NAME) " +
                        "VALUES (?)",
                skill.getSkillName());

    }

    @Override
    public Skill findByName(String name) {

        return jdbcTemplate.queryForObject("SELECT * FROM " +
                        "SKILL WHERE " +
                        "SKILL_NAME = ? ",
                (rs, rowNum) -> {
                    Skill skill = new Skill();
                    skill.setId(rs.getInt("skill_id"));
                    skill.setSkillName(rs.getString("skill_name"));
                    return skill;
                },
                name);

    }

    @Override
    public Skill findById(int id) {

        return jdbcTemplate.queryForObject("SELECT * FROM " +
                        "SKILL WHERE " +
                        "SKILL_ID = ? ",
                (rs, rowNum) -> {
                    Skill skill = new Skill();
                    skill.setId(rs.getInt("skill_id"));
                    skill.setSkillName(rs.getString("skill_name"));
                    return skill;
                },
                id);

    }
}
