package example;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
public class TestPdfbox3 {
    public static void main(String args[]) {
        try {

            // ドキュメントオブジェクトの作成
            PDDocument document = new PDDocument();

            // ページオブジェクトの作成
            PDPage page = new PDPage();
            document.addPage(page);

            //文字出力処理
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            //フォント指定
            PDFont font = PDType1Font.TIMES_ITALIC;
            contentStream.setFont(font, 12);
            //出力位置指定
            contentStream.newLineAtOffset(0f, 0f);
            //出力文字列
            contentStream.showText( "HelloWorld" );
            contentStream.endText();
            contentStream.close();

            // ドキュメントを保存します
            document.save("./vedio/test2.pdf");
            document.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
