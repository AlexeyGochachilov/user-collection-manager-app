package ru.aston.finalproject;

import ru.aston.finalproject.BuildsEntitys.BuildUser;
import ru.aston.finalproject.entity.Entity;
import ru.aston.finalproject.parser.Parsing;
import ru.aston.finalproject.parser.UserParser;

public class main {
    public static void main(String[] args) {
        Parsing<Entity> user = new UserParser();
        Entity User = user.parse("lalalalal; lalalail.com; 24", "; ");
        System.out.println(User.toString());
    }
}
