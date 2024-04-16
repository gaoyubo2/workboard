package cn.cestc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Function;
import java.util.function.IntFunction;


@SpringBootTest
class Demo3ApplicationTests {

    @Test
    public void test(){
        IntFunction<String> fun = (num)->{
            return "Hello"+num;
        };
        String apply = fun.apply(2);
        System.out.println(apply);

    }
    
}
