package inaction;

import java.util.Map;

public final class UserMap {
    private final Map<Long, String> map;

    public UserMap(Map<Long, String> map) {
        this.map = map;
    }

    public Map<Long, String> getMap() {
        return map;
    }
}
