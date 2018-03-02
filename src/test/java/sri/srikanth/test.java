package sri.srikanth;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class test {
	
	HSSFWorkbook w;
	HSSFSheet s;
	HSSFCell c;
	HSSFRow r ;
	
	

	public void m() throws IOException {
		
		String xx ="C:\\Users\\sRikanth\\Desktop\\aaaa.xls";
		
	FileInputStream fs = new FileInputStream(xx);	
		
	w=new HSSFWorkbook(fs);
	s=w.getSheetAt(0);
	
	for(int i =0;i<2;i++) {
	if(s.getRow(i) != null) {
		r=s.getRow(i);
		System.out.println(" get");
	}else {
		
		r=s.createRow(i);
		System.out.println(" create");
	}
	
	if(r.getCell(1)!=null) {
		r.getCell(1).setCellValue("sri");
		
	}else {
		r.createCell(1).setCellValue("sri");
	}
	
	}
	

	
	
	FileOutputStream f = new FileOutputStream(xx) ;
		w.write(f);
		//w.close();
		f.close();
		
	}
		

	
	public static void main(String arg[]) throws IOException {
		
		test t = new test();
		t.m();
	}
	
	
}
