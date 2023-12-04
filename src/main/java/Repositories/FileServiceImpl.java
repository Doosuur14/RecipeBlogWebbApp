package Repositories;

import Models.FileInfo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class FileServiceImpl implements FileService {

    private FileRepository fileRepository;

    public FileServiceImpl(FileRepository filesRepository) {
        this.fileRepository = filesRepository;
    }


    @Override
    public String saveFileToStorage(InputStream file, String originalFileName, String contentType, Long size) {
        FileInfo fileInfo = FileInfo.builder()
                .originalFileName(originalFileName)
                .storageFileName(UUID.randomUUID().toString())
                .size(size)
                .type(contentType)
                .build();
        try {
            Files.copy(file, Paths.get("/Users/doosuur/IdeaProjects/ProjectWork/src/main/webapp/files/" + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]));
            fileRepository.save(fileInfo);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return originalFileName;
    }

    @Override
    public void writeFileFromStorage(Long fileId, OutputStream outputStream) {
        FileInfo fileInfo = fileRepository.findById(fileId);
        if (fileInfo != null) {
            File file = new File("/Users/doosuur/IdeaProjects/ProjectWork/src/main/webapp/files/" + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]);

                try {
                    Files.copy(file.toPath(), outputStream);
                } catch (IOException e) {
                    throw new IllegalArgumentException();
                }
            }
    }

    @Override
    public FileInfo getFileInfo(Long fileId) {
        return fileRepository.findById(fileId);
    }
}
