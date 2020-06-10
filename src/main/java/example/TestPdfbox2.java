package example;
import java.io.File;

import org.apache.fontbox.ttf.TrueTypeCollection;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
public class TestPdfbox2 {
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
            File file = new File("C:/Windows/Fonts/msmincho.ttc");
            TrueTypeCollection collection = new TrueTypeCollection(file);
            PDFont font = PDType0Font.load(document, collection.getFontByName("MS-Mincho"), true);

            contentStream.setFont(font, 12);
            //出力位置指定
            contentStream.newLineAtOffset(0f, 755f);
            //出力文字列
            contentStream.showText( "こんにちは世界" );
            contentStream.endText();
            contentStream.close();

            // ドキュメントを保存します
            document.save("./vedio/test3.pdf");
            document.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
