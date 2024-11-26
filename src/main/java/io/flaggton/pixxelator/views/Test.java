package io.flaggton.pixxelator.views;

public class Test {
    public static void main(String[] args) {
        new Test();
    }

    public Test() {
        int x = 5;
        System.out.println(x); // =5
        macheEtwas(x);
        System.out.println(x); // =5

        Person markus = new Person("Markus");
        System.out.println(markus.name); //Markus
        machWasMitPerson(markus, "Fred");
        System.out.println(markus.name); //Fred
    }

    public void machWasMitPerson(Person person, String newName) {
        person.name = newName;
//        person = new Person();
    }

    public void macheEtwas(int wert) {
        wert = wert * 2;
    }


    public class Person {
        public String name;

        public Person(String name) {
            this.name = name;
        }
    }
}


