
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import model.User;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		User elke = new User("Elke", 44, "zaza@gmail.com", "ikbenturk");
		System.out.println(elke.getAge());
		elke.addMembershipYear(2000);
		elke.addMembershipYear(2010);
		elke.addMembershipYear(1999);
		System.out.println(elke.countMembershipYearsAfter1999());

		User miyo = new User("Miyo", 14, "zaza@gmail.com", "ikbenturk");
		System.out.println(miyo);

		User yuki = new User("Yuki", 12, "zaza@gmail.com", "ikbenturk");
		System.out.println("User with name " + yuki.getName() + " is " + yuki.getAge() + " years old");

		User eric = new User("Eric", 65, "zaza@gmail.com", "ikbenturk");
		System.out.println(eric.getName());

		SpringApplication.run(DemoApplication.class, args);
	}

}