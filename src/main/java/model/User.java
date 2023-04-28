package model;

import java.time.Year;
import java.util.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "users")

public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public int id;

    @NotBlank(message = "name may not be empty")
    private String name;

    @PositiveOrZero(message = "age may not be negative")
    private int age;

    @Transient
    private List<Integer> membershipYears = new ArrayList<Integer>();

    @Email(message = "no valid email")
    private String email;

    @Pattern(regexp = "^(?!.*\\s).{8,}$", message = "password must be minimum 8 characters and may not contain white spaces")
    @Pattern(regexp = ".*\\d.*", message = "password must contain a digit")
    private String password;
    private String unencryptedPassword;

    public User(String name, int age, String email, String password) {
        this.name = name;

        if (age < 0) {
            this.age = 1;
        }
        this.age = age;

        if (email.contains("@")) {
            this.email = email;
        } else {
            this.email = "1";
        }

        this.password = password;

        // this.unencryptedPassword = password;
        // if (password.replaceAll("\\s", "").length() != 0)
        // this.password = "@$-" + password + "&%#";
        // else
        // this.password = "@$-t&%#";
    }

    public User() {
    }

    public int countMembershipYearsAfter1999() {
        int result = 0;
        for (Integer year : membershipYears) {
            if (year > 1999)
                result++;
        }
        return result;
    }

    public int countYearsOfMembership() {
        return membershipYears.size();
    }

    public void addMembershipYear(int year) {
        membershipYears.add(year);
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public List<Integer> getMembershipList() {
        return this.membershipYears;
    }

    public int getFirstMembershipYear() {
        if (membershipYears.size() == 0)
            return 0;
        else
            return Collections.min(membershipYears);
    }

    public String toString() {
        String s = String.format("%s is %s years old and has as email %s", name, age, email);
        return s;
    }

    public int getNumberOfMembershipYearsIn2000() {
        int currentYear = Year.now().getValue();
        int count = 0;
        for (int years : membershipYears) {
            if (years >= 2000 && years <= currentYear)
                count++;
        }
        return count;
    }

    public boolean isPasswordCorrect(String passwordToCheck) {
        return unencryptedPassword == passwordToCheck;
    }

}