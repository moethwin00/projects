package scm.bulletinboard.system.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import scm.bulletinboard.system.model.Post;

/**
 * <h2>Interface for ${Download Service Implementation}</h2>
 * <p>
 * Interface for ${Download Service Implementation}
 * </p>
 */
public interface DownloadService {
	public void downloadExcel(List<Post> postList) throws IOException;

	public String generateDownload(List<Post> postList, ByteArrayOutputStream baos, String filename,
	        HashMap<String, Object> parameter, String path) throws JRException;
}
