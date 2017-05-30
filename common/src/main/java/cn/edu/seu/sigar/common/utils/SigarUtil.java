package cn.edu.seu.sigar.common.utils;

import cn.edu.seu.sigar.common.utils.OsCheck;
import org.hyperic.sigar.Sigar;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Created by 11363 on 5/30/2017.
 */
public class SigarUtil {
    public static Sigar getSigar() throws ZipException,IOException{
        String sigarFolderName = "common/src/main/resources/sigar_lib";
        File sigarFolder = new File(sigarFolderName);
        if(!sigarFolder.exists()){
            sigarFolder.mkdir();

            File file = new File("common/src/main/resources/sigar_lib.zip");
            ZipFile sigarZip = new ZipFile(file);

            ZipInputStream zis = new ZipInputStream(new FileInputStream(file));

            ZipEntry entry;
            while((entry =zis.getNextEntry()) != null){
                System.out.println("decompress file :" + entry.getName());
                File outFile = new File("common/src/main/resources/sigar_lib/" + entry.getName());
                BufferedInputStream bis = new BufferedInputStream(sigarZip.getInputStream(entry));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(outFile));

                byte[] buffer = new byte[1024];
                while(true){
                    int len = bis.read(buffer);
                    if(len == -1)
                        break;
                    bos.write(buffer,0,len);
                }
                bis.close();
                bos.close();
            }
            zis.close();
        }

        String seperator = null;
        if (OsCheck.getOperatingSystemType() == OsCheck.OSType.Windows)
            seperator = ";";
        else
            seperator = ":";
        String path = System.getProperty("java.library.path") + seperator + sigarFolder.getCanonicalPath();
        System.setProperty("java.library.path",path);

        return new Sigar();
    }
}
