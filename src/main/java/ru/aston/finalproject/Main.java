package ru.aston.finalproject;

import ru.aston.finalproject.entity.BuildConcreteEntity;
import ru.aston.finalproject.entity.Bus;
import ru.aston.finalproject.entity.Entity;
import ru.aston.finalproject.entity.User;
import ru.aston.finalproject.parser.BusParser;
import ru.aston.finalproject.parser.EntityParser;
import ru.aston.finalproject.parser.Parsing;
import ru.aston.finalproject.parser.UserParser;
import ru.aston.finalproject.validators.UserValidator;

public class Main {

    public static void main(String[] args) {

        BuildConcreteEntity buildConcreteEntity = new BuildConcreteEntity();
        Entity userEntity = buildConcreteEntity.buildUser("Ivan", "asas@mail.com", 25);
        Entity busEntity = buildConcreteEntity.buildBus("Mercedes", "4545km.", 415);
        System.out.println(userEntity);
        System.out.println(busEntity);

        Parsing<Entity> userParser = new EntityParser(new UserValidator());
        userEntity = userParser.parse("Dasha:dasha@ya.ru:45");
        System.out.println(userEntity);

        Parsing<Bus> busParser = new BusParser();
        Bus bus = busParser.parse("BMW; 34km.; 213", "; ");
        System.out.println(bus);

        Parsing<User> userParser2 = new UserParser();
        User user3 = userParser2.parse("Dima:dina@gmail.com:35");
        System.out.println(user3);

        String userToString = userParser.parseToString(userEntity);
        System.out.println(userToString);

        User user = User.build("Ivan", "asas@mail.com", 25);
        System.out.println(user);

        Bus bus2 = Bus.build("Mercedes", "4545km.", 415);
        System.out.println(bus2);


    }
}
