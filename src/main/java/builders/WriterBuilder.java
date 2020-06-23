package builders;

import model.UserInput;
import service.IdWriter;
import service.Writer;

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
