import java.io.IOException;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.conf.Configuration;

public class Report1Mapper extends TableMapper<Text, Text>  {
	/*
	 * (non-Javadoc)
	 * @see org.apache.hadoop.mapreduce.Mapper#map(KEYIN, VALUEIN, org.apache.hadoop.mapreduce.Mapper.Context)
	 *
	 * for a particular contribution get the employee's details, project's
	 * name and defect's severity and solver
	 */

	public void map(ImmutableBytesWritable contributionID, Result contributionReferences, Context context)
			throws IOException, InterruptedException {

			String employeeID = new String(contributionReferences.getValue("reference".getBytes(), "employeeID".getBytes()));
			String defectID = new String(contributionReferences.getValue("reference".getBytes(), "defectID".getBytes()));
			
			Configuration configuration = HBaseConfiguration.create();

			// get the employee's first and last name based on his id
			HTable employees = new HTable(configuration, "employees");
		    Get get = new Get(employeeID.getBytes());
			get.addColumn("identification".getBytes(), "firstname".getBytes());
			get.addColumn("identification".getBytes(), "lastname".getBytes());
			Result result = employees.get(get);
			employees.close();
			
			String firstname = new String(result.getValue("identification".getBytes(), "firstname".getBytes()));
			String lastname = new String(result.getValue("identification".getBytes(), "lastname".getBytes()));
			
			// get the defect's projectSeverity and project id based on his id
			HTable defects = new HTable(configuration, "defects");
			get = new Get(defectID.getBytes());
			get.addColumn("details".getBytes(), "severity".getBytes());
			get.addColumn("reference".getBytes(), "projectID".getBytes());
			get.addColumn("reference".getBytes(), "employeeID".getBytes());
			result = defects.get(get);
			defects.close();
			
			String projectSeverity = new String(result.getValue("details".getBytes(), "severity".getBytes()));
			String projectID = new String(result.getValue("reference".getBytes(), "projectID".getBytes()));
			String defectEmployeeId = new String(result.getValue("reference".getBytes(), "employeeID".getBytes()));
			
			// get the project's name based on his id discovered in the previous step
			HTable projects = new HTable(configuration, "projects");
			get = new Get(projectID.getBytes());
			get.addColumn("identification".getBytes(), "name".getBytes());
			result = projects.get(get);
			projects.close();
			
			String projectName = new String(result.getValue("identification".getBytes(), "name".getBytes()));
			
			// compose the key 
			String key = employeeID;
			key += ", " +  firstname + ", " + lastname ;	
			key += ", " + projectName + ", " + projectSeverity;
			
			// check if current employee has solved the defect
			String hasSolved = "";
			if (employeeID.equals(defectEmployeeId))
				hasSolved = "1";
			else
				hasSolved = "0";
			
			// write the key and '0/1'(defect not solved or solved) - '1'(one defect) as the value
			context.write(new Text(key), new Text(hasSolved + " " + "1"));
	}
}
   
   