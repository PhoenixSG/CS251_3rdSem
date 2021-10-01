import java.util.ArrayList;

public class Teacher extends Person
{
    private Integer salary;
    private ArrayList<Student> studList = new ArrayList<Student>();
    public Teacher(String name, Integer age, Integer salary){
        super(name,age);
        this.salary = salary;
    }
    public Teacher(String name, Integer age){
        super(name,age);
        this.salary = 10000;
    }
    public Teacher (Person p, Integer salary){
        super(p);
        this.salary = salary;
    }
    public Integer getSalary (){
        return salary;
    }
    public void addStudent (Student stud){
        studList.add(stud);    
    }
    public ArrayList<Student> getStudents() {
        return studList;
    }
    public void intro(){
        String aj = age.toString();
        String salar = salary.toString();
        System.out.println("I am a Teacher, " + name + ", " + aj + ", " + salar);

    }

}
