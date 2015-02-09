package Program2and3;

import java.util.ArrayList;

public class Sorting {

	public static void selectionSort(ArrayList<Student> list) {
		int min;
		Comparable temp;
		
		for(int index = 0; index < list.size();index++)
		{
			min = index;
		for(int scan = index + 1; scan < list.size(); scan++)
		{
			if(list.get(scan).compareTo(list.get(min)) < 0)
			{
				min = scan;
			}
			
			temp = list.get(min);
			list.set(min, list.get(index));
			list.set(index, (Student) temp);
		}
		
		}
		
	}

	//insertion sort arraylist
	public static void insertionSort(ArrayList<Student> test){
		Comparable temp;
		int previousIndex;
		ArrayList<Student> objectSort = test;
		
		for(int i = 1; i<objectSort.size(); i++)
		{
			temp = objectSort.get(i);
			previousIndex = i - 1;
			
			while ((objectSort.get(previousIndex).compareTo((Student)temp)) < 0 && (previousIndex > 0))
			{
				objectSort.set(previousIndex + 1, objectSort.get(previousIndex));
                previousIndex -= 1;
			}
			 if (objectSort.get(previousIndex).compareTo((Student) temp) < 0) {
	                objectSort.set(previousIndex + 1, objectSort.get(previousIndex));
	                objectSort.set(previousIndex, (Student) temp);
	            } else {

	                objectSort.set(previousIndex + 1, (Student) temp);
	            }
		}
	}
	
	//binary stuff
	public static Comparable binarySearch (Comparable[] list, Comparable target)
	{
		int min=0, max = list.length-1, mid = 0;
		boolean found = false;
		
		while(!found && min <= max)
		{
			mid = (min+max) / 2;
					if(list[mid].compareTo(target) == 0)
					{
						found = true;
					}
					else
					{
						if(target.compareTo(list[mid])<0)
						{
							max = mid -1;
						}
						else
						{
							min = mid + 1;
						}
					}
		}
		
		if(found)
		{
			return list[mid];
		}
		else
		{
			return null;
		}
	}
}
