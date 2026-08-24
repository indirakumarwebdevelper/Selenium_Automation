package TestData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.apache.poi.ss.usermodel.Row;

public class LoginData {

	
	/*@DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {

        String filePath = "src/test/resources/Logincredentails.xlsx";

        FileInputStream file = new FileInputStream(filePath);

        XSSFWorkbook workbook = new XSSFWorkbook(file);

        XSSFSheet sheet = workbook.getSheet("Credentials");

        int rowCount = sheet.getLastRowNum();

        Object[][] data = new Object[rowCount][2];

        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i <= rowCount; i++) {

            data[i - 1][0] =
                    formatter.formatCellValue(sheet.getRow(i).getCell(0));

            data[i - 1][1] =
                    formatter.formatCellValue(sheet.getRow(i).getCell(1));
        }

        workbook.close();
        file.close();

        return data;
    }*/
	
	

/*@DataProvider(name = "loginData")
public Object[][] loginData() throws IOException {

    String filePath = "src/test/resources/Logincredentails.xlsx";

    FileInputStream file = new FileInputStream(filePath);
    XSSFWorkbook workbook = new XSSFWorkbook(file);

    XSSFSheet sheet = workbook.getSheet("Credentials");

    DataFormatter formatter = new DataFormatter();

    List<Object[]> loginData = new ArrayList<>();

    for (int i = 1; i <= sheet.getLastRowNum(); i++) {

        Row row = sheet.getRow(i);

        // Skip completely empty row
        if (row == null) {
            continue;
        }

        String username = formatter.formatCellValue(row.getCell(0)).trim();
        String password = formatter.formatCellValue(row.getCell(1)).trim();

        // Skip row if username OR password is empty
        if (username.isEmpty() || password.isEmpty()) {
            continue;
        }

        loginData.add(new Object[] { username, password });
    }

    workbook.close();
    file.close();

    return loginData.toArray(new Object[0][0]);
}*/
	
	
	
	@DataProvider(name = "loginData")
	public Object[][] loginData() throws IOException {

	    String filePath = "src/test/resources/Logincredentails.xlsx";

	    FileInputStream file = new FileInputStream(filePath);

	    XSSFWorkbook workbook = new XSSFWorkbook(file);

	    XSSFSheet sheet = workbook.getSheet("Credentials");

	    DataFormatter formatter = new DataFormatter();

	    List<Object[]> loginData = new ArrayList<>();

	    for (int i = 1; i <= sheet.getLastRowNum(); i++) {

	        Row row = sheet.getRow(i);

	        // Skip completely empty row
	        if (row == null) {
	            continue;
	        }

	        String username = formatter.formatCellValue(row.getCell(0)).trim();
	        String password = formatter.formatCellValue(row.getCell(1)).trim();
	        String divisionName = formatter.formatCellValue(row.getCell(2)).trim();
	        String month = formatter.formatCellValue(row.getCell(3)).trim();

	        // Skip row if any required value is empty
	        if (username.isEmpty() || password.isEmpty() ||
	                divisionName.isEmpty() || month.isEmpty()) {
	            continue;
	        }

	        loginData.add(new Object[] {
	                username,
	                password,
	                divisionName,
	                month
	        });
	    }

	    workbook.close();
	    file.close();

	    return loginData.toArray(new Object[0][0]);
	}

}

