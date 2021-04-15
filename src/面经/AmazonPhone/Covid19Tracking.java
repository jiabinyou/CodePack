package 面经.AmazonPhone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * input:
 *
 * */

class Person {
    String name;
    float x;
    float y;
    float distance_radius;
    public Person(String name, float x, float y, float distance_radius) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.distance_radius = distance_radius;
    }
}

class Cell {
    String name;
    float distance_radius;
    public Cell(String name, float distance_radius) {
        this.name = name;
        this.distance_radius = distance_radius;
    }
}

public class Covid19Tracking {
    Map<Person, List<Person>> map;
    public Covid19Tracking() {
        map = new HashMap<>();
    }

    public Cell most_persons(Person p) {
        map.putIfAbsent(p, new ArrayList<>());
        for (Person prev : map.keySet()) {
            float dis = distance(p, prev);
            if (dis < p.distance_radius) {
                map.get(p).add(prev);
            }
            if (dis < prev.distance_radius) {
                map.get(prev).add(p);
            }
        }
        //check res
        int maxLen = 0;
        Cell res = new Cell(null, 0);
        for (Map.Entry<Person, List<Person>> entry : map.entrySet()) {
            if (entry.getValue().size() > maxLen) {
                res.name = entry.getKey().name;
                res.distance_radius = entry.getKey().distance_radius;
            }
        }
        return res;
    }

    private float distance(Person self, Person other) {
        return (float)Math.sqrt(Math.pow((self.x - other.x), 2) + Math.pow((self.y - other.y), 2));
    }
}

