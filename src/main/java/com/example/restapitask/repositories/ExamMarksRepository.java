package com.example.restapitask.repositories;

import com.example.restapitask.entities.ExamMark;
import com.example.restapitask.entities.projections.ExamMarkExcerpt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "examMarks", path = "examMarks", excerptProjection = ExamMarkExcerpt.class)
public interface ExamMarksRepository extends JpaRepository<ExamMark, Integer> {

    @RestResource(exported = false)
    List<ExamMark> findAll();

    @RestResource(exported = false)
    Page<ExamMark> findAll(Pageable pageable);

    @RestResource(exported = false)
    List<ExamMark> findAll(Sort sort);

    @RestResource(path = "updateMarks", rel = "updateMarks", description = @Description("Updates all marks by param: \"mark\""))
    @Transactional
    @Modifying
    @Query("update ExamMark e set e.mark = :mark")
    int updateMarks(Integer mark);

    @RestResource(path = "deleteByMark", rel = "deleteByMark", description = @Description("Deletes all marks by param: \"mark\""))
    @Transactional
    @Modifying
    @Query("delete from ExamMark e where e.mark = :mark")
    int deleteByMark(Integer mark);

    @RestResource(path = "find", rel = "find")
    @Query("select e from ExamMark e where e.mark = :mark")
    List<ExamMark> findByMark(Integer mark);
}
