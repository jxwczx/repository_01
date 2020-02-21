package wtp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

/**
 * ����aspose���߽�wordתpdf������ѧϰʹ�� �����á�
 * 
 * */
public class WtpTest {
	 /**
     * ��ȡlicense
     * 
     */
    public static boolean getLicense() {
        boolean result = false;
        try {
            InputStream is = WtpTest.class.getClassLoader().getResourceAsStream("license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * ֧��DOC, DOCX, OOXML, RTF, HTML, OpenDocument, PDF, EPUB, XPS, SWF���໥ת��<br>
     * 
     */
    public static void main(String[] args) {
        // ��֤License
        if (!getLicense()) {
            return;
        }
        try {
            long old = System.currentTimeMillis();
            Document doc = new Document("E:\\wtp\\A.docx");// ԭʼword·��
            File pdfFile = new File("E:\\\\wtp\\\\A.pdf");// ���·��
            FileOutputStream fileOS = new FileOutputStream(pdfFile);
            doc.save(fileOS, SaveFormat.PDF);
            long now = System.currentTimeMillis();
            System.out.println("����ʱ��" + ((now - old) / 1000.0) + "��");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
