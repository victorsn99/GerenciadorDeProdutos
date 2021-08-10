package com.victor.pdfcreator;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.victor.view.SelectDir;

public class PDFGenerator {
	
	public static void gerarPDFencomendasTotais() {
		// criação do documento
        Document document = new Document();
        try {
            
            PdfWriter.getInstance(document, new FileOutputStream(SelectDir.dir + "\\RELATORIO_ENCOMENDAS-PDF.pdf"));
            document.open();
            
            // adicionando um parágrafo no documento
            document.add(new Paragraph("Gerando PDF - Java"));
            Desktop desk = Desktop.getDesktop();
            try {
				desk.open(new File(SelectDir.dir + "\\RELATORIO_ENCOMENDAS-PDF.pdf"));
			} catch (IOException b) {
				b.printStackTrace();
			}
            
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        document.close();
   }
	
	public static void gerarPDFencomendasFinalizadas() {
		// criação do documento
        Document document = new Document();
        try {
            
            PdfWriter.getInstance(document, new FileOutputStream(SelectDir.dir + "\\RELATORIO_ENCOMENDAS-PDF.pdf"));
            document.open();
            
            // adicionando um parágrafo no documento
            document.add(new Paragraph("Gerando PDF - Java"));
            Desktop desk = Desktop.getDesktop();
            try {
				desk.open(new File("D:\\Chamado.pdf"));
			} catch (IOException b) {
				b.printStackTrace();
			}
            
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        document.close();
   }

}
