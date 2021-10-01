import java.util.ArrayList;
public class Student extends Person
{
    private Integer rollno;
    private ArrayList<Teacher> teachrList = new ArrayList<Teacher>();
    public Student(String name, Integer age, Integer rollno){
        super(name,age);
        this.name = name;
        this.age = age;
        this.rollno = rollno;
    }
    public Student(Person p, Integer rollno){
        //name = p.getName();
        //age = p.getAge();
        super(p);
        this.rollno = rollno;
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
