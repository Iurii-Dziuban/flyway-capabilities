package model;

/**
 * Created by iurii.dziuban on 06/07/2017.
 */
public class Person implements Comparable<Person> {
    private final int id;
    private final String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return String.format("Person id = '%d'  and name = '%s'", getId(), getName());
    }

    @Override
    public int compareTo(Person o) {
        return id - o.getId();
    }
}
