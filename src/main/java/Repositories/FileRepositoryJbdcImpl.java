package Repositories;

import Models.FileInfo;
import Models.User;
import Models.UserProfile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.SQLException;

public class FileRepositoryJbdcImpl implements FileRepository {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private final static String SQL_INSERT = "insert into file_storage( original_file_name, storage_name, size, file_type) " +
            "values (?, ?, ?, ?)";

    public FileRepositoryJbdcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<FileInfo> fileRowMapper = (row, rowNumber) ->
            FileInfo.builder()
                    .id(row.getLong("id"))
                    .originalFileName(row.getString("original_file_name"))
                    .storageFileName(row.getString("storage_file_name"))
                    .size(row.getLong("size"))
                    .type(row.getString("type"))
                    .build();

    @Override
    public void save(FileInfo fileInfo)  {
        jdbcTemplate.update(SQL_INSERT,  fileInfo.getOriginalFileName(), fileInfo.getStorageFileName(),
                fileInfo.getSize(),
                fileInfo.getType());


    }

    @Override
    public FileInfo findById(Long id) {
        return null;
    }
}
