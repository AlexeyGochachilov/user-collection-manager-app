package ru.aston.finalproject.app.actions;

import org.apache.commons.lang3.ObjectUtils;
import ru.aston.finalproject.app.AppData;
import ru.aston.finalproject.app.AppException;
import ru.aston.finalproject.app.AppRequest;
import ru.aston.finalproject.entity.User;
import ru.aston.finalproject.service.sorting.MergeSorter;
import ru.aston.finalproject.util.Message;

import java.util.Comparator;
import java.util.List;

public class SortingAction extends AppAction {
    private static final Integer EXPECTED_PARAMETERS_AMOUNT = 1;
    private static final String FIELD_PARAMETER = "-by";

    @Override
    public void action(AppData appData, AppRequest request) throws AppException {
        request.checkParametersAmount(EXPECTED_PARAMETERS_AMOUNT);

        String field = request.getStringParameter(FIELD_PARAMETER);

        List<User> userList = appData.getUserList();
        if (ObjectUtils.isEmpty(userList)) {
            System.out.println(Message.EXCEPTION_LIST_NOT_LOADED);
            return;
        }

        Comparator<User> comparator = createComparator(field);

        List<User> sortedList = new MergeSorter().sort(userList, comparator);

        appData.setUserList(sortedList);

        System.out.println(Message.USERS_SORTED);
    }

    private Comparator<User> createComparator(String field) throws AppException {
        switch (field.toLowerCase()) {
            case "name":
                return Comparator.comparing(User::getName);
            case "age":
                return Comparator.comparingInt(User::getAge);
            case "email":
                return Comparator.comparing(User::getEmail);
            default:
                throw new AppException("Неверное поле для сортировки: " + field +
                        ". Доступные поля: name, age, email");
        }
    }
}