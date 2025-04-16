package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import service.UploadAvatarService;

import javax.servlet.http.Part;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UploadAvatarServiceImpl implements UploadAvatarService {

    /*--------------------------------------------    私有变量    --------------------------------------------*/
    private UserDao userDao = new UserDaoImpl();


    @Override
   public Boolean uploadAvatar(Part filePart, String savePath, String fileName, Integer userId) throws Exception {

        // 确保目录存在
        java.nio.file.Path path = Paths.get(savePath).getParent();
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        // 获取输入流in
        try (InputStream in = filePart.getInputStream();
             OutputStream out = Files.newOutputStream(Paths.get(savePath))) {

            // 缓冲区
            byte[] buffer = new byte[4096];
            int bytesRead;

            // 当输入流没有结束
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);    //将读取到的数据写入到输出流中
            }
        }
        // 更新数据库
        userDao.updateAvatar(userId, fileName);
        System.out.println("=头像保存成功，数据库更新完成");

        return true;
    }

}
