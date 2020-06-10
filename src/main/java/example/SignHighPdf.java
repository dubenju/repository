package example;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.itextpdf.awt.AsianFontMapper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfStream;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.security.BouncyCastleDigest;
import com.itextpdf.text.pdf.security.DigestAlgorithms;
import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.ExternalSignature;
import com.itextpdf.text.pdf.security.MakeSignature;
import com.itextpdf.text.pdf.security.MakeSignature.CryptoStandard;
import com.itextpdf.text.pdf.security.PrivateKeySignature;

public class SignHighPdf {

    
    /**
     * @param password
     *            秘钥密码
     * @param keyStorePath
     *            秘钥文件路径
     * @param signPdfSrc
     *            签名的PDF文件
     * @param x
     * 
     * @param y
     * @return
     */
    public static byte[] sign(String password, String keyStorePath, String signPdfSrc, 
            float x, float y,
            String signText) {
        File signPdfSrcFile = new File(signPdfSrc);
        PdfReader reader = null;
        ByteArrayOutputStream signPDFData = null;
        PdfStamper stp = null;
        FileInputStream fos = null;
        try {
            BouncyCastleProvider provider = new BouncyCastleProvider();
            Security.addProvider(provider);
            KeyStore ks = KeyStore.getInstance("PKCS12", new BouncyCastleProvider());
            fos = new FileInputStream(keyStorePath);
            ks.load(fos, password.toCharArray()); // 私钥密码
            String alias = (String) ks.aliases().nextElement();
            PrivateKey key = (PrivateKey) ks.getKey(alias, password.toCharArray());
            Certificate[] chain = ks.getCertificateChain(alias);
            reader = new PdfReader(signPdfSrc);
            signPDFData = new ByteArrayOutputStream();
            // 临时pdf文件
            File temp = new File(signPdfSrcFile.getParent(), System.currentTimeMillis() + ".pdf");
            stp = PdfStamper.createSignature(reader, signPDFData, '\0', temp, true);
            PdfSignatureAppearance sap = stp.getSignatureAppearance();
            sap.setReason("数字签名，不可改变");
            // 是对应x轴和y轴坐标
            sap.setVisibleSignature(new Rectangle(x, y, x + 150, y + 65), 1,
                    "sr"+String.valueOf(System.nanoTime()));
            /////////////////layer 0 Creating the appearance for layer 0
            PdfTemplate n0 = sap.getLayer(0);
            n0.reset();
            float lx = n0.getBoundingBox().getLeft();
            float by = n0.getBoundingBox().getBottom();
            float width = n0.getBoundingBox().getWidth();
            float height = n0.getBoundingBox().getHeight();
            n0.setRGBColorFill(255, 0, 0);
            n0.rectangle(lx, by, 5, height);
            n0.rectangle(lx, by, width, 5);
            n0.rectangle(lx, by+height-5, width, 5);
            n0.rectangle(lx+width-5, by, 5, height);
            n0.fill();
            ///////////////////////layer 2
            PdfTemplate n2 = sap.getLayer(2);
            n2.setCharacterSpacing(0.0f);
            ColumnText ct = new ColumnText(n2);
            ct.setSimpleColumn(n2.getBoundingBox());
            n2.setRGBColorFill(255, 0, 0);
            //做一个占位的动作
            Paragraph p1 = new Paragraph(" ");
//            BaseFont bf = BaseFont.createFont(
//                    // AsianFontMapper.ChineseSimplifiedFont, AsianFontMapper.ChineseSimplifiedEncoding_H, 
//                    AsianFontMapper.JapaneseFont_Go, AsianFontMapper.JapaneseEncoding_H ,
//                    BaseFont.NOT_EMBEDDED);
//            // 日文
//            BaseFont bf = BaseFont.createFont("HeiseiMin-W3",
//                "UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED);
            //3
            BaseFont bf = BaseFont.createFont("MS-PGothic", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            
            Font font1 = new Font(bf, 5, Font.BOLD, BaseColor.RED);
            Font font2 = new Font(bf, 13, Font.BOLD, BaseColor.RED);
            p1.setFont(font1);
            ct.addElement(p1);
            Paragraph p = new Paragraph(signText);
            p.setAlignment(Element.ALIGN_CENTER);
            p.setFont(font2);
            ct.addElement(p);
            ct.go();
            stp.getWriter().setCompressionLevel(PdfStream.BEST_COMPRESSION);
            ExternalDigest digest = new BouncyCastleDigest();
            ExternalSignature signature = new PrivateKeySignature(key, DigestAlgorithms.SHA512, provider.getName());
            MakeSignature.signDetached(sap, digest, signature, chain, null, null, null, 0, CryptoStandard.CADES);
            stp.close();
            reader.close();
            return signPDFData.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (signPDFData != null) {
                try {
                    signPDFData.close();
                } catch (IOException e) {
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }
 
    public static void main(String[] args) throws Exception {
        //对已经签章的signed.pdf文件再次签章，这次是高清签章
        byte[] fileData = sign("123456", "./vedio/keystore.p12",//
                "./vedio/signed.pdf", 350, 290, "华佗\n2017-12-20");
        FileOutputStream f = new FileOutputStream(new File("./vedio/signed2.pdf"));
        f.write(fileData);
        f.close();
    }

}
