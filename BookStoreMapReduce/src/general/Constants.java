package general;

public interface Constants {
	final static String		FILE									= "fisier";
	final static String		TABLE									= "tabela";
	final static String		RESULTS_DESTINATION						= FILE; // available options: FILE, TABLE
	
	final static int 		REDUCE_TASKS_NUMBER						= 1;
	
	final static int		CACHING_VALUE							= 500;
	final static boolean	CACHE_BLOCKS_VALUE						= false;
	
	final static String		KEY_VALUE_SEPARATOR_PROPERTY_NAME		= "mapreduce.input.keyvaluelinerecordreader.key.value.separator";
	final static String 	KEY_VALUE_SEPARATOR_VALUE				= "\t";
	
	final static String 	REPORT1_OUTPUT_PATH						= "hdfs://localhost:9000/user/aii2013/report1";
	final static String 	REPORT1_EXCEPTION_MESSAGE				= "Error running report1 job!";
	
	final static String 	REPORT2_OUTPUT_PATH						= "hdfs://localhost:9000/user/aii2013/report2";
	final static String 	REPORT2_EXCEPTION_MESSAGE				= "Error running report2 job!";
}
