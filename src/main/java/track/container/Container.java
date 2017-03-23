package track.container;

import track.container.config.Bean;
import track.container.config.InvalidConfigurationException;
import track.container.config.Property;
import track.container.config.ValueType;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Основной класс контейнера
 * У него определено 2 публичных метода, можете дописывать свои методы и конструкторы
 */
public class Container {

    private Map<String, Object> mapByClassName;
    private Map<String, String> idToClassName;

    // Реализуйте этот конструктор, используется в тестах!
    public Container(List<Bean> beans) {
        initializeContainer(beans);
    }

    public Container(String pathToConfig) throws InvalidConfigurationException {
        JsonConfigReader reader = new JsonConfigReader();
        List<Bean> beans = reader.parseBeans(new File(pathToConfig));
        if (false == initializeContainer(beans)) {
            throw new InvalidConfigurationException("Exception during initializing container");
        }
    }

    private boolean initializeContainer(List<Bean> beans) {
        initializeIdToClassNameMap(beans);
        mapByClassName = new TreeMap<>();
        for (Bean bean : beans) {
            String className = bean.getClassName();
            Object object = getObjectByClassName(className);
            if (null == object) {
                return false;
            }
            if (false == instantiateBean(object, bean)) {
                return false;
            }
        }
        return true;
    }

    private void initializeIdToClassNameMap(List<Bean> beans) {
        idToClassName = new TreeMap<>();
        for (Bean bean : beans) {
            idToClassName.put(bean.getId(), bean.getClassName());
        }
    }

    private Object getObjectByClassName(String className) {
        Object object;
        if (false == mapByClassName.containsKey(className)) {
            try {
                Class beanClass = Class.forName(className);
                object = beanClass.getConstructor(new Class[]{}).newInstance();
                mapByClassName.put(className, object);
            } catch (ClassNotFoundException | InstantiationException |
                    IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            object = mapByClassName.get(className);
        }
        return object;
    }

    private boolean instantiateBean(Object object, Bean bean) {
        Collection<Property> properties = bean.getProperties().values();
        for (Property property : properties) {
            if (property.getType().equals(ValueType.REF)) {
                Object childObject = getObjectByClassName(idToClassName.get(property.getValue()));
                if (null == childObject) {
                    return false;
                }
                invokeSetter(object, property.getName(), childObject);
            } else {
                try {
                    invokeSetter(object, property.getName(), Integer.parseInt(property.getValue()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    private boolean invokeSetter(Object object, String fieldName, Object setterValue) {
        try {
            String methodName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            Method method = object.getClass().getMethod(methodName, new Class[]{setterValue.getClass()});
            method.invoke(object, setterValue);
            return true;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean invokeSetter(Object object, String fieldName, int setterValue) {
        try {
            String methodName = "set" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
            Method method = object.getClass().getMethod(methodName, new Class[]{int.class});
            method.invoke(object, setterValue);
            return true;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Вернуть объект по имени класса
     * Например, Car car = (Car) container.getByClass("track.container.beans.Car")
     */

    public Object getByClass(String className) {
        if (mapByClassName.containsKey(className)) {
            return mapByClassName.get(className);
        }
        return null;
    }

    /**
     * Вернуть объект по имени бина из конфига
     * Например, Car car = (Car) container.getById("carBean")
     */
    public Object getById(String id) {
        if (idToClassName.containsKey(id)) {
            return getByClass(idToClassName.get(id));
        }
        return null;
    }
}
