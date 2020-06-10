package example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class TestPdfbox {

    public static void main(String[] args) {
        try {

            PDDocument document = new PDDocument();

            PDPage page = new PDPage();
            document.addPage(page);

            document.save("./vedio/test.pdf");
            document.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
