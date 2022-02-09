package com.uaian.bigdata.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.sparkproject.guava.collect.Lists;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SparkOperatorConversion {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("sparkbase");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        JavaPairRDD<String, String> rdd= sparkContext.parallelizePairs(Lists.newArrayList(
                new Tuple2<String, String>("a", "1"), new Tuple2<String, String>("b", "2"),
                new Tuple2<String, String>("a", "0"), new Tuple2<String, String>("b", "0"),
                new Tuple2<String, String>("c", "3"), new Tuple2<String, String>("d", "4")), 1);
        //转化算子
        //1. filter() 过滤
        JavaPairRDD<String, String> filter = rdd.filter(new Function<Tuple2<String, String>, Boolean>() {
            @Override
            public Boolean call(Tuple2<String, String> stringStringTuple2) throws Exception {
                return "a".equals(stringStringTuple2._1);
            }
        });
        //1.1 具名类实现
        JavaPairRDD<String, String> filter_2 = rdd.filter(new ContainsWord("a"));
        //1.2 lambda实现
        JavaPairRDD<String, String> filter_3 = rdd.filter(item -> item._1.equals("a"));
        //2. union() 合并RDD 不会去重
        JavaPairRDD<String, String> union = rdd.union(filter);
        //3. map() 接收一个函数，把这个函数用于 RDD 中的每个元素
        //map() 的返回值类型不需要和输入类型一样
        JavaRDD<Object> map = rdd.map(new Function<Tuple2<String, String>, Object>() {
            @Override
            public Object call(Tuple2<String, String> stringStringTuple2) throws Exception {
                return stringStringTuple2._1 + "_map";
            }
        });
        //4. flatMap() 和 map() 类似，我们提供给 flatMap() 的函数被分别应用到了输入 RDD 的每个元素上。不过返回的不是一个元素，而是一个返回值序列的迭代器。输出的 RDD 倒不是由迭代器组成的。我们得到的是一个包含各个迭代器可访问的所有元素的 RDD。
        JavaRDD<String> flatMap = rdd.flatMap(new FlatMapFunction<Tuple2<String, String>, String>() {
            @Override
            public Iterator<String> call(Tuple2<String, String> stringStringTuple2) throws Exception {
                return Arrays.asList(stringStringTuple2._1, stringStringTuple2._2).iterator();
            }
        });
        // 5.intersection(other) 求两个RDD中的交集 ,会去重（单个RDD里重复的）
        JavaPairRDD<String, String> intersection = rdd.intersection(filter);
        // 6. subtrcct(other) 返回一个由只存在于第一个 RDD 中而不存在于第二个 RDD 中的所有元素组成的 RDD
        JavaPairRDD<String, String> subtract = rdd.subtract(filter);
        // 7. distinct 去重 不过需要注意，distinct() 操作的开销很大，因为它需要将所有数据通过网络进行混洗（shuffle），以确保每个元素都只有一份
        JavaPairRDD<String, String> distinct = rdd.distinct();
        // 8. certesian(other) 笛卡尔积
        JavaPairRDD<Tuple2<String, String>, Tuple2<String, String>> cartesian = rdd.cartesian(filter);
        // 9.sample 对RDD进行采样，以及是否替换
        JavaPairRDD<String, String> sample = rdd.sample(false, 0.5);

        sample.foreach(item -> System.out.println(item));



    }
}

class ContainsWord implements Function<Tuple2<String, String>, Boolean>{
    
    private String word;

    public ContainsWord(String word) {
        this.word = word;
    }

    @Override
    public Boolean call(Tuple2<String, String> stringStringTuple2) throws Exception {
        return word.equals(stringStringTuple2._1);
    }
}
