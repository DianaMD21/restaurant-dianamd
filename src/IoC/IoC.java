package IoC;

import java.util.Map;

public class IoC {
    private static IoC instance;
    private Map<String,Object> myMap;

    private IoC() {
    }

    public static IoC getInstance() {
        if (instance == null) {
            instance = new IoC();
        }
        return instance;
    }

    public <O> O getFromPool(String key) {
        return (O) myMap.get(key);
    }
}
