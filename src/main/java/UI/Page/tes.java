package UI.Page;
import javax.swing.*;
import java.io.FileOutputStream;


import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
// import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
// import com.itextpdf.awt.PdfGraphics2D;

// import java.io.FileOutputStream;
import java.io.FileNotFoundException;


public class tes {
    public tes() {}

    public void printComponentToPdf(String fileName) throws DocumentException {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            document.add(new Paragraph("Hello, world!"));
            document.close();
            System.out.println("PDF file created successfully.");
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        tes a = new tes();
        try {
            a.printComponentToPdf("tes.pdf");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
