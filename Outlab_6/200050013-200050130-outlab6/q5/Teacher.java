import java.util.ArrayList;

public class Teacher extends Person
{
    private Integer salary;
    private ArrayList<Student> studList = new ArrayList<Student>();
    public Teacher(String n, Integer a, Integer s){
        super(n,a);
        name = n;
        age = a;
        salary = s;
    }
    public Teacher(String n, Integer a){
        super(n,a);
        name = n;
        age = a;
        salary = 10000;
    }
    public Teacher (Person p, Integer s){
        name = p.getName();
        age = p.getAge();
        salary = s;
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
        System.out.print("I am a Teacher, " + name + ", " + aj + ", " + salar);

    }

}