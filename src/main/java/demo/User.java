package demo;

import java.time.Year;
import java.util.*;

public class User {

    private String name;
    private int age;
    private List<Integer> membershipYears = new ArrayList<Integer>();
    private String email;
    private String password;
    private String unencryptedPassword;

    public User(String name, int age, String email, String password) {
        this.name = name;

        if (age >= 0) 
            this.age = age;

        if (email.contains("@"))    
            this.email = email;
        else 
            this.email = null;

        this.unencryptedPassword = password;

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
        if (membershipYears.size() == 0)
            return 0;
        else
            return Collections.min(membershipYears);
    }

    public String toString() {
        String s = String.format("%s is %s years old and has as email %s",name,age,email);
        return s;
    }

    public int getNumberOfMembershipYearsIn2000(){
        int currentYear = Year.now().getValue();
        int count = 0;
        for (int years : membershipYears){
            if (years >= 2000 && years <= currentYear)
                count ++;
        }
        return count;
    }

    public boolean isPasswordCorrect(String passwordToCheck){
        return unencryptedPassword == passwordToCheck;
    }

    
}