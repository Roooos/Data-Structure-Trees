
public class Contact implements Comparable<Contact> {
	public String firstName;
	public String lastName;

	public Contact(String f, String l) {
		firstName = f;
		lastName = l;
	}

	public String toString() {
		return firstName + " " + lastName;
	}

	public int compareTo(Contact c) {
		int i = lastName.compareTo(c.lastName);
		if(i != 0) return i;
		
		int j = firstName.compareTo(c.firstName);
		if(j != 0) return j;
		
		return 0;
	}
}