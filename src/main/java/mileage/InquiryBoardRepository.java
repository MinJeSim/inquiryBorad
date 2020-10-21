package mileage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InquiryBoardRepository extends CrudRepository<InquiryBoard, Long> {

    List<InquiryBoard> findByInquiryId(Long inquiryId);

    void deleteByMemberId(Long memberId);
}