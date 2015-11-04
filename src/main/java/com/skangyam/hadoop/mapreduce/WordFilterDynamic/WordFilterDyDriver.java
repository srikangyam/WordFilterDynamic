package com.skangyam.hadoop.mapreduce.WordFilterDynamic;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordFilterDyDriver extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new WordFilterDyDriver(), args);
		System.exit(exitCode);

	}

	public int run(String[] args) throws Exception {
		
		if (args.length != 3) {
			System.out.printf("Usage: %s [generic options] "
					+ "<input dir> <output Directory> [State By default CA]\n",
					getClass().getSimpleName());
			return -1;
		}
		
		Job job = new Job(getConf());
		job.setJarByClass(WordFilterDyDriver.class);
		job.setJobName(this.getClass().getName());
		
		//This would set the dynamism of args[2] parameter
		job.getConfiguration().set("state", args[2]);
		
		job.setNumReduceTasks(0);
		
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(WordFilterDyMapper.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		while (job.waitForCompletion(true)){
			return 0;
		}
	
		return 0;
	}

}
