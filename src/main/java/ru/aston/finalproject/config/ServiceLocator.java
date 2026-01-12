package ru.aston.finalproject.config;

import ru.aston.finalproject.app.AppException;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceLocator {
    private static final Map<Class<?>, Object> components = new ConcurrentHashMap<>();

    public static <T> T getService(Class<T> clazz) {
        if (!components.containsKey(clazz)) {
            try {
                Constructor<?> constructor = clazz.getConstructors()[0];
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Object[] parameters = new Object[parameterTypes.length];
                for (int i = 0; i < parameterTypes.length; i++) {
                    parameters[i] = ServiceLocator.getService(parameterTypes[i]);
                }
                Object newInstance = constructor.newInstance(parameters);
                components.put(clazz, newInstance);
            } catch (ReflectiveOperationException e) {
                throw new AppException("%s not found".formatted(clazz.getSimpleName()));
            }
        }

        return (T) components.get(clazz);
    }
}
