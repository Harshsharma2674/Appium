package utils;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;

public class ExcelUtils {

    public static Object[][] getLoginData() throws Exception {

        FileInputStream fis =
                new FileInputStream("src/test/resources/loginData.xlsx");

        Workbook workbook = WorkbookFactory.create(fis);

        Sheet sheet = workbook.getSheet("loginData");

        int rowCount = sheet.getLastRowNum();

        Object[][] data = new Object[rowCount][2];

        for (int i = 1; i <= rowCount; i++) {

            data[i - 1][0] =
                    sheet.getRow(i).getCell(0).toString();

            data[i - 1][1] =
                    sheet.getRow(i).getCell(1).toString();
        }

        workbook.close();
        fis.close();

        return data;
    }
}