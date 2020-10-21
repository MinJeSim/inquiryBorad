package mileage;

import mileage.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class InquiryBoardViewHandler {


    @Autowired
    private InquiryBoardRepository inquiryBoardRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenInquirySent_then_CREATE_1 (@Payload InquirySent inquirySent) {
        try {
            if (inquirySent.isMe()) {
                // view 객체 생성
                InquiryBoard inquiryBoard = new InquiryBoard();
                // view 객체에 이벤트의 Value 를 set 함
                inquiryBoard.setInquiryId(inquirySent.getInquiryId());
                inquiryBoard.setMemberId(inquirySent.getMemberId());
                inquiryBoard.setInquiryContents(inquirySent.getInquiryContents());
                // view 레파지 토리에 save
                inquiryBoardRepository.save(inquiryBoard);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenReceiptInquiry_then_UPDATE_1(@Payload ReceiptInquiry receiptInquiry) {
        try {
            if (receiptInquiry.isMe()) {
                // view 객체 조회
                List<InquiryBoard> inquiryBoardList = inquiryBoardRepository.findByInquiryId(receiptInquiry.getInquiryId());
                for(InquiryBoard inquiryBoard : inquiryBoardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    inquiryBoard.setInquiryStatus(receiptInquiry.getInquiryStatus());
                    // view 레파지 토리에 save
                    inquiryBoardRepository.save(inquiryBoard);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCancelInquiry_then_UPDATE_2(@Payload CancelInquiry cancelInquiry) {
        try {
            if (cancelInquiry.isMe()) {
                // view 객체 조회
                List<InquiryBoard> inquiryBoardList = inquiryBoardRepository.findByInquiryId(cancelInquiry.getInquiryId());
                for(InquiryBoard inquiryBoard : inquiryBoardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    inquiryBoard.setInquiryContents(cancelInquiry.getInquiryStatus());
                    // view 레파지 토리에 save
                    inquiryBoardRepository.save(inquiryBoard);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenMemberWithdrawn_then_DELETE_1(@Payload MemberWithdrawn memberWithdrawn) {
        try {
            if (memberWithdrawn.isMe()) {
                // view 레파지 토리에 삭제 쿼리
                inquiryBoardRepository.deleteByMemberId(memberWithdrawn.getMemberId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}