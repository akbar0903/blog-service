package com.akbar.mapper;

import com.akbar.pojo.entity.Image;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImageMapper {
    @Insert(("insert into tb_image(url, object_name,admin_id) values(#{url}, #{objectName}, #{adminId})"))
    int addImage(String url, String objectName, Integer adminId);

    @Delete("delete from tb_image where object_name = #{objectName}")
    int deleteImage(String objectName);

    @Select("select * from tb_image order by created_time desc")
    List<Image> getImageList();
}
