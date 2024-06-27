package hello.hellospring.controller;

public class MemberForm {
    private String name; //form name="name"에서 name -> spring

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }
}
