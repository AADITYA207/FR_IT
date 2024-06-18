package finalProject;

import java.io.File;
import java.util.Comparator;

class ComparatorClass implements Comparator<File> {

	@Override
	public int compare(File o1, File o2) {
		// TODO Auto-generated method stub
        // Get the last modified time of each file
        long lastModifiedTime1 = o1.lastModified();
        long lastModifiedTime2 = o2.lastModified();

        // Compare the last modified times
        if (lastModifiedTime1 < lastModifiedTime2) {
            return -1;
        } else if (lastModifiedTime1 > lastModifiedTime2) {
            return 1;
        } else {
            return 0;
        }
	}
	 
    // Method
    // Sorting in ascending order of name

}
