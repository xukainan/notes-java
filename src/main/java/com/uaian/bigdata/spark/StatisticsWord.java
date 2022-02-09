package com.uaian.bigdata.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

public class StatisticsWord {
    public static void main(String[] args) {

        String inputfile = "C:\\Users\\xukainan\\Desktop\\test.txt";
        String outputfile = "C:\\Users\\xukainan\\Desktop\\output";
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("sparkbase");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        //读入数据
        //也是惰性的，此时并没有读取数据，而是在必要时才会进行读取，也可能多次执行
        JavaRDD<String> input = sparkContext.textFile(inputfile);
        //切分为单词
        JavaRDD<String> words = input.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(s.split(" ")).iterator();
            }
        });
        //转化为键值对并计数
        JavaPairRDD<String, Integer> counts = words.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2(s, 1);
            }
        }).reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer x, Integer y) throws Exception {
                return x + y;
            }
        });
        //循环打印
        counts.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            @Override
            public void call(Tuple2<String, Integer> stringIntegerTuple2) throws Exception {
                System.out.println(stringIntegerTuple2._1 + "，数量是：" + stringIntegerTuple2._2);
            }
        });
        //coalesce(1,true)合并成一个文件输出，否则各个机器上的RDD partition分别输出
        //saveAsTextFile报错：(null) entry in command string: null chmod 0644
        //解决：下载hadoop.dll文件，拷贝到c:\windows\system32目录中即可
        //hadoop.dll可以在github上下载：https://github.com/4ttty/winutils
        counts.coalesce(1,true).saveAsTextFile(outputfile);



        sparkContext.close();
    }
}
