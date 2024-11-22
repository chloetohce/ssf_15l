package sg.edu.nus.iss.ssf_15l.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Person {
    @NotNull
    private int id;

    @NotEmpty
    private String name;

    @Email
    private String email;

    public Person(String data) {
        String[] dataArr = data.split(",");
        this.id = Integer.parseInt(dataArr[0]);
        this.name = dataArr[1];
        this.email = dataArr[2];
    }

    public Person(@NotNull int id, @NotEmpty String name, @Email String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    public Person() {
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    @Override
    public String toString() {
        return id + "," + name + "," + email;
    }
    
}
