package demo.mybatis.entity;

public class User {
	private int id;
	private String name;
	private int age;
	
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("User{")
		.append("id").append(":").append(id).append(", ")
		.append("name").append(":").append(name).append(", ")
		.append("age").append(":").append(age)
		.append("}");
		return buf.toString();
	}
}
