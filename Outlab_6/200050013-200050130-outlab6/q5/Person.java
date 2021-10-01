public class Person
{
    protected String name;
    protected Integer age;

    protected Person(Person person){
        this.name = person.name;
        this.age = person.age;
    }

    public Person(String name, Integer age){
        this.name = name;
        this.age = age;
    }
    public String getName(){
        return name;
    }
    public Integer getAge(){
        return age;
    }
    public void intro(){
        String aj = age.toString();
        System.out.print("I am a person, " + name + ", " + aj);
    }
}
