package hello.core.singleton;

public class SingletonService {

    private static SingletonTest instance = new SingletonTest();

    public static SingletonTest getInstance() {
        return instance;
    }

    private SingletonService() {}
}
