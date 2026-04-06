package me.test_zadatak.daos;

import me.test_zadatak.entities.Skill;

public interface SkillDAO {

    //TODO add skill
    void save(Skill skill);

    //TODO search name
    Skill findByName(String name);

    Skill findById(int id);
}
