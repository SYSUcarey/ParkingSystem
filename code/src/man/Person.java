package man;
public class Person {
    private String name;
    private String password;
    
    public Person(String name) {
    	this.name = name;
    }
    public Person() {
    }
    public void setName(String name) {
    	this.name = name;
    }
    public String getName() { return name; }
    public String getPassword(){ return password;}
}
