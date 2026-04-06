package me.test_zadatak.dtos;

public class SkillDTO {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SkillDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
