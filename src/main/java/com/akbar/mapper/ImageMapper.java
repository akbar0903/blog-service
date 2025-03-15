package com.akbar.mapper;

import com.akbar.pojo.entity.Image;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImageMapper {
    @Insert(("insert into image(url, object_name,admin_id, upload_time) values(#{url}, #{objectName}, #{adminId}, #{uploadTime})"))
    void addImage(Image image);

    @Delete("delete from image where object_name = #{objectName}")
    void delete(String objectName);

    @Select("select * from image order by upload_time desc")
    List<Image> selectImagePage();
}
