package wtp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

/**
 * 利用aspose工具将word转pdf；仅共学习使用 非商用。
 * 
 * */
public class WtpTest {
	 /**
     * 获取license
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

  //将PDF保存为word
    public static void saveAsWord(String targetFile,String newFile) {
        //调用去水印的方法 读取license.xml文件
        if (!getLicense()) {
            System.out.println("获取验证失败");
        }
        try {
            long old = System.currentTimeMillis();
            Document pdf = new Document(targetFile);// 原始word路径
            File doc = new File(newFile);// 输出路径
            FileOutputStream fileOS = new FileOutputStream(doc);
            pdf.save(fileOS, SaveFormat.DOCX);
            long now = System.currentTimeMillis();
            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 支持DOC, DOCX, OOXML, RTF, HTML, OpenDocument, PDF, EPUB, XPS, SWF等相互转换<br>
     * 
     */
    public static void main(String[] args) {
        // 验证License
//        if (!getLicense()) {
//            return;
//        }
//        try {
//            long old = System.currentTimeMillis();
//            Document doc = new Document("E:\\wtp\\A.docx");// 原始word路径
//            File pdfFile = new File("E:\\\\wtp\\\\A.pdf");// 输出路径
//            FileOutputStream fileOS = new FileOutputStream(pdfFile);
//            doc.save(fileOS, SaveFormat.PDF);
//            long now = System.currentTimeMillis();
//            System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
