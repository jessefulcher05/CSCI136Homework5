package Program2and3;


public class Student implements Comparable{

	String name;
	int age;
	int aptitude;
	int incomeLevelIndex;
	String [] incomeLevelCB = {"$100,000.00 +","$70,000.00 - $99,999","$40000.00 - $69,999","$25,000.00 - $39,999","$10,000.00 - $24,999","$0.00 - $9,999"};
	
	
	public Student(String name, int age, int incomeLevelIndex, int aptitude)
	{
		this.name = name;
		this.age = age;
		this.aptitude = aptitude;
		this.incomeLevelIndex = incomeLevelIndex;
	}
	
	
	public String toString()
	{
		return "Name: "+ name +" Age: "+  age  +" Aptitude: "+ aptitude + " Income Level: " +incomeLevelCB[incomeLevelIndex] + "\n";
	}


	@Override
	public int compareTo(Object other) {
		// TODO Auto-generated method stub
		int result;
		int aptandIncomeIndex = aptitude + incomeLevelIndex;
		
		int otherAptandIncomeIndex = (((Student) other).getAptitude() + ((Student) other).getIncomeLevelIndex());
		int otherAge = ((Student) other).getAge();

		if (aptandIncomeIndex == otherAptandIncomeIndex)
		{
			result = Integer.compare(age, otherAge);
		}
		else
		{
			result = Integer.compare(aptandIncomeIndex, otherAptandIncomeIndex);
		}

		return result;
	}
	
	public int getAptitude()
	{
		return aptitude;
	}
	
	public int getIncomeLevelIndex()
	{
		return incomeLevelIndex;
	}
	
	public int getAge() 
	{
		return age;
	}
}
