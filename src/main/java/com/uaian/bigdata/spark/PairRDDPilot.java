package com.uaian.bigdata.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.sparkproject.guava.collect.Lists;
import scala.Tuple2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PairRDDPilot {
    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setAppName("PairRDD").setMaster("local");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);
        
        //创建 1. parallelizePairs
        JavaPairRDD<String, String> rdd= sparkContext.parallelizePairs(Lists.newArrayList(
                new Tuple2<String, String>("a", "1"), new Tuple2<String, String>("b", "2"),
                new Tuple2<String, String>("a", "0"), new Tuple2<String, String>("b", "0"),
                new Tuple2<String, String>("c", "3"), new Tuple2<String, String>("d", "4")), 1);
        //2. map()
        JavaRDD<Integer> intrdd = sparkContext.parallelize(Arrays.asList(1,3,4,5,6,7,8,9,10,3), 1);
        JavaPairRDD<String, Integer> mapToPair = intrdd.mapToPair(new PairFunction<Integer, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(Integer integer) throws Exception {
                return new Tuple2<>(String.valueOf(integer), integer);
            }
        });

        //3.PairRDD的转换操作
        //reduceByKey 合并具有相同键的值
        JavaPairRDD<String, String> reduceByKey = rdd.reduceByKey(new Function2<String, String, String>() {
            @Override
            public String call(String s, String s2) throws Exception {
                return s + s2;
            }
        });
        //groupByKey 对具有相同键进行分组
        //groupBy() 可以用于未成对的数据上，也可以根据除键相同以外的条件进行分组。它可以接收一个函数，对源 RDD 中的每个元素使用该函数，将返回结果作为键再进行分组。
        //cogroup() 对多个共享同一个键的 RDD 进行分组。对两个键的类型均为 K 而值的类型分别为 V 和 W 的 RDD 进行 cogroup() 时，得到的结果 RDD 类型为 [(K, (Iterable[V], Iterable[W]))]。
        // 如果其中的一个 RDD 对于另一个 RDD 中存在的某个键没有对应的记录，那么对应的迭代器则为空。cogroup() 提供了为多个 RDD 进行数据分组的方法。
        // cogroup不仅可以用于实现连接操作，还可以用来求键的交集。除此之外，cogroup() 还能同时应用于三个及以上的 RDD。
        JavaPairRDD<String, Iterable<String>> groupByKey = rdd.groupByKey();
        //foldByKey 类似于fold
        //combineByKey 使用不同的返回类型合并相同键的值，是最为常用的基于键进行聚合的函数。大多数基于键聚合的函数都是用它实现的。
        JavaPairRDD<String, Integer> integerJavaPairRDD= sparkContext.parallelizePairs(Lists.newArrayList(
                new Tuple2<String, Integer>("a", 1), new Tuple2<String, Integer>("b", 2),
                new Tuple2<String, Integer>("a", 0), new Tuple2<String, Integer>("b", 0),
                new Tuple2<String, Integer>("c", 3), new Tuple2<String, Integer>("d", 4)), 1);
        Function<Integer, AvgCount2> createAcc = new Function<Integer, AvgCount2>() {
            public AvgCount2 call(Integer x) {
                return new AvgCount2(x, 1);
            }
        };
        Function2<AvgCount2, Integer, AvgCount2> addAndCount = new Function2<AvgCount2, Integer, AvgCount2>() {
            public AvgCount2 call(AvgCount2 a, Integer x) {
                a.total_ += x;
                a.num_ += 1;
                return a;
            }
        };
        Function2<AvgCount2, AvgCount2, AvgCount2> combine = new Function2<AvgCount2, AvgCount2, AvgCount2>() {
            public AvgCount2 call(AvgCount2 a, AvgCount2 b) {
                a.total_ += b.total_;
                a.num_ += b.num_;
                return a;
            }
        };
        AvgCount2 initial = new AvgCount2(0,0);
        JavaPairRDD<String, AvgCount2> avgCounts = integerJavaPairRDD.combineByKey(createAcc, addAndCount, combine);
        Map<String, AvgCount2> countMap = avgCounts.collectAsMap();
        for (Map.Entry<String, AvgCount2> entry:countMap.entrySet()) {
            System.out.println(entry.getKey() + "：" + entry.getValue().avg());
        }
        //mapValues 对RDD的每个值应用函数而不改变键的值
        JavaPairRDD<String, Object> mapValues = rdd.mapValues(new Function<String, Object>() {
            @Override
            public Object call(String s) throws Exception {
                return s + "_";
            }
        });
        //keys 返回keys
        //values 返回values
        //sortByKey 返回排序的RDD
        //flatMapValues 对RDD里的每个值返回迭代器
        JavaPairRDD<String, String> flatMapValues = rdd.flatMapValues(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                List<String> list = Arrays.asList(s.split(" "));
                return list.iterator();
            }
        });

        //4. 针对两个PairRDD的转换操作
        //subtractByKey 删除rdd中与mapToPair键相同的元素
        JavaPairRDD<String, String> subtractByKey = rdd.subtractByKey(mapToPair);
        //join 内连接
        JavaPairRDD<String, Tuple2<String, Integer>> join = rdd.join(mapToPair);
        //rightOuterJoin 右外连接
        //leftOuterJoin 左外连接
        //mapToPair 将两个RDD中相同键分组
        JavaPairRDD<String, Tuple2<Iterable<String>, Iterable<Integer>>> cogroup = rdd.cogroup(mapToPair);


        //行动操作：
        //countByKey 对每个键对应的元素分别计数
        //collectAsMap() 将结果以映射表的形式返回，以便查询
        //lookup(key) 返回指定键对应的所有值





        //spark并行度 在执行聚合或分组操作时，可以要求 Spark 使用给定的分区数。Spark 始终尝试根据集群的大小推断出一个有意义的默认值，但是有时候你可能要对并行度进行调优来获取更好的性能表现。
        //本章讨论的大多数操作符都能接收第二个参数，这个参数用来指定分组结果或聚合结果的RDD 的分区数 如
        rdd.reduceByKey((String s, String s2) -> {return s + s2; }, 2);
        //有时，我们希望在除分组操作和聚合操作之外的操作中也能改变 RDD 的分区。对于这样的情况，Spark 提供了 repartition() 函数 。
        //它会把数据通过网络进行混洗，并创建出新的分区集合。切记，对数据进行重新分区是代价相对比较大的操作。Spark 中也有一个优化版的 repartition()，叫作 coalesce()。
        // 你可以使用 Java 或 Scala 中的 rdd.partitions.size() 以及 Python 中的 rdd.getNumPartitions 查看 RDD 的分区数，并确保调用 coalesce() 时将 RDD 合并到比现在的分区数更少的分区中。

        //partitionBy(new HashPartitioner(100)) 转换为Hash分区 100跟集群的总核心数一致
        //partitionBy() 是一个转化操作，因此它的返回值总是一个新的 RDD，但它不会改变原来的 RDD。RDD 一旦创建就无法修改。因此应该对 partitionBy() 的结果进行持久化

        //sortByKey() 和 groupByKey() 会分别生成范围分区的 RDD 和哈希分区的 RDD。而另一方面，诸如 map() 这样的操作会导致新的 RDD 失去父 RDD 的分区信息，因为这样的操作理论上可能会修改每条记录的键。

        rdd.partitioner(); //获取RDD的分区方式


        mapValues.foreach(item -> System.out.println(item));

    }
}
class AvgCount2 implements Serializable {
    public int total_;
    public int num_;
    public AvgCount2(int total, int num) {total_ = total;num_ = num; }
    public float avg() {return total_/(float)num_; }
}