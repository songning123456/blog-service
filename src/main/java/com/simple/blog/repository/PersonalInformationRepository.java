package com.simple.blog.repository;

import com.simple.blog.entity.PersonalInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by songning on 2019/8/25 2:47 PM
 */
@Repository
public interface PersonalInformationRepository extends JpaRepository<PersonalInformation, String> {

    /**
     * @param
     * @param author
     * @return
     */
    List<PersonalInformation> findByAuthorAndInfoType(String author, String infoType);

    /**
     * @param userId
     * @return
     */
    @Query(value = "select distinct(info_type) from personal_information where user_id = ?1", nativeQuery = true)
    List<String> findInfoTypeByInfoOwnerNative(String userId);

    @Query(value = "select author, mechanism, position, start_time as startTime, end_time  as endTime, introduction from personal_information where user_id = ?1 and info_type=?2", nativeQuery = true)
    List<Map<String, Object>> findByUserIdAndInfoTypeNative(String userId, String infoType);
}
