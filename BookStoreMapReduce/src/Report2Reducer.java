import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Report2Reducer extends Reducer<Text, Text, Text, Text> {
	/*
	 * (non-Javadoc)
	 * @see org.apache.hadoop.mapreduce.Reducer#reduce(KEYIN, java.lang.Iterable, org.apache.hadoop.mapreduce.Reducer.Context)
	 * 
	 * Count for each employee the number of unique projects and the total amount of defects
	 * he worked on
	 */

	public void reduce(Text userDetails, Iterable<Text> report1Values, Context context) throws IOException, InterruptedException {
		ArrayList<String> uniqueProjects = new ArrayList<>();
		Integer noDefects = 0;

		for(Text value : report1Values) {
			String currentProject = value.toString().split(", ")[0];
			
			Boolean isAlreadyCounted = false;
			for (String project : uniqueProjects) {
				if (project.equals(currentProject)) {
					isAlreadyCounted = true;
					break;
				}
			}
			
			if (!isAlreadyCounted) {
				uniqueProjects.add(currentProject);
			}
			
			// count the defects
			noDefects += Integer.parseInt(value.toString().split(", ")[1]);
		}
		
		String value = new String(uniqueProjects.size() + ", " + noDefects);
		context.write(userDetails, new Text(value));
	}

}