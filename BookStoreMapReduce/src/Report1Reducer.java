import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.security.authentication.util.KerberosName.NoMatchingRule;

public class Report1Reducer extends Reducer<Text, Text, Text, Text>  {
	/*
	 * (non-Javadoc)
	 * @see org.apache.hadoop.mapreduce.Reducer#reduce(KEYIN, java.lang.Iterable, org.apache.hadoop.mapreduce.Reducer.Context)
	 * 
	 * For a particular employee and a particular project of his with a particular severity
	 * count the number of defects as well as the solved ones
	 */
	
	public void reduce(Text key, Iterable<Text> noContributions, Context context)
			throws IOException, InterruptedException {
		long totalNoDefects = 0;
		long totalNoDefectsSolved = 0;
		
		for (Text val : noContributions) {
			Integer noDefectsSolved = Integer.parseInt(val.toString().split(" ")[0]);
			Integer noDefects = Integer.parseInt(val.toString().split(" ")[1]);
			totalNoDefectsSolved += noDefectsSolved;
			totalNoDefects += noDefects;
		}
		String value = String.valueOf(totalNoDefectsSolved) + "/" + String.valueOf(totalNoDefects);
		
		// write the key and the total number of contributions as value 
		context.write(key, new Text(value));
	}
	
}
  
  