package scm.bulletinboard.system.service;

import java.io.IOException;
import java.util.List;

import scm.bulletinboard.system.model.Post;

/**
 * <h2>Interface for Download Service Implementation</h2>
 * <p>
 * Interface for Download Service Implementation
 * </p>
 */
public interface DownloadService {

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
    public byte[] generateDownload(List<Post> postList) throws IOException;
}
