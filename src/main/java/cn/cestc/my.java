package cn.cestc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class my {
    public static void main(String[] args) {
        // 1.求出所有年龄的总和
        Integer sumAge = Stream.of(
                        new Person("张三", 18)
                        , new Person("李四", 22)
                        , new Person("张三", 13)
                        , new Person("王五", 15)
                        , new Person("张三", 19)
                ).map(Person::getAge) // 实现数据类型的转换
                .reduce(0, Integer::sum);
        System.out.println(sumAge);

        // 2.求出所有年龄中的最大值
        Integer maxAge = Stream.of(
                        new Person("张三", 18)
                        , new Person("李四", 22)
                        , new Person("张三", 13)
                        , new Person("王五", 15)
                        , new Person("张三", 19)
                ).map(Person::getAge) // 实现数据类型的转换，符合reduce对数据的要求
                .reduce(0, Math::max); // reduce实现数据的处理
        System.out.println(maxAge);

        // 3.统计 字符 a 出现的次数
        Integer count = Stream.of("a", "b", "c", "d", "a", "c", "a")
                .map(ch -> "a".equals(ch) ? 1 : 0)
                .reduce(0, Integer::sum);
        System.out.println(count);
    }

    }
class Human{
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Person{
    private String name;
    private Integer age;
}


class Father extends Human{
}
class Son extends Father{
}
class LeiFeng extends Father {
}
