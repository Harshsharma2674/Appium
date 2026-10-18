package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;

public class ExcelUtils {

    public static Object[][] getLoginData() throws Exception {

        //Step 1: Open the Excel file
        FileInputStream fis =
                new FileInputStream("src/test/resources/loginData.xlsx");

        Workbook workbook = WorkbookFactory.create(fis);

        //Step 2: Get the first sheet (index 0), so the sheet name does not matter
        Sheet sheet = workbook.getSheetAt(0);

        //Step 3: Count the rows. Row 0 is the header (Username | Password), so it is not counted
        int rowCount = sheet.getLastRowNum();

        Object[][] data = new Object[rowCount][2];

        //DataFormatter gives the cell value exactly as shown in Excel
        //Without it, the password 10203040 would become "1.020304E7"
        DataFormatter formatter = new DataFormatter();

        //Step 4: Read each row, starting from row 1
        for (int i = 1; i <= rowCount; i++) {

            Row row = sheet.getRow(i);

            String username = formatter.formatCellValue(row.getCell(0)); //Column A
            String password = formatter.formatCellValue(row.getCell(1)); //Column B

            data[i - 1][0] = username;
            data[i - 1][1] = password;
        }

        //Step 5: Close the file
        workbook.close();
        fis.close();

        return data;
    }
}
