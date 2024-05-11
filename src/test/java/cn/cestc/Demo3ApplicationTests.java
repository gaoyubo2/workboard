package cn.cestc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@SpringBootTest
class Demo3ApplicationTests {

    @Test
    public void test(){
        try {
            // 定义PowerShell命令
            String command = "powershell.exe -Command \"cd D:\\EasySpider_0.6.0_windows_x64\\EasySpider_windows_x64\\ ; .\\EasySpider\\resources\\app\\chrome_win64\\easyspider_executestage.exe --ids [15] --user_data 1 --server_address http://localhost:8074 --config_folder \\\"D:\\EasySpider_0.6.0_windows_x64\\EasySpider_windows_x64\\\" --headless 0 --read_type remote --config_file_name config.json --saved_file_name\"";

            // 启动进程并执行命令
            Process process = Runtime.getRuntime().exec(command);

            // 可以读取命令输出

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }


            // 等待命令执行完毕
            int exitCode = process.waitFor();
            //等待执行10s
            System.out.println("PowerShell命令执行完毕，退出码：" + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
    
}
