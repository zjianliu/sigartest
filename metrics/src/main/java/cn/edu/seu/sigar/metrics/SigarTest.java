package cn.edu.seu.sigar.metrics;

import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import java.io.IOException;
import cn.edu.seu.sigar.common.utils.SigarUtil;

/**
 * Created by 11363 on 5/30/2017.
 */
public class SigarTest {
    public static void main(String[] args)throws IOException{
        Sigar sigar = SigarUtil.getSigar();
        try{
            Mem mem = sigar.getMem();
            System.out.println("已使用内存为：" + mem.getUsedPercent() + "%");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
