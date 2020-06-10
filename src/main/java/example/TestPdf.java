package example;
import org.docx4j.Docx4J;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TestPdf {

    public static void main(String[] args) throws Exception{
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(TestPdf.class.getResource(""));
        System.out.println(TestPdf.class.getResource("/"));
        System.out.println(TestPdf.class.getClassLoader().getResource("./ftl/resume.ftl"));

        Map<String, Object> map = new HashMap<>(3);
        map.put("name", "小明");
        map.put("address", "北京市朝阳区");
        map.put("email", "xiaoming@abc.com");
        String ftlName = TestPdf.class.getClassLoader().getResource("./ftl/resume.ftl").getPath();
        ftlName = "resume.ftl";
        String outputFilePath = "./vedio/简历.pdf";
        FileOutputStream os = new FileOutputStream(outputFilePath);
        Docx4JUtil.process(ftlName, map, os);

    }   
}
