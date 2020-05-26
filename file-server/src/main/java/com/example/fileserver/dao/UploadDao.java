package com.example.fileserver.dao;

import com.example.fileserver.pojo.file.GroupFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author ltx
 * @Date 23:31 2020/5/22
 */
@Mapper
public interface UploadDao {

    /**
     * 前端上传记录写库
     */
    @Insert("insert into file (identify_number, filename, bucket, url) " +
            " values (#{identify_number}, #{file.file_name}, #{file.bucket}, #{file.url})")
    Integer witeUploadRecord(@Param("identify_number") int identify_number, @Param("file") GroupFile file);
}
