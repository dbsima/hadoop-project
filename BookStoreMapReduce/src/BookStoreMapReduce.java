import java.io.IOException;
import general.Constants;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class BookStoreMapReduce {
	/*
	 * 
	 */

	public static void main(String[] args) throws Exception {
		Configuration configuration = HBaseConfiguration.create();
		configuration.set(Constants.KEY_VALUE_SEPARATOR_PROPERTY_NAME, Constants.KEY_VALUE_SEPARATOR_VALUE);
		
		Job report1 = Job.getInstance(configuration, "report1Job");
		report1.setJarByClass(BookStoreMapReduce.class);
		Scan scan = new Scan();
		scan.setCaching(Constants.CACHING_VALUE);
		scan.setCacheBlocks(Constants.CACHE_BLOCKS_VALUE);
		TableMapReduceUtil.initTableMapperJob(
			"contributions",
			scan,
			Report1Mapper.class,
			Text.class,
			Text.class,
			report1
		);
		report1.setReducerClass(Report1Reducer.class);
		report1.setNumReduceTasks(Constants.REDUCE_TASKS_NUMBER);
		FileOutputFormat.setOutputPath(report1, new Path(Constants.REPORT1_OUTPUT_PATH));
		boolean report1Result = report1.waitForCompletion(true);
		if (!report1Result) {
			throw new IOException(Constants.REPORT1_EXCEPTION_MESSAGE);
		}

		Job report2= Job.getInstance(configuration, "report2Job");
		report2.setJarByClass(BookStoreMapReduce.class);
		
		report2.setMapperClass(Report2Mapper.class);
		report2.setReducerClass(Report2Reducer.class);
		report2.setNumReduceTasks(Constants.REDUCE_TASKS_NUMBER);
		report2.setInputFormatClass(utilities.CustomFileInputFormat.class);
		report2.setOutputFormatClass(TextOutputFormat.class);
		report2.setOutputKeyClass(Text.class);
		report2.setOutputValueClass(Text.class);

		FileInputFormat.setInputPaths(report2, new Path(Constants.REPORT1_OUTPUT_PATH));
		FileOutputFormat.setOutputPath(report2, new Path(Constants.REPORT2_OUTPUT_PATH));

		boolean report2Result = report2.waitForCompletion(true);
		if (!report2Result) {
			throw new IOException(Constants.REPORT2_EXCEPTION_MESSAGE);
		}

	}
}