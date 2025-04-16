package service;

import javax.servlet.http.Part;

public interface UploadAvatarService {

    Boolean uploadAvatar(Part filePart, String savePath, String fileName, Integer userId) throws Exception;
}
