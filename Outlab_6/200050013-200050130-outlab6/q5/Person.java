public class Person
{
    protected String name;
    protected Integer age;

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
        System.out.println("I am a person, " + name + ", " + aj);
    }
}
