package gsonTest;

public class Person {
	private int id;
	private String name;
	private Address address;
	private long[] mobileNums;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public long[] getMobileNums() {
		return mobileNums;
	}

	public void setMobileNums(long[] mobileNums) {
		this.mobileNums = mobileNums;
	}
}