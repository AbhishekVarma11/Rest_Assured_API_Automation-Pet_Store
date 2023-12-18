package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
@DataProvider(name="Data")
public String[][] getALLData() throws IOException{
	String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";
	XLUtility xl=new XLUtility(path);
	int rownum=xl.getRowCount("Sheet1");
	int colnum=xl.getCellCount("Sheet1",1);
	String apidata[][]=new String[rownum][colnum];
	for(int i=1;i<=rownum;i++)
	{
		for(int j=0;j<colnum;j++)
		{
			apidata[i-1][j]=xl.getCellData("Sheet1",i,j);
		}
	}
	return apidata;
}

@DataProvider(name="UserNames")
public String[] getUserNames() throws IOException
{
	String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";
	XLUtility xl=new XLUtility(path);

	int rownum=xl.getRowCount("Sheet1");	
		
	String apidata[]=new String[rownum];
	
	for(int i=1;i<=rownum;i++)
	{		
		apidata[i-1]= xl.getCellData("Sheet1",i, 1);  
		
	}

	return apidata;
}
}
