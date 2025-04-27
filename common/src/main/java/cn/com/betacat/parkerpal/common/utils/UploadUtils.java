package cn.com.betacat.parkerpal.common.utils;


import cn.com.betacat.parkerpal.common.constants.AppConstants;
import com.google.zxing.common.BitMatrix;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.imageio.ImageIO;


public final class UploadUtils {

    //私有不可更改的变量：生成二维码图片的颜色
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    public static BufferedImage matrixToImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }



    public static byte[] imageToBytes(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        return baos.toByteArray();
    }

    /**
     * 上传文件到到第三方服务器
     *
     * @param imageData
     * @param remotePath
     * @param fileName
     * @throws Exception
     */
    public static void uploadToServerB(byte[] imageData, String remotePath, String fileName) throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(
                AppConstants.THIRD_PARTY_USER_NAME,
                AppConstants.THIRD_PARTY_IP,
                AppConstants.THIRD_PARTY_PORT);
        session.setPassword(AppConstants.THIRD_PARTY_PASSWORD);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
        sftpChannel.connect();
        // 上传文件
        sftpChannel.cd(remotePath);
        sftpChannel.put(new ByteArrayInputStream(imageData), fileName);
        sftpChannel.disconnect();
        session.disconnect();
    }

    /**
     * 读取上传到第三方服务器的文件
     *
     * @param remotePath
     * @return
     */
    public static InputStream readPrivateKeyFromServer(String remotePath) {
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(
                    AppConstants.THIRD_PARTY_USER_NAME,
                    AppConstants.THIRD_PARTY_IP,
                    AppConstants.THIRD_PARTY_PORT);
            session.setPassword(AppConstants.THIRD_PARTY_PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
            sftpChannel.connect();

            // 读取文件
            InputStream inputStream = sftpChannel.get(remotePath);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            sftpChannel.disconnect();
            session.disconnect();

            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static List<String> saveFilesToLocal(MultipartFile[] files, String filePaths) throws Exception{
        List<String> fileDTOS = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }

            // 生成UUID
            String uuid = UUID.randomUUID().toString().replace("-", "");

            // 生成文件名
            String fileName = uuid + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(filePaths, fileName);
            // 保存文件
            Files.copy(file.getInputStream(), filePath);
            // 添加到文件列表
            fileDTOS.add(filePath.toString());
        }
        return fileDTOS;
    }

    // 存储到本地
    public static void saveToLocal(String path, String fileName, String formatName, byte[] imageData) throws IOException {
        try (InputStream inputStream = new ByteArrayInputStream(imageData)) {
            BufferedImage image = ImageIO.read(inputStream);
            File outputFile = new File(path, fileName);
            ImageIO.write(image, formatName, outputFile);
        }
    }
}
