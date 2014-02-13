import java.io.IOException;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.conf.Configuration;

public class Report2Mapper extends Mapper<Text, Text, Text, Text>  {
	/*
	 * (non-Javadoc)
	 * @see org.apache.hadoop.mapreduce.Mapper#map(KEYIN, VALUEIN, org.apache.hadoop.mapreduce.Mapper.Context)
	 * 
	 * for a particular row from the first report find the 
	 */

	public void map(Text userDetails, Text contribDetails, Context context)
			throws IOException, InterruptedException {
			
			String employeeID = userDetails.toString().split(", ")[0];
			
			Configuration configuration = HBaseConfiguration.create();
			
			// get the employeer's department name based on his id
			HTable angajati = new HTable(configuration, "employees");
			Get get = new Get(employeeID.getBytes());
			get.addColumn("department".getBytes(), "name".getBytes());
			Result res = angajati.get(get);
			angajati.close();
			
			String departmentName = new String(res.getValue("department".getBytes(), "name".getBytes()));

			// compute the key
			String key = userDetails.toString().split(", ")[1] + " " + userDetails.toString().split(", ")[2];
			key += ", " + departmentName + " = ";
			
			// compute the value as the total number of projects and defects for the current user
			String value = userDetails.toString().split(", ")[3] + ", " + contribDetails.toString().split("/")[1];
			
			// write the key and value
			context.write(new Text(key), new Text(value));
	}
}
   
   