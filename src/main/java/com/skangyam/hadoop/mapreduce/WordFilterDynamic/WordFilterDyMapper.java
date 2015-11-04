package com.skangyam.hadoop.mapreduce.WordFilterDynamic;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordFilterDyMapper extends Mapper<LongWritable, Text, Text, Text> 
{
    @Override
    protected void map(LongWritable key, Text value, Context context)
    		throws IOException, InterruptedException{
    	String[] str = value.toString().split(",");
    	String filter = context.getConfiguration().get("state");
    	
    	if (str[1].equalsIgnoreCase(filter)){
    		context.write(new Text(str[0]+", "+str[1]+", "+str[2]), new Text());
    	}
    }
}
