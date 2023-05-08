package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Printer {
    public Printer() {}

    public void printComponentToPdf(String text) throws DocumentException {
        Document document = new Document();
        try {
            String directoryPath = "src/main/resources/results/";
            File directory = new File(directoryPath);
            int fileCount = 0;
            
            try {
                File[] files = directory.listFiles();
                for (File file : files) {
                    if (file.isFile()) {
                        fileCount++;
                    }
                }
            } catch (Exception e) {

            }

            PdfWriter.getInstance(document, new FileOutputStream(directoryPath + Integer.toString(fileCount+1) + ".pdf"));
            document.open();
            document.add(new Paragraph(text));
            document.close();
            Thread.sleep(10000);
            System.out.println("PDF file created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Printer a = new Printer();
        try {
            a.printComponentToPdf("tes\n        halo");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}