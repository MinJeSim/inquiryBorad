package mileage;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="InquiryBoard_table")
public class InquiryBoard {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long inquiryId;
        private Long memberId;
        private String inquiryStatus;
        private String inquiryContents;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Long getInquiryId() {
            return inquiryId;
        }

        public void setInquiryId(Long inquiryId) {
            this.inquiryId = inquiryId;
        }
        public Long getMemberId() {
            return memberId;
        }

        public void setMemberId(Long memberId) {
            this.memberId = memberId;
        }
        public String getInquiryStatus() {
            return inquiryStatus;
        }

        public void setInquiryStatus(String inquiryStatus) {
            this.inquiryStatus = inquiryStatus;
        }
        public String getInquiryContents() {
            return inquiryContents;
        }

        public void setInquiryContents(String inquiryContents) {
            this.inquiryContents = inquiryContents;
        }

}
