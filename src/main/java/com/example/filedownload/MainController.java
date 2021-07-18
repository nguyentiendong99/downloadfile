package com.example.filedownload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private DocumentRepository repository;

    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }
    @GetMapping("/download_file")
    public void downLoadFile(HttpServletResponse response) throws IOException {
        File file = new File("files\\16_TEL1415_THONG TIN DI DONG_NGUYEN TIEN DONG_05.pdf");

        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + file.getName();
        response.setHeader(headerKey , headerValue);
        ServletOutputStream outputStream = response.getOutputStream();
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        byte[] buffer = new byte[8192];
        int bytesRead = -1;
        while((bytesRead = inputStream.read(buffer)) != -1 ){
            outputStream.write(buffer , 0 , bytesRead);
        }
        inputStream.close();
        outputStream.close();
    }
    @GetMapping("/download_document")
    public void downloadDocuments(HttpServletResponse response) throws IOException {
        Integer id = 1;
        Documents documents = repository.findById(id).get();
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + documents.getName();
        response.setHeader(headerKey , headerValue);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(documents.getContent());
    }
    @GetMapping("/download_csv")
    public void downLoadCSV(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=customers.csv";
        response.setHeader(headerKey , headerValue);
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1 , "Dong Nguyen" , "dong@gmail.com" ,"Ha Noi"));
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter() , CsvPreference.STANDARD_PREFERENCE);
        String[] header = {"ID" , "Customer Name " , "Email" , "Country"};

        String[] filedMapping = {"id" , "name" , "email" , "country"};
        csvWriter.writeHeader(header);
        for (Customer customer : customers){
            csvWriter.write(customer , filedMapping);
        }
        csvWriter.close();
    }
}
