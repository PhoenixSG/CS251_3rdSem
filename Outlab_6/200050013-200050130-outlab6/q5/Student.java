import java.util.ArrayList;
public class Student extends Person
{
    private Integer rollno;
    private ArrayList<Teacher> teachrList = new ArrayList<Teacher>();
    public Student(String n, Integer a, Integer r){
        super(n,a);
        name = n;
        age = a;
        rollno = r;
    }
    public Student(Person p, Integer r){
        name = p.getName();
        age = p.getAge();
        rollno = r;
    }
    public Integer getRollno(){
        return rollno;
    }
    public void addTeacher(Teacher teachr){
        teachrList.add(teachr); 
    }
    public ArrayList<Teacher> getTeachers() {
        return teachrList;
    }
    public void intro(){
        String aj = age.toString();
        String rol = rollno.toString();
        System.out.print("I am a Student, " + name + ", " + aj + ", " + rol);

    }

}