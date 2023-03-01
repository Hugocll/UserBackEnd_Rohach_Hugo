package demo;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private int age;
    private List<Integer> membershipYears = new ArrayList<Integer>();
    private String email;
    private String password;

    public User(String name, int age, String email, String password) {
        this.name = name;

        if (age >= 0) 
            this.age = age;

        if (email.contains("@"))    
            this.email = email;
        else 
            this.email = null;
        if (password.replaceAll("\\s","").length() != 0)
            this.password = "@$-"+password+"&%#";
        else 
            this.password = "@$-t&%#";
    }

    public int countMembershipYearsAfter1999 () {
        int result = 0;
        for(Integer year: membershipYears) {
            if (year > 1999)
                result++;
        }
        return result;
    }

    public int countYearsOfMembership () {
        return membershipYears.size();
    }

    public void addMembershipYear (int year) {
        membershipYears.add(year);
    }

    public int getAge() {
        return this.age;
    }

    public String getName () {
        return name;
    }

    public String getEmail () {
        return email;
    }

    public String getPassword () {
        return password;
    }

    public int getFirstMembershipYear() {
        return membershipYears
    }

}