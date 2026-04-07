package me.test_zadatak.daos;

import me.test_zadatak.entities.Job2skill;

public interface Job2skillDAO {

    //TODO update job with skill
    void save(Job2skill j2s);

    //TODO remove skill from job
    int remove(int candidateId, int skillId);
}
