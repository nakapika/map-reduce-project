package com.mapreduce.ajay;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class RecordCount extends Configured implements Tool {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		int exitValue = ToolRunner.run(new RecordCount(), args);
		System.out.println("Exiting now with "+exitValue);
		System.exit(exitValue);
	}

	public int run(String[] arg0) throws Exception {
		// TODO Auto-generated method stub
		
		Job job = Job.getInstance(getConf());
		
		job.setMapperClass(RecordMapper.class);
/*		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);*/
		
		job.setReducerClass(RecordReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		
		job.setNumReduceTasks(1);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.setInputPaths(job, new Path("movies.txt"));
		FileOutputFormat.setOutputPath(job, new Path("output"));
		
		return job.waitForCompletion(true) ? 0 : 1;
	}

}
