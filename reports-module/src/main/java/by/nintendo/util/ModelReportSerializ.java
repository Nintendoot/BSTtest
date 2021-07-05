package by.nintendo.util;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class ModelReportSerializ {
    public void serializ(String departmentModel,String fileName) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, true);
            writer.write(departmentModel + System.lineSeparator());
            writer.flush();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }
}
