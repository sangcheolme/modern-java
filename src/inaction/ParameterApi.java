package inaction;

import java.util.*;

public class ParameterApi {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("2013UK0001");
        list.add("2014US0002");
        list.add("2011KO0001");
        list.add("2014LS0005");
        list.add("2014DN0002");

        //고객ID 순으로 정렬
        list.sort(Comparator.comparing(o -> o.substring(6, 10)));
        System.out.println(list);

        final Map<Long, String> map = new HashMap<>();
        map.put(1L, "mike");
        map.put(2L, "bob");
        map.put(3L, "jun");
        System.out.println(map);
        map.remove(1L);
        System.out.println(map);


        UserMap userMap = new UserMap(Map.of(
                1L, "mike",
                2L, "bob",
                3L, "jun"
        ));
        System.out.println(userMap.getMap());
    }
}
