package learn;

class EncapsulateTest {
	private String studentName;
	private String studentRoll;
	private int studentAge;

	public String getStudentName() {
		return studentName;
	}
	public int getStudentAge() {
		return studentAge;
	}
	public String getStudentRoll() {
		return studentRoll;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public void setStudentAge(int studentAge) {
		this.studentAge = studentAge;
	}
	public void setStudentRoll(String studentRoll) {
		this.studentRoll = studentRoll;
	}
}

