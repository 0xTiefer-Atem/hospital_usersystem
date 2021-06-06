package org.usersystem.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @Entity generator.domain.Caseinfo
*/

@Mapper
public interface CaseInfoMapper {
    void addFeedBackByRegisterId(@Param("registerId") String registerId, @Param("feedBack") String feedBack);
}
