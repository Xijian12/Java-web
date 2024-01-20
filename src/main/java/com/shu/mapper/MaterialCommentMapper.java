package com.shu.mapper;

import com.shu.entity.vo.request.MaterialComment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    //根据学校、专业、学科三者SMS来筛选评论
    List<MaterialComment> getMaterialCommentBySMS(String school,String major,String subject);

    //根据学校、专业、学科三者SMS来删除评论
    @Delete("delete from commentformaterial where school = #{school} and major = #{major} and subject =#{subject}")
    void deleteMaterialCommentBySMS(String school,String major,String subject);

    //根据学校、专业、学科、类型四者SMST来删除评论
    @Delete("delete from commentformaterial where school = #{school} and major = #{major} and subject =#{subject} and type = #{type}")
    void deleteMaterialCommentBySMST(String school,String major,String subject,Integer type);


}
