package me.test_zadatak.daos;

import me.test_zadatak.entities.Job2skill;

public interface Job2skillDAO {

    void save(Job2skill j2s);

    int remove(int candidateId, int skillId);
}
