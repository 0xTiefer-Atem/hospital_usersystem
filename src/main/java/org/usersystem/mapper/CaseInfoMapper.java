package org.usersystem.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @Entity generator.domain.Caseinfo
*/

@Mapper
public interface CaseInfoMapper {
    void addFeedBackByRegisterId(@Param("caseId") String caseId, @Param("feedBack") String feedBack);
}
