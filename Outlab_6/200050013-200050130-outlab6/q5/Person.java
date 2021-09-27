public class Person
{
    protected String name;
    protected Integer age;
    protected Person(){}
    public Person(String s, Integer i){
        name = s;
        age = i;
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