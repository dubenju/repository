package example;
import java.io.File;
import java.io.StringWriter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
public class GetPdfText {
    public static void main(String args[]) {
        try {

            String pdfFile = "./vedio/test3.pdf";
            PDDocument document = PDDocument.load(new File(pdfFile));
            StringWriter output = new StringWriter();
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(1);
            stripper.setEndPage(1);
            stripper.setSortByPosition(false);
            stripper.setShouldSeparateByBeads(true);
            stripper.writeText(document, output);
            String content = output.toString();
            System.out.println("---------出力開始-------------");
            System.out.println(content);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
