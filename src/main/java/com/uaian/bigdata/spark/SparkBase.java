package com.uaian.bigdata.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.DoubleFlatMapFunction;
import org.apache.spark.api.java.function.DoubleFunction;
import org.apache.spark.api.java.function.PairFlatMapFunction;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

public class SparkBase {
    public static void main(String[] args) {
        //初始化SparkContext
        //●集群 URL：告诉 Spark 如何连接到集群上。在这几个例子中我们使用的是 local，这个特殊值可以让 Spark 运行在单机单线程上而无需连接到集群。
        //●应用名：例子在中我们使用的是 sparkbase。当连接到一个集群时，这个值可以帮助你在集群管理器的用户界面中找到你的应用。
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("sparkbase");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        //创建RDD
        //1. parallelize方法直接创建
        String[] strs = new String[]{"pandas", "numpy"};
        JavaRDD<String> parallelize = sparkContext.parallelize(Arrays.asList(strs));
        //2. 外部读取创建：textFile方法
        String inputfile = "C:\\Users\\xukainan\\Desktop\\test.txt";
        JavaRDD<String> stringJavaRDD = sparkContext.textFile(inputfile);
        //3. JDBC
        //4. PairRDD



        //RDD之间的转换 Java中有两个特殊的RDD  JavaDoubleRDD 和 JavaPairRDD
        JavaRDD<Integer> intrdd = sparkContext.parallelize(Arrays.asList(1,3,4,5,6,7,8,9,10,3), 5);
        intrdd.mapToDouble(new DoubleFunction<Integer>() {
            @Override
            public double call(Integer integer) throws Exception {
                return integer;
            }
        });
        intrdd.mapToPair(new PairFunction<Integer, Object, Object>() {
            @Override
            public Tuple2<Object, Object> call(Integer integer) throws Exception {
                return new Tuple2<>(integer, 1);
            }
        });
        intrdd.flatMapToDouble(new DoubleFlatMapFunction<Integer>() {
            @Override
            public Iterator<Double> call(Integer s) throws Exception {
                return null;
            }
        });
        intrdd.flatMapToPair(new PairFlatMapFunction<Integer, Object, Object>() {
            @Override
            public Iterator<Tuple2<Object, Object>> call(Integer s) throws Exception {
                return null;
            }
        });
        //关闭Spark
        sparkContext.close();
    }
}
