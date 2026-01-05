package ru.aston.finalproject.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class User implements Comparable<User> {

    private final String name;
    private final String email;
    private final int age;

    private User(Entity entity) {
        this.name = entity.getFieldOne();
        this.email = entity.getFieldTwo();
        this.age = entity.getFieldInt();
    }

    public static User build(String name, String email, int age) {
        BuildConcreteEntity buildUserEntity = new BuildConcreteEntity();
        Entity userEntity = buildUserEntity.buildUser(name, email, age);
        return new User(userEntity);
    }

    @Override
    public int compareTo(User o) {
        if (!this.name.equals(o.name)) {
            return this.name.compareTo(o.name);
        } else if (!this.email.equals(o.email)) {
            return this.email.compareTo(o.email);
        } else {
            return Integer.compare(this.age, o.age);
        }
    }

    @Override
    public String toString() {
        return String.format("User{name: %s, email: %s, age: %d}", this.name, this.email, this.age);
    }
}
