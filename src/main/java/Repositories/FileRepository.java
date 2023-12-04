package Repositories;

import Models.FileInfo;
import Models.User;

import java.sql.SQLException;

public interface FileRepository {
    void save(FileInfo fileInfo);
    FileInfo findById(Long id);
}
