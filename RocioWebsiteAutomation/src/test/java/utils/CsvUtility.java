package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvUtility {

    public static Object[][] getCsvData(String filePath) {

        List<Object[]> dataList = new ArrayList<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String line;

            boolean isFirstLine = true;

            while((line = br.readLine()) != null) {

                if(isFirstLine) {

                    isFirstLine = false;

                    continue;
                }

                String[] data = line.split(",");

                dataList.add(data);
            }

            br.close();

        }

        catch(Exception e) {

            e.printStackTrace();
        }

        Object[][] dataArray = new Object[dataList.size()][];

        return dataList.toArray(dataArray);
    }
}