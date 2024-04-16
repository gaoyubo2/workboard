package cn.cestc.handler.impl;

import cn.cestc.handler.FileHandler;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.IOException;
import java.io.InputStream;

public class WordFileHandler implements FileHandler {
    @Override
    public String handleFile(InputStream inputStream) throws IOException {
        XWPFDocument doc = new XWPFDocument(inputStream);
        XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
        return extractor.getText();
    }
}
