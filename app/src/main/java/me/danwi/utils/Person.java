package me.danwi.utils;

/**
 * Created by RunningSnail on 16/6/12.
 */
public class Person {
    private int age;

    private int weight;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Person(Builder builder) {
        this.age = builder.age;
        this.weight = builder.weight;
    }

    public static class Builder {
        private int age;
        private int weight;

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder weight(int weight) {
            this.weight = weight;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }
}
