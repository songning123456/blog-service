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
     * @param infoOwner
     * @return
     */
    List<PersonalInformation> findByInfoOwnerAndInfoType(String infoOwner, String infoType);

    /**
     * @param infoOwner
     * @return
     */
    @Query(value = "select distinct(info_type) from personal_information where info_owner = ?1", nativeQuery = true)
    List<String> findInfoTypeByInfoOwnerNative(String infoOwner);

    @Query(value = "select mechanism, position, start_time as startTime, end_time  as endTime, introduction from personal_information where info_owner = ?1 and info_type=?2", nativeQuery = true)
    List<Map<String, Object>> findByInfoOwnerAndInfoTypeNative(String infoOwner, String infoType);
}
