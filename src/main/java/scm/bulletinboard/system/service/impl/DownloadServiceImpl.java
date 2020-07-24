package scm.bulletinboard.system.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gembox.spreadsheet.CellStyle;
import com.gembox.spreadsheet.ExcelFile;
import com.gembox.spreadsheet.ExcelFont;
import com.gembox.spreadsheet.ExcelWorksheet;
import com.gembox.spreadsheet.HorizontalAlignmentStyle;
import com.gembox.spreadsheet.LengthUnit;
import com.gembox.spreadsheet.SaveOptions;


import scm.bulletinboard.system.model.Post;
import scm.bulletinboard.system.service.DownloadService;

/**
 * Service Implementation for Downloading Posts
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
@Primary
public class DownloadServiceImpl implements DownloadService {

//	/**
//	 * <h2>${Download Post List as Excel File format}</h2>
//	 * <p>
//	 * Creation Excel Downloading File
//	 * </p>
//	 * 
//	 * @param ${postList}
//	 * @throws ${IOException}
//	 */
//	@SuppressWarnings("resource")
//	@Override
//	public void downloadExcel(List<Post> postList) throws IOException {
//		Workbook workbook = new XSSFWorkbook();
//		CreationHelper creationHelper = workbook.getCreationHelper();
//		Sheet sheet = workbook.createSheet("PostList");
//
//		Font headerFont = workbook.createFont();
//		headerFont.setBold(true);
//		headerFont.setFontHeightInPoints((short) 14);
//		headerFont.setColor(IndexedColors.RED.getIndex());
//		CellStyle headerCellStyle = workbook.createCellStyle();
//		headerCellStyle.setFont(headerFont);
//		Row headerRow = sheet.createRow(0);
//		String[] columnHeaders = { "Title", "Description", "Status", "Created User", "Updated User", "Created At",
//		        "Updated At" };
//		for (int i = 0; i < columnHeaders.length; i++) {
//			Cell cell = headerRow.createCell(i);
//			cell.setCellValue(columnHeaders[i]);
//			cell.setCellStyle(headerCellStyle);
//		}
//
//		CellStyle dateCellStyle = workbook.createCellStyle();
//		dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd-MM-yyyy"));
//
//		int rowNumber = 1;
//
//		for (int j = 0; j < postList.size(); j++) {
//			Row row = sheet.createRow(rowNumber++);
//			row.createCell(0).setCellValue(postList.get(j).getTitle());
//			row.createCell(1).setCellValue(postList.get(j).getDescription());
//			row.createCell(2).setCellValue(postList.get(j).getStatus());
//			row.createCell(3).setCellValue(postList.get(j).getUser().getId());
//			row.createCell(4).setCellValue(postList.get(j).getUser2().getId());
//			row.createCell(5).setCellValue(postList.get(j).getCreatedAt());
//			row.createCell(6).setCellValue(postList.get(j).getUpdatedAt());
//		}
//		for (int k = 0; k < columnHeaders.length; k++) {
//			sheet.autoSizeColumn(k);
//		}
//		FileOutputStream outputStream = new FileOutputStream(
//		        System.getProperty("user.home") + "/Downloads/PostList" + System.currentTimeMillis() + ".xlsx");
//		workbook.write(outputStream);
//		outputStream.close();
//	}

	/**
	 * <h2>${Download Post List as Excel File format}</h2>
	 * <p>
	 * Creation Excel Downloading File
	 * </p>
	 * 
	 * @param ${postList, option, book, sheet}
	 * @return ${byte[]}
	 * @throws IOException 
	 */
	@Override
	public byte[] generateDownload(List<Post> postList) throws IOException {
		SaveOptions option = SaveOptions.getCsvDefault();
		ExcelFile book = new ExcelFile();
		ExcelWorksheet sheet = book.addWorksheet("Sheet1");
		sheet.getCell("A1").setValue("Title");
		sheet.getCell("B1").setValue("Description");
		sheet.getCell("C1").setValue("Status");
		sheet.getCell("D1").setValue("Created User");
		sheet.getCell("E1").setValue("Updated User");
		sheet.getCell("F1").setValue("Created At");
		sheet.getCell("G1").setValue("Updated At");

		for (int i = 1; i <= postList.size(); i++) {
			Post post = postList.get(i - 1);
			sheet.getCell(i, 0).setValue(post.getTitle());
			sheet.getCell(i, 1).setValue(post.getDescription());
			sheet.getCell(i, 2).setValue(post.getStatus());
			sheet.getCell(i, 3).setValue(post.getUser().getId());
			sheet.getCell(i, 4).setValue(post.getUser2().getId());
			sheet.getCell(i, 5).setValue(post.getCreatedAt()+"");
			sheet.getCell(i, 6).setValue(post.getUpdatedAt()+"");
		}
		
		CellStyle style = new CellStyle();
		style.getFont().setWeight(1000);
		style.setHorizontalAlignment(HorizontalAlignmentStyle.CENTER);
		sheet.getRow(0).setStyle(style);
		System.out.println(sheet.getRow(0).getStyle().getFont());
		sheet.getColumn(0).setWidth(2.0, LengthUnit.INCH);
		sheet.getColumn(1).setWidth(2.0, LengthUnit.INCH);
		sheet.getColumn(2).setWidth(0.5, LengthUnit.INCH);
		sheet.getColumn(3).setWidth(0.5, LengthUnit.INCH);
		sheet.getColumn(4).setWidth(0.5, LengthUnit.INCH);
		sheet.getColumn(5).setWidth(2.0, LengthUnit.INCH);
		sheet.getColumn(6).setWidth(2.0, LengthUnit.INCH);
		
		byte[] bytes = this.getBytes(book, option);
		return bytes;
	}
	
	private byte[] getBytes(ExcelFile book, SaveOptions option) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		book.save(baos, option);
		return baos.toByteArray();
	}

}
