package com.CsvWriter.builders;


import com.CsvWriter.model.UserInput;
import com.CsvWriter.service.IdWriter;
import com.CsvWriter.service.Writer;

public class WriterBuilder {

    private UserInput userInput;

    public WriterBuilder(UserInput userInput) {
        this.userInput = userInput;
    }

    public WriterBuilder() {}

    public Writer getWriter() {
        return new IdWriter(userInput.getDelimiter()
                            , userInput.getNewHeaderName()
                            , userInput.getNewHeaderPosition()
                            , userInput.getOrigFileLocation()
                            , userInput.getNewFileLocation());
    }

}
