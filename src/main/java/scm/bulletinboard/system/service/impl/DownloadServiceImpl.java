package scm.bulletinboard.system.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import scm.bulletinboard.system.model.Post;
import scm.bulletinboard.system.service.DownloadService;

/**
 * Service Implementation for Downloading Posts
 */
@SuppressWarnings("deprecation")
@Service
@Transactional(propagation = Propagation.REQUIRED)
@Primary
public class DownloadServiceImpl implements DownloadService {

	/**
	 * <h2>${Download Post List as Excel File format}</h2>
	 * <p>
	 * Creation Excel Downloading File
	 * </p>
	 * 
	 * @param ${postList}
	 * @throws ${IOException}
	 */
	@SuppressWarnings("resource")
	@Override
	public void downloadExcel(List<Post> postList) throws IOException {
		Workbook workbook = new XSSFWorkbook();
		CreationHelper creationHelper = workbook.getCreationHelper();
		Sheet sheet = workbook.createSheet("PostList");

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		Row headerRow = sheet.createRow(0);
		String[] columnHeaders = { "Title", "Description", "Status", "Created User", "Updated User", "Created At",
		        "Updated At" };
		for (int i = 0; i < columnHeaders.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columnHeaders[i]);
			cell.setCellStyle(headerCellStyle);
		}

		CellStyle dateCellStyle = workbook.createCellStyle();
		dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd-MM-yyyy"));

		int rowNumber = 1;

		for (int j = 0; j < postList.size(); j++) {
			Row row = sheet.createRow(rowNumber++);
			row.createCell(0).setCellValue(postList.get(j).getTitle());
			row.createCell(1).setCellValue(postList.get(j).getDescription());
			row.createCell(2).setCellValue(postList.get(j).getStatus());
			row.createCell(3).setCellValue(postList.get(j).getUser().getId());
			row.createCell(4).setCellValue(postList.get(j).getUser2().getId());
			row.createCell(5).setCellValue(postList.get(j).getCreatedAt());
			row.createCell(6).setCellValue(postList.get(j).getUpdatedAt());
		}
		for (int k = 0; k < columnHeaders.length; k++) {
			sheet.autoSizeColumn(k);
		}
		FileOutputStream outputStream = new FileOutputStream(
		        System.getProperty("user.home") + "/Downloads/PostList" + System.currentTimeMillis() + ".xlsx");
		workbook.write(outputStream);
		outputStream.close();
	}

	/**
	 * <h2>${Download Post List as Excel File format with jasper template}</h2>
	 * <p>
	 * Creation Excel Downloading File
	 * </p>
	 * 
	 * @param ${postList, baos, filename, parameter, path}
	 * @return ${String}
	 * @throws JRException 
	 */
	@Override
	public String generateDownload(List<Post> postList, ByteArrayOutputStream baos, String filename,
	        HashMap<String, Object> parameter, String path) throws JRException {
		String downloadFileName = System.getProperty("user.home") + "/Downloads/PostList" + System.currentTimeMillis()
		        + ".xlsx";
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(postList);
		JasperReport jasperReport = JasperCompileManager.compileReport(path + "postListReport.jrxml");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, dataSource);
		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, downloadFileName);
		exporter.exportReport();
		return downloadFileName;
	}

}
