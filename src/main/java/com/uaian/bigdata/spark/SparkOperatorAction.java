package com.uaian.bigdata.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function2;
import org.sparkproject.guava.collect.Lists;
import scala.Tuple2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class SparkOperatorAction {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("sparkbase");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
        JavaPairRDD<String, String> rdd= sparkContext.parallelizePairs(Lists.newArrayList(
                new Tuple2<String, String>("a", "1"), new Tuple2<String, String>("b", "2"),
                new Tuple2<String, String>("a", "0"), new Tuple2<String, String>("b", "0"),
                new Tuple2<String, String>("c", "3"), new Tuple2<String, String>("d", "4")), 1);

        //行动算子
        //1. take() 收集元素
        List<Tuple2<String, String>> take = rdd.take(3); //收集前三个元素
        //2. count() 计数
        long count = rdd.count();
        //3. collect() 收集整个RDD的数据放到本地上，注意只有本地内存能放下整个数据集才能使用
        List<Tuple2<String, String>> collect = rdd.collect();
        //4. reduce() 操作两个 RDD 的元素类型的数据并返回一个同样类型的新元素
        //很方便地计算出 RDD 中所有元素的总和、元素的个数
        Tuple2<String, String> reduce = rdd.reduce(new Function2<Tuple2<String, String>, Tuple2<String, String>, Tuple2<String, String>>() {
            @Override
            public Tuple2<String, String> call(Tuple2<String, String> t1, Tuple2<String, String> t2) throws Exception {
                String key = t1._1 + "_" + t2._1;
                String val = t1._2 + "_" + t2._2;
                Tuple2<String, String> res = new Tuple2(key, val);
                return res;
            }
        });
        //5. fold() ，接收一个与 reduce() 接收的函数签名相同的函数，再加上一个“初始值”来作为每个分区第一次调用时的结果。
        //计算过程是，首先，在每个节点应用fold：初始值+节点内元素；
        //然后，各节点汇总的小计值再应用fold一次；
        //所以，最终结果是 初始值*(节点数目+1) + Rdd各元素求和
        JavaRDD<Integer> intrdd = sparkContext.parallelize(Arrays.asList(1,3,4,5,6,7,8,9,10,3), 5);
        Integer fold = intrdd.fold(1, new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer x, Integer y) throws Exception {
                return x + y;
            }
        });
        //6. aggregate()算子 fold() 和 reduce() 都要求函数的返回值类型需要和我们所操作的 RDD 中的元素类型相同
        //使用 aggregate() 时，需要提供我们期待返回的类型的初始值。
        //addAndCount：针对每个分区（节点）的操作函数
        //combine：在addAndCount对每个分区操作完成之后，将每个分区的结果进行整合，从而求出最后的结果
        Function2<AvgCount, Integer, AvgCount> addAndCount = new Function2<AvgCount, Integer, AvgCount>() {
            @Override
            public AvgCount call(AvgCount a, Integer x) throws Exception {
                a.total += x;
                a.num += 1;
                return a;
            }
        };
        Function2<AvgCount, AvgCount, AvgCount> combine = new Function2<AvgCount, AvgCount, AvgCount>() {
            @Override
            public AvgCount call(AvgCount a, AvgCount b) throws Exception {
                a.total += b.total;
                a.num += b.num;
                return a;
            }
        };
        AvgCount initial = new AvgCount(0,0);
        AvgCount result = intrdd.aggregate(initial, addAndCount, combine);
        System.out.println(result.avg());
        System.out.println(result.total);
        System.out.println(result.num);
        //7. top() top() 从 RDD 中获取前几个元素。top() 会使用数据的默认顺序，但我们也可以提供自己的比较函数，来提取前几个元素。
        intrdd.top(3);
        //8. takeSample(withReplacement, num, seed) 函数可以让我们从数据中获取一个采样，并指定是否替换。
        intrdd.takeSample(false, 3);
        //9. countByValue 各元素在RDD中的出现次数
        //10. takeOrdered 从RDD中按照提供的顺序返回N个

    }
}
//aggregate
class AvgCount implements Serializable{
    public int total;
    public int num;

    public AvgCount(int i, int i1) {
        this.total = i;
        this.num = i1;
    }

    public double avg() {
        return total / (double)num;
    }
}