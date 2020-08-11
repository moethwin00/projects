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

    /**
     * <h2>Download Post List as Excel File format</h2>
     * <p>
     * Creation Excel Downloading File
     * </p>
     * 
     * @param postList, option, book, sheet
     * @return byte[]
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
            sheet.getCell(i, 5).setValue(post.getCreatedAt() + "");
            sheet.getCell(i, 6).setValue(post.getUpdatedAt() + "");
        }

        CellStyle style = new CellStyle();
        style.getFont().setWeight(1000);
        style.setHorizontalAlignment(HorizontalAlignmentStyle.CENTER);
        sheet.getRow(0).setStyle(style);
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

    /**
     * <h2>Get Byte Array</h2>
     * <p>
     * Get Byte Array To Download Excel File
     * </p>
     * 
     * @param book, option
     * @return byte[]
     */
    private byte[] getBytes(ExcelFile book, SaveOptions option) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        book.save(baos, option);
        return baos.toByteArray();
    }

}