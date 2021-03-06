package cn.vtyc.ehs.dao.first;

import cn.vtyc.ehs.core.BaseDao;
import cn.vtyc.ehs.entity.Image;
import cn.vtyc.ehs.entity.Image2;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

@Component
public interface Image2Dao extends BaseDao<Image2>, InsertListMapper<Image2> {
    @Delete("delete from image2 where img_source_name = '${imgSourceName}' and uuid = '${uuid}'")
    void deleteFile(@Param("imgSourceName") String imgSourceName, @Param("uuid") String uuid);

    @Select("select img_name from image2 where img_source_name = '${imgSourceName}' and uuid = '${uuid}'")
    String selectImgName(@Param("imgSourceName") String imgSourceName, @Param("uuid") String uuid);

    @Select("select img_name,img_source_name from image2 where uuid = '${uuid}'")
    List<Image2> selectImgSourceName(@Param("uuid") String uuid);
}
