package com.example.mapper;

import com.example.entity.vo.request.Material;
import com.example.entity.vo.request.MaterialComment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MaterialCommentMapper {
    //新增评论
    @Insert("insert into commentForMaterial(school, major, subject, material_comment, material_grade, user_nickname, user_email, type, user_id, create_time, update_time,role) " +
            "values (#{school},#{major},#{subject},#{materialComment},#{materialGrade},#{userNickname},#{userEmail},#{type},#{userId},#{createTime},#{updateTime},#{role})")
    void insertMaterialComment(MaterialComment materialComment);
//
//    //根据学校、专业、学科来添加评论
//    @Insert("insert into commentForMaterial(school, major, subject, material_comment, material_grade, user_nickname, user_email, type, user_id, create_time, update_time) " +
//            "values (#{school},#{major},#{subject},#{materialComment},#{materialGrade},#{userNickname},#{userEmail},#{type},#{userId},#{createTime},#{updateTime})")
//    void insertMaterialCommentBySMS(MaterialComment materialComment);

    //根据评论主键ID删除评论
    @Delete("delete  from commentformaterial where comment_id = #{commentId}")
    void deleteMaterialCommentById(Integer commentId);

    //根据评论主键ID获得评论对象
    @Select("select * from commentformaterial where comment_id = #{commentId}")
    MaterialComment getMaterialCommentById(Integer commentId);

}
