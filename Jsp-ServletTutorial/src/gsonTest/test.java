package gsonTest;

import java.util.HashMap;

public class test {
//	public static void main(String[] args) {
//
//		// Create a HashTable to store
//		// String values corresponding to integer keys
//		Hashtable<Integer, String> hm = new Hashtable<Integer, String>();
//
//		// Input the values
//		hm.put(12, "forGeeks");
//		hm.put(1, "Geeks");
//		hm.put(15, "A computer");
//		hm.put(3, "Portal");
//
//		// Printing the Hashtable
//		System.out.println(hm);
//
//	}
	
	static void createHashMap(int arr[]) 
    { 
        // Creates an empty HashMap 
        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>(); 
  
        // Traverse through the given array 
        for (int i = 0; i < arr.length; i++) { 
  
            // Get if the element is present 
            Integer c = hmap.get(arr[i]); 
            
            System.out.println(c);
            
            // If this is first occurrence of element 
            // Insert the element 
            if (hmap.get(arr[i]) == null) { 
                hmap.put(arr[i], 1); 
            } 
  
            // If elements already exists in hash map 
            // Increment the count of element by 1 
            else { 
                hmap.put(arr[i], ++c); 
            } 
        } 
  
        // Print HashMap 
        System.out.println(hmap); 
    } 
  
    // Driver method to test above method 
    public static void main(String[] args) 
    { 
        int arr[] = { 10, 34, 5, 10, 3, 5, 10 }; 
        createHashMap(arr); 
    } 
}
