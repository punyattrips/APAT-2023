package org.academy.trainer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.academy.trainer.login.LoginPage;
import org.academy.trainer.webUI.Wvf_Trainer_UI;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Academy_Trainer extends LoginPage {

	public static By dashboard= By.xpath("//*[@title='Report Dashboard']");
	public static By batchInList= By.xpath("//*[@id='session-problem-wise-analysis']/div[3]//*[contains(@title,'"+Wvf_Trainer_UI.batchName.getText()+"')]");
	public static By candidateInList= By.xpath("//*[@id='heatgrid-report_wrapper']/div[2]/div/div/div[3]/div[2]/div/table/tbody/tr");
	public static By courseLink= By.linkText(Wvf_Trainer_UI.courseName.getText());
	public static void collectBatchData() throws InterruptedException, IOException {
		Thread.sleep(2000);
		driver.findElement(dashboard).click();
		System.out.println("Trainer dashboard icon clicked!");
		driver.findElement(courseLink).click();
		System.out.println("Course linked!");
		Thread.sleep(5000);
		element = driver.findElement(batchInList);
		actions.moveToElement(element).click().build().perform();
		System.out.println("Batch is selected!");
		Thread.sleep(5000);
		element = driver.findElement(candidateInList);
		actions.moveToElement(element).build().perform();
		System.out.println("Candidate list is selected");
		System.out.println("Candidate performance collection - Started!");
		List<WebElement> candidateList = driver.findElements(candidateInList);
		File file=new File("C:\\Academy\\trainer\\Batch\\export\\Performance_monitor_report.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sh=wb.createSheet();
		System.out.println("Number of candidates: "+ candidateList.size());
		for(int i=0;i<candidateList.size();i++)
		{
			List<WebElement> cell=candidateList.get(i).findElements(By.tagName("td"));        
			Row r=sh.createRow(0);
			r.createCell(0).setCellValue("Trainer_ID: ");
			r.createCell(1).setCellValue(Wvf_Trainer_UI.loginID.getText());
			r=sh.createRow(1);
			r.createCell(0).setCellValue("Batch_Name: ");
			r.createCell(1).setCellValue(Wvf_Trainer_UI.batchName.getText());
			r=sh.createRow(2);
			r.createCell(0).setCellValue("Course_Name: ");
			r.createCell(1).setCellValue(Wvf_Trainer_UI.courseName.getText());
			r=sh.createRow(3);
			r.createCell(0).setCellValue("SL.NO");
			r.createCell(1).setCellValue("NAME");
			r.createCell(2).setCellValue("USER_NAME");
			r.createCell(3).setCellValue("PERFORMANCE");
			r=sh.createRow(i+4);
			for(int j=0; j<4; j++)
			{
				String s=cell.get(j).getText();
				r.createCell(j).setCellValue(s.toString());
				try
				{
					FileOutputStream fos=new FileOutputStream(file);
					wb.write(fos);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		System.out.println("Candidate performance collection - Completed!");
		wb.close();
		driver.close();
	}
}